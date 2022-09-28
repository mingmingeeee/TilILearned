package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.board.util.DBUtil;

public class HouseDataDao {
	
	private static final String SERVICE_KEY = "G8uhuvnu6rvHT6pD42axRVrWxR5GY/kwsEh72zEjrvwM7+0ZI4tRgzuBpx0rQyBFPb6CDda/xs1f3ezzPwTx6A==";
	private static final String URL = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade";

	public Map<String, String> selectSidoNames(Connection conn) throws SQLException {
		
		// 실행할 쿼리문 작성
		String sql = "SELECT dongCode, sidoName FROM dongcode WHERE dongcode LIKE ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, "__00000000");
		
		ResultSet result = stmt.executeQuery();
		
		Map<String, String> map = new TreeMap<>();  // TreeMap을 사용하는 이유는 키 값 기준 오름차순 정렬을 위해
		while (result.next()) {
			map.put(result.getString("dongCode"), result.getString("sidoName"));
		}
		
		DBUtil.close(result);
		DBUtil.close(stmt);
		
		return map;
	}

	public Map<String, String> selectGuGunNames(Connection conn, String sidoCode) throws SQLException {
		
		// 실행할 쿼리문 작성
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT dongCode, gugunName \n");
		sb.append("FROM dongcode \n");
		sb.append("WHERE dongcode LIKE ? \n");
		sb.append("AND gugunName IS NOT NULL \n");
		
		String sql = sb.toString();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, sidoCode + "___00000");
		
		ResultSet result = stmt.executeQuery();
		
		Map<String, String> map = new TreeMap<>();
		while (result.next()) {
			map.put(result.getString("dongCode"), result.getString("gugunName"));
		}
		
		DBUtil.close(result);
		DBUtil.close(stmt);
		
		return map;
	}

	public Map<String, Object> requestRowHouseTrade(String regionCode, String dealYmd) {
		HttpHeaders headers = new HttpHeaders();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
				.queryParam("pageNo", 1)  // 페이지 번호
				.queryParam("numOfRows", 9999)  // 한 페이지 결과 수 (9999는 한번에 모든 정보 가져오려고)
				.queryParam("LAWD_CD", regionCode)  // 지역코드(법정동 앞 5자리, 구/군 단위)
				.queryParam("DEAL_YMD", dealYmd);  // 계약월
		
		// 공공 데이터 포털에서 받은 인증키 포함시키기
		// 따로 작성하는 이유는 인증키에 "/" 포함되어 제대로 동작 안해서
		String url = builder.toUriString() + "&serviceKey=" + SERVICE_KEY;
		System.out.println(url);
		
		// 헤더 정보를 포함해서 요청 보낼 때 사용. 현재는 보낼 내용이 없음
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		// 공공데이터 API 호출하기
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Map> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				entity,
				Map.class);
		
		// 공공데이터 API 호출 결과를 Map에 담기
		Map<String, Object> result = response.getBody();
		return result;
	}

}
