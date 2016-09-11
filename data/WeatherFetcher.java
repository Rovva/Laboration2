package data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * WeatherFetcher will connect and request an XML with appropriate configurations
 * from a weather API, to parse through and receive temperature in a specific city.
 * 
 */

public class WeatherFetcher {
	
	DocumentBuilderFactory factory;
	Document doc;
	
	void WeatherFetcher() {
		
	}
	
	String url;
	
	/**
	 * fetchWeather
	 * 
	 * @param altitude The altitude to send
	 * @param latitude The latitude to send
	 * @param longitude The longiture to send
	 * @param time The time we are interested in
	 * @param cache Are we are using our cached information? True/False
	 * @return Returns the temperature.
	 * 
	 */
	
	String fetchWeather(String altitude, String latitude, String longitude, String time, boolean cache) {
		
		
		if(!cache) {	//Is the aging time over? 
			url = "http://api.met.no/weatherapi/locationforecast/1.9/?lat="+latitude+";lon="+longitude+";msl="+altitude;
			factory = DocumentBuilderFactory.newInstance();
			try {
				URL url = new URL("http://api.met.no/weatherapi/locationforecast/1.9/?lat="+latitude+";lon="+longitude+";msl="+altitude);
				BufferedInputStream bis = new BufferedInputStream(url.openStream());
				FileOutputStream fis = new FileOutputStream("cache.xml");
				byte[] buffer = new byte[1024];
				int count=0;
				while((count = bis.read(buffer,0,1024)) != -1) {
		            fis.write(buffer, 0, count);
				}
				fis.close();
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			url = "cache.xml";
			factory = DocumentBuilderFactory.newInstance();
		}
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(url);
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
						NodeList temperature = times.getElementsByTagName("temperature");
						Node t = temperature.item(0);
						Element degrees = (Element) t;
						String old = degrees.getAttribute("value");
						degrees.setAttribute("value", "666");
						return old;
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
		return null; 
	}
	
}
