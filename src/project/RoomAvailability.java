package project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Room availability of hotel reservation system
 *
 * MODEL of the required MVC pattern
 *
 * @author Kristani
 */
public class RoomAvailability {

    private ArrayList<Room> roomList;
    private ArrayList<Room> viewData;
    private ArrayList<Room> occupiedRooms; 
    private ReservationList reservations;
    private final ArrayList<ChangeListener> listeners;
    

    /**
     * Constructor that is initialized using a ReservationList to populate
     * the proper data structures
     * 
     * @param reservations the reservation list
     */
    public RoomAvailability(ReservationList reservations) {
        this.reservations = reservations;
        roomList = new ArrayList<>();
        viewData = new ArrayList<>();
        listeners = new ArrayList<>();
        occupiedRooms = new ArrayList<>();
        //Economy rooms
        for(int i = 0; i < 10; i++){
            roomList.add(new Room(100 + i, 80));
        }
        
        //Luxury rooms
        for(int i = 0; i < 10; i++){
            roomList.add(new Room(200 + i, 200));
        }
        
        int k = 0;
        while(k < reservations.size()){
            for(int i = 0; i < roomList.size(); i++){
                if(reservations.get(k).getRoomNumber() == roomList.get(i).getRoomNumber()){
                    roomList.get(i).addReservation(reservations.get(k));
                }
            }
            k++;
        }
    }

    /**
     * Updates the view list of available rooms
     * 
     * @param checkIn check-in date
     * @param checkOut check-out date
     * @param type room type
     */
    public void updateViewData(Calendar checkIn, Calendar checkOut, double type) {
        viewData.clear();

        for (int i = 0; i < roomList.size(); i++) {//traverses roomList
            Room room = roomList.get(i); //<---room to be added to the available rooms list
            if (room.noReservations()) {
                viewData.add(roomList.get(i));
                for (ChangeListener l : listeners) {
                    l.stateChanged(new ChangeEvent(this));
                }
            } else { //if a room has reservations, check each reservation for conflicting dates
                //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
                
                boolean flag = false;
                
                for (int j = 0; j < room.reservations.size(); j++) {//traverses room's list of reservations
                        Reservation r = room.reservations.get(j);
                        
                            if((checkIn.before(r.checkInDate) || checkIn.after(r.checkOutDate) )
                                && (checkOut.before(r.checkInDate) || checkOut.after(r.checkOutDate)) && 
                                (checkIn.compareTo(r.checkInDate) != 0 && checkOut.compareTo(r.checkOutDate) != 0)){
                            flag = true; //okay to add
                        }else{
                            flag = false;
                        }
                }
                if(flag){
                    viewData.add(room);
                    for (ChangeListener l : listeners) {
                        l.stateChanged(new ChangeEvent(this));
                    }
                }
            }
        }
        if (type == 0) {
            viewData.clear();
        } 
        else {
            for (int k = 0; k < viewData.size(); k++) {
                double temp = viewData.get(k).getRate();
                if (temp != type) {
                    System.out.println("viewData rate: " + viewData.get(k).getRate());
                    System.out.println("Type: " + type);
                    viewData.remove(k);
                }
            }
            for (ChangeListener l : listeners) {
                l.stateChanged(new ChangeEvent(this));
            }
        }
    }

     
    
    /**
     * Method that allows the Manager to view room availability by date. 
     * @param date date that Manager chooses to view 
     * @author Aishwarya Borkar 
     */
    public void updateViewByDay(Calendar date)
    {
    	String roomInformation = "";
    	for (int i = 0; i < roomList.size(); i++) 
    	{
    		Room room = roomList.get(i); 
    		
    		for (int j = 0; j < room.reservations.size(); j++) 
    		{
    			
    			Reservation r = room.reservations.get(j);
    			
    			if ( r.occupied(date))
    			{
    				room.setAvailable(false);
    				  for (ChangeListener l : listeners) {
		                    l.stateChanged(new ChangeEvent(this));
		                }
    				 String email = r.getGuestEmail();
    				AccountList al = new AccountList();
    				
    				if ( al.accountFoundInList(email))
    				{
    					roomInformation = "Guest " + email + "Price: " + room.getRate() + "Room number: " + room.getRoomNumber(); 
    				
    				}
    			}
    			else
    			{
    				room.setAvailable(true);
    				  for (ChangeListener l : listeners) {
  	                    l.stateChanged(new ChangeEvent(this));
  	                }
    				
    			
    			}
    		}

    		 
    	}
    	
    
      
    }
    
    /**
     * Gets the ReservationList
     * @return the ReservationList
     */
    public ReservationList getReservationList(){
        return reservations;
    }
    
    /**
     * Gets the array list containing all rooms
     * @return the room array list
     */
    public ArrayList<Room> getRoomList(){
        return (ArrayList<Room>) roomList.clone();
    }
    
    /**
     * Gets the view array list of rooms
     * @return the view array list
     */
    public ArrayList<Room> getViewData(){
        return (ArrayList<Room>) viewData.clone();
    }

    /**
     * Updates the listeners
     * @param c ChangeListener
     */
    public void attach(ChangeListener c) {
        listeners.add(c);
    }
}
