package project;

/**
 * ManagerAccount.java 
 * 
 * This class will be responsible for the manager account.
 * 
 * Only one Manager account is allowed (at the moment). 
 * Password will be noted below.
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - Dr. S. Kim
 * San Jose State University
 */

public class ManagerAccount implements Account
{
	private final String password = "Krisandaish";
	private final String name = "Manager";
	
	/**
	 * setPassword method:	Inherited method from Account.
	 * 						Empty because we are not changing
	 * 						the manager password. Assume there
	 * 						is only one manager.
	 */
	public void setPassword (String p)
	{
		
	}
	
	/**
	 * setName method:	Inherited method from Account.
	 * 					Empty because we do not need to
	 * 					implement it.
	 */
	public void setName (String n)
	{
		
	}
	
	/**
	 * getPassword method:	This method returns the manager password
	 * 						simply for error handling purposes ONLY.
	 * 						Inherited method from Account.
	 */
	public String getPassword ()
	{
		return password;
	}
	
	/**
	 * getName method:	This is a dummy method. We do not necessarily
	 * 					need this in our implementation. Inherited method
	 * 					from Account.
	 */
	public String getName ()
	{
		return name;
	}
}

