package com.ssafy.sample.dto;

public class Product {
	
	private String code;
	private String model;
	private String price;
	
	// 1. 기본 생성자
	public Product() {}
	
	// 2. 모든 필드를 초기화 하는 생성자
	public Product(String code, String model, String price) {
		super();
		this.code = code;
		this.model = model;
		this.price = price;
	}

	
	// 3. Getter/Setter
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	// 4. toString() 재정의
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [code=");
		builder.append(code);
		builder.append(", model=");
		builder.append(model);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}


}
