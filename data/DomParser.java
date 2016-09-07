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

public class DomParser {

	public static void main(String[] args) {
		ArrayList name = new ArrayList();
		ArrayList longitude = new ArrayList();
		ArrayList latitude = new ArrayList();
		ArrayList altitude = new ArrayList();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("places.xml");
			NodeList localityList = doc.getElementsByTagName("locality");
			NodeList locationList = doc.getElementsByTagName("location");
			for(int i = 0; i < localityList.getLength(); i++){
				Node l = localityList.item(i);
				if (l.getNodeType()==Node.ELEMENT_NODE){
					Element locality = (Element) l; 
					name.add(locality.getAttribute("name"));
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
		for (int i = 0; i < name.size(); i++){
			System.out.println(name.get(i) + " Longitude: " + longitude.get(i) +
					" Latitude: " + latitude.get(i) + " Altitude: " + altitude.get(i));
		}
		

	}
 
}
