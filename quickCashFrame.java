/**@author Isaac Angle and Shane Snediker
 * this class creates a frame the allows the user to click
 * on five buttons, 20,40,60,80 and 100 respectively
 * after that the amount written on the button is subtracted from 
 * the user balance
 */
package atm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class quickCashFrame extends JFrame {

    // private member attributes

    private JPanel buttonHolder = new JPanel();
    private JButton twenty = new JButton("$20"), fourty = new JButton("$40"), sixty = new JButton("$60"),
                                eighty = new JButton("$80"), hundred = new JButton("$100");
    private int pin;
    private CSVFileReader file;
    private String date;
    private int index;
    private GridBagConstraints layoutConst = new GridBagConstraints();

    /**
     * The quick cash pop up JFrame constructor
     * @param pin allows the program to use the pin number to follow the customer that is currently using the ATM
     * @param file allows the program to have access to the full database of ATM customers for this bank
     * @param date allows the program to track the current date of this transaction for record keeping purposes
     */
    quickCashFrame(int pin, CSVFileReader file, String date) {
    	
    	//creating the intial parameters for the frame
        this.pin = pin;
        this.file = file;
        this.date = date;
        this.setLayout(new BorderLayout());
        this.setBounds(200, 200, 500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("ATM machine");
        this.add(buttonHolder,BorderLayout.CENTER);
        
        //getting the index at which the customer resides
		for(int i = 0; i < this.file.getCustomers().size(); i++) {
			if(pin == this.file.getCustomers().get(i).getIDNum()) {
				index = i;
			}
		}
		//creating components and adding action listeners
        createUIComponents();
        createButtonActions();
        this.setVisible(true);
        revalidate();
        repaint();
    }

    public void createUIComponents() {

        // Set the font style and size for the buttons on this frame
        twenty.setFont(new Font("Calisto MT", Font.PLAIN,15));
		fourty.setFont(new Font("Calisto MT", Font.PLAIN,15));
		sixty.setFont(new Font("Calisto MT", Font.PLAIN,15));
        eighty.setFont(new Font("Calisto MT", Font.PLAIN,15));
        hundred.setFont(new Font("Calisto MT", Font.PLAIN,15));
        
        // Set the size of the buttons
		twenty.setPreferredSize(new Dimension(130,50));
		fourty.setPreferredSize(new Dimension(130,50));
		sixty.setPreferredSize(new Dimension(130,50));
        eighty.setPreferredSize(new Dimension(130,50));
        hundred.setPreferredSize(new Dimension(130,50));
		
        // Adjust the color of the buttons
        twenty.setBackground(Color.GRAY);
		fourty.setBackground(Color.GRAY);
		sixty.setBackground(Color.GRAY);
        eighty.setBackground(Color.GRAY);
        hundred.setBackground(Color.GRAY);
		
        // Adjust the font color of the buttons
        twenty.setForeground(Color.BLACK);
		fourty.setForeground(Color.BLACK);
		sixty.setForeground(Color.BLACK);
        eighty.setForeground(Color.BLACK);
        hundred.setForeground(Color.BLACK);
        
        // Format the Panel that will hold the buttons
        // Set up the style and formatting for the panels that will hold the buttons and the customer information
        
        buttonHolder.setBackground(Color.BLACK);
        buttonHolder.setPreferredSize(new Dimension(200,500));
        buttonHolder.setLayout(new GridBagLayout());
        
        //puts the "20" button in the middle of the screen and 
        //on top
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonHolder.add(twenty,layoutConst);
		
        //puts the "40" button in the middle of the screen and 
        //slightly below the "20" button
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonHolder.add(fourty,layoutConst);
		
        //puts the "60" button in the middle of the screen and 
        //slightly below the "40" button
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
		layoutConst.insets = new Insets(10,10,10,10);
        buttonHolder.add(sixty,layoutConst);
        
        //puts the "80" button in the middle of the screen and 
        //slightly below the "60" button
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
		layoutConst.insets = new Insets(10,10,10,10);
        buttonHolder.add(eighty,layoutConst);
        
        //puts the "100" button in the middle of the screen and 
        //slightly below the "80" button
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
		layoutConst.insets = new Insets(10,10,10,10);
        buttonHolder.add(hundred,layoutConst);   
         

   }
    
    /**
     * this method creates the action listeners for the buttons
     * so that the program actually subtracts money from the accounts
     */
    public void createButtonActions() {
    	//allows the "20" button to work
    	twenty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				file.getCustomers().get(index).getSavAcc().withdraw(20, date);
				SavingsRestartFrame frame = new SavingsRestartFrame(pin,file,date);
				
			}});
    	//allows the "40" button to work
    	fourty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				file.getCustomers().get(index).getSavAcc().withdraw(40, date);
				SavingsRestartFrame frame = new SavingsRestartFrame(pin,file,date);
				
			}});
    	//allows the "60" button to work
    	sixty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				file.getCustomers().get(index).getSavAcc().withdraw(60, date);
				SavingsRestartFrame frame = new SavingsRestartFrame(pin,file,date);
				
			}});
    	//allows the "80" button to work
    	eighty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				file.getCustomers().get(index).getSavAcc().withdraw(80, date);
				SavingsRestartFrame frame = new SavingsRestartFrame(pin,file,date);
				
			}});
    	
    	//allows the "100" button to work
    	hundred.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				file.getCustomers().get(index).getSavAcc().withdraw(100, date);
				SavingsRestartFrame frame = new SavingsRestartFrame(pin,file,date);
				
			}});
    }

}