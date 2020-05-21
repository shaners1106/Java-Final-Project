/**@author Isaac Angle and Shane Snediker
 * this class creates a frame that allows the user to 
 * deposit money into their savings account leads to the frame
 * that asks the user if they would like to do another transaction*/
package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SavingsDepositFrame extends JFrame {
	
	//private member variables
	private int pin, index;
	private String date;
	private CSVFileReader file;
	private JLabel enterLabel = new JLabel("Enter amount: ");
	private JPanel enterPanel = new JPanel(),
			buttonPanel = new JPanel();
	private JTextField getAmount = new JTextField();
	private JButton enterBtn = new JButton("Enter");
	private JLabel errorLabel = new JLabel("Sorry invalid entry");
	private JPanel errorPanel = new JPanel();
	private String stringAmount;
	private double amount;
	
	/**
	 * default constructor that creates the frame
	 * @param pin the pin number of the user
	 * @param file an object of a class that allows for the access of customer information
	 * @param date a string that contains the current date
	 */
	SavingsDepositFrame(int pin, CSVFileReader file, String date){
	//settting up the initial frame, black and size 500,500
	this.date = date;
	this.pin = pin;
	this.file = file;
	this.setVisible(true);
	this.setBounds(200, 200, 500, 500);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setTitle("ATM machine");
	this.setLayout(new BorderLayout());
	this.setResizable(false);
	//adds all the panels the the frame
	this.add(enterPanel,BorderLayout.NORTH);
	this.add(buttonPanel, BorderLayout.CENTER);
	this.add(errorPanel,BorderLayout.SOUTH);
	errorPanel.setBackground(Color.BLACK);
	
	//gets the index at which the customer is located
	for(int i = 0; i < file.getCustomers().size(); i++) {
		if(pin == file.getCustomers().get(i).getIDNum()) {
			index = i;
		}
	}
	//sets the size, color, position, and font of the enterpanel
	enterPanel.setPreferredSize(new Dimension(500,250));
	buttonPanel.setPreferredSize(new Dimension(500,250));
	FlowLayout vSpacing = new FlowLayout();
	vSpacing.setVgap(200);
	//adds the panel and sets its spacing and size and color
	enterPanel.setLayout(vSpacing);
	enterPanel.add(enterLabel);
	enterPanel.add(getAmount);
	enterPanel.setBackground(Color.BLACK);
	enterLabel.setFont(new Font("Calibri",Font.PLAIN,20));
	enterLabel.setForeground(Color.RED);
	
	//changes the text fields size and color
	getAmount.setColumns(10);
	getAmount.setBackground(Color.LIGHT_GRAY);
	
	//adds the button to the panel
	buttonPanel.add(enterBtn);
	
	FlowLayout vSpacing2 = new FlowLayout();
	vSpacing2.setVgap(0);
	//changes the color and size of the button panel
	buttonPanel.setLayout(vSpacing2);
	buttonPanel.setBackground(Color.BLACK);
	enterBtn.setPreferredSize(new Dimension(100,50));
	enterBtn.setBackground(Color.GRAY);
	enterBtn.setForeground(Color.BLACK);
	//adds action listeners to the button
	addButtonActions();
}

	public void addButtonActions() {
		enterBtn.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//changes the string amount into a double so it can be used
					stringAmount = getAmount.getText();
					stringAmount = stringAmount.trim();
					amount = Double.parseDouble(stringAmount);
					if(amount > 0) {
					file.getCustomers().get(index).getSavAcc().deposit(amount,date,0);
					setVisible(false);
					new SavingsRestartFrame(pin,file,date);
					}else {
						//creates the error message stating the entry was invalid
						errorPanel.setBackground(Color.BLACK);
						errorPanel.add(errorLabel);
						errorLabel.setHorizontalAlignment(JLabel.CENTER);
						errorLabel.setVerticalAlignment(JLabel.NORTH);
						errorLabel.setText("Sorry you cannot deposit zero dollars");
						errorLabel.setForeground(Color.RED);
						revalidate();
						repaint();
					}
				}catch(Exception ex) {
					//creates the error message stating the entry was invalid
					errorPanel.setBackground(Color.BLACK);
					errorPanel.add(errorLabel);
					errorLabel.setHorizontalAlignment(JLabel.CENTER);
					errorLabel.setVerticalAlignment(JLabel.NORTH);
					errorLabel.setText("Sorry invalid entry");
					errorLabel.setForeground(Color.RED);
					revalidate();
					repaint();
				}
				
			}});
	}

}
