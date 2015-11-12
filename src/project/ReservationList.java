package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Reservation list of hotel reservation system. Loads and updates a
 * reservation.txt file.
 *
 * 
 * CS151 - Section 1
 * Team Krisandaish
 * @author Kristani Alcantara
 */
public class ReservationList {

    private ArrayList<Reservation> list;
    private File f;
    private PrintWriter w;

    /**
     * Constructor for ReservationList. Initializes using the reservations.txt
     * file. Populates array list of reservations using the file.
     * @throws FileNotFoundException 
     */
    public ReservationList() throws FileNotFoundException {
        list = new ArrayList<Reservation>();
        f = new File("reservations.txt");
        
        Scanner in = new Scanner(f);
        Reservation r = new Reservation();

        if (f.length() != 0) {
            while (in.hasNextLine()) {
                boolean complete = false;
                String s = in.nextLine();
                Scanner lineScanner = new Scanner(s);
                lineScanner.useDelimiter("[^0-9]+");
                if (s.contains("Room")) {
                    int n = Integer.parseInt(lineScanner.next());
                    r.setRoomNumber(n);
                } else if (s.contains("Guest")) {
                    String email = s.substring(14);
                    r.setGuestEmail(email);
                } else if (s.contains("Check-in")) {
                    Calendar checkInDate = Calendar.getInstance();
                    int month = Integer.parseInt(lineScanner.next());
                    month--;
                    int day = Integer.parseInt(lineScanner.next());
                    int year = Integer.parseInt(lineScanner.next());
                    checkInDate.set(year, month, day);
                    r.setCheckInDate(checkInDate);
                } else if (s.contains("Check-out")) {
                    Calendar checkOutDate = Calendar.getInstance();
                    int month = Integer.parseInt(lineScanner.next());
                    month--;
                    int day = Integer.parseInt(lineScanner.next());
                    int year = Integer.parseInt(lineScanner.next());
                    checkOutDate.set(year, month, day);
                    r.setCheckOutDate(checkOutDate);
                } else if (s.contains("Cost")) {
                    double cost = Double.parseDouble(lineScanner.next());
                    r.setCost(cost);
                    complete = true;
                }
                if(complete){
                    list.add(r);
                    
                    r = new Reservation();
                }
            }
        }
        
        in.close();
    }
    
    /**
     * Gets the reservation in the reservation array list
     * @param i index of array list
     * @return the reservation at index i
     */
    public Reservation get(int i){
        return list.get(i);
    }
    
    /**
     * Returns the size of the array list
     * @return the size of the array list
     */
    public int size(){
        return list.size();
    }

    /**
     * Adds a reservation to the reservation array list
     * @param r the new reservation to be added
     * @throws FileNotFoundException 
     */
    public void addReservation(Reservation r) throws FileNotFoundException {
        list.add(r);
        updateFile();
    }

    /**
     * Cancels a reservation from the reservation array list    
     * @param r the reservation to be cancelled
     * @throws FileNotFoundException 
     */
    public void cancelReservation(Reservation r) throws FileNotFoundException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(r)) {
                list.remove(i);
            }
        }
        updateFile();
    }
    
    /**
     * Returns if the reservation array list is empty
     * @return true is empty, false if not empty
     */
    public boolean isEmpty(){
        if(f.length() == 0){
            return true;
        }
        return false;
    }

    /**
     * Gets the reservation array list
     * @return the reservation array list
     */
    public ArrayList<Reservation> getReservationList() {
        return (ArrayList<Reservation>) list.clone();
    }

    /**
     * Updates the reservation.txt file
     * @throws FileNotFoundException 
     */
    public void updateFile() throws FileNotFoundException {
        w = new PrintWriter("reservations.txt");
        
            for (int i = 0; i < list.size(); i++) {
                Reservation r = list.get(i);
                w.println("Room " + r.getRoomNumber());
                w.println("Guest e-mail: " + r.getGuestEmail());
                w.println("Check-in date: " + r.getCheckInDate());
                w.println("Check-out date: " + r.getCheckOutDate());
                w.println("Cost: " + r.getCost());
            }
        w.close();
    }
}
