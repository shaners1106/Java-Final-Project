/**@author Isaac Angle and Shane Snediker
 * this class creates a frame that allows the user to 
 * withdraw money from their savings account*/
package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SavingsWithdrawFrame extends JFrame {
	//private member variables
	private String date;
	private int pin;
	private CSVFileReader file;
	private JTextField moneyField = new JTextField();
	private JLabel enterAmount = new JLabel("Enter Amount: ");
	private JPanel holder = new JPanel();
	private JButton enterBtn = new JButton("Enter");
	private String stringAmount;
	private double amount;
	private int index;
	private JLabel errorLabel = new JLabel("Sorry invalid entry");
	private JPanel errorPanel = new JPanel();
	
	/**
	  * savingsHistoryFrame constructor sets up the characteristics of the Savings account options for this customer
	  * @param pin allows the program to use the pin number to follow the customer that is currently using the ATM
	  * @param file allows the program to have access to the full database of ATM customers for this bank
	  * @param date allows the program to track the current date of this transaction for record keeping purposes
	  */
	SavingsWithdrawFrame(int pin,CSVFileReader file,String date){
		//initial parameters that all the frames have
		this.date = date;
		this.pin = pin;
		this.file = file;
		this.setBounds(200, 200, 500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.BLACK);
		this.setVisible(true);
		this.setTitle("ATM machine");
		
		//sets the possiton and colr of the errorlabel and panel
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		FlowLayout errorSpacing = new FlowLayout(FlowLayout.CENTER);
		errorSpacing.setVgap(100);
		errorPanel.setLayout(errorSpacing);
		errorPanel.setBackground(Color.BLACK);
		errorLabel.setForeground(Color.RED);
		
		//sets the font,color and text color of the button
		enterBtn.setBackground(Color.GRAY);
		enterBtn.setFont(new Font("Calisto MT",Font.PLAIN,10));
		enterBtn.setForeground(Color.BLACK);
		
		//adds all the fields that allows the user to enter money and also
		//the button and label associated with it
		holder.add(enterAmount);
		holder.add(moneyField);
		holder.add(enterBtn);
		holder.setBackground(Color.BLACK);
		enterAmount.setForeground(Color.RED);
		enterAmount.setFont(new Font("Calibri",Font.PLAIN,20));
		moneyField.setColumns(10);
		moneyField.setBackground(Color.LIGHT_GRAY);
		//creates space
		FlowLayout vSpacing = new FlowLayout();
		vSpacing.setVgap(200);
		holder.setLayout(vSpacing);
		//sets the layout
		this.setLayout(new BorderLayout());
		this.add(holder,BorderLayout.CENTER);
		this.add(errorPanel, BorderLayout.SOUTH);
		//allows the buttons to be clicked
		addButtonActions();
		revalidate();
		repaint();
	}

	/**
	 * this method allows for the button to be clicked
	 */
	public void addButtonActions() {
		//allows the button to submit the amount
		enterBtn.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				//getting the customers index
				for(int i = 0; i < file.getCustomers().size(); i++) {
					if(pin == file.getCustomers().get(i).getIDNum()) {
						index = i;
					}
				}
				/*gets the number from the user, turns it into a double
				 * and then makes sure that it is greater than zero, a double and
				 * that something was entered into the field*/
					try {
						stringAmount = moneyField.getText();
						stringAmount = stringAmount.trim();
						amount = Double.parseDouble(stringAmount);
						//making sure that amount fits description
						if(amount <= file.getCustomers().get(index).getSavAcc().getBalance()) {
							//making sure amount is greater than zero
							if(amount > 0) {
							file.getCustomers().get(index).getSavAcc().withdraw(amount,date);
							setVisible(false);
							SavingsRestartFrame frame = new SavingsRestartFrame(pin,file,date);
							//if less than zero
							}else {
								errorPanel.add(errorLabel);
								errorLabel.setVerticalAlignment(JLabel.NORTH);
								errorLabel.setText("Sorry you cannot withdraw zero dollars");
								errorPanel.setForeground(Color.RED);
								errorLabel.setForeground(Color.RED);
								revalidate();
								repaint();
							}
							//if greater than max amount
						}else {
							errorPanel.add(errorLabel);
							errorLabel.setVerticalAlignment(JLabel.NORTH);
							errorLabel.setText("Sorry you do not have that much in your account, your balance is: " + file.getCustomers().get(index).getSavAcc().getBalance());
							errorPanel.setForeground(Color.RED);
							errorLabel.setForeground(Color.RED);
							revalidate();
							repaint();
						}
						
						//if not a valid character
					}catch(Exception ex) {
						errorPanel.add(errorLabel);
						errorLabel.setVerticalAlignment(JLabel.NORTH);
						errorPanel.setForeground(Color.RED);
						errorLabel.setText("invalid entry");
						errorLabel.setForeground(Color.RED);
						revalidate();
						repaint();
					}
				
			}
		
		
		});
	}

}
