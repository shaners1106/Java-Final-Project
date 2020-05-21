/**@author Isaac Angle and Shane snediker
 * this class allows for the creation of transactions
 * which have a type, an amount, and a date in which they are done*/
package atm;

public class Transactions {
	//private members
	private int transNum = Customer.transactions.size() + 1;
	private String type,date;
	private double amount;
	private int determinant, checkID;
	/**@param type,amount,date Takes the type (checking/savings), the amount
	 * of money and the date on which the transaction occurred*/
	Transactions(String type,double amount, String date,int determinant, int checkID){
		this.date = date;
		this.amount = amount;
		this.type = type;
		this.determinant = determinant;
		this.checkID = checkID;
	}
	
	/**Overriding the to string method to give an appropriate description
	 * that is based on whether the transaction is a check, a deposit or a withdrawl
	 */
	@Override
	public String toString() {
		if(checkID != 0) {
			return "Transaction number: " + transNum + "\n " + "Amount deposited: " + amount + "\n " +  type + " Transaction on" + " " + date  + "\n" + "Check Number: " + checkID + "\n\n";
		}else if(determinant == 0) {
			return "Transaction number: " + transNum + "\n " + "Amount deposited: " + amount + "\n " +  type + " Transaction on" + " " + date + "\n\n";
		}else {
			return "Transaction number: " + transNum + "\n " + "Amount withdrawn: " + amount + "\n " +  type + " Transaction on" + " " + date + "\n\n";
		}
		
	}   
}
