package project;


import java.util.ArrayList;

/**
 * Room for hotel reservation system.
 * 
 * CS151 - Section 1
 * Team Krisandaish
 * @author Kristani Alcantara
 */
public class Room {
    
	private boolean available; 
    private int roomNumber;
    private double rate;
    public ArrayList<Reservation> reservations;
    
    /**
     * Constructs a Room
     * 
     * @param roomNumber the room number
     * @param rate the cost per night
     */
    public Room(int roomNumber, double rate){
        this.roomNumber = roomNumber;
        this.rate = rate;
        available = true; 
        reservations = new ArrayList<>();
    }
    
    public boolean getAvailable()
    {
    	return available; 
    }
    
    public void setAvailable (boolean b)
    {
    	available = b; 
    }
    
    /**
     * Gets the room number
     * @return the room number
     */
    public int getRoomNumber(){
        return roomNumber;
    }
    
    /**
     * Sets the rate of this room
     * @param newRate new rate
     */
    public void setRate(double newRate){
        rate = newRate;
    }
    
    /**
     * Gets the rate of the room
     * @return the rate
     */
    public double getRate(){
        return rate;
    }
    
    /**
     * Adds a reservation to the room's list of reservations
     * @param r the new reservation
     */
    public void addReservation(Reservation r){
        reservations.add(r);
    }
    
    /**
     * Gets the array list of reservations
     * @return the array list of reservations
     */
    public ArrayList<Reservation> getReservations(){
        return (ArrayList<Reservation>) reservations.clone();
    }
    
    /**
     * Sets the reservation array list
     * @param r the new reservation array list
     */
    public void setReservationList(ArrayList<Reservation> r){
        reservations = r;
    }
    
    /**
     * Checks if the reservation list of this room is empty
     * @return true if empty, false if not empty
     */
    public boolean noReservations(){
        if(reservations.isEmpty()){
            return true;
        }
        return false;
    }
    
    /**
     * Returns a string of the room's information
     * @return a string of the room's information
     */
    public String toString(){
        return "Room " + roomNumber + " $" + rate;
    }
}
