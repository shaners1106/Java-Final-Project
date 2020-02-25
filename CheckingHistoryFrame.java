/**
 * @author Isaac Angle and Shane Snediker
 * This class determines the characteristics of the JFrame
 * that pops up when a customer chooses to access their account history
 */

package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class CheckingHistoryFrame extends JFrame {
	
	// Private member attributes
	
	private String date;
	private int pin;
	private CSVFileReader file;
	private JPanel scrollPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JScrollPane scrollPane = new JScrollPane(scrollPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private int index;
	private JTextArea informationArea = new JTextArea();
	private JPanel buttonPanel = new JPanel();
	private JButton exitBtn = new JButton("Done");
	private JScrollBar bar;
	
	/**
	 * This JFrame's constructor
	 * @param pin allows the program to track this customer's pin number
	 * @param file allows the program to access the bank's customers and their transaction histories
	 * @param date allows the program to save the date of a transaction to the customer's records
	 */
	CheckingHistoryFrame(int pin, CSVFileReader file, String date){
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
		this.add(buttonPanel, BorderLayout.SOUTH);
		CreateUIComponents();
		addButtonActions();
	}
	
	/**
	 *  A method that sets up and defines this JFrame's JComponents	
	 */
	public void CreateUIComponents() {
		scrollPanel.add(Box.createVerticalStrut(20));
		scrollPanel.setPreferredSize(new Dimension(500,4000));
		scrollPanel.setBackground(Color.BLACK);
		
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		bar = scrollPane.getVerticalScrollBar();
		bar.setBackground(Color.BLACK);
		bar.setForeground(Color.GRAY);
		
		for(int i = 0; i < file.getCustomers().size(); i++) {
			if(pin == file.getCustomers().get(i).getIDNum()) {
				index = i;
			}
		}
		scrollPanel.add(informationArea);
		informationArea.setBackground(Color.BLACK);
		informationArea.setForeground(Color.RED);
		informationArea.setEditable(false);
		
		informationArea.setFont(new Font("Calibri",Font.PLAIN,20));
		for(int i = 0; i < file.getCustomers().get(index).getTransactions().size();i++) {
			
			informationArea.append(file.getCustomers().get(index).getTransactions().get(i).toString());
			informationArea.setCaretPosition(0);
		}
		
		buttonPanel.add(exitBtn);
		buttonPanel.setBackground(Color.BLACK);
		exitBtn.setPreferredSize(new Dimension(100,25));
		exitBtn.setBackground(Color.GRAY);
		exitBtn.setForeground(Color.BLACK);
		
		
	}
	
	/**
	 * A method that sets up this JFrame's JButton with an
	 * actionListener and determines what happens after the button is clicked
	 */
	public void addButtonActions() {
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				CheckingRestartFrame frame = new CheckingRestartFrame(pin,file,date);
				
			}});
	}
	
}
