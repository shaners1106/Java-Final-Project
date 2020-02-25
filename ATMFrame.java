/**@author Isaac Angle and Shane Snediker
 * this class creates the first frame used in
 * the ATM and also sets up all the other frames*/
package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class ATMFrame extends JFrame{
	
	//initializing the components needed for the frame
	private JLabel welcomeLabel = new JLabel("Welcome to CS 372 ATM!");
	private JPanel welcomePanel  = new JPanel();
	private JPasswordField pinEnter = new JPasswordField();
	private JPanel pinPanel = new JPanel();
	private JLabel enterLabel = new JLabel("Please enter your pin: ");
	private JButton enterBtn = new JButton("Enter");
	private CSVFileReader files;
	private JLabel errorLabel = new JLabel("Sorry invalid entry");
	private boolean correctPin = false;
	private JPanel errorPanel = new JPanel();
	private String stringPin;
	private int pin;
	private JLabel backgroundLabel = new JLabel();
	private DateFormat df = new SimpleDateFormat("MM/dd/yy");
	private Date myDate = new Date();
	private String date  = df.format(myDate);
	
	/**
	 * our main constructor for the main frame
	 * */
	ATMFrame(){
		//adding the names of the customers to the class
		files = new CSVFileReader();
		files.readNames();
		files.addCustomers();
		files.readTrans();
		files.addTrans();
		//creating the frame 
		this.setBounds(200, 200, 500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.black);
		this.setTitle("ATM machine");
		this.add(backgroundLabel);
		this.add(welcomePanel,BorderLayout.NORTH);
		this.add(pinPanel,BorderLayout.CENTER);
		this.add(errorPanel,BorderLayout.SOUTH);
		//adding the UI components and making the button work
		createUIComponents();
		addEnterButtonAction();
		this.setVisible(true);
	}
	
	/**
	 * a method defining the j components of this frame
	 * */
	public void createUIComponents() {
		
		//creating a welcome panel that holds a welcome message
		welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		welcomePanel.setPreferredSize(new Dimension(100,100));
		FlowLayout spacing = new FlowLayout(FlowLayout.CENTER);
		spacing.setVgap(50);
		FlowLayout pinSpacing = new FlowLayout(FlowLayout.CENTER);
		pinSpacing.setVgap(100);
		enterLabel.setForeground(Color.RED);
		
		//creating a pin panel that hold the pin number of the client
		pinPanel.add(enterLabel);
		pinPanel.add(pinEnter);
		pinPanel.setPreferredSize(new Dimension(100,100));
		pinPanel.setLayout(pinSpacing);
		
		// creates a panel that holds a message that runs if the user puts
		//in incorrect information
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		FlowLayout errorSpacing = new FlowLayout(FlowLayout.CENTER);
		errorSpacing.setVgap(100);
		errorPanel.setLayout(errorSpacing);
		errorPanel.setBackground(Color.BLACK);
		errorLabel.setForeground(Color.RED);
		
		//sets the location and color of the welcome message
		welcomePanel.setLayout(spacing);
		welcomePanel.add(welcomeLabel);
		welcomePanel.setBackground(Color.BLACK);
		welcomeLabel.setForeground(Color.red);
		welcomeLabel.setAlignmentY(JLabel.SOUTH);
		welcomeLabel.setFont(new Font("Calibri",Font.PLAIN,20));
		
		//sets the color of the pin message
		pinPanel.setBackground(Color.BLACK);
		pinPanel.add(enterBtn);
		pinEnter.setBackground(Color.LIGHT_GRAY);
		
		//sets the color of the button
		enterBtn.setBackground(Color.GRAY);
		enterBtn.setForeground(Color.BLACK);
		
		//sets the size of the pin pad
		pinEnter.setColumns(4);
	}
	/**this method allows the button to react to a click
	when the button is clicked it checks to see if the pin is correct*/
	public void addEnterButtonAction() {
		enterBtn.addActionListener(new ActionListener() {
			//setting what actions to take when the button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				//turning the string into an int
				try {
						stringPin = new String(pinEnter.getPassword());
						stringPin = stringPin.trim();
						pin = Integer.parseInt((stringPin));
				}catch(Exception ex) {
					errorPanel.add(errorLabel);
					errorLabel.setVerticalAlignment(JLabel.NORTH);
					errorPanel.setForeground(Color.RED);
					revalidate();
					repaint();
				}
				//checking to see if the pin is correct
					for(int i = 0; i < files.getCustomers().size(); i++) {
						if(pin == files.getCustomers().get(i).getIDNum()) {
							correctPin = true;
						}
					}
				//if the pin is correct make another frame
				if(correctPin) {
					setVisible(false);
					new PopUp1(pin,files,date);
				// if the pin is not correct give an error message
				}else {
					errorPanel.add(errorLabel);
					errorLabel.setVerticalAlignment(JLabel.NORTH);
					errorPanel.setForeground(Color.RED);
					revalidate();
					repaint();
				}
				
			}
			
		});
	}

}
