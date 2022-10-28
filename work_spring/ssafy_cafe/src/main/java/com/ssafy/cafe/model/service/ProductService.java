package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.Product;

public interface ProductService {
	
	void removeAll() throws Exception;
	
	int getCount() throws Exception;

	int addProduct(Product product) throws Exception;

	int modifyProduct(Product product) throws Exception;

	// sql => string으로 넘겨도 상관 없고 특별하게 숫자로 바꿔야 할 때  parseInt
	int removeProduct(Integer productId) throws Exception;

	Product getProduct(Integer productId) throws Exception;

	List<Product> getProducts() throws Exception;

}
