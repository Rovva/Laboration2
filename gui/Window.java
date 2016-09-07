package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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
	private JComboBox city_choose = new JComboBox();
	private JLabel time_from, time_to, altitude, latitude, longitude, city,
		temperature = new JLabel();
	
	
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
		contentPane.add(time_from);
		contentPane.add(time_to);
		contentPane.add(altitude);
		contentPane.add(latitude);
		contentPane.add(longitude);
		contentPane.add(city);
		contentPane.add(temperature);
		
		layout.putConstraint(SpringLayout.NORTH, city_label, 2, SpringLayout.NORTH, contentPane);
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
