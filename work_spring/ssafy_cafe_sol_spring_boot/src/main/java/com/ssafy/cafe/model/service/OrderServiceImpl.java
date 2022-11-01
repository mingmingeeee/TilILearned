package com.ssafy.cafe.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.OrderDetail;
import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.mapper.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao;
	
	@Autowired
	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	@Transactional
	public int addOrder(List<Map<String, String>> orders, User user) {
		
		// Order를 먼저 DB에 추가하고 Order Id를 얻는다.
		Order order = new Order(null, user.getId(), null, null, null);
		int cnt = orderDao.insertOrder(order);
		
		// 클라이언트로 부터 전달받은 주문 상세정보를 DB 테이블 형태에 맞춰 리스트로 만들기
		List<OrderDetail> details = new ArrayList<>();
		for (Map<String, String> item : orders) {
			Integer orderId = order.getId();
			Integer productId = Integer.parseInt(item.get("id"));
			Integer quantity = Integer.parseInt(item.get("quantity"));
			
			OrderDetail detail = new OrderDetail(null, orderId, productId, quantity);
			details.add(detail);
		}
		
		// 주문 상세정보 DB에 저장
		order.setDetails(details);
		if (details != null && !details.isEmpty()) {
			cnt = orderDao.registerDetail(order);
		}
		
		return cnt;
	}

	@Override
	public List<Map<String, Object>> getOrders() {
		return orderDao.selectAll();
	}

	@Override
	public int modifyOrder(Order order) {
		return orderDao.update(order);
	}

	@Override
	public int removeOrder(Integer orderId) {
		return orderDao.delete(orderId);
	}

}
