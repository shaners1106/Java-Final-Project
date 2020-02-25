/**@author Isaac Angle
 * this class makes the second panel that
 * the user sees which asks them if they
 * would like to make a transaction with their 
 * checking or savings account*/
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

public class PopUp1 extends JFrame {
	
	private JLabel message = new JLabel("Which account would you like to access?");
	private JButton savings = new JButton("Savings Account");
	private JButton checking = new JButton("Checking Account");
	private JPanel messagePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private int pin;
	private CSVFileReader file;
	private String date;
	/**
	 * default constructor that runs the entire frame
	 * @param pin the pin number of the current user
	 * @param file an instance of the file reader used to get information about the customer
	 * @param date a string that holds the current date
	 */
	PopUp1(int pin, CSVFileReader file, String date){
		this.date = date;
		this.file = file;
		this.pin = pin;
		this.setBounds(200, 200, 500, 500);
		this.setTitle("ATM machine");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.add(messagePanel,BorderLayout.NORTH);
		this.add(buttonPanel,BorderLayout.CENTER);
		this.setResizable(false);
		//creating the panel that hold the message and the button
		messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		messagePanel.setBackground(Color.BLACK);
		buttonPanel.setBackground(Color.BLACK);
		// creates a layout for spacing
		FlowLayout mSpacing = new FlowLayout();
		mSpacing.setVgap(100);
		FlowLayout bSpacing = new FlowLayout();
		bSpacing.setVgap(100);
		//adds the spacing
		messagePanel.setLayout(mSpacing);
		buttonPanel.setLayout(bSpacing);
		//sets the color of the message and the buttons
		checking.setBackground(Color.GRAY);
		savings.setBackground(Color.GRAY);
		message.setForeground(Color.RED);
		checking.setForeground(Color.BLACK);
		savings.setForeground(Color.black);
		message.setForeground(Color.RED);
		//sets the font and size of the text
		message.setFont(new Font("Calibri",Font.PLAIN,20));
		savings.setFont(new Font("Calisto MT",Font.PLAIN,20));
		checking.setFont(new Font("Calisto MT",Font.PLAIN,20));
		savings.setPreferredSize(new Dimension(200,75));
		checking.setPreferredSize(new Dimension(200,75));
		//adds the message and the buttons to the frame 
		messagePanel.add(message);
		buttonPanel.add(checking);
		buttonPanel.add(savings);
		clickCheckingAccount();
		clickSavingsAccount();
		this.setVisible(true);
		repaint();
		revalidate();
	}
	
	/**
	 * this method allows the checking account button to be clicked
	 * and takes the user to a new frame when it is clicked
	 */
	public void clickCheckingAccount() {
		checking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CheckingFrame checkFrame = new CheckingFrame(pin,file,date);
				
			}});
	}
	
	/**
	 * this method allows the savings account button to be clicked
	 * and takes the user to a new frame when it is clicked
	 */
	public void clickSavingsAccount() {
		savings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SavingsFrame savingsFrame = new SavingsFrame(pin,file,date);
				
			}});
	}

}
