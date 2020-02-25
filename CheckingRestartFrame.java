/**
 * @author Isaac Angle and Shane Snediker
 * This class determines the characteristics of the JFrame
 * that pops up when after a customer makes a transaction
 */

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

public class CheckingRestartFrame extends JFrame {
	
    // Private member attributes
	
	private int pin;
    private CSVFileReader file;
    private String date;
    private int index;
    private JLabel messageLabel = new JLabel("Would you like another transaction? ");
    private JButton yesBtn = new JButton("Yes"),noBtn = new JButton("No");
    private JPanel messagePanel = new JPanel(), buttonPanel = new JPanel();
    
	/**
	 * This JFrame's constructor
	 * @param pin allows the program to track this customer's pin number
	 * @param file allows the program to access the bank's customers and their transaction histories
	 * @param date allows the program to save the date of a transaction to the customer's records
	 */
    CheckingRestartFrame(int pin, CSVFileReader file, String date){
		
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
	    
	    yesBtn.setPreferredSize(new Dimension(100,50));
	    noBtn.setPreferredSize(new Dimension(100,50));
	    
	    yesBtn.setBackground(Color.GRAY);
	    noBtn.setBackground(Color.GRAY);
	    
	    yesBtn.setForeground(Color.BLACK);
	    noBtn.setForeground(Color.BLACK);
	    
	    
	    messageLabel.setFont(new Font("Calibri",Font.PLAIN,20));
	    messageLabel.setForeground(Color.RED);
	    messageLabel.setBackground(Color.BLACK);
	    
	    buttonPanel.setPreferredSize(new Dimension(500,200));
	    
	    FlowLayout vSpacing = new FlowLayout();
	    vSpacing.setVgap(180);
	    messagePanel.setLayout(vSpacing);
	    
	    FlowLayout vSpacing2 = new FlowLayout(FlowLayout.CENTER,50,0);
	    vSpacing2.setVgap(20);
	    buttonPanel.setLayout(vSpacing2);
	    
	    messagePanel.add(messageLabel);
	    buttonPanel.add(yesBtn);
	    buttonPanel.add(noBtn);
	    messagePanel.setBackground(Color.BLACK);
	    buttonPanel.setBackground(Color.BLACK);
	    addButtonActions();
	}
	
    /**
	 * A method that sets up this JFrame's JButtons with actionListeners
	 * and determines what happens after each button is clicked
	 */
    public void addButtonActions() {
		noBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				EndCheckingFrame frame = new EndCheckingFrame(pin,file,date);
				
			}});
		
		yesBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				PopUp1 frame = new PopUp1(pin,file,date);
				
			}});
	}

}
