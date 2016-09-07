package data;

import java.util.ArrayList;
import java.util.Observable;

public class Fetcher extends Observable {
	
	String file = "places.xml";
	PlaceFetcher places;
	WeatherFetcher weather;
	
	String choosen_city, date;

	public Fetcher() {
		places = new PlaceFetcher(file);
		places.updatePlaces();
		weather = new WeatherFetcher();
	}
	
	/*public String[] getCityNames() {
		places.updatePlaces();
		return places.returnCities();
	}*/

}
