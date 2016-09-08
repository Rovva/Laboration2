package data;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WeatherDOM {
	private static String url;
	private static String altitude;
	private static String latitude;
	private static String longitude;
	private static String time = "2016-09-08T16:00:00Z";
	public static void main(String[] args) {
		altitude = "70";
		latitude = "60.10";
		longitude = "9.58";
		url = "http://api.met.no/weatherapi/locationforecast/1.9/?lat="+latitude+";lon="+longitude+";msl="+altitude;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(url);
			NodeList timeList = doc.getElementsByTagName("time");
			NodeList locationList = doc.getElementsByTagName("location");
			NodeList temperatureList = doc.getElementsByTagName("temperature");
			for(int i = 0; i < timeList.getLength(); i++){
				Node l = timeList.item(i);
				if (l.getNodeType()==Node.ELEMENT_NODE){
					Element times = (Element) l;
					String temp_from = times.getAttribute("from");
					String temp_to = times.getAttribute("to");
					if(temp_from.equals(time) && temp_to.equals(time)) {
						System.out.println("ok");
						NodeList temperature = times.getElementsByTagName("temperature");
						Node t = temperature.item(0);
						Element degrees = (Element) t;
						System.out.println(degrees.getAttribute("value"));
					}
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
}
