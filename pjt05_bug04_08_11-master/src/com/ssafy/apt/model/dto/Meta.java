package com.ssafy.apt.model.dto;

public class Meta {
	public Object same_name;

	public Meta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int pageable_count;
	public int total_count;
	public boolean is_end;

	public Meta(Object same_name, int pageable_count, int total_count, boolean is_end) {
		super();
		this.same_name = same_name;
		this.pageable_count = pageable_count;
		this.total_count = total_count;
		this.is_end = is_end;
	}

	public Object getSame_name() {
		return same_name;
	}

	public void setSame_name(Object same_name) {
		this.same_name = same_name;
	}

	public int getPageable_count() {
		return pageable_count;
	}

	public void setPageable_count(int pageable_count) {
		this.pageable_count = pageable_count;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public boolean isIs_end() {
		return is_end;
	}

	public void setIs_end(boolean is_end) {
		this.is_end = is_end;
	}
}