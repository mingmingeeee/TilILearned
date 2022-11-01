package com.ssafy.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.apt.model.dto.AptInfo;

public class AptSaxHandler extends DefaultHandler {

	// 파싱한 아파트객체를 넣을 리스트
	private List<AptInfo> aptList;
	// 파싱한 아파트
	private AptInfo aptInfo;
	// character 메소드에서 저장할 문자열 변수
	private String str;

	public AptSaxHandler() {
		aptList = new ArrayList<>();
	}

	public void startElement(String uri, String localName, String name, Attributes att) {
		// 시작 태그를 만났을 때 발생하는 이벤트
		if (name.equals("item")) {
			aptInfo = new AptInfo();
			aptList.add(aptInfo);
		}
	}

	public void endElement(String uri, String localName, String name) {
		// 끝 태그를 만났을 때,
		if (name.equals("갱신요구권사용")) {
			aptInfo.setRenewalRequest(str);
		} else if (name.equals("건축년도")) {
			aptInfo.setYearOfConstruction(checkNull(str));
		} else if (name.equals("계약구분")) {
			aptInfo.setContractClassification(str);
		} else if (name.equals("계약기간")) {
			aptInfo.setTerm(str);
		} else if (name.equals("년")) {
			aptInfo.setYear(checkNull(str));
		} else if (name.equals("법정동")) {
			aptInfo.setDong(str);
		} else if (name.equals("보증금액")) {
			aptInfo.setGuarantee(str);
		} else if (name.equals("아파트")) {
			aptInfo.setAptName(str);
		} else if (name.equals("월")) {
			aptInfo.setMonth(str);
		} else if (name.equals("월세금액")) {
			aptInfo.setMonthlyRent(checkNull(str));
		} else if (name.equals("일")) {
			aptInfo.setDay(checkNull(str));
		} else if (name.equals("전용면적")) {
			aptInfo.setDedicatedArea(str);
		} else if (name.equals("종전계약보증금")) {
			aptInfo.setContractDeposit(checkNull(str));
		} else if (name.equals("종전계약월세")) {
			aptInfo.setContractMonthlyRent(str);
		} else if (name.equals("지번")) {
			aptInfo.setJibun(str);
		} else if (name.equals("지역코드")) {
			aptInfo.setAreaCode(str);
		} else if (name.equals("층")) {
			aptInfo.setFloor(checkNull(str));
		}
	}

	public void characters(char[] ch, int start, int length) {
		// 태그와 태그 사이의 내용을 처리
		str = new String(ch, start, length).trim();
	}

	public List<AptInfo> getAptInfoList() {
		return aptList;
	}

	public void setAptInfoList(List<AptInfo> aptList) {
		this.aptList = aptList;
	}

	public static String checkNull(String str) {
		str=str.trim();
		return (str.isEmpty() || str == null) ? "0" : str;
	}
}