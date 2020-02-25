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

public class AccountHistoryFrame extends JFrame{
	private String date;
	private int pin, index;
	private CSVFileReader file;
	
	
	AccountHistoryFrame(int pin, CSVFileReader file,String date){
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
		
		//gets the index at which the customer is located
		for(int i = 0; i < file.getCustomers().size(); i++) {
			if(pin == file.getCustomers().get(i).getIDNum()) {
				index = i;
			}
		}
		
		
	}
}
