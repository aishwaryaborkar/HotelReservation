package project;



import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The ManagerView displays a clickable Calendar in which the Manager can select a 
 * particular date and view the room availability and guest info for that date. In other words, 
 * the ManagerView serves as the "View".
 * 
 * @author Aishwarya Borkar
 *
 */
public class ManagerView implements ChangeListener, ItemListener{

	private JButton view[][];
	private int[][] calendar; 
	private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private String[] years ={"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015"}; 
	private String[] days ={"Su","Mo", "Tu", "We", "Th", "Fr", "Sa"};
	private ManagerCalendar cal;
	private JComboBox<String> monthOptions; 
	private JComboBox<String> yearOptions;
	private String monthName; 
	private String yearName; 
	private JLabel monthLabel; 
	private RoomAvailability r; 
	
	
	public ManagerView(ManagerCalendar m, RoomAvailability ra)
	{
		
		cal = m; 
		calendar = m.getCalendar();
		r = ra; 
		
		//Initialize 2D array of JButtons and set each JButton's text to the corresponding int value in the 2D int array.
		JPanel calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(6,7));
		
		view = new JButton[6][7];

		for( int row = 0; row < view.length; row++ )
		{
			for( int col = 0; col < view[row].length; col++ )
			{
				view[row][col]  = new JButton() ;
				calendarPanel.add( view[row][col]);
				
				if ( calendar[row][col] == 0)
				{
					view[row][col].setText( "");
					
				}
				else
				{
					view[row][col].setText( String.valueOf(calendar[row][col]));
				}
				
				
			}
		}
		
		
		for( int row = 0; row < calendar.length; row++ )
		{
			for( int col = 0; col < calendar[row].length; col++ )
			{
				int day = calendar[row][col];
				view[row][col].addActionListener(new ActionListener()
				{ 
					
					public void actionPerformed(ActionEvent e)
					{
						//instantiate RoomAvailabilityView
						GregorianCalendar date = new GregorianCalendar(Integer.parseInt(yearName),getMonthValue(monthName), day);
						ra.updateViewByDay(date);
						try {
							RoomAvailabilityView v = new RoomAvailabilityView(new RoomAvailability(new ReservationList()));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
//						SimpleDateFormat fmt = new SimpleDateFormat("MM-dd-yyyy");
//						 fmt.setCalendar(date);
//						 String dateFormatted = fmt.format(date.getTime());
//						 System.out.println(dateFormatted);
					}});				
			}
		}
		
		JPanel monthPanel = new JPanel();
		monthPanel.setLayout(new BorderLayout());
		////Initialize a JComboBox with a String[] that corresponds to years.
		monthOptions = new JComboBox<String>(months);
		monthOptions.addItemListener(this);
		monthPanel.add(monthOptions, BorderLayout.WEST);

		GregorianCalendar greg = new GregorianCalendar();

		monthLabel = new JLabel(months[greg.get(GregorianCalendar.MONTH)], JLabel.CENTER); 
		monthPanel.add(monthLabel, BorderLayout.CENTER);
		
		//Update the name of the month each time a new month is selected. 
		monthOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
		        String monthName = (String)cb.getSelectedItem();
		        monthLabel.setText(monthName);
			}

		});
		
		//Initialize a JComboBox with a String[] that corresponds to years.
		yearOptions = new JComboBox<String>(years);
		yearOptions.addItemListener(this);
		monthPanel.add(yearOptions, BorderLayout.EAST);

		//Label each day of the week.
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(1, 7));
		for(int i =0; i < days.length; i++)
		{
			JLabel label = new JLabel(days[i]);
			dayPanel.add(label);
		}
		monthPanel.add(dayPanel, BorderLayout.SOUTH);
		
		
		
		final JFrame frame = new JFrame("Manager View");
		frame.add(monthPanel, BorderLayout.NORTH);
		frame.add(calendarPanel, BorderLayout.CENTER);
		
		
		m.addChangeListener(this);
		
		frame.setSize(500,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		

	
	}
	
	/**
	 * Returns the int value of a particular month (i.e. the index of the String representation
	 * of the month in the JComboBox) in order to set the month
	 * using the set() method of GregorianCalendar and its instance variable MONTH.
	 * @param month the selected month to display
	 * @return int value of month (0-11)
	 */
	public int getMonthValue(String month)
	{
		int j =0;
		for(int i = 0; i < months.length; i++)
		{
			if ( month.equals(months[i]))
			{
				j = i;
				
			}
		}
		
		return j;
	}

	/**
	 * Updates the view when the month or year is changed.
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) 
	{
		for( int row = 0; row < calendar.length; row++ )
		{
			for( int col = 0; col < calendar[row].length; col++ )
			{
				if( calendar[row][col] != 0 )
				{
					 view[row][col].setText( String.valueOf(calendar[row][col]) );
				}
				else
				{
					view[row][col].setText("");
				}
		
			}
			
		}
		
	}

	/**
	 * Obtains the selected values for month and year and calls the
	 * corresponding method in ManagerCalendar.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		if ( e.getStateChange() == ItemEvent.SELECTED)
		{
			monthName = (String)monthOptions.getSelectedItem();
			yearName = (String)yearOptions.getSelectedItem();
			
			cal.updateCalendar(getMonthValue(monthName), Integer.parseInt(yearName));
		}
		
	}


}
