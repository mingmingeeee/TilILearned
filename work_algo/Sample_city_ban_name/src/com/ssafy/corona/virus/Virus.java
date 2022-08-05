package com.ssafy.corona.virus;

import java.io.Serializable;

// *** Comparable ***
// Virus 기준으로 정렬할 것 

// 파일 객체 -> Serializable
public class Virus implements Comparable<Virus>, Serializable{
	private String name;
	private int level;

	public Virus() {}
	public Virus(String name, int level) {
		setName(name);
		setLevel(level);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(getName()).append("\t")
		  .append(getLevel());
		return sb.toString();
	}
	@Override
	public int compareTo(Virus o) {
		// TODO Auto-generated method stub
		// 오름차순: 내꺼(this) - 전달받은 객체(o)
		// return this.level - o.level;
		
		// 내림차순: 반대로
		return o.level - this.level;
	}
}
