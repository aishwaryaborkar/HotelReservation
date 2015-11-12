package project;

/**
 * LoadAccountFile.java  
 * 
 * This class will load GuestAccount data from account.txt to reconstruct
 * the ArrayList<GuestAccount> accountList for the implementation of this
 * Hotel Reservation System. Multiple accounts is not allowed and this is
 * needed to guarantee that it would not happen.
 * 
 * Note: account.txt contains three pre-set Accounts to be able to check 
 * 		 responsibility of this class and of SaveAccountFile. View 
 * 		 account.txt manually to get hold of the e-mail and password which
 * 		 will be needed for "Returning User".
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - MW 12:00pm - 13:15pm
 * San Jose State University
 */

import java.io.*;  
import java.util.*;

public class LoadAccountFile
{
	//for creating a GuestAccount object
	private GuestAccount account;		
	//reconstruct accountList
	private AccountList accountList;
	//reconstruct accountList
	private ArrayList<GuestAccount> dataListFromFile;
	
	/**
	 * CONSTRUCTOR:	Reconstructs accountList by using I/O Operations
	 * 				to retrieve
	 * 				data from file.
	 * @throws IOException
	 */
	public LoadAccountFile () throws IOException
	{
		//lineFromeFile will temporarily store the lines from account.txt
		String lineFromFile = "";	
		String n = "";					//name
		String eAdd = "";				//email
		String p = "";					//password
		int a = 0;						//age
		int accountInfo = 0;			//counter
		
		/**
		 * Creating instance of ArrayList<GuestAccount>
		 */
		dataListFromFile = new ArrayList<GuestAccount> ();
		
		/**
		 * Create String tokenizer object to distinguish each account info 
		 * from one another.
		 */
		StringTokenizer token;
		
		/**
		 * Create instances of fields.
		 */
		accountList = new AccountList ();
		
		/**
		 * Create IO Objects needed to read account.txt
		 */
		FileReader file_Account = new FileReader (new File ("account.txt"));
		BufferedReader buffer = new BufferedReader (file_Account);
		
		/**
		 * Loop to restore data from file: account.txt
		 */
		while ((lineFromFile = buffer.readLine()) != null)
		{
			token = new StringTokenizer (lineFromFile, "*", false);
			while (token.hasMoreTokens() && accountInfo < 4)
			{
				switch (accountInfo)
				{
					case 0:
						n = (String) token.nextElement();
						break;
					case 1:
						p = (String) token.nextElement();
						break;
					case 2:
						eAdd = (String) token.nextElement();
						break;
					case 3:
						a = Integer.parseInt((String)token.nextElement());
						break;
					default:
						break;
				}
				accountInfo++;
			}
			//reset the counter for every line read from file
			accountInfo = 0;	
			
			/**
			 * Create new account based on the data restored from the file
			 */
			account = new GuestAccount(n, p, eAdd, a);
			
			/**
			 * Reconstructing the ArrayList<GuestAccount> with accounts created
			 */
			accountList.addToAccountList(account);
			//uncomment below to check accountList content
			//accountList.printArrayList();
		}
		
		//
		dataListFromFile = accountList.getList();
		
		//closing the file
		buffer.close();
	}

	/**
	 * getAccountList method: 	This method will return the reference to the
	 * 							newly reconstructed accountList with the Account
	 * 							objects created using the account information 
	 * 							found in account.txt which will be passed as a 
	 * 							parameter to all the classes that needs to check
	 * 							the contents of the accountList.
	 * @return	reference to the newly reconstructed accountList with data from file
	 */
	public ArrayList<GuestAccount> getAccountList ()
	{
		return dataListFromFile;
	}
}
