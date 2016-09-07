package main;

import gui.Window;
import controller.EventHandler;
import data.Fetcher;

public class Main {
	public static void main(String[] args) {
		Fetcher fetcher = new Fetcher("./places.xml");
		EventHandler control = new EventHandler();
		Window window = new Window();
		fetcher.addObserver(control);
		fetcher.addObserver(window);
	}
}
