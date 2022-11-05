package com.ssafy.apt.model.dto;

import java.util.ArrayList;

public class Root {
	public Meta meta;
	public ArrayList<Document> documents;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}

	public Root(Meta meta, ArrayList<Document> documents) {
		super();
		this.meta = meta;
		this.documents = documents;
	}
}
