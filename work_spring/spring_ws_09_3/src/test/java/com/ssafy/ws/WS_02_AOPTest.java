package com.ssafy.ws;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.dto.SearchCondition;
import com.ssafy.ws.model.service.BookService;

public class WS_02_AOPTest extends AbstractTest {
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void testInsert() throws IllegalStateException, IOException {
		bookService.insert(null);
		bookService.select("111-222-3333");
		bookService.search(new SearchCondition());
	}

}
