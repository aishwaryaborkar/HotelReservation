package project;

/**
 * SaveAccountList.java 
 * 
 * This class will save all data in the ArrayList<GuestAccount>
 * in the file account.txt to preserve all the information about
 * accounts created since multiple accounts by one user is not allowed.
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - MW 12:00pm - 13:15pm
 * San Jose State University
 */

import java.io.*; 
import java.util.*;

public class SaveAccountList 
{
	private final String accountFile =
			"C:/Users/Candice/workspace/Krisandaish/src/account.txt";
	private ArrayList<GuestAccount> list;
	/**
	 * CONSTRUCTOR:
	 * @throws
	 */
	public SaveAccountList (ArrayList<GuestAccount> l) throws IOException
	{
		
		/**
		 * Initiating accountList
		 */
		list = l;
			
		/**
		 * Creating IO Objects needed to append data into account.txt
		 */
		PrintWriter saveData = new PrintWriter (accountFile);
		
		for (int index = 0 ; index < list.size() ; index++)
		{
			saveData.print(list.get(index).getName() + "*");
			saveData.print(list.get(index).getPassword() + "*");
			saveData.print(list.get(index).getEmail() + "*");
			saveData.println(list.get(index).getAge());
		}
	
		//close account.txt
		saveData.close();
	}
}

