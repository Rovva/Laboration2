package data;

import java.util.ArrayList;
import java.util.Observable;

public class Fetcher extends Observable {
	
	PlaceFetcher places;
	WeatherFetcher weather;

	public Fetcher(String file) {
		places = new PlaceFetcher(file);
		places.updatePlaces();
		weather = new WeatherFetcher();
	}

}
