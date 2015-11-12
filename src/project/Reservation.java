package project;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Reservation class for hotel reservation system.
 * 
 * CS151 - Section 1
 * Team Krisandaish
 * @author Kristani Alcantara
 */
public class Reservation {
    
    private int roomNumber;
    private String guestEmail;
    private int duration;
    private double cost;
    public Calendar checkInDate, checkOutDate;
    
    /**
     * Constructor for the Reservation. Initializes instance variables
     */
    public Reservation(){
        checkInDate = Calendar.getInstance();
        checkOutDate = Calendar.getInstance();
        guestEmail = "";
    }
    
    public boolean occupied(Calendar date)
    {
    	if( date.compareTo(checkInDate) > 0 && date.compareTo(checkOutDate) < 0 )
    	{
    		return true; 
    	}
    	
    	return false; 
    }
    
    /**
     * Sets the check-in date of the reservation
     * @param checkInDate the new check-in date
     * @precondition checkInDate must be a Calendar object
     * @postcondition checkInDate is set to the new one
     */
    public void setCheckInDate(Calendar checkInDate){
        this.checkInDate = checkInDate;
    }
    
    /**
     * Get the check-in date as a string
     * @return the check-in date
     */
    public String getCheckInDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        return sdf.format(checkInDate.getTime());
    }
    
    /**
     * Sets the check-out date of the reservation
     * @param checkOutDate the new check-in date
     * @precondition checkOutDate must be a Calendar object
     * @postcondition checkOutDate is set to the new one
     */
    public void setCheckOutDate(Calendar checkOutDate){
        this.checkOutDate = checkOutDate;
    }
    
    /**
     * Get the check-out date as a string
     * @return the check-in date
     */
    public String getCheckOutDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        return sdf.format(checkOutDate.getTime());
    }
    
    /**
     * Sets the guest email for this reservation
     * @param e the guest email
     * @precondition e must be a string
     * @postcondition guestEmail is updated
     */
    public void setGuestEmail(String e){
        guestEmail = e;
    }
    
    /**
     * Gets the guest's email
     * @return the guest's email
     */
    public String getGuestEmail(){
        return guestEmail;
    }
    
    /**
     * Sets the duration of the reservation
     * @param d the duration
     */
    public void setDuration(int d){
        duration = d;
    }
    
    /**
     * Gets the duration of the reservation by counting how many days are in
     * between the check-in and check-out date
     * @return the duration
     */
    public int getDuration(){
        Calendar temp = (Calendar) checkInDate.clone();
        while(temp.before(checkOutDate)){
            temp.add(Calendar.DAY_OF_MONTH, 1);
            duration++;
            System.out.println("Duration:" + duration);
        }
        
        return duration;
    }
    
    /**
     * Sets the room number of the reservation
     * @param n the room number
     * 
     */
    public void setRoomNumber(int n){
        roomNumber = n;
    }
    
    /**
     * Gets the room number of the reservation
     * @return the room number
     */
    public int getRoomNumber(){
        return roomNumber;
    }
    
    /**
     * Sets the total cost of the reservation
     * @param cost the total cost
     */
    public void setCost(double cost){
        this.cost = cost;
    }
    
    /**
     * Gets the total cost of the reservation
     * @return the total cost
     */
    public double getCost(){
        return cost;
    }
    
    /**
     * Returns a string format of the reservation information
     * @return reservation information
     */
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
        return "Room " + roomNumber + ": " + sdf.format(checkInDate.getTime())
                + " - " + sdf.format(checkOutDate.getTime()) + ", $" + cost
                + "\n reserved by " + guestEmail;
    }
}
