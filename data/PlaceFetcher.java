package data;

import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

/**
 * Reads places.xml parses through cities, altitudes, latitudes, longitudes for
 * the GUI comboboxes and url settings for WeatherFetcher.
 */

public class PlaceFetcher {

	File file;
	ArrayList cities, altitude, latitude, longitude;
	Document placesDoc;
	DocumentBuilderFactory factory;
	
	/**
	 * Constructor
	 * 
	 * Creates arraylists for our parsed values.
	 */	
	
	public PlaceFetcher() {
		cities = new ArrayList();
		longitude = new ArrayList();
		latitude = new ArrayList();
		altitude = new ArrayList();
		factory = DocumentBuilderFactory.newInstance();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * updatePlaces
	 * 
	 * Parses through the .xml file and adds the results into the appropriate arraylist.
	 * 
	 */

	void updatePlaces() {
	
		//Clear existing values before parsing
		clearCities();
		clearAltitude();
		clearLatitude();
		clearLongitude();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder(); 	//Used to store XML instances
			Document doc = builder.parse("places.xml"); 				//The XML which the DocumentBuilder will parse from
			NodeList localityList = doc.getElementsByTagName("locality"); //Will store tagnames "locality" to localityList
			NodeList locationList = doc.getElementsByTagName("location"); //Will store "location" elements to locationList
			for(int i = 0; i < localityList.getLength(); i++){
				Node l = localityList.item(i);
				if (l.getNodeType()==Node.ELEMENT_NODE){ 				//If the item in current index, is of type; ELEMENT_NODE...
					Element locality = (Element) l; 
					cities.add(locality.getAttribute("name")); 			//Add the "name" value to "cities" Arraylist.
				}
			}
			for(int i = 0; i < locationList.getLength(); i++){
				Node l = locationList.item(i);
				if (l.getNodeType()==Node.ELEMENT_NODE){
					Element location = (Element) l; 
					
					//Add the "longitude/latitude/altitude" values to "longitude/latitude/altitude" Arraylist.
					longitude.add(location.getAttribute("longitude"));
					latitude.add(location.getAttribute("latitude"));
					altitude.add(location.getAttribute("altitude"));
				}
			}
			
			//Catch exceptions for parsing and missing files...
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * addCities
	 * 
	 * @param city Adds a city to the cities array.
	 * 
	 */
	
	void addCities(String city) {
		cities.add(city);
	}
	

	/**
	 * returnCities
	 * 
	 * @return The cities Arraylist
	 * 
	 */
	
	ArrayList returnCities() {
		 return cities;
	}
	
	/**
	 * clearCities
	 * 
	 * clears cities Arraylist
	 * 
	 */
	
	void clearCities() {
		cities.clear();
	}
	
	/**
	 * addAltitude
	 * 
	 * @param alt Adds an altitude to the altitude array.
	 * 
	 */
	
	void addAltitude(String alt) {
		altitude.add(alt);
	}
	
	/**
	 * returnAltitude
	 * 
	 * @param city The city of which altitude is requested
	 * @return The altitude from the index corresponding the city's index
	 * 
	 */
	
	String returnAltitude(String city) {
		int i = cities.indexOf(city);
		return (String) altitude.get(i);
	}
	
	/**
	 * clearAltitude
	 * 
	 * clears altitude Arraylist
	 * 
	 */
	
	void clearAltitude() {
		altitude.clear();
	}
	
	/**
	 * addLatitude
	 * 
	 * @param lat Adds a latitude to the altitude array.
	 * 
	 */
	
	void addLatitude(String lat) {
		latitude.add(lat);
	}
	
	/**
	 * returnLatitude
	 * 
	 * @param city The city of which latitude is requested
	 * @return The latitude from the index corresponding the city's index
	 * 
	 */
	
	String returnLatitude(String city) {
		int i = cities.indexOf(city);
		return (String) latitude.get(i);
	}
	
	/**
	 * clearLatitude
	 * 
	 * clears latitude Arraylist
	 * 
	 */
	
	void clearLatitude() {
		latitude.clear();
	}
	
	
	/**
	 * addLongitude
	 * 
	 * @param lon Adds a longitude to the longitude array.
	 * 
	 */
	
	void addLongitude(String lon) {
		longitude.add(lon);
	}
	
	/**
	 * returnLongitude
	 * 
	 * @param city The city of which longitude is requested
	 * @return The longitude from the index corresponding the city's index
	 * 
	 */
	
	String returnLongitude(String city) {
		int i = cities.indexOf(city);
		return (String) longitude.get(i);
	}
	
	/**
	 * clearLongitude
	 * 
	 * clears longitude Arraylist
	 * 
	 */
	
	void clearLongitude() {
		longitude.clear();
	}
	
	
}
