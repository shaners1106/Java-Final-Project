/**@author Isaac Angle and shane Snediker
 * this class creates a frame that allows the user to look at previous
 * transactions. This is done with a JScrollbar and a text area*/
package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class SavingsHistoryFrame extends JFrame{
	//private member variables
	private String date;
	private int pin;
	private CSVFileReader file;
	private JPanel scrollPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JScrollPane scrollPane = new JScrollPane(scrollPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private int index;
	private JTextArea informationArea = new JTextArea();
	private JButton exitBtn = new JButton("Done");
	private JPanel buttonPanel = new JPanel();
	private JScrollBar bar;
	
	   /**
     * savingsHistoryFrame constructor sets up the characteristics of the Savings account options for this customer
     * @param pin allows the program to use the pin number to follow the customer that is currently using the ATM
     * @param file allows the program to have access to the full database of ATM customers for this bank
     * @param date allows the program to track the current date of this transaction for record keeping purposes
     */
	SavingsHistoryFrame(int pin, CSVFileReader file, String date){
		//adding the intial parameters that all the frames share
		this.date = date;
		this.pin = pin;
		this.file = file;
		this.setVisible(true);
		this.setBounds(200, 200, 500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ATM machine");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
		CreateUIComponents();
		addButtonActions();
	}
	
	/**
	 * creates the components that are on the frame
	 */
	public void CreateUIComponents() {
		scrollPanel.add(Box.createVerticalStrut(20));
		scrollPanel.setPreferredSize(new Dimension(500,4000));
		scrollPanel.setBackground(Color.BLACK);
		
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		//gets the index at which the customer is located at
		for(int i = 0; i < file.getCustomers().size(); i++) {
			if(pin == file.getCustomers().get(i).getIDNum()) {
				index = i;
			}
		}
		
		//creates the scrollbar and the panel inside
		scrollPanel.add(informationArea);
		bar = scrollPane.getVerticalScrollBar();
		bar.setBackground(Color.BLACK);
		informationArea.setBackground(Color.BLACK);
		informationArea.setForeground(Color.RED);
		informationArea.setEditable(false);
		informationArea.setFont(new Font("Calibri",Font.PLAIN,20));
		
		//gets the text that goes into the main area
		for(int i = 0; i < file.getCustomers().get(index).getTransactions().size();i++) {
			
			informationArea.append(file.getCustomers().get(index).getTransactions().get(i).toString());
			informationArea.setCaretPosition(0);
		}
		//adds the button at the bottom of the screen
		buttonPanel.add(exitBtn);
		buttonPanel.setBackground(Color.BLACK);
		exitBtn.setPreferredSize(new Dimension(100,25));
		exitBtn.setBackground(Color.GRAY);
		exitBtn.setForeground(Color.BLACK);
		
		
	}
	/**
	 * allows the button to create a new frame when clicked
	 */
	public void addButtonActions() {
		//allows the exitbutton to create a new frame
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				SavingsRestartFrame frame = new SavingsRestartFrame(pin,file,date);
				
			}});
	}
}
