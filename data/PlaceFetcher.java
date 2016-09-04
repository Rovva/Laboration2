package data;

import java.util.ArrayList;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;

public class PlaceFetcher {

	File file;
	String filepath;
	ArrayList cities, altitude, latitude, longitude;
	Document placesDoc;
	
	public PlaceFetcher(String file) {
		filepath = file;
		cities  = new ArrayList();
		altitude  = new ArrayList();
		longitude  = new ArrayList();
		// TODO Auto-generated constructor stub
	}

	void updatePlaces() {
		try {
			file = new File(filepath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			placesDoc = dBuilder.parse(filepath);
			placesDoc.getDocumentElement().normalize();
			NodeList nList = placesDoc.getElementsByTagName("locality");
			for(int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					this.addCities(eElement.getAttribute("name"));
					this.addAltitude(eElement.getAttribute("altitude"));
					this.addLongitude(eElement.getAttribute("longitude"));
					this.addLatitude(eElement.getAttribute("latitude"));
				}
			}
		} catch (Exception e) {
	         e.printStackTrace();
	    }
	}
	
	void addCities(String city) {
		cities.add(city);
	}
	
	void clearCities() {
		cities.clear();
	}
	
	void addAltitude(String alt) {
		altitude.add(alt);
	}
	
	void clearAltitude() {
		altitude.clear();
	}
	
	void addLatitude(String lat) {
		latitude.add(lat);
	}
	
	void clearLatitude() {
		latitude.clear();
	}
	
	void addLongitude(String lon) {
		longitude.add(lon);
	}
	
	void clearLongitude() {
		longitude.clear();
	}
	
	
}
