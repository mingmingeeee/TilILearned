package com.ssafy.cafe.model;

public class Product {

	private Integer id;
	private String name;
	private String type;
	private Integer price;
	private String img;
	private String orgImg;
	
	public Product() {}

	public Product(Integer id, String name, String type, Integer price, String img, String orgImg) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.img = img;
		this.orgImg = orgImg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", price=");
		builder.append(price);
		builder.append(", img=");
		builder.append(img);
		builder.append(", orgImg=");
		builder.append(orgImg);
		builder.append("]");
		return builder.toString();
	}

}
