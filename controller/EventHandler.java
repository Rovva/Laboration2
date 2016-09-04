package controller;

public class EventHandler implements Observer {
	
	private boolean start;
	
	public EventHandler() {
		start = false;
	}
	
	public void run() {
		start = true;
	}
}
