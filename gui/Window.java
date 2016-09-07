package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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
	private JLabel date_label = new JLabel("Dagens datum:");
	private JLabel current_date = new JLabel();
	private JComboBox city_choose = new JComboBox();
	private JComboBox hour_choose = new JComboBox();
	private JLabel cache_label = new JLabel("Åldringstid:");
	private JComboBox cache = new JComboBox();
	private JButton select = new JButton("Hämta");
	private JLabel prognos = new JLabel("Prognos för vald timma:");
	private JLabel choosen_hour = new JLabel("Vald timma:");
	private JLabel temperature = new JLabel();
	
	
	public Window() {
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
		contentPane.add(date_label);
		contentPane.add(current_date);
		contentPane.add(cache_label);
		contentPane.add(cache);
		contentPane.add(select);

		city_choose.addItem("Skellefteå");
		Date date = new Date();
		current_date.setText(date.toString());
		
		layout.putConstraint(SpringLayout.NORTH, city_label, 2, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, city_label, 2, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.NORTH, hour_label, 2, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, hour_label, 22, SpringLayout.EAST, city_choose);
		
		layout.putConstraint(SpringLayout.NORTH, city_choose, 5, SpringLayout.SOUTH, city_label);
		layout.putConstraint(SpringLayout.WEST, city_choose, 2, SpringLayout.WEST, contentPane);

		layout.putConstraint(SpringLayout.NORTH, hour_choose, 5, SpringLayout.SOUTH, hour_label);
		layout.putConstraint(SpringLayout.WEST, hour_choose, 22, SpringLayout.EAST, city_choose);

		layout.putConstraint(SpringLayout.NORTH, date_label, 2, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, date_label, 10, SpringLayout.EAST, hour_label);
		
		layout.putConstraint(SpringLayout.NORTH, current_date, 2, SpringLayout.SOUTH, date_label);
		layout.putConstraint(SpringLayout.WEST, current_date, 10, SpringLayout.EAST, hour_label);

		layout.putConstraint(SpringLayout.NORTH, cache_label, 2, SpringLayout.SOUTH, city_choose);
		layout.putConstraint(SpringLayout.WEST, cache_label, 2, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, cache, 2, SpringLayout.SOUTH, cache_label);
		layout.putConstraint(SpringLayout.WEST, cache, 2, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, select, 0, SpringLayout.NORTH, current_date);
		layout.putConstraint(SpringLayout.WEST, select, 10, SpringLayout.EAST, current_date);
		
		
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
