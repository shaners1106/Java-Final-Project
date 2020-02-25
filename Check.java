/**@author Isaac Angle and Shane Snediker
 * this class allows for the creation of checks which
 * can be used to withdraw from checking accounts
 * they have an ID number associated with them.*/
package atm;

public class Check {
	private int checkID;
	
	/**@param checkID, takes the checks ID number*/
	Check(int checkID){
		this.checkID = checkID;
	}
	
	/**@return checkID, returns the checks ID number*/
	public int getCheckID() {
		return checkID;
	}
	
}
