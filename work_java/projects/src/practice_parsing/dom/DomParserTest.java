package practice_parsing.dom;

import java.util.*;

import practice_parsing.Weather;

public class DomParserTest {
	public static void main(String[] args) {
		WeatherDomParser parser = new WeatherDomParser();
		List<Weather> list = parser.getWeather();
		
		for(Weather w : list) {
			System.out.println(w);
		}
	}
}
