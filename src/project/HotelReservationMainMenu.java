package project;

/**
 * HotelReservationMainMenu.java  
 * 
 * This class serves as the main menu for our hotel reservation 
 * implementation. 
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

public class HotelReservationMainMenu
{
	private ArrayList<GuestAccount> accountList;	//keep track of the accountList
	private JFrame userTypeFrame;					//main frame of this class
	
	/**
	 * CONSTRUCTOR:	This is the constructor of the HotelReservationMainMenu.
	 * 				There are no methods. It basically creates the interface
	 * 				of our hotel reservation main menu.
	 * 				[Interface for the main menu is created every time this
	 * 				class is instantiated.]
	 * @param list	reference of the accountList reconstructed from account.txt
	 * @throws IOException	
	 */
	public HotelReservationMainMenu (ArrayList<GuestAccount> list) throws IOException
	{
		int userTypeFrame_width = 400;	
		int userTypeFrame_height = 100;
		int userButton_width = 100;
		int userButton_height = 50;
		
		/**
		 * Keeps track of the AccountList<GuestAccount> accountList for the entire
		 * implementation (specifically for the error handling, loading and saving
		 * to file.
		 */
		accountList = list;
				
		/**
		 * User Type Screen
		 */
		userTypeFrame = new JFrame ();
		userTypeFrame.setVisible(true);
		userTypeFrame.setSize(userTypeFrame_width, userTypeFrame_height);
		userTypeFrame.setLocationRelativeTo(null);
		userTypeFrame.setTitle("Hotel Reservation System");
		userTypeFrame.setLayout(new BorderLayout());
		userTypeFrame.setResizable(false);
		userTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * Creted a JPanel as a container for the other components
		 */
		JPanel userTypeContainer = new JPanel();
		userTypeContainer.setSize(userTypeFrame_width, userTypeFrame_height);
		userTypeContainer.setLayout(new BorderLayout ());
		
		/**
		 * Creates a JLabel that has a centralized text as a welcoming 
		 * greeting to the user.
		 */
		JLabel userTypeLabel = new JLabel ("Welcome! Please select user type to proceed."
				, SwingConstants.CENTER);
		userTypeLabel.setSize(userButton_width * 2, userButton_height);
		
		/**
		 * Creates a JPanel that serves as the container for our userTypeLabel
		 */
		JPanel labelContainer = new JPanel ();
		labelContainer.setSize(userButton_width * 2, userButton_height);

		//adds the JLabel userTypeLabel to the JPanel labelContainer
		labelContainer.add(userTypeLabel);
		
		/**
		 * Creates a JPanel that serves as the container for JButtons:
		 * guestButton and managerButton.
		 */
		JPanel buttonContainer = new JPanel ();
		buttonContainer.setSize(userButton_width * 2, userButton_height);
		buttonContainer.setLayout(new GridLayout(1,2));
		
		/**
		 * Creates a JButton to proceed to the "Guest" frame implemented by
		 * Guest.java.
		 */
		JButton guestButton = new JButton ();
		guestButton.setSize(userButton_width, userButton_height);
		guestButton.setText("Guest");
		/**
		 * ActionListener for the "Guest" user type
		 */
		guestButton.addActionListener(new ActionListener ()
			{
				public void actionPerformed (ActionEvent event)
				{
					userTypeFrame.setVisible(false);
					try {
						/**
						 * Proceeds to the "Guest" frame
						 */
						new Guest(accountList);
					}
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Error occurred. Reboot system.");
					}
				}
			}
			);
		
		/**
		 * Creates a JButton to proceed to the "Manager" frame implemented by
		 * Manager.java
		 */
		JButton managerButton = new JButton ();
		managerButton.setSize(userButton_width, userButton_height);
		managerButton.setText("Manager");
		/**
		 * ActionListener for the 'Manager' user type
		 */
		managerButton.addActionListener(new ActionListener ()
			{
				public void actionPerformed (ActionEvent event)
				{	
					/**
					 * Create ManagerAccount object to verify if user is
					 * indeed a "Manager".
					 */
					ManagerAccount manager = new ManagerAccount ();
					
					/**
					 * Create Error object to handle any errors
					 */
					Error errorMessage = new Error (accountList);
					
					String passwordInput = "";	//user input
					
					/**
					 * do-while #1:	will keep on iterating until a password is entered.
					 */
					do
					{		
						passwordInput = JOptionPane.showInputDialog("Enter password to "
								+ "confirm user is a manager.");
					
					}while (passwordInput == null);
					
					/**
					 * if password is correct, next frame will be displayed which is 
					 * the ManagerView.
					 */
					if (passwordInput.equals(manager.getPassword()))
					{
						userTypeFrame.setVisible(false);
						/**
						 * [AB] <name of your class> to proceed to the Manager frame
						 */
						try {
							new ManagerView(new ManagerCalendar(), new RoomAvailability(new ReservationList()));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	//Ash's ManagerView
					}
					else
					{
						userTypeFrame.setVisible(false);
						errorMessage.notManager();
					}
						
				}
			}
			);
		
		//add the JButtons to the buttonContainer
		buttonContainer.add(guestButton);
		buttonContainer.add(managerButton);
		
		//adds the containers for the JLabel and JButtons to the main frame
		userTypeFrame.add(labelContainer, BorderLayout.NORTH);
		userTypeFrame.add(buttonContainer, BorderLayout.SOUTH);
	}
}

