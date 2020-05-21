/** @author Isaac Angle and Shane Snediker 
 * This class defines the JFrame that customers are 
 * routed to when they decide to withdraw cash from
 * their checking account
 * */

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

public class CheckGetCashFrame extends JFrame {
	
	// Private member attributes
	
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
	 * The checking withdraw JFrame constructor
	 * @param pin allows the program to track this customer's pin number
	 * @param file allows the program access to the bank's customer's and their transaction histories
	 * @param date allows the program to save the date of a transaction to the customer's records
	 */
	CheckGetCashFrame(int pin,CSVFileReader file, String date){
		
		// Initial characteristics of this JFrame
		this.date = date;
		this.pin = pin;
		this.file = file;
		this.setBounds(200, 200, 500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.BLACK);
		this.setVisible(true);
		this.setTitle("ATM machine");
		
		// Format the panel that will hold the JLabel that will appear
		// if a customer has an erroneous entry
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		FlowLayout errorSpacing = new FlowLayout(FlowLayout.CENTER);
		errorSpacing.setVgap(100);
		errorPanel.setLayout(errorSpacing);
		errorPanel.setBackground(Color.BLACK);
		errorLabel.setForeground(Color.RED);
		
		// Format the enter JButton
		enterBtn.setBackground(Color.GRAY);
		enterBtn.setFont(new Font("Calisto MT",Font.PLAIN,10));
		enterBtn.setForeground(Color.BLACK);
	
		// Format the JPanel that holds the buttons of this JFrame
		holder.add(enterAmount);
		holder.add(moneyField);
		holder.add(enterBtn);
		holder.setBackground(Color.BLACK);
		enterAmount.setForeground(Color.RED);
		enterAmount.setFont(new Font("Calibri",Font.PLAIN,20));
		moneyField.setColumns(10);
		moneyField.setBackground(Color.LIGHT_GRAY);
		FlowLayout vSpacing = new FlowLayout();
		vSpacing.setVgap(200);
		holder.setLayout(vSpacing);
		this.setLayout(new BorderLayout());
		this.add(holder,BorderLayout.CENTER);
		this.add(errorPanel, BorderLayout.SOUTH);
		addButtonActions();
		revalidate();
		repaint();
	}
	
	/**
	 * A function that sets up the enter JButton with an
	 * actionListener and determines what happens when 
	 * the enter button is clicked
	 */
	public void addButtonActions() {
		enterBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < file.getCustomers().size(); i++) {
					if(pin == file.getCustomers().get(i).getIDNum()) {
						index = i;
					}
				}
						try {
							stringAmount = moneyField.getText();
							stringAmount = stringAmount.trim();
							amount = Double.parseDouble(stringAmount);
							
							if(amount <= file.getCustomers().get(index).getCheckacc().getBalance()) {
								if(amount > 0) {
									file.getCustomers().get(index).getCheckacc().withdraw(amount,date);
									setVisible(false);
									CheckingRestartFrame frame = new CheckingRestartFrame(pin,file,date);
								}else {
									errorPanel.add(errorLabel);
									errorLabel.setForeground(Color.RED);
									errorLabel.setText("Sorry you cannot withdraw zero dollars");
								}
							}else {
								errorPanel.add(errorLabel);
								errorLabel.setVerticalAlignment(JLabel.NORTH);
								errorLabel.setText("Sorry you do not have that much in your account, your balance is: " + file.getCustomers().get(index).getCheckacc().getBalance());
								errorPanel.setForeground(Color.RED);
								errorLabel.setForeground(Color.RED);
								revalidate();
								repaint();
							}
							
						}catch(Exception ex) {
							errorPanel.add(errorLabel);
							errorLabel.setVerticalAlignment(JLabel.NORTH);
							errorPanel.setForeground(Color.RED);
							errorLabel.setForeground(Color.RED);
							revalidate();
							repaint();
						}
					
			}
			
			
		});
	}
}
