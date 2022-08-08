package com.ssafy.corona.virus;

import java.io.Serializable;

public class Virus implements Comparable<Virus>, Serializable {
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
		return o.getLevel() - this.getLevel();
	}
}
