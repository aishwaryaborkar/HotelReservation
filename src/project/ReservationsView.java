package project;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Reservation UI of hotel registration system.
 *
 * VIEW of the required MVC pattern
 *
 * Also contains CONTROLLERS of required MVC pattern. CONTROLLERS: check-in,
 * check-out, and confirm functionalities that change the room availability
 * (model).
 *
 * 
 * CS151 - Section 1
 * Team Krisandaish
 * @author Kristani Alcantara
 */
public class ReservationsView extends JFrame implements ChangeListener {

    private RoomAvailability model;
    private ArrayList<Room> roomList;
    private DefaultListModel data;
    private JList listView;
    private Calendar checkInDate;
    private Calendar checkOutDate;
    private double rate;
    private ReservationList reservationList;
    private String guestEmail;

    /**
     * Constructs reservation view of GUI
     * @param model data that the view is updated from
     * @throws FileNotFoundException 
     */
    public ReservationsView(RoomAvailability model) throws FileNotFoundException {
        this.model = model;
        roomList = new ArrayList<>();
        //fieldList = new ArrayList<>();
        data = new DefaultListModel();
        listView = new JList(data);
        checkInDate = Calendar.getInstance();
        checkOutDate = Calendar.getInstance();
        reservationList = new ReservationList();
        this.guestEmail = guestEmail;
        
        defaultMenu();
    }
    
    /**
     * Sets the guest's email
     * @param email the new email
     * @precondition email is a string
     * @postcondition email is set to the new email
     */
    public void setGuestEmail(String email){
        guestEmail = email;
    }

    /**
     * Displays the default reservation menu of the GUI.
     * User can make a reservation or view/cancel a reservation
     */
    public void defaultMenu() {
        setTitle("Reservation Menu");
        setSize(425, 100);
        setLocationRelativeTo(null);
        setLayout(new GridLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JButton makeButton = new JButton();
        JButton viewCancelButton = new JButton();

        makeButton.setText("Make a Reservation");
        viewCancelButton.setText("View/Cancel a Reservation");

        add(makeButton);
        add(viewCancelButton);

        makeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(makeButton);
                remove(viewCancelButton);
                makeReservation();
            }
        });

        viewCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(makeButton);
                remove(viewCancelButton);
                try {
                    viewCancelReservation();
                } catch (FileNotFoundException ex) {
                    
                }
            }
        });
    }

    /**
     * GUI for making a reservation
     */
    public void makeReservation() {
        setTitle("Make a Reservation");
        setSize(350, 450);
        setLayout(new BorderLayout());
        
        //selectionPanel: check-in, check-out, room type components
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridLayout(0, 1, 0, 5));

        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new GridLayout(0, 2));
        
        JLabel checkIn = new JLabel();
        checkIn.setText("Check-in: MM/DD/YYY");
        JTextField checkInField = new JTextField();
        
        JLabel checkOut = new JLabel();
        checkOut.setText("Check-out: MM/DD/YYYY");
        JTextField checkOutField = new JTextField();
        
        
        checkPanel.add(checkIn);
        checkPanel.add(checkOut);
        checkPanel.add(checkInField);
        checkPanel.add(checkOutField);

        JPanel roomTypePanel = new JPanel();
        roomTypePanel.setLayout(new GridLayout(0, 3, 20, 0));
        
        JLabel roomType = new JLabel();
        roomType.setText("Room Type:");

        //Room type buttons
        JToggleButton econButton = new JToggleButton();
        econButton.setText("$80");
        JToggleButton luxButton = new JToggleButton();
        luxButton.setText("$200");

        roomTypePanel.add(roomType);
        roomTypePanel.add(econButton);
        roomTypePanel.add((luxButton));

        selectionPanel.add(checkPanel);
        selectionPanel.add(roomTypePanel);

        add(selectionPanel, BorderLayout.NORTH);

        //bottomPanel: available rooms list, confirm button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        JLabel availRooms = new JLabel();
        availRooms.setText("Available Rooms:");
        
        
        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        JScrollPane roomPane = new JScrollPane(listView);
        roomPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton confirm = new JButton();
        confirm.setText("Confirm");

        bottomPanel.add(availRooms, BorderLayout.NORTH);
        bottomPanel.add(roomPane, BorderLayout.CENTER);
        bottomPanel.add(confirm, BorderLayout.SOUTH);

        add(bottomPanel);
        
        
        //---------------LISTENERS-------------------------------------------
        
        checkInField.addKeyListener(new KeyListener() { //CONTROLLER
            public void keyTyped(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
                String inputDate = checkInField.getText();
                if (inputDate.length() == 10){
                    int month = Integer.parseInt(inputDate.substring(0, 2));
                    month--;
                    int day = Integer.parseInt(inputDate.substring(3, 5));
                    int year = Integer.parseInt(inputDate.substring(6));
                    checkInDate.set(year, month, day);
                    
                    Calendar today = Calendar.getInstance();
                    today.add(Calendar.DAY_OF_MONTH, -1);
                    //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
                    
                    if(checkInDate.before(today)){
                        data.addElement("Invalid check-in date. ");
                        data.addElement("The check-in date must not be prior to the current date");
                    }
                    else{
                        model.updateViewData(checkInDate, checkOutDate, rate);
                        System.out.println(rate);
                        
                    }
                }
                else{
                    data.clear();
                    data.addElement("");
                }
                
            }
        });
        
        checkOutField.addKeyListener(new KeyListener() { //CONTROLLER
            public void keyTyped(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
                String inputDate = checkOutField.getText();
                
                if (inputDate.length() == 10){
                    int month = Integer.parseInt(inputDate.substring(0, 2));
                    month--;
                    int day = Integer.parseInt(inputDate.substring(3, 5));
                    int year = Integer.parseInt(inputDate.substring(6));
                    checkOutDate.set(year, month, day);
                    
                    Calendar today = Calendar.getInstance();
                    today.add(Calendar.DAY_OF_MONTH, -1);
                                        Calendar temp = (Calendar) checkInDate.clone();
                                
                    int numberOfNights = 0;
                    while(temp.before(checkOutDate)){
                        temp.add(Calendar.DAY_OF_MONTH, 1);
                        numberOfNights++;
                    }
                    if(checkOutDate.before(today)){
                        data.addElement("Invalid check-out date. ");
                        data.addElement("The check-out date must not be prior to the current date");
                    }
                    else if(numberOfNights > 60){
                        data.addElement("Invalid check-out date. ");
                        data.addElement("Duration of stay cannot exceed 60 days");
                    }
                    else{
                        model.updateViewData(checkInDate, checkOutDate, rate);
                        System.out.println(rate);
                    }
                }
                else{
                    data.clear();
                    data.addElement("");
                }
            }
        });

        
        
        econButton.addActionListener(new ActionListener() { //CONTROLLER
            public void actionPerformed(ActionEvent e) {
                luxButton.getModel().setSelected(false);
                rate = 80;
                model.updateViewData(checkInDate, checkOutDate, rate);
                int index = 0;
                for(index = 0; index < data.getSize(); index++){
                    if(data.getElementAt(index).toString().contains("200")){
                            data.removeElementAt(index);
                    }
                }
                for(index = 0; index < data.getSize(); index++){
                    if(data.getElementAt(index).toString().contains("200")){
                            data.removeElementAt(index);
                    }
                }
                for(index = 0; index < data.getSize(); index++){
                    if(data.getElementAt(index).toString().contains("200")){
                            data.removeElementAt(index);
                    }
                }
            }
        });
        luxButton.addActionListener(new ActionListener() { //CONTROLLER
            public void actionPerformed(ActionEvent e) {
                econButton.getModel().setSelected(false);
                rate = 200;
                model.updateViewData(checkInDate, checkOutDate, rate);
                int index = 0;
                for(index = 0; index < data.getSize(); index++){
                    if(data.getElementAt(index).toString().contains("80")){
                            data.removeElementAt(index);
                    }
                }
                for(index = 0; index < data.getSize(); index++){
                    if(data.getElementAt(index).toString().contains("80")){
                            data.removeElementAt(index);
                    }
                }
                for(index = 0; index < data.getSize(); index++){
                    if(data.getElementAt(index).toString().contains("80")){
                            data.removeElementAt(index);
                    }
                }
            }
        });
        
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(selectionPanel);
                remove(bottomPanel);
                Reservation r = new Reservation();
                
                String selected = listView.getSelectedValue().toString();
                int roomNum = Integer.parseInt(selected.substring(5,8));
                double cost = Double.parseDouble(selected.substring(10));
                r.setRoomNumber(roomNum);
                r.setCost(cost);
                r.setCheckInDate(checkInDate);
                r.setCheckOutDate(checkOutDate);
                
                try {
                    r.setGuestEmail(guestEmail);
                    reservationList.addReservation(r);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ReservationsView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                finishedReservation();
            }
        });
    }
    
    
    /**
     * GUI for after the user completes a reservation
     */
    public void finishedReservation() {
        setTitle("Reservation Confirmed");
        setSize(425, 100);
        setLayout(new BorderLayout());
        JButton makeReservation = new JButton();
        JButton transactionDone = new JButton();
        JLabel title = new JLabel();
        title.setText("Would you like to make another reservation?");

        makeReservation.setText("Make a Reservation");
        transactionDone.setText("Transaction Done");

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout());

        buttons.add(makeReservation);
        buttons.add(transactionDone);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        makeReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(title);
                remove(buttons);
                makeReservation();
            }
        });

        transactionDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                remove(title);
                remove(buttons);

                System.exit(1);
            }
        });
    }

    /**
     * GUI for user to view/cancel his/her reservations
     * @throws FileNotFoundException 
     */
    public void viewCancelReservation() throws FileNotFoundException {
        setTitle("View/Cancel a Reservation");
        setSize(400, 200);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        JLabel title = new JLabel();
        title.setText("Your reservations:");

        DefaultListModel d = new DefaultListModel();
        if(!reservationList.isEmpty()){
            for(int j = 0; j < reservationList.getReservationList().size(); j++){
                ArrayList<Reservation> r = reservationList.getReservationList();
                if(r.get(j).getGuestEmail().equals(guestEmail)){
                    d.addElement(reservationList.getReservationList().get(j).toString());
                }
            }
        }
        JList rList = new JList(d);
        rList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        
        JScrollPane listScroller = new JScrollPane(rList);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton cancelButton = new JButton();
        cancelButton.setText("Cancel");
        
        container.add(title, BorderLayout.NORTH);
        container.add(listScroller, BorderLayout.CENTER);
        container.add(cancelButton, BorderLayout.SOUTH);

        add(container);
        
        //-------------------LISTENERS-------------------------------------
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!reservationList.isEmpty()){
                    int index = rList.getSelectedIndex();

                    for(int i = 0; i < reservationList.size(); i++){
                        if(rList.getSelectedValue().toString().equals(reservationList.get(i).toString())){
                            try {
                                reservationList.cancelReservation(reservationList.get(i));
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ReservationsView.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    try {
                        reservationList.updateFile();
                    } catch (FileNotFoundException ex) {
                    }
                    rList.setSelectedIndex(d.getSize()-1);
                    d.removeElementAt(index);
                    //cancelledReservation();
                }
            }
        });
    }
    
    /**
     * GUI for after the user cancels a reservation
     */
    public void cancelledReservation(){
        setTitle("Reservation Cancelled");
        setSize(425, 100);
        setLayout(new BorderLayout());
        JButton makeReservation = new JButton("Make a Reservation");
        JButton viewCancelButton = new JButton("View/Cancel a Reservation");
        
        JLabel label1 = new JLabel("The selected reservation has been cancelled.");
        JLabel label2 = new JLabel("What would you like to do?");
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout());

        buttons.add(makeReservation);
        buttons.add(viewCancelButton);

        add(label1, BorderLayout.NORTH);
        add(label2, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        makeReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(label1);
                remove(label2);
                remove(buttons);
                makeReservation();
            }
        });

        viewCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(label1);
                remove(label2);
                remove(buttons);
                try {
                    viewCancelReservation();
                } catch (FileNotFoundException ex) {
                    
                }
            }
        });
    }

    /**
     * Updates the view getting data from the model
     * @param e ChangeEvent
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        roomList = model.getViewData();
        data.clear();
        for(int i = 0; i < roomList.size(); i++){
            String s = roomList.get(i).toString();
            data.addElement(s);
        }
        repaint();
    }
}