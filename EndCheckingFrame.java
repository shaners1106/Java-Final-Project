/** @author Isaac Angle and Shane Snediker
 * This class determines the characteristics of the JFrame
 * that pops up when a customer is finished using the ATM
 * */

package atm;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EndCheckingFrame extends JFrame{
	
	// Private member attributes
	
	private int pin;
	private CSVFileReader file;
	private String date;
	private JPanel infoPanel = new JPanel();
	private JTextArea custInfo = new JTextArea(3,1);
	private int index;
	
	/**
	 *  The Checking transaction JFrame constructor	
	 * @param pin allows the program to track this customer's pin number
	 * @param file allows the program to access the bank's customers and their transaction histories
	 * @param date allows the program to save the date of a transaction to the customer's records
	 */
	EndCheckingFrame(int pin,CSVFileReader file,String date){
		this.date = date;
		this.pin = pin;
		this.file = file;
		this.setBounds(200, 200, 500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setTitle("ATM machine");
		for(int i = 0; i < file.getCustomers().size(); i++) {
			if(pin == file.getCustomers().get(i).getIDNum()) {
				index = i;
			}
		}
		
		custInfo.setText(file.getCustomers().get(index).displayCheckingInfo());
		this.add(infoPanel,BorderLayout.CENTER);
		infoPanel.setPreferredSize(new Dimension(500,500));
		infoPanel.setBackground(Color.BLACK);
		FlowLayout vSpacing = new FlowLayout();
		vSpacing.setVgap(100);
		infoPanel.setLayout(vSpacing);
		custInfo.setFont(new Font("Calibri",Font.PLAIN,20));
		custInfo.setForeground(Color.RED);
		custInfo.setBackground(Color.BLACK);
		custInfo.setEditable(false);
		infoPanel.add(custInfo);
		revalidate();
		repaint();
		
	}
}
