/**
 * 
 * @author Christoffer Rova and Jonas Roininen
 * 
 *  Väderdataklient
 *
 */

package main;

import gui.Window;
import controller.EventHandler;
import data.Fetcher;

public class Main {
	
	public static void main(String[] args) {
		Fetcher fetcher = new Fetcher();
		Window window = new Window(fetcher);
	}
}
