package com.ssafy.apt.model.dto;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoGeoRes {
	@JsonProperty("meta")
	private HashMap<String, Object> meta;
	@JsonProperty("documents")
	private List<Documents> documents;
	public HashMap<String, Object> getMeta() {
		return meta;
	}
	public void setMeta(HashMap<String, Object> meta) {
		this.meta = meta;
	}
	public List<Documents> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}
}