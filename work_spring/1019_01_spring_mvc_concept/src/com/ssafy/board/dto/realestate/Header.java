package com.ssafy.board.dto.realestate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "header")
public class Header {
	
	@XmlElement(name = "resultCode")
    public String resultCode;
	@XmlElement(name = "resultMsg")
    public String resultMsg;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Header [resultCode=");
		builder.append(resultCode);
		builder.append(", resultMsg=");
		builder.append(resultMsg);
		builder.append("]");
		return builder.toString();
	}
	
}
