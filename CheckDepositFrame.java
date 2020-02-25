/**@author Isaac Angle and Shane Snediker
 * this class creates a frame that asks the user for
 * the number on their check and the amount on the check
 * then it transitions to the EndFrame class ending the program*/
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckDepositFrame extends JFrame{
	
	private int pin;
	private String date;
	private CSVFileReader file;
	//holds the deposit field
	private JPanel depositPanel = new JPanel();
	//holds the amount of money the person is going to deposit
	private JTextField depositField = new JTextField();
	//holds the index that the customer is at
	private int index;
	//asks the user for their check number
	private JLabel checkIDLabel = new JLabel("Enter check ID: ");
	//gets the check number
	private JTextField amountText = new JTextField();
	//holds the amount label
	private JPanel amountPanel = new JPanel();
	//asks the user to enter the amount on the check
	private JLabel enterAmount = new JLabel("Enter amount: ");
	//holds the enter button
	private JPanel buttonPanel = new JPanel();
	//creates the button on the bottom of the screen
	private JButton enterBtn = new JButton("Enter");
	//these are used to get the strings from the user and convert them to ints
	private String checkId,stringAmount;
	private double amount;
	//tells the user they entered an invalid string
	private JLabel errorLabel = new JLabel("Sorry invalid entry");
	private GridBagConstraints layoutconst = new GridBagConstraints();
	private int intCheckId;
	private Check myCheck;
	
	CheckDepositFrame(int pin, CSVFileReader file,String date){
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
		this.add(depositPanel,BorderLayout.NORTH);
		this.add(amountPanel, BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
		//gets the index at which the customer is located
		for(int i = 0; i < file.getCustomers().size(); i++) {
			if(pin == file.getCustomers().get(i).getIDNum()) {
				index = i;
			}
		}
		//sets the size of the panels
		amountPanel.setPreferredSize(new Dimension(500,250));
		depositPanel.setPreferredSize(new Dimension(500,250));
		//adds the text field to the panel
		amountPanel.add(enterAmount);
		//sets the font size and color of the text
		enterAmount.setFont(new Font("Colisto MT",Font.PLAIN,20));
		enterAmount.setForeground(Color.RED);
		enterAmount.setBackground(Color.BLACK);
		//adds the text field to the panel and sets its size and color and font
		amountPanel.add(amountText);
		amountText.setColumns(10);
		amountText.setBackground(Color.LIGHT_GRAY);
		amountText.setFont(new Font("Calibri",Font.PLAIN,20));
		depositPanel.add(checkIDLabel);
		
		//used for pushing the labels lower down
		FlowLayout vSpacing = new FlowLayout();
		vSpacing.setVgap(150);
		
		FlowLayout vSpacing2 = new FlowLayout();
		vSpacing2.setVgap(0);
		
		//adds the button to the panel and sets its size and color
		buttonPanel.setPreferredSize(new Dimension(150,150));
		enterBtn.setPreferredSize(new Dimension(100,50));
		enterBtn.setBackground(Color.GRAY);
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Color.BLACK);
		
		layoutconst.gridx = 0;
		layoutconst.gridy = 0;
		layoutconst.insets = new Insets(10,10,10,10);
		buttonPanel.add(enterBtn,layoutconst);
		
		amountPanel.setLayout(vSpacing2);
		
		//sets the background colors of the panels and text
		depositPanel.setLayout(vSpacing);
		depositPanel.setBackground(Color.BLACK);
		amountPanel.setBackground(Color.BLACK);
		checkIDLabel.setBackground(Color.BLACK);
		checkIDLabel.setForeground(Color.RED);
		checkIDLabel.setFont(new Font("Colisto MT",Font.PLAIN,20));
		
		//sets the font and color of the text field
		depositPanel.add(depositField);
		depositField.setBackground(Color.LIGHT_GRAY);
		depositField.setFont(new Font("Calibri",Font.PLAIN,20));
		depositField.setColumns(10);
		addButtonActions();
		
	}
	/**This function is used to add an action listener to the
	 * button so that when you click the button the frame changes to
	 * the end frame which displays a thank you message*/
	public void addButtonActions() {
		//adding the action listener
		enterBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//getting the checks number from the user
				checkId = depositField.getText();
				checkId = checkId.trim();
				//making sure the check number is 6 number long
				boolean matches = checkId.matches("\\d\\d\\d\\d\\d\\d");
				//makes sure that a check has to match the format
				if(matches) {
					try {
						//changes the amount into an int instead of an int
						stringAmount = amountText.getText();
						stringAmount = stringAmount.trim();
						intCheckId = Integer.parseInt(checkId);
						amount = Double.parseDouble(stringAmount);
						if(amount > 0) {
							//adds the money to the customers account
							myCheck = new Check(intCheckId);
							file.getCustomers().get(index).getCheckacc().remitCheck(myCheck,amount,date,intCheckId);
							file.getCustomers().get(index).getPersonalCheck().add(myCheck);
							//creates the new frame with the thank you message
							setVisible(false);
							CheckingRestartFrame frame  = new CheckingRestartFrame(pin,file,date);
						}else {
							amountPanel.add(errorLabel);
							layoutconst.gridx = 0;
							layoutconst.gridy = 2;
							layoutconst.insets = new Insets(10,10,10,10);
							buttonPanel.add(errorLabel,layoutconst);
							errorLabel.setHorizontalAlignment(JLabel.CENTER);
							errorLabel.setVerticalAlignment(JLabel.NORTH);
							errorLabel.setForeground(Color.RED);
							errorLabel.setText("Sorry you cannot deposit zero dollars");
							revalidate();
							repaint();
						}
					}catch(Exception ex) {
						//creates the error message stating the entry was invalid
						amountPanel.add(errorLabel);
						layoutconst.gridx = 0;
						layoutconst.gridy = 2;
						layoutconst.insets = new Insets(10,10,10,10);
						errorLabel.setText("Sorry invalid entry");
						buttonPanel.add(errorLabel,layoutconst);
						errorLabel.setHorizontalAlignment(JLabel.CENTER);
						errorLabel.setVerticalAlignment(JLabel.NORTH);
						errorLabel.setForeground(Color.RED);
						revalidate();
						repaint();
					}
					
				} else {
					//creates the error message stating the entry was invalid
					amountPanel.add(errorLabel);
					layoutconst.gridx = 0;
					layoutconst.gridy = 2;
					layoutconst.insets = new Insets(10,10,10,10);
					buttonPanel.add(errorLabel,layoutconst);
					errorLabel.setHorizontalAlignment(JLabel.CENTER);
					errorLabel.setVerticalAlignment(JLabel.NORTH);
					errorLabel.setForeground(Color.RED);
					revalidate();
					repaint();
				}
			
			}
		});
	}
}
