package com.ssafy.cafe.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.User;

public interface OrderService {

	int addOrder(List<Map<String, String>> orders, User user);

	List<Map<String, Object>> getOrders();

	int modifyOrder(Order order);

	int removeOrder(Integer orderId);

}
