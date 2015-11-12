package project;

/**
 * CreateAccount.java    
 * 
 * This class creates a frame wherein user can create an account
 * to proceed in the Hotel Reservation System.
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - MW 12:00pm - 13:15pm
 * San Jose State University
 */

import java.awt.*; 
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class CreateAccount 
{
	private JFrame createAccountFrame;		//main frame
	private Error errorMessage;				//error handling mechanism
	//to preserve accountList reference and for error handling mechanism
	private AccountList accountList;		
	private ArrayList<GuestAccount> list;	//preserve accountList reference
	private String n;						//name
	private String p;						//password
	private String eAdd;					//e-mail address
	private int a;							//age
	private boolean ageAllowed;				//age > 18
	private boolean passwordAccepted;		//password is correct

	
	/**
	 * CONSTRUCTOR:	This creates the frame to create an account by instantiating
	 * 				CreateAccount.java. 
	 * @param l reference to the reconstructed accountList in LoadAccountFile.java
	 * @throws IOException
	 */
	public CreateAccount (ArrayList<GuestAccount> l) throws IOException
	{
		/**
		 * Saves the reference of the reconstructed accountList in LoadAccountFile.java
		 */
		list = l;
		
		/**
		 * Create Error class instance for error handling
		 */
		errorMessage = new Error (list);
		
		/**
		 * Create frame for this interface
		 */
		createAccountFrame = new JFrame ();
		createAccountFrame.setEnabled(true);
		createAccountFrame.setSize(300,300);
		createAccountFrame.setTitle("Create an account");
		createAccountFrame.setResizable(false);
		createAccountFrame.setLocationRelativeTo(null);
		createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createAccountFrame.setVisible(true);
		
		/**
		 * Create JPanel as containers for the components in
		 * making an account
		 */
		JPanel createAccountContainer = new JPanel ();
		createAccountContainer.setSize(300,300);
		createAccountContainer.setLayout(new BorderLayout());
		JPanel createAccountLabels = new JPanel ();
		createAccountLabels.setLayout(new GridLayout(4,1));
		JPanel createAccountTFields = new JPanel ();
		createAccountTFields.setLayout(new GridLayout(4,1));
		final JPanel createAccountButtons = new JPanel ();
		createAccountButtons.setLayout(new GridLayout(1,2));
		
		/**
		 * Create JLabels for account information
		 * (*will need to collaborate with Account class*)
		 */
		JLabel name = new JLabel ("Name: ", SwingConstants.RIGHT);
		JLabel age = new JLabel ("Age: ", SwingConstants.RIGHT);
		JLabel email = new JLabel ("E-mail: ", SwingConstants.RIGHT);
		JLabel password = new JLabel ("Password: ", SwingConstants.RIGHT);
		
		/**
		 * Create Text Fields for user input with ActionListeners
		 */
		final JTextField field_name = new JTextField ();
		field_name.addActionListener (new ActionListener()
				{
					public void actionPerformed (ActionEvent e)
					{
						n = field_name.getText();
					}
				});
		final JTextField field_age = new JTextField ();
		field_age.setText("Must be 18 years or older.");
		field_age.addActionListener (new ActionListener ()
				{
					public void actionPerformed (ActionEvent e)
					{
						a = Integer.parseInt(field_age.getText());
						if (a < 18)
						{
							ageAllowed = false;
							createAccountFrame.setVisible(false);
							errorMessage.underAge();
						}
						else
							ageAllowed = true;
					}
				});
		final JTextField field_password = new JTextField ();
		field_password.setText("Must be 8 - 20 characters.");
		field_password.addActionListener (new ActionListener ()
				{
					public void actionPerformed (ActionEvent e)
					{
						p = field_password.getText();
						if (p.length() > 20)
						{
							errorMessage.passwordTooLong();
							passwordAccepted = false;
						}
						else if (p.length() < 8)
						{
							errorMessage.passwordTooShort();
							passwordAccepted = false;
						}
						else
							passwordAccepted = true;						
					}
				});
		final JTextField field_email = new JTextField ();
		field_email.addActionListener (new ActionListener ()
				{
					public void actionPerformed (ActionEvent e)
					{
						eAdd = field_email.getText();
						
						for (int index = 0 ; index < list.size() ; index++)
							if (eAdd.equals(list.get(index).getEmail()))
							{
								index = list.size();
								createAccountFrame.setVisible(false);
								errorMessage.emailFound();
							}
					}
				});
		
		/**
		 * Creating JButton createAccount for creating new accounts
		 */
		JButton createAccount = new JButton ();
		createAccount.setSize(200,100);
		createAccount.setText("Create");
		createAccount.addActionListener (new ActionListener ()
				{
					public void actionPerformed (ActionEvent event)
					{
						/**
						 * if the JTextFields are left blank, do nothing
						 */
						if (n == null || p == null || eAdd == null);
						/**
						 * else, proceed to the next frame
						 */
						else
						{
						
							/**
							 * Creates a GuestAccount with the user inputs for 
							 * name (n), password (p), email (eAdd) and age (a).
							 */
							GuestAccount createdAccount = new GuestAccount(n, p, eAdd, a);
							//adds the newly created account to the accountList
							list.add(createdAccount);
							//hides this frame before proceeding to the next frame
							createAccountFrame.setVisible(false);
							try
							{
								/**
								 * This saves all the accounts found in accountList including
								 * the newly created one.
								 */
								new SaveAccountList (list);
							
								/**
								 * [KA] After saving accountList to account.txt, proceeds to the
								 * Reservation frame by instantiating ReservationView.
								 */
                                                                ReservationList reservationList = new ReservationList();
                                                                RoomAvailability model = new RoomAvailability(reservationList);
                                                                ReservationsView frame = new ReservationsView(model);
                                                                frame.setGuestEmail(eAdd);
                                                                model.attach(frame);
								//new ReservationView(list);
							}
							catch (IOException i)
							{
								JOptionPane.showMessageDialog(null, "Error Occured. Reboot "
									+ "system.");
							}
						}
					}
				});
		
		/**
		 * Creating a JButton cancel if the user changes their mind and decides not to create a 
		 * new account and return to the main menu HotelReservationMainMenu.java
		 */
		JButton cancel = new JButton ();
		cancel.setSize(50,100);
		cancel.setText("Cancel");
		cancel.addActionListener (new ActionListener ()
				{
					public void actionPerformed (ActionEvent e)
					{
						//re-initializes the Account fields in this class
						n = "";	
						p = "";
						a = 0;
						eAdd = "";
						//hides this frame before proceeding to the next one
						createAccountFrame.setVisible(false);
						try
						{
							/**
							 * Creates a frame of the Hotel Reservation System's
							 * main menu by instantiating HotelReservationMainMenu.java
							 * while passing the reference of the accountList.
							 */
							new HotelReservationMainMenu (list);
						}
						catch (IOException i)
						{
							JOptionPane.showMessageDialog(null, "Error Occured. Reboot system.");
						}
					}
				});
		/**
		 * adding the components to their corresponding containers and then to the main frame
		 */
		//adds JLabels to their respective container
		createAccountLabels.add(name);
		createAccountLabels.add(age);
		createAccountLabels.add(email);
		createAccountLabels.add(password);
		//adds JTextFields to their respective container
		createAccountTFields.add(field_name);
		createAccountTFields.add(field_age);
		createAccountTFields.add(field_email);
		createAccountTFields.add(field_password);
		//adds JButtons to their respective container
		createAccountButtons.add(createAccount);
		createAccountButtons.add(cancel);
		//adds these containers to a bigger container (JPanel)
		createAccountContainer.add(createAccountLabels, BorderLayout.WEST);
		createAccountContainer.add(createAccountTFields, BorderLayout.CENTER);
		createAccountContainer.add(createAccountButtons, BorderLayout.SOUTH);
			
		//adds the bigger container (JPanel) to the main frame
		createAccountFrame.add(createAccountContainer);
	}
}
