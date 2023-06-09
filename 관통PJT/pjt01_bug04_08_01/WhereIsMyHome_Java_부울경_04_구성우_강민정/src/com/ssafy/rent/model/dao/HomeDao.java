package com.ssafy.rent.model.dao;

import java.util.List;

import com.ssafy.rent.model.dto.EnvInfo;
import com.ssafy.rent.model.dto.HomeDeal;
import com.ssafy.rent.model.dto.HomePageBean;
import com.ssafy.rent.model.dto.StoreInfo;

public interface HomeDao {
	/**
	 * 아파트 거래 정보를 xml 파일에서 로딩하는 기능 
	 */
	public void loadData();
		
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HomeInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 주택 목록
	 */
	public List<HomeDeal> searchAll(HomePageBean  bean);
	
	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HomeDeal search(int no);
	
	public List<StoreInfo> storeSearch(String dong);
	public List<EnvInfo> envSearch(String dong);
	
}
