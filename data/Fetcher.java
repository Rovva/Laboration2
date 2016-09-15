package data;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Date;

/**
 * Fetcher will act as the intermediator between PlaceFetcher and WeatherFetcher and use them.
 * 
 */

public class Fetcher extends Observable {
	
	String file = "places.xml";
	PlaceFetcher places;
	WeatherFetcher weather;
	String choosen_city, date, temperature;
	long fetchTime = 0;
	long cacheTime = 0;
	
	/**
	 * Constructor
	 * 
	 * Creates the different fetchers and gathers the cities from placeFetcher.
	 */	

	public Fetcher() {
		places = new PlaceFetcher();
		places.updatePlaces();
		weather = new WeatherFetcher();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * getTemperature
	 * 
	 * @param city 			The city we want to fetch temperature from
	 * @param time 			The hour we want to fetch it...
	 * @param cacheTime 	The cacheTime we have set.
	 * 
	 * This method will check whether to fetch data from
	 * the cache or from the Weather api, and do so.
	 * 
	 * 
	 */
	
	public void getTemperature(String city, String time, int cacheTime) {
		Date tmpDate = new Date(); 					//The date upon getting Temperature
		boolean cache;
		
		//Checks whether enough time has passed to get temperature from the weather api or cache.
		if(this.cacheTime > tmpDate.getTime()) {	
			cache = true;
		} else {
			cache = false;
			this.fetchTime = tmpDate.getTime();					//the time we fetch data
			this.cacheTime = fetchTime + (cacheTime*60*1000);	//Adds the current time with the cacheTime in milliseconds.
		}
		
		this.date = time;											//Which hour we want to fetch temperature from.
		temperature = weather.fetchWeather(this.getAltitude(city),	//Fetch temperature
				this.getLatitude(city),
				this.getLongitude(city), 
				time, cache);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * getCityNames
	 * 
	 * @return will return the list of cities from placeFetcher.
	 * 
	 * 
	 */
	
	public ArrayList getCityNames() {
		return places.returnCities();
	}
	
	/**
	 * getAltitude
	 * 
	 * @return will return the list of Altitudes from placeFetcher.
	 * 
	 * 
	 */
	
	public String getAltitude(String city) {
		return places.returnAltitude(city);
	}
	
	
	/**
	 * getLatitude
	 * 
	 * @return will return the list of Latitudes from placeFetcher.
	 * 
	 * 
	 */
	
	public String getLatitude(String city) {
		return places.returnLatitude(city);
	}
	
	
	/**
	 * getLongitude
	 * 
	 * @return will return the list of Longitudes from placeFetcher.
	 * 
	 * 
	 */
	
	public String getLongitude(String city) {
		return places.returnLongitude(city);
	}
	
	
	/**
	 * getTemperature
	 * 
	 * @return will return the temperature
	 * 
	 * 
	 */
	
	public String getTemperature(){
		return temperature;
	}
	
	
	/**
	 * getTime
	 * 
	 * @return will return the date
	 * 
	 * 
	 */
	
	public String getTime() {
		return date;
	}

}
