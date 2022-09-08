package com.ssafy.rent.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ssafy.rent.model.dto.EnvInfo;

public class EnvCsvParser {
	
	private List<EnvInfo> envList = new ArrayList<>();
	
	public EnvCsvParser() {
		loadData();
	}
	
	private void loadData() {
		String path = "res/env.csv";
		List<List<String>> list = EnvCsvParser.readToList(path);
		for(int i=1; i<list.size(); i++) {
			List<String> line = list.get(i);
			String[] strings = line.get(4).split(" ");
			String dong = strings[2];
//			System.out.println(dong);
			EnvInfo si = new EnvInfo(line.get(0), line.get(1), line.get(2), line.get(3), dong);
			
			envList.add(si);
			
//			for(int j=0; j<line.size(); j++) {
//				System.out.print(line.get(j) + " ");
				
//			}
//			System.out.println();
		}
	}
	
	public List<EnvInfo> getEnvList(){
		return envList;
	}
	
	
	
	public static List<List<String>> readToList(String path) {
		List<List<String>> list = new ArrayList<List<String>>();
		File csv = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
			Charset.forName("UTF-8");
			String line = "";
			
			while((line=br.readLine()) != null) {
				String[] token = line.split(",");
				List<String> tempList = new ArrayList<String>(Arrays.asList(token));
				list.add(tempList);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {br.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		StoreCsvParser.loadData();
//	}
}
