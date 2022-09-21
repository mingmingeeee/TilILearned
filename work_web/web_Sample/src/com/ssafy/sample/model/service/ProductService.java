package com.ssafy.sample.model.service;

import com.ssafy.sample.dto.Product;
import com.ssafy.sample.model.dao.ProductDao;

public class ProductService {

	public int regist(Product product) {
		
		private ProductDao dao = new ProductDao();
		
		int cnt = dao.insert(product);
		return 0;
	}

}
