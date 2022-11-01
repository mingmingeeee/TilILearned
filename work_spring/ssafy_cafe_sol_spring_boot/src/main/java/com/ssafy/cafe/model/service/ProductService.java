package com.ssafy.cafe.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.cafe.model.dto.Product;

public interface ProductService {

	void removeAll();

	int getCount();

	int addProduct(Product product) throws IllegalStateException, IOException;

	int modifyProduct(Product product) throws IllegalStateException, IOException;
	
	int modifyProductFile(Product product) throws IllegalStateException, IOException;

	int removeProduct(Integer productId) throws IOException;

	Product getProduct(Integer productId);

	List<Product> getProducts();

}
