package hwalgo12_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		
		String[] split;
		
		Ref[] refs = new Ref[N];
		for(int i=0; i<N; i++) {
			split = in.readLine().split(" ");
			refs[i] = new Ref(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}
		
		List<Ref> result = getRef(refs);
		
		
		System.out.println(result.size());
		
	}
	
	private static List<Ref> getRef(Ref[] refs){
		
		List<Ref> result = new ArrayList<Ref>();
		Arrays.sort(refs);
		
		result.add(refs[0]);
		for(int i=1; i<refs.length; i++) {
			if(result.get(result.size()-1).end < refs[i].start) {
				result.add(refs[i]);
			}
		}
		return result;
		
	}

}

class Ref implements Comparable<Ref>{
	
	int start;
	int end;
	
	public Ref(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Ref o) {
		// TODO Auto-generated method stub
		return this.end == o.end ? this.start - o.start : this.end - o.end ;
	}
	
}