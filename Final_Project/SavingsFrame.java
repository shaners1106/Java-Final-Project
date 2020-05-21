/**@author Isaac Angle and Shane Snediker
 * this class creates a frame that allows the user to
 * deposit, get quick cash, withdraw and see previous transactions*/
package atm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavingsFrame extends JFrame{

    
    // Private member attributes

    private JButton getCash = new JButton("Get Cash"), depositCash  = new JButton("Deposit Cash"),
			 quickCash = new JButton("Quick cash"), accountHistory = new JButton("History");
	private JTextArea information = new JTextArea(3,1);
	private JPanel infoPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
    private int pin;
    private CSVFileReader file;
    private String date;
    private int index;
    private GridBagConstraints layoutConst = new GridBagConstraints();
    
    /**
     * savingsFrame constructor sets up the characteristics of the Savings account options for this customer
     * @param pin allows the program to use the pin number to follow the customer that is currently using the ATM
     * @param file allows the program to have access to the full database of ATM customers for this bank
     * @param date allows the program to track the current date of this transaction for record keeping purposes
     */
    SavingsFrame(int pin, CSVFileReader file, String date) {
    	//setting up the default parameters that each frame has
        this.pin = pin;
        this.file = file;
        this.date = date;
        this.setBounds(200, 200, 500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("ATM machine");
        this.getContentPane().setBackground(Color.BLACK);
		this.add(infoPanel,BorderLayout.WEST);
		this.add(buttonPanel,BorderLayout.EAST);
        createUIComponents();
        createButtonActions();
        this.setVisible(true);
        revalidate();
        repaint();

    }    

    /**
     * This method sets up the formatting characteristics of the JComponents for this frame
     */
    public void createUIComponents() {

        // Set the font style and size for the buttons on this frame
        getCash.setFont(new Font("Calisto MT", Font.PLAIN,15));
		depositCash.setFont(new Font("Calisto MT", Font.PLAIN,15));
		quickCash.setFont(new Font("Calisto MT", Font.PLAIN,15));
		accountHistory.setFont(new Font("Calisto MT", Font.PLAIN,15));
        
        // Set the size of the buttons
		getCash.setPreferredSize(new Dimension(130,50));
		depositCash.setPreferredSize(new Dimension(130,50));
		accountHistory.setPreferredSize(new Dimension(130,50));
		quickCash.setPreferredSize(new Dimension(130,50));
		
        // Adjust the color of the buttons
        getCash.setBackground(Color.GRAY);
		depositCash.setBackground(Color.GRAY);
		quickCash.setBackground(Color.GRAY);
		accountHistory.setBackground(Color.GRAY);
		
        // Adjust the font color of the buttons
        getCash.setForeground(Color.BLACK);
		depositCash.setForeground(Color.BLACK);
		quickCash.setForeground(Color.BLACK);
		accountHistory.setForeground(Color.BLACK);
		
        // Set up the style and formatting for the panels that will hold the buttons and the customer information
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
		
		//finding the index that the customer is at
		for(int i = 0; i <file.getCustomers().size(); i++) {
			if(pin == file.getCustomers().get(i).getIDNum()) {
				index= i;
			}
		}
		information.setText(file.getCustomers().get(index).savingsInfo());
		
        // Using gribBagLayout we now set up the formatting parameters for the buttons on this popUp JFrame
        
        layoutConst.gridx = 0;
		layoutConst.gridy  = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonPanel.add(quickCash,layoutConst);
		
		layoutConst.gridx = 1;
		layoutConst.gridy  = 0;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonPanel.add(getCash,layoutConst);
		
		layoutConst.gridx = 0;
		layoutConst.gridy  = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonPanel.add(depositCash,layoutConst);
		
		layoutConst.gridx = 1;
		layoutConst.gridy  = 1;
		layoutConst.insets = new Insets(10,10,10,10);
		buttonPanel.add(accountHistory,layoutConst);
    }

    /**
     * A method that determines the program's response to button clicks
     */
    public void createButtonActions() {
    	//allowing the "quickCash" button to work
        quickCash.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				quickCashFrame frame = new quickCashFrame(pin,file,date);
				
			}});
        
      //allowing the "getCash" button to work
        getCash.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				 SavingsWithdrawFrame frame = new SavingsWithdrawFrame(pin,file,date);
				
			}});
        
      //allowing the "depositCash" button to work
        depositCash.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				 SavingsDepositFrame frame = new SavingsDepositFrame(pin,file,date);
				
			}});
        
      //allowing the "History" button to work
        accountHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				 SavingsHistoryFrame frame = new SavingsHistoryFrame(pin,file,date);
				
			}});
    }}