package project;

import java.util.ArrayList;

/**
 * Transaction class for hotel reservation system.
 * @author Kristani
 */
public class Transaction {
    
    private double totalAmount;
    private ArrayList<Reservation> reservations;
    
    public Transaction(){
        reservations = new ArrayList<Reservation>();
    }
    
    public void addReservation(Reservation newReserv){
        reservations.add(newReserv);
        totalAmount += newReserv.getCost();
    }
    
    public double getTotalAmount(){
        return totalAmount;
    }
}