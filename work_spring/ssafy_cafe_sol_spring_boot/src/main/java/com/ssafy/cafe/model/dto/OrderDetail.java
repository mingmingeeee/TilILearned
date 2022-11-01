package com.ssafy.cafe.model.dto;

public class OrderDetail {

	private Integer id;
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	
	public OrderDetail() {}

	public OrderDetail(Integer id, Integer orderId, Integer productId, Integer quantity) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDetail [id=");
		builder.append(id);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", productId=");
		builder.append(productId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}

}
