package com.ssafy.cafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.Product;
import com.ssafy.cafe.model.mapper.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;
	
	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public void removeAll() throws Exception {
		productDao.deleteAll();
	}

	@Override
	public int getCount() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getCount();
	}

	@Override
	public int addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return productDao.insert(product);
	}

	@Override
	public int modifyProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return productDao.update(product);
	}
	
	@Override
	public List<Product> getProducts() throws Exception {
		// TODO Auto-generated method stub
		return productDao.selectAll();
	}

	@Override
	public int removeProduct(Integer  productId) throws Exception {
		// TODO Auto-generated method stub
		return productDao.delete(productId);
	}

	@Override
	public Product getProduct(Integer  productId) throws Exception {
		// TODO Auto-generated method stub
		return productDao.select(productId);
	}

}
