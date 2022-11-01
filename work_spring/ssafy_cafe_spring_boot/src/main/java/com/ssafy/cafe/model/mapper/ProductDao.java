package com.ssafy.cafe.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.cafe.model.dto.Product;

public interface ProductDao {
	
	void deleteAll() throws SQLException;
	
	int getCount() throws SQLException;

	int insert(Product product) throws SQLException;

	int update(Product product) throws SQLException;

	int delete(Integer productId) throws SQLException;

	Product select(Integer productId) throws SQLException;

	List<Product> selectAll() throws SQLException;

	

}
