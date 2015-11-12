package project;

import java.util.ArrayList;
/**
 * The Receipt and the classes that implement it (SimpleReceipt and ComprehensiveReceipt) are implemented in the
 * Strategy pattern that allows the user to choose which type of receipt he would like to keep for his records. 
 * A concrete class (ReservationsView) then takes Receipt as a parameter in a method that is invoked when the user
 * clicks "TransactionDone" after making his reservation.
 * @author Aishwarya Borkar
 *
 */
public interface Receipt 
{	
	/**
	 * Formats the Guest's name and email.
	 * @param guest current GuestAccount
	 */
	void formatGuestInfo(GuestAccount guest);
	/**
	 * Formats the single transaction (SimpleReceipt) or history of transactions (ComprehensiveReceipt). 
	 * @param guest current GuestAccount making Reservation
	 * @param reservations
	 */
	void formatReservationInfo(GuestAccount guest, ArrayList<Reservation> reservations);
}
