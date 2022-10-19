package com.ssafy.board.dto.realestate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class ResponseRealEstate {
	
	@XmlElement(name = "header")
    public Header header;
	
	@XmlElement(name = "body")
    public Body body;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseRealEstate [header=");
		builder.append(header);
		builder.append(", body=");
		builder.append(body);
		builder.append("]");
		return builder.toString();
	}
	
}
