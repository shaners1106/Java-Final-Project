/**@author Isaac Angle and Shane Snediker
 * this class reads the names, pin numbers, account numbers, and 
 * the starting amount of the accounts from a.CSV file
 * and puts everything into organized vectors*/
package atm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class CSVFileReader {
	
	// Private member attributes

	private String[] parse;
	private String line = "";
	private BufferedReader r = null;
	private String csvFile = "J:\\ATM\\resources/15 ATM Customers.csv";
	private String csvFile2 = "J:\\ATM\\resources/Transactions.csv";
	
	private String csvSplitBy = ",";
	//holds the customers information
	private ArrayList<String> customersNames = new ArrayList<String>();
	private ArrayList<String>  customersPins= new ArrayList<String>();
	private ArrayList<String> customersSavings = new ArrayList<String>();
	private ArrayList<String> customersChecking = new ArrayList<String>();
	private ArrayList<String> customersSavBeg = new ArrayList<String>();
	private ArrayList<String> customersCheckBeg = new ArrayList<String>();
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	
	//holds the transactions information
	private ArrayList<String> typeOfTrans = new ArrayList<String>();
	private ArrayList<String> amountOfTrans = new ArrayList<String>();
	private ArrayList<String> dateOfTrans = new ArrayList<String>();
	private double theAmount;
	private Random rand = new Random();
	
	/**
	 * No Arg constructor
	 */
	CSVFileReader(){ }

	/**
	 * This function reads in the customer information from an external
	 * file so that there is customer data to work with so that the ATM
	 * is functional
	 */
	public void readNames(){
		// reads the names from the lists and puts them into vectors
		try {
			r = new BufferedReader(new FileReader(csvFile));
			while((line = r.readLine()) != null) {
				parse = line.split(csvSplitBy);
				customersNames.add(parse[0]);
				customersPins.add(parse[1]);
				customersSavings.add(parse[2]);
				customersChecking.add(parse[3]);
				customersSavBeg.add(parse[4]);
				customersCheckBeg.add(parse[5]);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function generates access the customer pin numbers
	 * @return customersPins, returns a vector containing the pins of the customer
	 * */
	public ArrayList<String> getCustomersPins() {
		return customersPins;
	}
	
	/**
	 * This function generates access the customer Savings account
	 * @return customersSavings, returns a vector containing the saving account 
	 * numbers of the customers
	 * */
	public ArrayList<String> getCustomersSavings() {
		return customersSavings;
	}
	
	/**
	 * This function generates access the customer Checking account
	 * @return customersChecking, returns the checking account numbers
	 * for all the customers
	 * */
	public ArrayList<String> getCustomersChecking() {
		return customersChecking;
	}
	
	/**
	 * This function generates access this customer's beginning savings account balance
	 * @return customersSavBeg, returns a vector containing the saving accounts
	 * starting balance for all customers
	 * */
	public ArrayList<String> getCustomersSavBeg() {
		return customersSavBeg;
	}
	
	/**
	 * This function generates access this customer's beginning checking account balance
	 * @return customersCheckBeg. Returns a vecotr containing the checking accounts
	 * starting balance for all the customers
	 * */
	public ArrayList<String> getCustomersCheckBeg() {
		return customersCheckBeg;
	}
	
	/**
	 * This function loads a vector of Customer objects, each with information read from an external file
	 */
	public void addCustomers() {
		for(int i = 0; i < customersNames.size(); i++) {
			String pin = customersPins.get(i).trim();
			String savings = customersSavings.get(i).trim();
			String checking = customersChecking.get(i).trim();
			String savBeg = customersSavBeg.get(i).trim();
			String checkBeg = customersCheckBeg.get(i).trim();
			customers.add(new Customer(customersNames.get(i),Integer.parseInt(pin),Integer.parseInt(savings),
					Integer.parseInt(checking),Double.parseDouble(savBeg),Double.parseDouble(checkBeg)));
		}
	}
	
	/**
	 * This function generates access the array holding this bank's Customer objects
	 * @return customers, returns a vector containing all the customers
	 * that exist for this ATM
	 * */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	/**
	 * This function generates access the array holding the String values representing the customers' names
	 * @return customersNames, returns a vector containing all
	 * the names of the customers who are registered in the ATM
	 * */
	public ArrayList<String> getCustomersNames() {
		return customersNames;
	}
	
	/**
	 * This function reads from an external file containing transaction data
	 * so that this bank's customer's will have transaction history
	 */
	public void readTrans() {
		try {
			r = new BufferedReader(new FileReader(csvFile2));
			while((line = r.readLine()) != null) {
				parse = line.split(csvSplitBy);
				typeOfTrans.add(parse[0]);
				amountOfTrans.add(parse[1]);
				dateOfTrans.add(parse[2]);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function adds any transactions made to the customer's transaction history
	 */
	public void addTrans() {
		for(int i = 0; i < customers.size(); i++) {
				int determinant = rand.nextInt(2);
				String type = typeOfTrans.get(i).trim();
				String amount = amountOfTrans.get(i).trim();
				theAmount = Double.parseDouble(amount);
				String date = dateOfTrans.get(i).trim();
				customers.get(i).getTransactions().add(new Transactions(type,theAmount,date,determinant,0));
			
		}
	}
	
	
}
