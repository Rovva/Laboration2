package data;

import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class PlaceFetcher {

	File file;
	ArrayList cities, altitude, latitude, longitude;
	Document placesDoc;
	DocumentBuilderFactory factory;
	
	public PlaceFetcher() {
		cities = new ArrayList();
		longitude = new ArrayList();
		latitude = new ArrayList();
		altitude = new ArrayList();
		factory = DocumentBuilderFactory.newInstance();
		// TODO Auto-generated constructor stub
	}

	void updatePlaces() {
		
		clearCities();
		clearAltitude();
		clearLatitude();
		clearLongitude();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("places.xml");
			NodeList localityList = doc.getElementsByTagName("locality");
			NodeList locationList = doc.getElementsByTagName("location");
			for(int i = 0; i < localityList.getLength(); i++){
				Node l = localityList.item(i);
				if (l.getNodeType()==Node.ELEMENT_NODE){
					Element locality = (Element) l; 
					cities.add(locality.getAttribute("name"));
				}
			}
			for(int i = 0; i < locationList.getLength(); i++){
				Node l = locationList.item(i);
				if (l.getNodeType()==Node.ELEMENT_NODE){
					Element location = (Element) l; 
					longitude.add(location.getAttribute("longitude"));
					latitude.add(location.getAttribute("latitude"));
					altitude.add(location.getAttribute("altitude"));
				}
			}
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
	
	void addCities(String city) {
		cities.add(city);
	}
	
	ArrayList returnCities() {
		 return cities;
	}
	
	void clearCities() {
		cities.clear();
	}
	
	void addAltitude(String alt) {
		altitude.add(alt);
	}
	
	String returnAltitude(String city) {
		int i = cities.indexOf(city);
		return (String) altitude.get(i);
	}
	
	void clearAltitude() {
		altitude.clear();
	}
	
	void addLatitude(String lat) {
		latitude.add(lat);
	}
	
	String returnLatitude(String city) {
		int i = cities.indexOf(city);
		return (String) latitude.get(i);
	}
	
	void clearLatitude() {
		latitude.clear();
	}
	
	void addLongitude(String lon) {
		longitude.add(lon);
	}
	
	String returnLongitude(String city) {
		int i = cities.indexOf(city);
		return (String) longitude.get(i);
	}
	
	void clearLongitude() {
		longitude.clear();
	}
	
	
}
