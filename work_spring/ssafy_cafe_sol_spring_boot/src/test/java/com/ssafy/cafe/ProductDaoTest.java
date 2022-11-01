package com.ssafy.cafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.cafe.model.dto.Product;
import com.ssafy.cafe.model.mapper.ProductDao;

public class ProductDaoTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(ProductDaoTest.class);

	// 픽스처(fixture): 테스트를 수행하는데 필요한 정보나 객체
	@Autowired
	private ProductDao productDao;

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
		
	}

	@Test
	public void addAndGet() {  // 추가, 조회 테스트
		
		productDao.deleteAll();
		assertEquals(0, productDao.getCount());
		
		// 1. 추가 테스트
		assertEquals(1, productDao.insert(product1));
		assertEquals(1, productDao.insert(product2));
		assertEquals(2, productDao.getCount());
		
		// 2. 조회 테스트
		Product productget1 = productDao.select(product1.getId());
		assertEquals(product1.getId(), productget1.getId());
		assertEquals(product1.getImg(), productget1.getImg());
		assertEquals(product1.getName(), productget1.getName());
		assertEquals(product1.getOrgImg(), productget1.getOrgImg());
		assertEquals(product1.getPrice(), productget1.getPrice());
		assertEquals(product1.getType(), productget1.getType());
		
		Product productget2 = productDao.select(product2.getId());
		assertEquals(product2.getId(), productget2.getId());
		assertEquals(product2.getImg(), productget2.getImg());
		assertEquals(product2.getName(), productget2.getName());
		assertEquals(product2.getOrgImg(), productget2.getOrgImg());
		assertEquals(product2.getPrice(), productget2.getPrice());
		assertEquals(product2.getType(), productget2.getType());
	}
	
	@Test
	public void count() {  // count 메서드 테스트 
		
		productDao.deleteAll();
		assertEquals(0, productDao.getCount());
		
		assertEquals(1, productDao.insert(product1));
		assertEquals(1, productDao.getCount());
		
		assertEquals(1, productDao.insert(product2));
		assertEquals(2, productDao.getCount());
		
		assertEquals(1, productDao.insert(product3));
		assertEquals(3, productDao.getCount());
	}
	
	@Test
	public void addAndUpdate() {  // 추가, 수정 테스트
		
		productDao.deleteAll();
		assertEquals(0, productDao.getCount());
		
		// 1. 추가 테스트
		assertEquals(1, productDao.insert(product1));
		assertEquals(1, productDao.getCount());
		
		// 가격 변경
		product1.setPrice(15000);
		
		// 2. 수정 테스트
		assertEquals(1, productDao.update(product1));
		
		// 3. 도서 조회 테스트
		Product productget1 = productDao.select(product1.getId());
		assertEquals(product1.getId(), productget1.getId());
		assertEquals(product1.getImg(), productget1.getImg());
		assertEquals(product1.getName(), productget1.getName());
		assertEquals(product1.getOrgImg(), productget1.getOrgImg());
		assertEquals(product1.getPrice(), productget1.getPrice());
		assertEquals(product1.getType(), productget1.getType());
	}
	
	@Test
	public void addAndDelete() {  // 추가, 삭제 테스트
		
		productDao.deleteAll();
		assertEquals(0, productDao.getCount());
		
		// 1. 추가 테스트
		assertEquals(1, productDao.insert(product1));
		assertEquals(1, productDao.getCount());
		
		// 2. 삭제 테스트
		assertEquals(1, productDao.delete(product1.getId()));
		
		// 3. 조회 테스트
		Product productget1 = productDao.select(product1.getId());
		assertNull(productget1);
	}
	
	@Test
	public void selectAll() {  // 모두 조회 테스트
		
		productDao.deleteAll();
		assertEquals(0, productDao.getCount());
		
		// 1. 3개 추가
		assertEquals(1, productDao.insert(product1));
		assertEquals(1, productDao.insert(product2));
		assertEquals(1, productDao.insert(product3));
		assertEquals(3, productDao.getCount());
		
		// 2. 모두 조회 테스트
		List<Product> products = productDao.selectAll();
		assertEquals(3, products.size());
	}
	
}
