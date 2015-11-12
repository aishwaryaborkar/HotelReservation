package project;

import java.util.ArrayList;

/**
 * A SimpleReceipt includes user id, name, and reserved rooms and total amount due for THIS
 * particular reservation. 
 * @author Aishwarya
 *
 */

public class SimpleReceipt implements Receipt{

	@Override
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
