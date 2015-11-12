package project;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The ManagerCalendar class supports the underlying data structure (2D int array) and provides
 * a method to attach ChangeListeners to update the ManagerView accordingly when the month or
 * year is changed. In other words, the ManagerCalendar serves as the "Model."
 * 
 * @author Aishwarya Borkar
 *
 */
public class ManagerCalendar {
	
	
	private int[][] calendar; 
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Initialize the calendar with the current month. 
	 */
	public ManagerCalendar()
	{
		listeners = new ArrayList<ChangeListener>();
		GregorianCalendar gc = new 	GregorianCalendar();

		gc.set(GregorianCalendar.DATE, 1); 

		int firstDayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH); 
		int lastDayOfMonth = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		int day = gc.get(GregorianCalendar.DAY_OF_WEEK);
		day--;

		int week = gc.get(GregorianCalendar.WEEK_OF_MONTH);
		week--;
		int numWeeks = gc.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);

		
		calendar = new int[6][7];


		for(int i = 0; i < calendar.length; i++)
		{

			while( day < 7 && firstDayOfMonth <= lastDayOfMonth )
			{
				calendar[week][day] = firstDayOfMonth; 
				
				firstDayOfMonth++;
				day++; 
			}
			week++; 
			day = 0;


		}
	}
	
	/**
	 * Returns the Calendar. 
	 * @return int[][]
	 */
	public int[][] getCalendar()
	{
		return calendar;
	}
	
	/**
	 * Method that attaches ChangeListeners.
	 * @param listener
	 */
	public void addChangeListener(ChangeListener listener) 
	{
		listeners.add(listener);
	}
	
	/**
	 * Clears the Calendar just before it is updated with the corresponding
	 * int values of the selected month. 
	 */
	public void clear()
	{
		for (int row = 0; row < calendar.length; row++)
		{
			for (int col = 0; col < calendar[row].length; col++)
			{
				calendar[row][col] = 0;
			}
		}
	}
	/**
	 * Updates ManagerView based on the selected values of month and year. 
	 * 
	 * @param month int value ranging from 0-11
	 * @param year int
	 */
	public void updateCalendar(int month, int year)
	{
		clear(); 
		GregorianCalendar gc = new 	GregorianCalendar();
		gc.set(GregorianCalendar.MONTH, month);
		gc.set(GregorianCalendar.YEAR, year);

		gc.set(GregorianCalendar.DATE, 1); 

		int firstDayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH); 
		int lastDayOfMonth = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		int day = gc.get(GregorianCalendar.DAY_OF_WEEK);
		day--;

		int week = gc.get(GregorianCalendar.WEEK_OF_MONTH);
		week--;
		

		for(int i = 0; i < calendar.length; i++)
		{

			while( day < 7 && firstDayOfMonth <= lastDayOfMonth )
			{
				calendar[week][day] = firstDayOfMonth; 
				ChangeEvent e = new ChangeEvent(this);
				for (ChangeListener list : listeners) 
				{
					list.stateChanged(e);
				}
				firstDayOfMonth++;
				day++; 
			}
			week++; 
			day = 0;


		}
		
	}
	


}
