package com.ssafy.util;

public class Pagenation {
	public int listSize = SizeConstant.SIZE_PER_LIST; // 초기값으로 목록개수를 10으로 셋팅
	private static int rangeSize = 5; // 초기값으로 페이지범위를 10으로 셋팅
	private static int page; // 현재 목록의 페이지 번호
	private static int range;// 각 페이지 범위 시작 번호
	private static int listCnt; // 전체 게시물의 개수
	private static int pageCnt; // 전체 페이지 범위의 개수
	private static int startPage; // 각 페이지 범위 시작 번호
	private static int startList; 
	private static int endPage; // 각 페이지 범위 끝 번호
	private static boolean prev; // 이전 페이지 여부
	private static boolean next; // 다음 페이지 여부

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getRangeSize() {
		return rangeSize;
	}

	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getListCnt() {
		return listCnt;
	}

	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getStartList() {
		return startList;
	}

	public void setStartList(int startList) {
		this.startList = startList;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public void pageInfo(int page, int range, int listCnt) { // page: 현재 페이지 정보, rage: 현재 페이지 범위 정보, listCnt: 게시물의 총 개수
		this.page = page;
		this.range = range;
		this.listCnt = listCnt;
		// 전체 페이지수 
		this.pageCnt = (int) Math.ceil(listCnt / listSize) + 1;
		if (listCnt % listSize == 0) {
			this.pageCnt = (int) Math.ceil(listCnt / listSize);
		}
		// 시작 페이지
		this.startPage = (range - 1) * rangeSize + 1;
		// 끝 페이지
		this.endPage = range * rangeSize;
		// 게시판 시작번호
		this.startList = (page - 1) * listSize;
		// 이전 버튼 상태
		this.prev = range == 1 ? false : true;
		// 다음 버튼 상태
		this.next = endPage > pageCnt ? false : true;
		if (this.endPage > this.pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pagenation [listSize=");
		builder.append(listSize);
		builder.append(", getListSize()=");
		builder.append(getListSize());
		builder.append(", getRangeSize()=");
		builder.append(getRangeSize());
		builder.append(", getPage()=");
		builder.append(getPage());
		builder.append(", getRange()=");
		builder.append(getRange());
		builder.append(", getListCnt()=");
		builder.append(getListCnt());
		builder.append(", getPageCnt()=");
		builder.append(getPageCnt());
		builder.append(", getStartPage()=");
		builder.append(getStartPage());
		builder.append(", getStartList()=");
		builder.append(getStartList());
		builder.append(", getEndPage()=");
		builder.append(getEndPage());
		builder.append(", isPrev()=");
		builder.append(isPrev());
		builder.append(", isNext()=");
		builder.append(isNext());
		builder.append("]");
		return builder.toString();
	}

	
}
