package com.ssafy.ws;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.service.BookService;

public class WS_02_AOPTest extends AbstractTest {
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void testInsert() {
		bookService.insert(null);
		bookService.select("111-222-3333");
		bookService.search();
	}

}
