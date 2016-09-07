package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import data.Fetcher;


public class Window implements Observer, ActionListener {

	int size_x = 500, size_y = 400;
	private JLabel city_label = new JLabel("Välj stad:");
	private JLabel hour_label = new JLabel("Välj timma:");
	private JComboBox city_choose = new JComboBox();
	private JComboBox hour_choose = new JComboBox();
	private JLabel cache_label = new JLabel("Åldringstid (minuter):");
	private JComboBox cache = new JComboBox();
	private JButton select = new JButton("Hämta");
	private JLabel prognos_label = new JLabel("Prognos:");
	private JLabel choosen_hour_label = new JLabel("Vald timma:");
	private JLabel choosen_hour = new JLabel();
	private JLabel temperature_label = new JLabel("Temperatur:");
	private JLabel temperature = new JLabel();
	
	private Fetcher fet;
	
	Date currentDate;
	
	public Window(Fetcher fet) {
		
		this.fet = fet;
		fet.addObserver(this);
		
		String cities_temp[];
		
		JFrame frame = new JFrame("Weather");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(size_x, size_y);
		frame.setVisible(true);
		SpringLayout layout = new SpringLayout();
		Container contentPane = frame.getContentPane();

		contentPane.setLayout(layout);
		contentPane.add(city_label);
		contentPane.add(city_choose);
		contentPane.add(hour_choose);
		contentPane.add(hour_label);
		contentPane.add(cache_label);
		contentPane.add(cache);
		contentPane.add(select);
		
		contentPane.add(prognos_label);
		contentPane.add(choosen_hour_label);
		contentPane.add(choosen_hour);
		contentPane.add(temperature_label);
		contentPane.add(temperature);
		
		/*cities_temp = fet.getCityNames();
		for(int i = 0; i < cities_temp.length; i++) {
			city_choose.addItem(cities_temp[i]);
		}*/
		
		city_choose.addItem("Skellefteå");
		
		updateHourChoose();
		updateCacheTime();
		
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
	
	void updateHourChoose() {
		Date date = new Date();
		long milli = 0;
		milli = date.getTime();
		Date temp;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH':00:00'");
		for(int i = 0; i < 24; i++) {
			temp = new Date(milli);
			hour_choose.addItem(ft.format(temp));
			milli = milli + 3600000;
		}
	}
	
	void updateCacheTime() {
		for(int i = 1; i <= 60; i++) {
			cache.addItem(i);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
}
