/**@author Isaac Angle and Shane Snediker
 * this class creates a frame that allows the user to 
 * choose if they would like to create another transaction or
 * to end the program
 * */
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

public class SavingsRestartFrame extends JFrame {
	//private member variables
    private int pin;
    private CSVFileReader file;
    private String date;
    private int index;
    private JLabel messageLabel = new JLabel("Would you like another transaction? ");
    private JButton yesBtn = new JButton("Yes"),noBtn = new JButton("No");
    private JPanel messagePanel = new JPanel(), buttonPanel = new JPanel();
    
	
/**
  * savingsHistoryFrame constructor sets up the characteristics of the Savings account options for this customer
  * @param pin allows the program to use the pin number to follow the customer that is currently using the ATM
  * @param file allows the program to have access to the full database of ATM customers for this bank
  * @param date allows the program to track the current date of this transaction for record keeping purposes
  */
	SavingsRestartFrame(int pin, CSVFileReader file, String date){
		//adding the intial parameters that the frames share
	    this.pin = pin;
	    this.file = file;
	    this.date = date;
	    this.setLayout(new BorderLayout());
	    this.setBounds(200, 200, 500, 500);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setTitle("ATM machine");
	    this.add(buttonPanel, BorderLayout.SOUTH);
	    this.add(messagePanel,BorderLayout.CENTER);
	    this.setVisible(true);
	    
	    //sets the size of the yes and no buttons
	    yesBtn.setPreferredSize(new Dimension(100,50));
	    noBtn.setPreferredSize(new Dimension(100,50));
	    
	    //sets the color of the yes and no buttons
	    yesBtn.setBackground(Color.GRAY);
	    noBtn.setBackground(Color.GRAY);
	    
	    //sets the text color of the yes and no buttons
	    yesBtn.setForeground(Color.BLACK);
	    noBtn.setForeground(Color.BLACK);
	    
	    //sets the font and color and text color of the label
	    messageLabel.setFont(new Font("Calibri",Font.PLAIN,20));
	    messageLabel.setForeground(Color.RED);
	    messageLabel.setBackground(Color.BLACK);
	    
	    //sets the size of the panel
	    buttonPanel.setPreferredSize(new Dimension(500,200));
	    
	    //allows for space between the two panels
	    FlowLayout vSpacing = new FlowLayout();
	    vSpacing.setVgap(180);
	    messagePanel.setLayout(vSpacing);
	    
	    FlowLayout vSpacing2 = new FlowLayout(FlowLayout.CENTER,50,0);
	    vSpacing2.setVgap(20);
	    buttonPanel.setLayout(vSpacing2);
	    
	    //adds the label and buttons to the screen
	    messagePanel.add(messageLabel);
	    buttonPanel.add(yesBtn);
	    buttonPanel.add(noBtn);
	    messagePanel.setBackground(Color.BLACK);
	    buttonPanel.setBackground(Color.BLACK);
	    //allows the buttons to react to being clicked
	    addButtonActions();
	}
	
	/**
	 * allows the button to create a new frame when clicked
	 * if yes then the second frame appears and if no then the
	 * end frame appears
	 */
	public void addButtonActions() {
		//allows the no button to react to a click
		noBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				EndCheckingFrame frame = new EndCheckingFrame(pin,file,date);
				
			}});
		
		//allows the yes button to react to a click
		yesBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				PopUp1 frame = new PopUp1(pin,file,date);
				
			}});
	}
}
