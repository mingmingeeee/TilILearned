package com.ssafy.cafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.OrderDetail;
import com.ssafy.cafe.model.dto.Product;
import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.mapper.OrderDao;
import com.ssafy.cafe.model.mapper.ProductDao;
import com.ssafy.cafe.model.mapper.UserDao;

public class OrderDaoTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(OrderDaoTest.class);

	// 픽스처(fixture): 테스트를 수행하는데 필요한 정보나 객체
	@Autowired
	private ProductDao productDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrderDao orderDao;

	private Product product1;
	private Product product2;
	private Product product3;
	private Product product4;
	private Product product5;
	private Product product6;
	private Product product7;
	private Product product8;
	private Product product9;
	private Product product10;

	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;
	private User user6;
	private User user7;
	private User user8;
	private User user9;
	private User user10;
	
	private List<Map<String, String>> orders;
	// 픽스처 끝

	@Before
	public void setUp() {

		this.product1 = new Product(1, "아메리카노", "coffee", 4100, "coffee1.png", "coffee1.png");
		this.product2 = new Product(2, "카페라떼", "coffee", 4500, "coffee2.png", "coffee2.png");
		this.product3 = new Product(3, "카라멜 마끼아또", "coffee", 4800, "coffee3.png", "coffee3.png");
		this.product4 = new Product(4, "카푸치노", "coffee", 4800, "coffee4.png", "coffee4.png");
		this.product5 = new Product(5, "모카라떼", "coffee", 4800, "coffee5.png", "coffee5.png");
		this.product6 = new Product(6, "민트라떼", "coffee", 4300, "coffee6.png", "coffee6.png");
		this.product7 = new Product(7, "화이트 모카라떼", "coffee", 4800, "coffee7.png", "coffee7.png");
		this.product8 = new Product(8, "자몽에이드", "coffee", 5100, "coffee8.png", "coffee8.png");
		this.product9 = new Product(9, "레몬에이드", "coffee", 5100, "coffee9.png", "coffee9.png");
		this.product10 = new Product(10, "초코칩 쿠키", "cookie", 1500, "cookie.png", "cookie.png");

		this.user1 = new User("ssafy01", "김싸피", "pass01", 5);
		this.user2 = new User("ssafy02", "이싸피", "pass02", 0);
		this.user3 = new User("ssafy03", "박싸피", "pass03", 3);
		this.user4 = new User("ssafy04", "최싸피", "pass04", 4);
		this.user5 = new User("ssafy05", "정싸피", "pass05", 5);
		this.user6 = new User("ssafy06", "강싸피", "pass06", 6);
		this.user7 = new User("ssafy07", "조싸피", "pass07", 7);
		this.user8 = new User("ssafy08", "윤싸피", "pass08", 8);
		this.user9 = new User("ssafy09", "장싸피", "pass09", 9);
		this.user10 = new User("ssafy10", "임싸피", "pass10", 20);
		
		orders = new ArrayList<>();
		// 주문1: 아메리카노 10잔
		HashMap<String, String> order1 = new HashMap<String, String>();
		order1.put("id", "1");
		order1.put("quantity", "10");
		orders.add(order1);

		// 주문2: 카페라떼 3잔
		HashMap<String, String> order2 = new HashMap<String, String>();
		order2.put("id", "2");
		order2.put("quantity", "3");
		orders.add(order2);

	}

	@Test
	public void addOrder() {

		/**
		 * 제품 추가
		 */
		productDao.deleteAll();
		assertEquals(0, productDao.getCount());

		// 추가 테스트
		assertEquals(1, productDao.insert(product1));
		assertEquals(1, productDao.insert(product2));
		assertEquals(1, productDao.insert(product3));
		assertEquals(3, productDao.getCount());

		/**
		 * 사용자 추가
		 */
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());

		// 추가 테스트
		assertEquals(1, userDao.insert(user1));
		assertEquals(1, userDao.insert(user2));
		assertEquals(1, userDao.insert(user3));
		assertEquals(3, userDao.getCount());

		/**
		 * 주문하기 테스트
		 */
		orderDao.deleteAll();
		assertEquals(0, orderDao.getCount());

		// 김싸피가 주문: Order를 먼저 DB에 추가하고 Order Id를 얻는다.
		Order order = new Order(null, user1.getId(), null, null, null);
		int cnt = orderDao.insertOrder(order);
		assertEquals(1, cnt);
		assertNotNull(order.getId());

		// 클라이언트로부터 전달받은 주문 상세정보를 DB 테이블 형태에 맞춰 리스트로 만들기
		List<OrderDetail> details = new ArrayList<>();
		for (Map<String, String> item : orders) {
			Integer orderId = order.getId();
			Integer productId = Integer.parseInt(item.get("id"));
			Integer quantity = Integer.parseInt(item.get("quantity"));

			OrderDetail detail = new OrderDetail(null, orderId, productId, quantity);
			details.add(detail);
		}

		// 주문 상세정보 DB에 저장
		cnt = 0;
		order.setDetails(details);
		if (details != null && !details.isEmpty()) {
			cnt += orderDao.registerDetail(order);
		}
		
		// 정상적으로 처리되었다면 주문 상세정보 2번 추가됨
		assertEquals(2, cnt);
	}
}
