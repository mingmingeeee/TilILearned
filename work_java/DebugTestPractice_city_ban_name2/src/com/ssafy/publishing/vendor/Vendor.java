package com.ssafy.publishing.vendor;

import com.ssafy.publishing.book.Book;

public class Vendor implements Comparable<Vendor>{
	private String name;  // 이름
	private int capacity;  // 수용 가능 인원
	
	public Vendor() {};
	public Vendor(String name, int capacity) {
		setName(name);
		setCapacity(capacity);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public int hashCode() {
		final int prime=31;
		int result=1;
		result=prime*result+((name == null)? 0:name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null) return false;
		if(!(obj instanceof Vendor)) return false;
		Vendor other=(Vendor)obj;
		if(name==null){
			if(other.name!=null){
				return false;
			}
		}else if(!name.equals(other.name)){
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(getName()).append("\t")
		.append(getCapacity());
		return sb.toString();
	}
	@Override
	public int compareTo(Vendor o) {
		if(getCapacity() == o.getCapacity()) {
			if(this instanceof BookStore && o instanceof BookStore) {
				BookStore b1 = (BookStore) this;
				BookStore b2 = (BookStore) o;
				return b2.getStoreId().compareTo(b1.getStoreId());
			}
		}
		return getCapacity() - o.getCapacity();
		
	}
}