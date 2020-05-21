/**@author Isaac Angle and Shane Snediker
 * this interface is implemented by the savings account
 * and the checking account. in order to implement this interface
 * a class must be able to withdraw, deposit, get the previous transactions,
 * and get the balance of the account.*/
package atm;


/**
 * an interface implemented by the checking and savings
 * accounts of the customer
 * */
public interface Account {
	void withdraw(double amount, String date);
	void deposit(double amount, String date, int checkID);
	double getBalance();
	
}
