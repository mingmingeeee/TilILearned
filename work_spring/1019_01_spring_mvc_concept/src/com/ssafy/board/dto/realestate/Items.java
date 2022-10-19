package com.ssafy.board.dto.realestate;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
public class Items {

	@XmlElement(name = "item")
	public ArrayList<Item> item;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Items [item=");
		builder.append(item);
		builder.append("]");
		return builder.toString();
	}
	
}
