/**
 * @author Isaac Angle and Shane Snediker
 * this class makes customers who have account numbers
 *  and names.
 *  */
package atm;

import java.util.ArrayList;

public class Customer {

	// Private member attributes
	
	private String name;
	private int pinNum;
	private int checkingNum,savingsNum;
	private static int startingCheckID = 10000;
	private ArrayList<Check> personalCheck = new ArrayList<Check>();
	public static ArrayList<Transactions> transactions = new ArrayList<Transactions>();
	private double savSBal,checkSBal;
	private CheckingAccount checkAcc;
	private SavingsAccount savAcc;
	
	/**
	 * The Customer object constructor
	 * @param name represents this customer's name
	 * @param IDNum represents this customer's pin number
	 * @param checkingNum represents this customer's checking account number
	 * @param savingsNum represents this customer's savings account number
	 * @param savSBal represents this customer's beginning savings account balance
	 * @param checkSBal represents this customer's beginning checking account balance
	 */
	public Customer(String name, int IDNum,int checkingNum,int savingsNum,double savSBal,double checkSBal) {
		this.name = name;
		pinNum = IDNum;
		this.savingsNum = savingsNum;
		this.checkingNum = checkingNum;
		this.savSBal = savSBal;
		this.checkSBal = checkSBal;
		
		checkAcc = new CheckingAccount(checkSBal);
		savAcc = new SavingsAccount(savSBal);
	}
	
	/**
	 * A getter for accessing this customer's array of Transactions
	 * @return the array of transactions belonging to this customer
	 */
	public ArrayList<Transactions> getTransactions() {
		return transactions;
	}

	/**
	 * A function to override the toString method so that we can adequately display this customer's information
	 */
	@Override
	public String toString() {
		return "Welcome " + "\n" + name + "\n\n\n" + "Account number" + "\n" + checkingNum + "\n\n\n" + "Balance" + "\n" + checkAcc.getBalance();
		
	}
	
	/**
	 * An extension of a toString method.  This one displays savings account specific information
	 * @return the string form of this customer's savings account information
	 */
	public String savingsInfo() {
		return "Welcome " + "\n" + name + "\n\n\n" + "Account number" + "\n" + savingsNum + "\n\n\n" + "Balance" + "\n" + savAcc.getBalance();
	}
	
	/**
	 * A getter for accessing this customer's name
	 * @return name. Returns the customers name
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter for accessing this customer's pin number
	 * @return IDNum. returns the customers ID number
	 * */
	public int getIDNum() {
		return pinNum;
	}
	
	/**
	 * A getter for accessing this customer's checking account number
	 * @return checkingNum. returns the checking account number of the customer
	 * */
	public int getCheckingNum() {
		return checkingNum;
	}
	
	/**
	 * A getter for accessing this customer's savings account number
	 * @return savingsNum. Returns the savings account number of the customer
	 * */
	public int getSavingsNum() {
		return savingsNum;
	}
	
	/**
	 * A getter for accessing this customer's checking account
	 * @return A CheckingAccount object belonging to this customer
	 */
	public CheckingAccount getCheckacc() {
		return checkAcc;
	}

	/**
	* A getter for accessing this customer's savings account
	 * @return A SavingsAccount object belonging to this customer
	 */
	public SavingsAccount getSavAcc() {
		return savAcc;
	}
	
	/**
	 * An extension of toString.  This one provides a checking account information display
	 * @return the string form of this customer's checking account information 
	 */
	public String displayCheckingInfo() {
		return "Thank you "+ name + "\n\n" + "Your new balance is $"+ checkAcc.getBalance() + "\n\n" + "Have a great day!";
	}
	
	/**
	 * An extension of toString.  This one provides a savings account information display
	 * @return the string form of this customer's savings account information
	 */
	public String displaySavingsInfo() {
		return "Thank you "+ name + "\n\n" + "Your new balance is $"+ savAcc.getBalance() + "\n\n" + "Have a great day!";
	}

	/**
	 * A getter for accessing this customer's array of personal checks
	 * @return an ArrayList containing of the the personal Check objects belonging to this customer
	 */
	public ArrayList<Check> getPersonalCheck() {
		return personalCheck;
	}
	
	
	
}
