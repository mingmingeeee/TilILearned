package com.ssafy.cafe.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.cafe.model.dto.Product;
import com.ssafy.cafe.model.mapper.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public void removeAll() {
		productDao.deleteAll();
	}

	@Override
	public int getCount() {
		return productDao.getCount();
	}

	@Override
	public int addProduct(Product product) throws IllegalStateException, IOException {
		
		MultipartFile file = product.getUpfile();

		// 클라이언트로 부터 전달받은 파일이 존재하면 아래와 같이 처리
		if (file != null && file.getSize() > 0) {

			// 파일을 저장할 폴더 지정
			Resource resource = resourceLoader.getResource("classpath:static/assets/img");

			// 서버에 저장할 파일 이름을 생성
			String img = System.currentTimeMillis() + "_" + file.getOriginalFilename();

			// 실제 파일이름(사용자가 올린 파일이름)
			String orgImg = file.getOriginalFilename();

			product.setImg(img);
			product.setOrgImg(orgImg);

			file.transferTo(new File(resource.getFile().getCanonicalPath() + "/" + product.getImg()));
		}
		
		return productDao.insert(product);
	}

	@Override
	public int modifyProduct(Product product) throws IllegalStateException, IOException {
		return productDao.update(product);
	}
	
	@Override
	public int modifyProductFile(Product product) throws IllegalStateException, IOException {
		
		/**
		 *  새로 업로드한 파일 추가
		 */
		MultipartFile file = product.getUpfile();

		// 클라이언트로 부터 전달받은 파일이 존재하면 아래와 같이 처리
		if (file != null && file.getSize() > 0) {
			
			/**
			 *  기존 파일 삭제
			 */
			// 파일이 저장된 폴더 가져오기
			Resource resource = resourceLoader.getResource("classpath:static/assets/img");
			
			// 기존 파일 삭제하기
			Product selectedProduct = productDao.select(product.getId());
			File selectedImgFile = new File(resource.getFile().getCanonicalPath() + "/" + selectedProduct.getImg());
			if (selectedImgFile.exists() && selectedImgFile.isFile()) {
				selectedImgFile.delete();
			}

			// 서버에 저장할 파일 이름을 생성
			String img = System.currentTimeMillis() + "_" + file.getOriginalFilename();

			// 실제 파일이름(사용자가 올린 파일이름)
			String orgImg = file.getOriginalFilename();

			product.setImg(img);
			product.setOrgImg(orgImg);

			file.transferTo(new File(resource.getFile().getCanonicalPath() + "/" + product.getImg()));
		}
		
		return productDao.update(product);
	}

	@Override
	public int removeProduct(Integer productId) throws IOException {
		
		/**
		 *  기존 파일 삭제
		 */
		// 파일이 저장된 폴더 가져오기
		Resource resource = resourceLoader.getResource("classpath:static/assets/img");
		
		// 기존 파일 삭제하기
		Product selectedProduct = productDao.select(productId);
		File selectedImgFile = new File(resource.getFile().getCanonicalPath() + "/" + selectedProduct.getImg());
		if (selectedImgFile.exists() && selectedImgFile.isFile()) {
			selectedImgFile.delete();
		}
		
		return productDao.delete(productId);
	}

	@Override
	public Product getProduct(Integer productId) {
		return productDao.select(productId);
	}

	@Override
	public List<Product> getProducts() {
		return productDao.selectAll();
	}

}
