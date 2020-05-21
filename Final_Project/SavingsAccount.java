/**@author Isaac Angle and Shane Snediker
 * This class will be used to create savings accounts
 *  for each user that they can withdraw and deposit from*/
package atm;

import java.util.ArrayList;

public class SavingsAccount implements Account{
	//private member variables
	private double balance;
	private static double minimumBalance = 100;
	private int deposit = 0,withdraw = 1;
	
	/**
	 * default constructor that take the balance in the account
	 * @param balance the current balance of the customer
	 */
	SavingsAccount(double balance){
		this.balance = balance;
	}
	
	/**@param amount. takes the amount the user wishes to withdraw
	 * @param date. A string that contains the current date
	 * @return balance. returns the amount of money the user has after the withdrawal*/
	@Override
	public void withdraw(double amount,String date) {
			balance -= amount;
			Transactions tran = new Transactions("Savings", amount, date,withdraw,0);
			Customer.transactions.add(tran);
		
	}

	/**@param amount. takes the amount the user wishes to deposit
	 * @param date. a string that contains the current date
	 * @param checkID. an int that contains the check number
	 * @return balance. Returns the new balance*/
	@Override
	public void deposit(double amount,String date,int checkID) {
		balance += amount;
		Transactions tran = new Transactions("Savings", amount, date,deposit,checkID);
		Customer.transactions.add(tran);
	}
	
	/**@return balance. Returns the users current balance*/
	@Override
	public double getBalance() {
		
		return balance;
	}

}
