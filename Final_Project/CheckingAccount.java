/**@author Isaac Angle and Shane Snediker
 * This class will be used to create checking accounts
 *  for each user that they can withdraw and deposit from*/
package atm;

import java.util.ArrayList;

public class CheckingAccount implements Account{
	
	// Private member attributes
	
	private double balance;
	private int deposit = 0,withdraw = 1;
	
	/**
	 * Checking Account constructor
	 * @param balance, takes the current balance of the account
	 * */
	CheckingAccount(double balance){
		this.balance = balance;
	}

	/**
	 * A method that allows a customer to withdraw money from their checking account
	 * @param amount. takes the amount the user wishes to withdraw
	 * @return balance. returns the amount of money the user has after the withdrawal*/
	@Override
	public void withdraw(double amount, String date) {
			balance -= amount;
			Transactions tran = new Transactions("Checking", amount, date,withdraw,0);
			Customer.transactions.add(tran);
	}

	/**
	 * A method that allows a customer to deposit money into their checking account
	 * @param amount. takes the amount the user wishes to deposit
	 * @return balance. Returns the new balance*/
	@Override
	public void deposit(double amount, String date, int checkID) {
		balance += amount;
		Transactions tran = new Transactions("Checking", amount, date,deposit,checkID);
		Customer.transactions.add(tran);
	}

	/**
	 * A getter function for retrieving this customer's checking account balance
	 * @return balance. Returns the users current balance*/
	@Override
	public double getBalance() {
		return balance;
	}
	
	/**
	 * A function used when a customer deposits a check into their account
	 * @param myCheck,amount takes a check and the amount on it
	 * @return returns the checks ID number for later user*/
	public int remitCheck(Check myCheck, double amount, String date, int checkID) {
		deposit(amount,date,checkID);
		return myCheck.getCheckID();
	}

}
