/**
 * 
 * @author Christoffer Rova and Jonas Roininen
 * @version 0.9
 * @since 2016-09-19
 * @docRoot
 * 
 *  Väderdataklient
 *
 */

package main;

import gui.Window;
import data.Fetcher;

public class Main {
	
	public static void main(String[] args) {
		Fetcher fetcher = new Fetcher();
		Window window = new Window(fetcher);
	}
}
