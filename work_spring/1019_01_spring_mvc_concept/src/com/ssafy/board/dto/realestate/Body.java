package com.ssafy.board.dto.realestate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "body")
public class Body {
	
	@XmlElement(name = "items")
	public Items items;
	@XmlElement(name = "numOfRows")
	public String numOfRows;
	@XmlElement(name = "pageNo")
	public String pageNo;
	@XmlElement(name = "totalCount")
	public String totalCount;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Body [items=");
		builder.append(items);
		builder.append(", numOfRows=");
		builder.append(numOfRows);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append("]");
		return builder.toString();
	}
	
}
