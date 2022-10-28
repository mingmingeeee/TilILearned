package com.ssafy.cafe.model;

import java.util.List;

public class Order {

	private Integer id;
	private String userId;
	private String orderTime;
	private Character completed;
	private List<OrderDetail> details;
	
	public Order() {}

	public Order(Integer id, String userId, String orderTime, Character completed, List<OrderDetail> details) {
		super();
		this.id = id;
		this.userId = userId;
		this.orderTime = orderTime;
		this.completed = completed;
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Character getCompleted() {
		return completed;
	}

	public void setCompleted(Character completed) {
		this.completed = completed;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", orderTime=");
		builder.append(orderTime);
		builder.append(", completed=");
		builder.append(completed);
		builder.append(", details=");
		builder.append(details);
		builder.append("]");
		return builder.toString();
	}

}
