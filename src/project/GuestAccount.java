package project;

/**
 * GuestAccount.java 
 * 
 * This class will be responsible for Guest accounts (whether
 * first time or returning user for our hotel reservation
 * implementation.
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - Dr. S. Kim
 * San Jose State University
 */

public class GuestAccount implements Account
{
	//These fields are mandatory to make an account
	private String name;
	private String password;
	private String email;
	private int age;
	
	/**
	 * CONSTRUCTOR:	Initialization of the fields.
	 */
	public GuestAccount ()
	{
		name = " ";
		password = " ";
		email = " ";
		age = 0;
	}
	
	/**
	 * Overloaded CONSTRUCTOR:	Initialization of the fields.
	 */
	public GuestAccount (String n, String p, String e, int a)
	{
		name = n;
		password = p;
		email = e;
		age = a;
	}
	
	/**
	 * createAccount method:	Set fields with parameter values.
	 * @param n	name
	 * @param p password
	 * @param e email
	 * @param a age
	 */
	public void createAccount (String n, String p, String e, int a)
	{
		name = n;
		password = p;
		email = e;
		age = a;
	}
	
	/**
	 * getName method:	This method will return the name.
	 * @return name
	 */
	public String getName ()
	{
		return name;
	}
	
	/**
	 * getPassword method:	This method will return the password.
	 * @return password
	 */
	public String getPassword ()
	{
		return password;
	}
	
	/**
	 * getEmail method:	This method will return the email.
	 * @return email
	 */
	public String getEmail ()
	{
		return email;
	}
	
	/**
	 * getAge method:	This method will return the age.
	 * @return age
	 */
	public int getAge ()
	{
		return age;
	}
	
	/**
	 * setName method:	Initializes the field name with parameter
	 * 					n.
	 * @param n name 
	 */
	public void setName (String n)
	{
		name = n;
	}
	
	/**
	 * setPassword method:	Initializes the field password with
	 * 						parameter p.
	 * @param p password
	 */
	public void setPassword (String p)
	{
		password = p;
	}
	
	/**
	 * setEmail method:	Initializes the field email with the 
	 * 					parameter eAdd.
	 * @param eAdd email
	 */
	public void setEmail (String eAdd)
	{
		email = eAdd;
	}
	
	/**
	 * setAge method:	Initializes the field age with the
	 * 					parameter a.
	 * @param a age
	 */
	public void setAge (int a)
	{
		age = a;
	}
}
