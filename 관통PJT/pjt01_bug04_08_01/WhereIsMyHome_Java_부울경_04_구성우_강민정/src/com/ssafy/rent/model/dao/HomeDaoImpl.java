package com.ssafy.rent.model.dao;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ssafy.rent.model.dto.EnvInfo;
import com.ssafy.rent.model.dto.HomeDeal;
import com.ssafy.rent.model.dto.HomeInfo;
import com.ssafy.rent.model.dto.HomePageBean;
import com.ssafy.rent.model.dto.StoreInfo;
import com.ssafy.rent.util.EnvCsvParser;
import com.ssafy.rent.util.HomeSaxParser;
import com.ssafy.rent.util.StoreCsvParser;

public class HomeDaoImpl implements HomeDao{
	
	private Map<String, HomeInfo> homeInfo;
	private Map<String, List<HomeDeal>> deals;
	private int size;
	private List<HomeDeal> search;
	private String[] searchType= {HomeDeal.APT_DEAL, HomeDeal.APT_RENT, HomeDeal.HOME_DEAL, HomeDeal.HOME_RENT};
	
	private List<StoreInfo> storeList;
	private List<EnvInfo> envList;
	
	public HomeDaoImpl() {
		loadData();
		storeLoadData();
		envLoadData();
	}
	
	/**
	 * 아파트 정보와 아파트 거래 정보를  xml 파일에서 읽어온다.
	 */
	public void loadData() {
		HomeSaxParser parser = new HomeSaxParser();
		homeInfo = parser.getHomeInfo();
		deals = parser.getDeals();
		size = parser.getSize();
		search = new ArrayList<HomeDeal>(size);
	}
	
	/**
	 * 스토어
	 * */
	 public void storeLoadData() {
		 StoreCsvParser parser = new StoreCsvParser();
		 storeList = parser.getStoreList();
	 }
	 
	 public void envLoadData() {
		 EnvCsvParser parser = new EnvCsvParser();
		 envList = parser.getEnvList();
	 }
	 
	 
	 public List<StoreInfo> storeSearch(String dong){
		 List<StoreInfo> finds = new LinkedList<StoreInfo>();
		 
		 for(int i =0; i<storeList.size(); i++) {
				if(storeList.get(i).getDong().equals(dong)) {
					finds.add(storeList.get(i));
				}
			}
			
			return finds;
	 }
	 
	 public List<EnvInfo> envSearch(String dong){
		 List<EnvInfo> finds = new LinkedList<EnvInfo>();
		 
		 for(int i =0; i<envList.size(); i++) {
				if(envList.get(i).getDong().equals(dong)) {
					System.out.println(envList.get(i));
					finds.add(envList.get(i));
					
				}
			}
			
			return finds;
	 }
	 
	 
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HomeInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 주택 목록
	 */
	public List<HomeDeal> searchAll(HomePageBean  bean){
		search.clear();
		List<HomeDeal> finds = new LinkedList<HomeDeal>();
		
		boolean[] type = bean.getSearchType();
		for (int i = 0, size=type.length; i < size; i++) {
			if(type[i]) {
				System.out.println(searchType[i]);
				search.addAll(deals.get(searchType[i]));
			}
		}
		System.out.println(search);
		String dong = bean.getDong();
		String aptName = bean.getAptname();
		if(dong!=null) {	
			for (HomeDeal deal : search) {
				if(deal.getDong() != null && deal.getDong().contains(dong)) {
					finds.add(deal);
				}
			}
		}else if(aptName != null) {
			for (HomeDeal deal : search) {
				if(deal.getDong() != null && deal.getAptName().contains(aptName)) {
					finds.add(deal);
				}
			}
		}else {
			finds = search;
		}
		return finds;
	}
	
	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환한다.<br/>
	 * 법정동+아파트명을 이용하여 HomeInfo에서 건축연도, 법정코드, 지번, 이미지 정보를 찾아서 HomeDeal에 setting한 정보를 반환한다. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HomeDeal search(int no) {
		// 파라미터는 리스트테이블의 번호
		// complete code #03
		// List<HomeDeal> search 로부터 no 에 해당하는 HomeDeal 정보를 검색하여 return 하도록 코드를 작성하세요.
		// 해당하는 no 가 없을 경우 null 을 리턴하세요.
		for(int i =0; i<search.size(); i++) {
			if(search.get(i).getNo() == no) {
				String key = search.get(i).getDong()+search.get(i).getAptName();
				if(homeInfo.get(key)!=null) 
					search.get(i).setImg(homeInfo.get(key).getImg());
				return search.get(i);
			}
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		HomeDaoImpl dao = new HomeDaoImpl();
//		//dao.loadData();
//		System.out.println(dao.search(1));
//		System.out.println("===========================법정동으로 검색=================================");
//		HomePageBean dongSearch = new HomePageBean();
//		dongSearch.setDong("사직동");
//		print(dao.searchAll(dongSearch));
//		
//		System.out.println("===========================아파트 이름으로 검색=================================");
//		HomePageBean nameSearch = new HomePageBean();
//		nameSearch.setAptname("신동아블루아광화문의 꿈");
//		print(dao.searchAll(nameSearch));
//		
//		System.out.println("===========================전체 검색=================================");
//		print(dao.searchAll(null));
//		System.out.println("============================================================");
//	}
	
	public static void print(List<HomeDeal> homes) {
		for (HomeDeal home : homes) {
			System.out.println(home);
		}
	}
}
