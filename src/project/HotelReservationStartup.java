package project;

/**
 * HotelReservationStartUp.java  
 * 
 * This java file has the main method for the entire Hotel Reservation
 * System implementation. Before it creates the main menu through
 * instantiating HotelReservationMainMenu.java, it will first load all
 * saved accounts in account.txt and reconstruct the ArrayList<GuestAccount>
 * accountList, in which the reference will be passed as a parameter to all
 * the classes that needs access to it to prevent losing reference of the 
 * reconstructed accountList.
 * 
 * Since this Hotel Reservation System is a team project, classes needed to 
 * implement this is divided amongst team members. Throughout the implementation,
 * some segments inside classes might be created by other team members. Thus,
 * in such cases, comments will include team member's initials (see team info below).
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - MW 12:00pm - 13:15pm
 * San Jose State University
 * ---Team Krisandaish---
 * Academia, Candice		[CA]
 * Alcantara, Kristani		[KA]
 * Borkar, Aishwarya		[AB]
 */

import javax.swing.*; 
import java.util.*;
import java.io.*;

public class HotelReservationStartup
{
	public static void main (String [] args) throws IOException
	{
		/**
		 * Creating an ArrayList<GuestAccount> accountList to reconstruct
		 * an array that will hold ALL accounts created through this hotel
		 * reservation system which is loaded from a saved file account.txt
		 */
		ArrayList<GuestAccount> accountList = new ArrayList<GuestAccount> ();
		
		/**
		 * Loads account.txt to AccountList to keep track of ALL accounts made
		 */
		accountList = new LoadAccountFile().getAccountList();
			
		
		/** 
		 * Starts the Hotel Reservation System by calling the
		 * HotelReservationMainMenu
		 */
		new HotelReservationMainMenu (accountList);
                
                
	
	}
}
