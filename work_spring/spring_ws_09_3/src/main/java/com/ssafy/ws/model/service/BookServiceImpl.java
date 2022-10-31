package com.ssafy.ws.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ws.model.dao.BookDao;
import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public int insert(Book book) throws IllegalStateException, IOException {
		
		MultipartFile file = book.getUpfile();
		
		if (file != null && file.getSize() > 0) {

			// 파일을 저장할 폴더 지정
			Resource resource = resourceLoader.getResource("resources/upload");

			// 서버에 저장할 파일 이름을 생성
			String img = System.currentTimeMillis() + "_" + file.getOriginalFilename();

			// 실제 파일이름(사용자가 올린 파일이름)
			String orgImg = file.getOriginalFilename();

			book.setImg(img);
			book.setOrgImg(orgImg);

			file.transferTo(new File(resource.getFile().getCanonicalPath() + "/" + book.getImg()));
		}
		return bookDao.insert(book);
	}

	@Override
	public int update(Book book) {
		return bookDao.update(book);
	}

	@Override
	public int delete(String isbn) {
		return bookDao.delete(isbn);
	}

	@Override
	public Book select(String isbn) {
		return bookDao.select(isbn);
	}

	@Override
	public List<Book> search(SearchCondition condition) {
		return bookDao.search(condition);
	}

	@Override
	public void deleteAll() {
		bookDao.deleteAll();
	}

	@Override
	public int getCount() {
		return bookDao.getCount();
	}

}
