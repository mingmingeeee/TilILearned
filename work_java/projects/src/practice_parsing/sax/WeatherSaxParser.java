package practice_parsing.sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import practice_parsing.Weather;

public class WeatherSaxParser extends DefaultHandler{
	
	String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2644056000";
	
	// 파싱될 내용 저장할 List
	private List<Weather> list = new ArrayList<>();
	// 현재 파싱하고 있는 대상 객체
	private Weather current;
	// 방금 읽은 텍스트 내용
	private String content;
	
	public List<Weather> getList(){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			
			parser.parse(url, this);
		} catch(IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("문서 시작");
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("문서 종료");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		// qName -> 시작하는 태그 
		if(qName.equals("data")){
			current = new Weather();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		
		if(qName.equals("hour")) {
			current.setHour(this.content);
		}else if(qName.equals("temp")) {
			current.setTemp(this.content);
		}else if(qName.equals("wfKor")){
			current.setWfKor(this.content);
		}else if(qName.equals("reh")) {
			current.setReh(this.content);
		}else if(qName.equals("data")) {
			list.add(current);
			current = null;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		this.content = new String(ch, start, length);
	}

}
