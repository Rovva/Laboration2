package data;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Date;

public class Fetcher extends Observable {
	
	String file = "places.xml";
	PlaceFetcher places;
	WeatherFetcher weather;
	String choosen_city, date, temperature;
	long fetchTime = 0;
	int cacheTime = 0;

	public Fetcher() {
		places = new PlaceFetcher();
		places.updatePlaces();
		weather = new WeatherFetcher();
		setChanged();
		notifyObservers();
	}
	
	public void getTemperature(String city, String time, int cacheTime) {
		Date tmpDate = new Date();
		boolean cache;
		if((fetchTime+cacheTime) > tmpDate.getTime()) {
			cache = true;
		} else {
			cache = false;
		}
		fetchTime = tmpDate.getTime();
		this.cacheTime = cacheTime*60*1000;
		date = time;
		temperature = weather.fetchWeather(this.getAltitude(city),
				this.getLatitude(city),
				this.getLongitude(city), 
				time, cache);
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
