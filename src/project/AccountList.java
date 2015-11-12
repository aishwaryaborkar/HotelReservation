package project;

/**
 * AccountList.java  
 * 
 * This class will be responsible for storing all accounts made
 * through login. Multiple accounts are not allowed (one account 
 * per person). Once the program is loaded, account saved on a
 * file will be restored in this list.
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - Dr. S. Kim
 * San Jose State University
 */

import java.util.*;

public class AccountList
{
	//array list that will hold ALL the accounts made
	private ArrayList<GuestAccount> accountList;
	
	/**
	 * CONSTRUCTOR:	creating a new instance of the list
	 */
	public AccountList ()
	{
		accountList = new ArrayList<GuestAccount> ();
	}
	
	/**
	 * addToAccountList method:	This method adds the newly created
	 * 							account to the account list arraylist.
	 * @param createdAccount
	 */
	public void addToAccountList (GuestAccount createdAccount)
	{
		accountList.add(createdAccount);
	}
	
	/**
	 * removeFromAccountList method:	This mehod removes the account
	 * 									with the given e-mail address
	 * 									from the accountList.
	 * @param eAdd
	 */
	public void removeFromAccountList (String eAdd)
	{
		for (int index = accountList.size() - 1 ; index >= 0 ; index--)
			if (eAdd.equals(accountList.get(index).getEmail()))
			{
				accountList.remove(index);
				index = 0;	//terminate the loop since element is found
			}
	}
	
	/**
	 * accountFoundInList method:	This method will search the array list
	 * 								to confirm if an e-mail is already used.
	 * @param email
	 * @return true if email address is found in accountList, false otherwise
	 */
	public boolean accountFoundInList (String email)
	{
		boolean inList = false;
		
		for (int index = 0 ; index < accountList.size(); index++)
			if (email.equals(accountList.get(index).getEmail()))
			{
				inList = true;
				index = accountList.size();
			}
		
		return inList;
	}
	

	
	/**
	 * accountListEmpty method:		Returns true if the accountList is empty.
	 * @return true if the list is empty, false otherwise
	 */
	public boolean accountListEmpty ()
	{
		return accountList.isEmpty();
	}
	
	
	/**
	 * getList method:	This method returns a link to the ArrayList<GuestAccount>
	 * @return reference to the array list
	 */
	public ArrayList<GuestAccount> getList ()
	{
		return accountList;
	}
	
	/**
	 * printArrayList method:	Prints the contents of ArrayList<GuestAccount>
	 * 							to check the contents. [Especially for loading
	 * 							data from account.txt. *See LoadAccountFile class.]
	 */
	public void printArrayList ()
	{
		for (int index = 0 ; index < accountList.size() ; index++)
			System.out.println(index +"\n" 
					+ accountList.get(index).getName() + "-"
					+ accountList.get(index).getAge() + "-"
					+ accountList.get(index).getEmail() + "-"
					+ accountList.get(index).getPassword() + "\n");
	}
}

