package com.ssafy.ws.model.dto;

public class SearchCondition {
	
	public final int countPerPage = 10;
	
	private String key = "none";
	
	private String word;
	
	private String orderBy = "none";
	
	private String orderByDir = "asc";
	
	private int currentPage = 1;
	
	private boolean limit = true;
	
	public SearchCondition() {}

	public SearchCondition(String key, String word, String orderBy, String orderByDir, int currentPage) {
		this.key = key;
		this.word = word;
		this.orderBy = orderBy;
		this.orderByDir = orderByDir;
		this.currentPage = currentPage;
	}
	
	public SearchCondition(String key, String word, String orderBy, String orderByDir) {
		this(key, word, orderBy, orderByDir, 1);
	}
	
	public SearchCondition(String key, String word, String orderBy) {
		this(key, word, orderBy, "asc");
	}
	
	public SearchCondition(String key, String word) {
		this(key, word, "none");
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderByDir() {
		return orderByDir;
	}

	public void setOrderByDir(String orderByDir) {
		this.orderByDir = orderByDir;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isLimit() {
		return limit;
	}

	public void setLimit(boolean limit) {
		this.limit = limit;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public int getOffset() {
		return (this.currentPage - 1) * countPerPage;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchCondition [countPerPage=");
		builder.append(countPerPage);
		builder.append(", key=");
		builder.append(key);
		builder.append(", word=");
		builder.append(word);
		builder.append(", orderBy=");
		builder.append(orderBy);
		builder.append(", orderByDir=");
		builder.append(orderByDir);
		builder.append(", currentPage=");
		builder.append(currentPage);
		builder.append(", limit=");
		builder.append(limit);
		builder.append("]");
		return builder.toString();
	}


}
