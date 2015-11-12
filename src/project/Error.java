package project;

/**
 * Error.java   
 * 
 * This class creates error messages for most of the error-handling
 * in this Hotel Reservation System.
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

public class Error
{
	private JFrame errorFrame;						//main frame
	/**
	 * accountList to preserve the reference of the accountList reconstructed
	 * in LoadAccoutnFile.java
	 */
	private ArrayList<GuestAccount> accountList;

	/**
	 * CONSTRUCTOR:	saves the reference of the accountList reconstructed in
	 * 				LoadAccountFile.java.
	 * @param list
	 */
	public Error (ArrayList<GuestAccount> list)
	{
		accountList = list;
	}
	
	/**
	 * emailNotFound method:	This method will display an error message
	 * 							with two options as solutions to the user.
	 */
	public void emailNotFound ()
	{
		/**
		 * Create JFrame
		 */
		errorFrame = new JFrame ();
		errorFrame.setSize(250,100);
		errorFrame.setTitle("[!] Error [!]");
		errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		errorFrame.setVisible(true);
		errorFrame.setResizable(false);
		errorFrame.setLocationRelativeTo(null);
	
		
		/**
		 * Creating JPanel as containers for all the other components
		 */
		JPanel errorContainer = new JPanel ();
		errorContainer.setSize(250,100);
		errorContainer.setLayout(new BorderLayout ());
		JPanel errorButtonContainer = new JPanel ();
		errorButtonContainer.setSize(200,50);
		errorButtonContainer.setLayout(new GridLayout (1,2));
				
		/**
		 * Create JLabel
		 */
		JLabel errorMessage = new JLabel ("E-mail is not found in database.", 
				SwingConstants.CENTER);
		errorMessage.setSize(250,50);
		
		/**
		 * Create JButton 
		 */
		final JButton createAccount = new JButton ();
		createAccount.setText("Create Account");
		createAccount.addActionListener(new ActionListener ()
				{
					public void actionPerformed (ActionEvent event) 
					{
						//hide this frame before proceeding to the next one
						errorFrame.setVisible(false);
						try
						{
							/**
							 * Create a new account by instantiating
							 * CreateAccount.java.
							 */
							new CreateAccount (accountList);
						} 
						catch (IOException e)
						{
							JOptionPane.showMessageDialog(null, "Error"
									+ " Occured. Reboot system.");
						}
					}
				});
		
		/**
		 * Create JButton
		 */
		final JButton tryAgain = new JButton ();
		tryAgain.setText("Try Again");
		tryAgain.addActionListener(new ActionListener ()
			{
				public void actionPerformed (ActionEvent event)
				{
					//hide frame before proceeding to the next one
					errorFrame.setVisible(false);
					try
					{
						/**
						 * Create a new Login frame by instantiating
						 * Login.java.
						 */
						new Login(accountList);
					}
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Error"
								+ " Occured. Reboot system.");
					}
				}
			});
		
		/**
		 * Adding the components to the JPanel containers
		 */
		errorButtonContainer.add(createAccount);
		errorButtonContainer.add(tryAgain);
		errorContainer.add(errorMessage, BorderLayout.NORTH);
		errorContainer.add(errorButtonContainer, BorderLayout.SOUTH);
		
		/**
		 * Adding the main container to the frame
		 */
		errorFrame.add(errorContainer);
	}
	
	/**
	 * emailFound method:	This method will prompt the user who is
	 * 						trying to create an account that the e-mail
	 * 						desired to use is already linked to an account
	 * 						in the account database (AccountList).
	 */
	public void emailFound ()
	{
		/**
		 * Create JFrame
		 */
		errorFrame = new JFrame ();
		errorFrame.setSize(200,50);
		errorFrame.setTitle("[!] Error [!]");
		errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setVisible(true);
		errorFrame.setResizable(false);
		
		/**
		 * Creating JPanel as containers for all the other components
		 */
		JPanel errorContainer = new JPanel ();
		errorContainer.setSize(300,200);
		errorContainer.setLayout(new BorderLayout ());

				
		/**
		 * Create JLabel
		 */
		JLabel errorMessage = new JLabel ("E-mail is already used by"
				+ " an account.", SwingConstants.CENTER);
		errorMessage.setSize(300,100);
		
		/**
		 * Create JButton with ActionListeners for options
		 */
		final JButton mainMenu = new JButton ();
		mainMenu.setText("Main Menu");
		mainMenu.addActionListener(new ActionListener ()
				{
					public void actionPerformed (ActionEvent event)
					{
						//hide frame before proceeding to the next one
						errorFrame.setVisible(false);
						try
						{
							/**
							 * Goes back to the main menu by instantiating
							 * HotelReservationMainMenu.java.
							 */
							new HotelReservationMainMenu (accountList);
						}
						catch (IOException e)
						{
							JOptionPane.showMessageDialog(null, "Error"
									+ " Occured. Reboot system.");
						}
					}
				});
				
		/**
		 * Adding the components to the JPanel containers
		 */
		errorContainer.add(errorMessage, BorderLayout.NORTH);
		errorContainer.add(mainMenu, BorderLayout.CENTER);
		
		/**
		 * Adding the main container to the frame
		 */
		errorFrame.add(errorContainer);
	}
	
	/**
	 * passwordIncorrect method:	This method will display an error
	 * 								message prompting the user that the 
	 * 								password is incorrect.
	 * @throws IOException
	 */
	public void passwordIncorrect () throws IOException
	{
		JOptionPane.showMessageDialog (null, "Password is incorrect.");
	}
	
	/**
	 * underAge method:	This method displays an error message prompting 
	 * 					the user that only 18 years and above are allowed
	 * 					to user this hotel reservation application.
	 */
	public void underAge ()
	{
		/**
		 * Creating a JFrame
		 */
		errorFrame = new JFrame ();
		errorFrame.setSize(200,100);
		errorFrame.setTitle("[!] Error [!]");
		errorFrame.setVisible(true);
		errorFrame.setResizable(false);
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * Creating a JPanel as a container of components
		 */
		JPanel errorContainer = new JPanel ();
		errorContainer.setSize (200, 100);
		errorContainer.setLayout(new BorderLayout());
		
		/**
		 * Creating a JTextArea for error message
		 */
		JTextArea errorMessage = new JTextArea ();
		errorMessage.setText("Only 18 years old and above can\nuse this hotel reservation\napplication.");
		errorMessage.setEditable(false);
		
		/**
		 * Creating a JButton to return to the main menu
		 */
		JButton backToMainMenu = new JButton ();
		backToMainMenu.setText("Go back to Main Menu");
		backToMainMenu.addActionListener(new ActionListener ()
				{
					public void actionPerformed (ActionEvent event)
					{
						//hides this frame before proceeding to the next one
						errorFrame.setVisible(false);
						try
						{
							/**
							 * Goes back to the main menu by instantiating 
							 * HotelReservationMainMenu.java.
							 */
							new HotelReservationMainMenu (accountList);
						}
						catch (IOException e)
						{
							JOptionPane.showMessageDialog(null, "Error Occured. Reboot system.");
						}
					}
				});
	
		//adds JLabel and JButtons to the container
		errorContainer.add(errorMessage, BorderLayout.NORTH);
		errorContainer.add(backToMainMenu, BorderLayout.SOUTH);
		
		//adds the JPanel container to the main frame
		errorFrame.add(errorContainer);
	}
	
	/**
	 * passwordTooLong method:	This method prompts the user that the password they entered is too long.
	 */
	public void passwordTooLong ()
	{
		JOptionPane.showMessageDialog(null,"Password is too long. Only 8 - 20 characters is allowed.");
	}
	
	/**
	 * passwordTooShort:	This method prompts the user that the password they entered is too short.
	 */
	public void passwordTooShort ()
	{
		JOptionPane.showMessageDialog(null,"Password is too short. Must be in between 8 - 20 characters.");
	}
	
	/**
	 * notManager method:	This method displays an error message if password
	 * 						is incorrect for the Manager frame to be accessed.
	 */
	public void notManager ()
	{
		/**
		 * Create new JFrame
		 */
		errorFrame = new JFrame ();
		errorFrame.setSize(200,100);
		errorFrame.setTitle("[!] Error [!]");
		errorFrame.setResizable(false);
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setVisible(true);
		errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * Create JPanel
		 */
		JPanel errorContainer = new JPanel ();
		errorContainer.setSize(200,100);
		errorContainer.setLayout(new BorderLayout ());
		
		/**
		 * Create JLabels
		 */
		JLabel errorMessageA = new JLabel ("[!] ACCESS DENIED [!]", SwingConstants.CENTER);
		JLabel errorMessageP = new JLabel ("Return to main menu.", SwingConstants.CENTER);
		
		
		/**
		 * Create JButton
		 */
		JButton backToMainMenu = new JButton ();
		backToMainMenu.setText("Main Menu");
		backToMainMenu.addActionListener(new ActionListener ()
				{
					public void actionPerformed (ActionEvent event)
					{
						//hides this frame before proceeding to the next one
						errorFrame.setVisible(false);
						try
						{
							/**
							 * Goes back to the main menu by instantiating
							 * HotelReservationMainMenu.java.
							 */
							new HotelReservationMainMenu (accountList);
						}
						catch (IOException e)
						{
							JOptionPane.showMessageDialog(null, "Error Occured. Reboot system.");
						}
					}
				});
		
		//adds the Jlabels and JButton to the container
		errorContainer.add(errorMessageA, BorderLayout.NORTH);
		errorContainer.add(errorMessageP, BorderLayout.CENTER);
		errorContainer.add(backToMainMenu, BorderLayout.SOUTH);
		
		//adds the container to the main frame
		errorFrame.add(errorContainer);
	}
}

