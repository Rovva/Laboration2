package data;

import java.util.ArrayList;
import java.util.Observable;

public class Fetcher extends Observable {
	
	PlaceFetcher places;

	public Fetcher(String file) {
		places = new PlaceFetcher(file);
		places.updatePlaces();
	}

	void updateFilePath(String file) {
		filepath = new File(file);
		
	}
	
	String getFilePath() {
		return filepath;
	}
	
	void fetchXML() {
		
	}
	
	String getCities() {
		return city;
	}
	
	double getAltitude() {
		return altitude;
	}
	
	double getLongitude() {
		
	}

}
