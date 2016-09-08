package data;

import java.util.ArrayList;
import java.util.Observable;

public class Fetcher extends Observable {
	
	String file = "places.xml";
	PlaceFetcher places;
	WeatherFetcher weather;
	String choosen_city, date, temperature;

	public Fetcher() {
		places = new PlaceFetcher();
		places.updatePlaces();
		weather = new WeatherFetcher();
		setChanged();
		notifyObservers();
	}
	
	public void getTemperature(String city, String time) {
		date = time;
		temperature = weather.fetchWeather(this.getAltitude(city),
				this.getLatitude(city),
				this.getLongitude(city), 
				time);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList getCityNames() {
		return places.returnCities();
	}
	
	public String getAltitude(String city) {
		return places.returnAltitude(city);
	}
	
	public String getLatitude(String city) {
		return places.returnLatitude(city);
	}
	
	public String getLongitude(String city) {
		return places.returnLongitude(city);
	}
	
	public String getTemperature(){
		return temperature;
	}
	
	public String getTime() {
		return date;
	}

}
