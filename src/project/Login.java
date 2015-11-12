package project;

/**
 * Login.java   
 * 
 * This class creates a frame wherein user can login with his/her
 * account information (e-mail and password) to access the
 * Reservation frame (ReservationView.java [KA]).
 *  
 * @author Candice Academia
 * Date: 6 December 2014
 * Semester Project - Krisandaish
 * CS151 - Sec 1 - MW 12:00pm - 13:15pm
 * San Jose State University
 */

import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Login 
{
	private JFrame loginFrame;				//main frame
	private Error errorMessage;				//error handling mechanism
	/**
	 * to preserve reference of the accountList reconstructed in LoadAccountFile.java
	 * and for error handling mechanism as well
	 */
	private AccountList accountList;
	private ArrayList<GuestAccount> list;	//preserve reference of the accountList
	private String email;					//user email
	private String password;				//user password
	
	/**
	 * CONSTRUCTOR:	This will create a Login interface.
	 * @param l	reference of the accountList reconstructed in LoadAccountFile.java.
	 * @throws IOException
	 */
	public Login (ArrayList<GuestAccount> l) throws IOException
	{
		int loginFrame_width = 300;
		int loginFrame_height = 200;
		int textField_width = 50;
		int textField_height = 10;
		
		/**
		 * saves the reference of the accountList reconstructe in LoadAccountFile.java
		 */
		list = l;
		
		/**
		 * Create instance of Error class for error handling
		 */
		errorMessage = new Error (list);
			
		/**
		 * Guest Login
		 */
		loginFrame = new JFrame ();
		loginFrame.setSize(loginFrame_width, loginFrame_height);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setTitle("Login");
		loginFrame.setLayout(new BorderLayout());
		loginFrame.setResizable(false);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setVisible(true);
		
		/**
		 * Create JPanel containers for the components of this frame
		 */
		//main container
		JPanel loginContainer = new JPanel();
		loginContainer.setSize(loginFrame_width, loginFrame_height);
		loginContainer.setLayout(new BorderLayout());
		
		//JLabel container
		JPanel labelContainer = new JPanel ();
		labelContainer.setSize(textField_width / 2, textField_height);
		labelContainer.setLayout(new GridLayout(2,1));
		//JTextField container
		JPanel textContainer = new JPanel ();
		textContainer.setSize(textField_width / 2, textField_height);
		textContainer.setLayout(new GridLayout(2,1));
		//JButton container
		JPanel buttonContainer = new JPanel ();
		buttonContainer.setLayout(new GridLayout (1,2));
				
		/**
		 * Creating JLabels to guide user account information needed to
		 * proceed to ReservationView.
		 */
		JLabel label_E = new JLabel ("E-mail", SwingConstants.CENTER);
		JLabel label_P = new JLabel ("Password", SwingConstants.CENTER);
		
		/**
		 * Creating JTextFields for user input
		 */
		final JTextField input_E = new JTextField ();
		input_E.setSize(textField_width, textField_height);
		input_E.addActionListener (new ActionListener () 
				{
					public void actionPerformed (ActionEvent e)
					{
						boolean emailNotFound = false;
						
						email = input_E.getText();
							
						for (int index = 0 ; index < list.size() ; index++)
						{
							if (email.equals(list.get(index).getEmail()))
							{
								index = list.size();
								emailNotFound = false;
							}
							else
								emailNotFound = true;
						}	
						if (emailNotFound)
						{
							loginFrame.setVisible(false);
							errorMessage.emailNotFound();
						}
					}
				});
		/**
		 * Creating JTextField for user input
		 */
		final JTextField input_P = new JPasswordField ();
		input_P.setSize(textField_width, textField_height);
		input_P.addActionListener (new ActionListener ()
				{
					public void actionPerformed (ActionEvent e)
					{
						//THIS PART! :'(
						boolean passwordIncorrect = false;
						int attempt = 4;
						
						loginFrame.setVisible(true);
						password = input_P.getText();
							
						for (int index = 0 ; index < list.size() ; index++)
							if (email.equals(list.get(index).getEmail()))
							{
								if (password.equals(list.get(index).getPassword()))
									passwordIncorrect = false;
								else
									passwordIncorrect = true;
								index = list.size(); //terminates the loop since e-mail is found
							}
	
						
						if (passwordIncorrect)
						{
							try 
							{
								errorMessage.passwordIncorrect();
							} 
							catch (IOException e1)
							{
								JOptionPane.showMessageDialog(null, "Error occurred. Reboot system.");
							}
						}
					}
				});
		
		/**
		 * Creating JButton
		 */
		JButton loginButton = new JButton ();
		loginButton.setText("Login");
		/**
		 * Add Actionlistener to the Login Button
		 */
		loginButton.addActionListener (new ActionListener ()
				{
					public void actionPerformed (ActionEvent event)
					{
                                            //System.out.println(email);
						/**
						 * if JTextFields are empty, do nothing
						 */
						if (email == null || password == null){}
						/**
						 * else, proceed to the next frame 
						 * [!] E-mail is found in database and Password is correct
						 * by this part is executed.
						 */
						else
						{
							//hide this frame before proceeding to the next
							loginFrame.setVisible(false);
							try
							{
                                                            /**
                                                             * [KA]
                                                             */
								ReservationList reservationList = new ReservationList();
                                                                RoomAvailability model = new RoomAvailability(reservationList);
                                                                ReservationsView frame = new ReservationsView(model);
                                                                frame.setGuestEmail(email);
                                                                model.attach(frame);
							}
							catch (FileNotFoundException e)
							{
								JOptionPane.showMessageDialog(null, "Error Occured. Reboot system.");
							}
						}
					}
				});
		
		/**
		 * Create JButton to return to the Hotel Reservation Main Menu
		 */
		JButton cancelButton = new JButton ();
		cancelButton.setText("Main Menu");
		cancelButton.addActionListener (new ActionListener ()
				{
					public void actionPerformed (ActionEvent event)
					{
						//hide this frame before proceeding to the next
						loginFrame.setVisible(false);
						try
						{
							/**
							 * Go back to the main menu by instantiating
							 * HotelReservationMainMenu.java
							 */
							new HotelReservationMainMenu (list);
						}
						catch (IOException i)
						{
							JOptionPane.showMessageDialog(null, "Error Occured. Reboot system.");
						}
					}
				});
		
		//add JButtons to the respective container
		buttonContainer.add(loginButton);
		buttonContainer.add(cancelButton);
		
		//add JLabels to the respective container		
		labelContainer.add(label_E);
		labelContainer.add(label_P);
		
		//add JTextFields to the respective container
		textContainer.add(input_E);
		textContainer.add(input_P);
		
		//add the respective component containers to the bigger container (JPanel)	
		loginContainer.add(labelContainer, BorderLayout.WEST);
		loginContainer.add(textContainer, BorderLayout.CENTER);
		loginContainer.add(buttonContainer, BorderLayout.SOUTH);
			
		//add the bigger container (JPanel) to the main frame
		loginFrame.add(loginContainer);
	}
}
