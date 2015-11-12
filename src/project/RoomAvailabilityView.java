package project;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * RoomAvailabilityView is instantiated each time Manager clicks on a Date to view and shows
 * which rooms are available and which ones aren't.
 * @author Aishwarya Borkar 
 *
 */
public class RoomAvailabilityView implements ChangeListener{
	
	private ReservationList reservations; 
	private ArrayList<Room> rooms; 
	private String roomStatus; 
	private RoomAvailability r; 
	private JTextArea roomInfo; 
	
	public RoomAvailabilityView(RoomAvailability ra) 
	{
		reservations = ra.getReservationList();
		rooms = ra.getRoomList();
		 
		r = ra; 
		roomInfo = new JTextArea();
		
		//Divide roomInfo into 2 parts: 
		//1) Available rooms - # of rooms available, type of rooms (economy or luxury)
		//2) Booked rooms - info of guest who booked the room, type of rooms (economy or luxury)
		
		ra.attach(this);
		
		final JFrame frame = new JFrame("Manager View");
		frame.add(roomInfo);
		
		frame.setSize(300,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) 
	{
		for(Room r: rooms)
		{
			if ( r.getAvailable())
			{
				System.out.println("hi");
				roomInfo.setText("Room ($"  + r.getRate() + ")"+ r.getRoomNumber() + "is available."); 
			}
			else
			{
				roomInfo.setText("Room ($"  + r.getRate() + ")"+ r.getRoomNumber() + "is unavailable.");
			}
		}
		
	}
	


}
