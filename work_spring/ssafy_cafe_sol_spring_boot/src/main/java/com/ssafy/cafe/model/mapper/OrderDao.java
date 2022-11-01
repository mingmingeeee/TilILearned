package com.ssafy.cafe.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.cafe.model.dto.Order;

public interface OrderDao {
	
	void deleteAll();
	
	int getCount();

	int insertOrder(Order order);

	int registerDetail(Order order);

	List<Map<String, Object>> selectAll();

	int update(Order order);

	int delete(Integer orderId);

}
