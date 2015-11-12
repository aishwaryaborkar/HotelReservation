package project;
/**
 * Account.java 
 * 
 * This interface will serve as a blueprint for all possible
 * types of accounts needed. In our implementation, we simply
 * need a GuestAccount and ManagerAccount.
 * 
 * *See classes mentioned for implementation of this interface.
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - MW 12:00pm - 13:15pm
 * San Jose State University
 */

public interface Account 
{
	public String getName ();
	public String getPassword ();
	public void setName (String name);
	public void setPassword (String password);
}
