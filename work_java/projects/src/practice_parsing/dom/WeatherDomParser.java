package practice_parsing.dom;

import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import practice_parsing.Weather;

// http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2644056000

public class WeatherDomParser {
	
	private List<Weather> list = new ArrayList<Weather>();

	public List<Weather> getWeather() {
		
		try {
			// XML 파싱
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// parse -> url 가능
			Document doc = builder.parse("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2644056000");
			
			// 최상위 element -> 첫 번째 요소 가져오기 
			Element root = doc.getDocumentElement();
			parse(root);
			
		}catch(IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
		}
		
		return list;
	}


	private void parse(Element root) {
		NodeList weathers = root.getElementsByTagName("data");
		for(int i=0; i<weathers.getLength(); i++) {
			Node child = weathers.item(i);
			list.add(getWeather(child));
		}
	}
	
	private Weather getWeather(Node node) {
		Weather weather = new Weather();
		
		NodeList subNodes = node.getChildNodes();
		
		for(int j=0; j<subNodes.getLength(); j++) {
			Node sub = subNodes.item(j);
			if(sub.getNodeName().equals("hour")) {
				weather.setHour(sub.getTextContent());
			}else if(sub.getNodeName().equals("temp")) {
				weather.setTemp(sub.getTextContent());
			}else if(sub.getNodeName().equals("wfKor")) {
				weather.setWfKor(sub.getTextContent());
			}else if(sub.getNodeName().equals("reh")) {
				weather.setReh(sub.getTextContent());
			}
		}
		
		return weather;
	}
	
	

}
