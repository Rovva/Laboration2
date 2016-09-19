package gui;


import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import data.Fetcher;


/**
 * Window will act as the GUI, have buttons, lists of cities and the result from the
 * weather api. The overall user interface.
 * 
 */


public class Window implements Observer, ActionListener {

	int size_x = 400, size_y = 300;										//The initial size of the window
	private JLabel city_label = new JLabel("Välj stad:");
	private JLabel hour_label = new JLabel("Välj timma:");
	private JComboBox city_choose = new JComboBox();					//Creates a new dropdown menu for cities
	private JComboBox hour_choose = new JComboBox();					//-.............................- hours
	private JLabel cache_label = new JLabel("Åldringstid (minuter):");	
	private JComboBox cache = new JComboBox();							//-.............................- cache/aging time
	private JButton select = new JButton("Hämta");
	private JLabel prognos_label = new JLabel("Prognos:");
	private JLabel choosen_hour_label = new JLabel("Vald timma:");
	private JLabel choosen_hour = new JLabel();							//Label to display the fetched time
	private JLabel temperature_label = new JLabel("Temperatur:");
	private JLabel temperature = new JLabel();							//Label to display the fetched temperature
	
	private Fetcher fet;
	
	private ArrayList cities_temp;
	
	Date currentDate;
	
	/**
	 * Constructor
	 * 
	 * Will set up the gui with spring layout and display the window for the user with
	 * the dropdown menus, having the content (Cities, hours, cache minutes) we want.
	 */	
	
	public Window(Fetcher fet) {
		
		this.fet = fet;
		fet.addObserver(this);
		
		JFrame frame = new JFrame("Weather");					//The weather frame...
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//...Which will shut down the program execution if we close the window.
		frame.setSize(size_x, size_y);
		frame.setVisible(true);
		SpringLayout layout = new SpringLayout();				//Create a new Spring Layout for our components.
		Container contentPane = frame.getContentPane();			//Our container to put our components in.

		contentPane.setLayout(layout);							//We want to use the Spring Layout.
		contentPane.add(city_label);							//Adding our components to our contentPane.
		contentPane.add(city_choose);
		contentPane.add(hour_choose);
		contentPane.add(hour_label);
		contentPane.add(cache_label);
		contentPane.add(cache);
		contentPane.add(select);
		select.addActionListener(this);							//"Hämta" will be listening to be pressed.
		
		contentPane.add(prognos_label);
		contentPane.add(choosen_hour_label);
		contentPane.add(choosen_hour);
		contentPane.add(temperature_label);
		contentPane.add(temperature);
		
		cities_temp = fet.getCityNames();						//Fetch the cities we have available in places.xml
		for(int i = 0; i < cities_temp.size(); i++) {			//Add each city to the dropdown menu
			city_choose.addItem(cities_temp.get(i));
		}
		
		updateHourChoose();
		updateCacheTime();
		
		
		//Snapping the components together.
		layout.putConstraint(SpringLayout.NORTH, city_label, 2, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, city_label, 2, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.NORTH, hour_label, 2, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, hour_label, 22, SpringLayout.EAST, city_choose);
		
		layout.putConstraint(SpringLayout.NORTH, city_choose, 5, SpringLayout.SOUTH, city_label);
		layout.putConstraint(SpringLayout.WEST, city_choose, 2, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.NORTH, hour_choose, 5, SpringLayout.SOUTH, hour_label);
		layout.putConstraint(SpringLayout.WEST, hour_choose, 22, SpringLayout.EAST, city_choose);

		layout.putConstraint(SpringLayout.NORTH, cache_label, 2, SpringLayout.SOUTH, city_choose);
		layout.putConstraint(SpringLayout.WEST, cache_label, 2, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, cache, 2, SpringLayout.SOUTH, cache_label);
		layout.putConstraint(SpringLayout.WEST, cache, 2, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, select, 0, SpringLayout.NORTH, hour_choose);
		layout.putConstraint(SpringLayout.WEST, select, 10, SpringLayout.EAST, hour_choose);
		
		layout.putConstraint(SpringLayout.NORTH, prognos_label, 60, SpringLayout.SOUTH, cache);
		layout.putConstraint(SpringLayout.WEST, prognos_label, 2, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, choosen_hour_label, 2, SpringLayout.SOUTH, prognos_label);
		layout.putConstraint(SpringLayout.WEST, choosen_hour_label, 2, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, choosen_hour, 2, SpringLayout.SOUTH, prognos_label);
		layout.putConstraint(SpringLayout.WEST, choosen_hour, 2, SpringLayout.EAST, choosen_hour_label);
		
		layout.putConstraint(SpringLayout.NORTH, temperature_label, 2, SpringLayout.SOUTH, choosen_hour_label);
		layout.putConstraint(SpringLayout.WEST, temperature_label, 2, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, temperature, 2, SpringLayout.SOUTH, choosen_hour);
		layout.putConstraint(SpringLayout.WEST, temperature, 2, SpringLayout.EAST, temperature_label);
		
	}
	
	/**
	 * updateHourChoose
	 * 
	 * Will update hours of today's date in the
	 * hour_choose dropdown menu with a SimpleDateFormat.
	 * 
	 */
	
	void updateHourChoose() {
		Date date = new Date();
		long milli = 0;
		milli = date.getTime();		//Store milliseconds since January 1st, 1970
		Date temp;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH':00:00Z'"); //New dateformat
		for(int i = 0; i < 24; i++) {											//Add every hour of the day to hour_choose dropdown menu.
			temp = new Date(milli);			        //Representation of the milliseconds to today's date 
			hour_choose.addItem(ft.format(temp));	//Add the date to the menu with SimpleDateFormat.
			milli = milli + 3600000;				//+1 hour
		}
	}
	
	/**
	 * updateCacheTime
	 * 
	 * Adds the minutes to cache into cache/agetime dropdown menu.
	 * 
	 * 
	 */
	
	void updateCacheTime() {
		for(int i = 1; i <= 60; i++) {
			cache.addItem(i);
		}
	}
	
	/**
	 * actionPerformed
	 * 
	 * Sends our configurations to fetch temperature
	 * 
	 * @param arg0 Will check if the action is: "Hämta"...
	 * 
	 */
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String op = arg0.getActionCommand();
		if(op == "Hämta") {
			fet.getTemperature((String) city_choose.getSelectedItem(), (String) hour_choose.getSelectedItem(), (int)cache.getSelectedItem());
		}
	}

	/**
	 * update
	 * 
	 * Updates the data for the gui
	 * 
	 * 
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		city_choose.removeAllItems();
		cities_temp = fet.getCityNames();
		for(int i = 0; i < cities_temp.size(); i++) {
			city_choose.addItem(cities_temp.get(i));
		}
		city_choose.setSelectedItem(fet.getChoosenCity());
		temperature.setText(fet.getTemperature()); 
		choosen_hour.setText(fet.getTime());
	}
	
	
}
