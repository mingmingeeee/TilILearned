package com.ssafy.publishing.vendor;

public class BookStore extends Vendor{
	private String storeId;  // 가게ID
	private int bookTotalCount;  // 보유 도서 수
	private int area;  // 가게 면적

	public BookStore() {}
	
	public BookStore(String name, int capacity, String storeId, int bookTotalCount, int area) {
		super(name, capacity);
		setStoreId(storeId);
		setBookTotalCount(bookTotalCount);
		setArea(area);
	}
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String campusId) {
		this.storeId = campusId;
	}
	public int getBookTotalCount() {
		return bookTotalCount;
	}
	public void setBookTotalCount(int bookTotalCount) {
		this.bookTotalCount = bookTotalCount;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString()).append("\t")
		  .append(getStoreId()).append("\t")
		  .append(getBookTotalCount()).append("\t")
		  .append(getArea());
		return sb.toString();
	}
}
