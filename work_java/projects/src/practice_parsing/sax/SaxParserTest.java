package practice_parsing.sax;

import java.util.List;

import practice_parsing.Weather;

public class SaxParserTest {
	
	public static void main(String[] args) {
		WeatherSaxParser handler = new WeatherSaxParser();
		
		List<Weather> list = handler.getList();
		for(Weather w : list) {
			System.out.println(w);
		}
	}

}
