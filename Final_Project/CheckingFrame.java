/** @author Isaac Angle and Shane Snediker
 * This class determines the characteristics of the JFrame
 * that pops up when a customer chooses to make a checking
 * transaction
 * */

package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CheckingFrame extends JFrame {
	
	// Private member attributes
	
	private JButton getCash = new JButton("Get Cash"),depositCash  = new JButton("Deposit Cash"),
			depositCheck = new JButton("Deposit Check"),accountHistory = new JButton("History");
	private String date;
	private JTextArea information = new JTextArea(3,1);
	private JPanel infoPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private CSVFileReader file;
	private int pin;
	private int index;
	private GridBagConstraints layoutConst = new GridBagConstraints();
	
	/**
	 *  The Checking transaction JFrame constructor	
	 * @param pin allows the program to track this customer's pin number
	 * @param file allows the program to access the bank's customers and their transaction histories
	 * @param date allows the program to save the date of a transaction to the customer's records
	 */
	CheckingFrame(int pin, CSVFileReader file,String date){
			this.date = date;
			this.file = file;
			this.pin = pin;
			this.setVisible(true);
			this.setBounds(200, 200, 500, 500);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("ATM machine");
			this.setLayout(new BorderLayout());
			this.add(infoPanel,BorderLayout.WEST);
			this.add(buttonPanel,BorderLayout.EAST);
			this.setResizable(false);			
			createUIComponents();
			createButtonActions();
			revalidate();
			repaint();
		}
		
	/**
	 *  A method that sets up and defines this JFrame's JComponents	
	 */
	public void createUIComponents() {
		
		getCash.setFont(new Font("Calisto MT", Font.PLAIN,15));
		depositCash.setFont(new Font("Calisto MT", Font.PLAIN,15));
		depositCheck.setFont(new Font("Calisto MT", Font.PLAIN,15));
		accountHistory.setFont(new Font("Calisto MT", Font.PLAIN,15));
		
		getCash.setPreferredSize(new Dimension(130,50));
		depositCash.setPreferredSize(new Dimension(130,50));
		accountHistory.setPreferredSize(new Dimension(130,50));
		depositCheck.setPreferredSize(new Dimension(130,50));
		
		getCash.setBackground(Color.GRAY);
		depositCash.setBackground(Color.GRAY);
		depositCheck.setBackground(Color.GRAY);
		accountHistory.setBackground(Color.GRAY);
		
		getCash.setForeground(Color.BLACK);
		depositCash.setForeground(Color.BLACK);
		depositCheck.setForeground(Color.BLACK);
		accountHistory.setForeground(Color.BLACK);
		
		information.setBackground(Color.black);
		infoPanel.setBackground(Color.BLACK);
		buttonPanel.setBackground(Color.BLACK);
		information.setEditable(false);
		buttonPanel.setPreferredSize(new Dimension(300,500));
		buttonPanel.setLayout(new GridBagLayout());
		infoPanel.setPreferredSize(new Dimension(200,500));
		FlowLayout vSpacing = new FlowLayout(FlowLayout.LEFT,10,10);
		vSpacing.setVgap(150);
		infoPanel.setLayout(vSpacing);
		information.setFont(new Font("Calisto MT",Font.PLAIN,15));
		infoPanel.add(information);
		information.setForeground(Color.RED);
		
		
		for(int i = 0; i <file.getCustomers().size(); i++) {
			if(pin == file.getCustomers().get(i).getIDNum()) {
				index= i;
			}
		}
		information.setText(file.getCustomers().get(index).toString());
		
		layoutConst.gridx = 0;
		layoutConst.gridy  = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonPanel.add(getCash,layoutConst);
		
		layoutConst.gridx = 1;
		layoutConst.gridy  = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonPanel.add(depositCash,layoutConst);
		
		layoutConst.gridx = 0;
		layoutConst.gridy  = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonPanel.add(depositCheck,layoutConst);
		
		layoutConst.gridx = 1;
		layoutConst.gridy  = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonPanel.add(accountHistory,layoutConst);
	}
	
	/**
	 * A method that sets up this JFrame's JButtons with actionListeners
	 * and determines what happens after each button is clicked
	 */
	public void createButtonActions() {
		getCash.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CheckGetCashFrame frame = new CheckGetCashFrame(pin,file,date);
				
			}});
		
		depositCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CheckDepositFrame frame = new CheckDepositFrame(pin,file,date);
				
			}});
		
		depositCash.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				depositCashFrame frame = new depositCashFrame(pin,file,date);
				
			}});
		
		accountHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CheckingHistoryFrame frame = new CheckingHistoryFrame(pin,file,date);
				
			}});
	}

}
