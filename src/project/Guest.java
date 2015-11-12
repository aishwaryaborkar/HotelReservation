package project;

/**
 * Guest.java   
 * 
 * This class will implement the Guest frame. 
 * 
 * [Note: Open account.txt manually to view e-mail
 *  address and password to test the Returning User
 *  JButton.]
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - MW 12:00pm - 13:15pm
 * San Jose State University
 */

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Guest 
{
	private JFrame guestFrame;					//main frame for Guest.java
	/**
	 * accountList saves the reference of the accountList reconstructed in
	 * LoadAccountFile.java
	 */
	private ArrayList<GuestAccount> accountList;	
	
	
	/**
	 * CONSTRUCTOR:	This creates the "Guest" frame when Guest.java is 
	 * 				instantiated.
	 * @param list	reference of the reconstructed accountList from
	 * 				LoadAccountFile.java
	 * @throws IOException
	 */
	public Guest (ArrayList<GuestAccount> list) throws IOException
	{
		/**
		 * saves the reference of the reconstructed accountList from
		 * LoadAccountFile.java
		 */
		accountList = list;
		
		/**
		 * Creating a new JFrame
		 */
		guestFrame = new JFrame ();
		guestFrame.setTitle("Hotel Reservation System");
		guestFrame.setSize(300,100);
		guestFrame.setLocationRelativeTo(null);
		guestFrame.setLayout(new BorderLayout());
		guestFrame.setResizable(false);
		guestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guestFrame.setVisible(true);
		
		/**
		 * Creating a container for "Guest" components
		 */
		JPanel guestContainer = new JPanel ();
		guestContainer.setSize(100,50);
		guestContainer.setLayout(new GridLayout (1,2));
		
		/**
		 * Create a JLabel to signal user to do some action
		 */
		JLabel selectLabel = new JLabel ();
		selectLabel.setText("Please select one from below to proceed.");
		
		/**
		 * Creating a JButton for the option "First Time User"
		 */
		JButton firstButton = new JButton ();
		firstButton.setText("First Time User");
		firstButton.setSize(50,50);
		/**
		 * ActionListener for the 'First Time User' user type
		 */
		firstButton.addActionListener(new ActionListener ()
			{
				public void actionPerformed (ActionEvent event)
				{
					//hides this frame before proceeding to the next frame
					guestFrame.setVisible(false);	
					try
					{
						/**
						 * Proceeds to the CreateAccount frame by instantiating
						 * CreateAccount.java.
						 */
						new CreateAccount(accountList);
					}
					catch (IOException i)
					{
						JOptionPane.showMessageDialog(null, "Error Occured. Reboot system.");
					}
				}
			}
			);
		
		/**
		 * Creating a JButton for the option "Returning User"
		 */
		JButton returningButton = new JButton();
		returningButton.setText("Returning User");
		returningButton.setSize(50,50);
		/**
		 * ActionListener for the 'First Time User' user type
		 */
		returningButton.addActionListener(new ActionListener ()
			{
				public void actionPerformed (ActionEvent event)
				{
					//hides this frame before proceeding to the next frame
					guestFrame.setVisible(false);
					try
					{
						/**
						 * Proceeds to the Login frame by instantiating
						 * Login.java.
						 */
						new Login(accountList);
					}
					catch (IOException i)
					{
						JOptionPane.showMessageDialog(null, "Error Occured. Reboot system.");
					}
				}
			}
			);
		
		//adds the JButtons to the JPanel container
		guestContainer.add(firstButton);
		guestContainer.add(returningButton);
		
		//adds the JLabel and JPanel to the main frame
		guestFrame.add(selectLabel, BorderLayout.NORTH);
		guestFrame.add(guestContainer, BorderLayout.CENTER);
	}
}
