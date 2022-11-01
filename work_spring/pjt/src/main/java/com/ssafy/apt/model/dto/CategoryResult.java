package com.ssafy.apt.model.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryResult implements Serializable {

	@JsonProperty("meta")
	private Meta meta;
	@JsonProperty("documents")
	private List<CategoryDocument> documents = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

	public List<CategoryDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<CategoryDocument> documents) {
		this.documents = documents;
	}

	public CategoryResult() {
	}

}