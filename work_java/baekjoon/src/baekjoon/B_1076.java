package baekjoon;

import java.io.*;
import java.util.*;

public class B_1076 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
	
		long[] value = new long[3];
		long[] number = new long[3];
		
		for(int i=0; i<3; i++) {
			String s = in.readLine();
			
			switch(s) {
			
			case "black":
				value[i] = 0;
				number[i] = 1;
				break;
			case "brown":
				value[i] = 1;
				number[i] = 10;
				break;
			case "red":
				value[i] = 2;
				number[i] = 100;
				break;
			case "orange":
				value[i] = 3;
				number[i] = 1000;
				break;
			case "yellow":
				value[i] = 4;
				number[i] = 10_000;
				break;
			case "green":
				value[i] = 5;
				number[i] = 100_000;
				break;
			case "blue":
				value[i] = 6;
				number[i] = 1_000_000;
				break;
			case "violet":
				value[i] = 7;
				number[i] = 10_000_000;
				break;
			case "grey":
				value[i] = 8;
				number[i] = 100_000_000;
				break;
			case "white":
				value[i] = 9;
				number[i] = 1_000_000_000;
				break;
			
			}
		}
		
		long result = (value[0]*10 + value[1]) * number[2];
		System.out.println(result);
		
		
	}

}


	

