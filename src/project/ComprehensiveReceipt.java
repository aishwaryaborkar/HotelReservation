package project;

import java.util.ArrayList;

/**
 * A ComprehensiveReceipt includes user id, name, and ALL valid reservations made by this user
 * with corresponding total amount due.
 * @author Aishwarya
 *
 */

public class ComprehensiveReceipt implements Receipt {

	public void formatGuestInfo(GuestAccount guest) 
	{
		System.out.println("Name: " + guest.getName() + "\n" + 
						   "Email: " + guest.getEmail());
		
	}

	@Override
	public void formatReservationInfo(GuestAccount guest,
			ArrayList<Reservation> reservations) {
		// TODO Auto-generated method stub
		
	}

}
