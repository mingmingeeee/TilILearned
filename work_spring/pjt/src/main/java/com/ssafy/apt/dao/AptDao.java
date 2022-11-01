package com.ssafy.apt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ssafy.apt.model.dto.Area;
import com.ssafy.util.DBUtil;

public class AptDao {
	private static AptDao instance = null;
	private static final String URL = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade";
	private static final String SERVICE_KEY = "tVjgW6ovTuqohBqi6D6Gf%2B6MndIEx3CVmdwPQL7F8SCduFG6bWYlbRnuA7RVc%2Bzgf75D5UHpGLNVVDye4W%2BWvQ%3D%3D";
	private static final String DB_NAME = "housedata2";

	public static AptDao getInstance() {
		if (instance == null) {
			instance = new AptDao();
		}
		return instance;
	}

	public Map<String, String> selectSidoNames() throws SQLException {
		System.out.println("selectSidoNames called");
		// 실행할 쿼리문 작성
		String sql = "SELECT dongCode, sidoName FROM dongcode WHERE dongcode LIKE ?";

		Connection conn = DBUtil.getConnection(DB_NAME);
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, "__00000000");

		ResultSet result = stmt.executeQuery();

		Map<String, String> map = new TreeMap<>(); // TreeMap을 사용하는 이유는 키 값 기준 오름차순 정렬을 위해
		while (result.next()) {
			map.put(result.getString("dongCode"), result.getString("sidoName"));
		}

		DBUtil.close(result);
		DBUtil.close(stmt);
		DBUtil.close(conn);

		return map;
	}

	public Map<String, String> selectGuGunNames(String sidoCode) throws SQLException {
		System.out.println("selectGuGunNames called");
		// 실행할 쿼리문 작성
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT dongCode, gugunName \n");
		sb.append("FROM dongcode \n");
		sb.append("WHERE dongcode LIKE ? \n");
		sb.append("AND gugunName IS NOT NULL \n");

		String sql = sb.toString();
		Connection conn = DBUtil.getConnection(DB_NAME);
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, sidoCode + "___00000");

		ResultSet result = stmt.executeQuery();

		Map<String, String> map = new TreeMap<>();
		while (result.next()) {
			map.put(result.getString("dongCode"), result.getString("gugunName"));
		}

		DBUtil.close(result);
		DBUtil.close(stmt, conn);

		return map;
	}

	public JSONArray selectAll(String regionCode, String deal_ymd) throws SQLException {
		System.out.println("selectGuGunNames called");
		// 실행할 쿼리문 작성

		String sql = "select distinct * from housedata2.houseinfo where dongCode like 1117010100";
		Connection conn = DBUtil.getConnection(DB_NAME);
		PreparedStatement stmt = conn.prepareStatement(sql);

//		stmt.setString(1, regionCode);

		ResultSet result = stmt.executeQuery();
		Map<String, Object> map = new TreeMap<>();
		JSONArray jarray = new JSONArray();
		if (result.next()) {
			while (result.next()) {
				JSONObject json = new JSONObject();
				json.put("aptCode", result.getBigDecimal("aptCode"));
				json.put("buildYear", result.getString("buildYear"));
				json.put("roadName", result.getString("roadName"));
				json.put("roadNameBonbun", result.getString("roadNameBonbun"));
				json.put("roadNameBubun", result.getString("roadNameBubun"));
				json.put("sigunguCode", result.getString("sigunguCode"));
				json.put("dongCode", result.getString("dongCode"));
				json.put("dong", result.getString("dong"));
				json.put("apartmentName", result.getString("apartmentName"));
				json.put("jibun", result.getString("jibun"));
				jarray.put(json);
			}
		}
		DBUtil.close(result);
		DBUtil.close(stmt, conn);
		map.put("list", jarray);
		return jarray;

	}

//	public Map<String, Object> selectAll(String regionCode, String deal_ymd) {
//		HttpHeaders headers = new HttpHeaders();
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL).queryParam("pageNo", 1) // 페이지 번호
//				.queryParam("numOfRows", 9999) // 한 페이지 결과 수 (9999는 한번에 모든 정보 가져오려고)
//				.queryParam("LAWD_CD", regionCode) // 지역코드 (동코드 5자리)
//				.queryParam("DEAL_YMD", deal_ymd); // 계약월
//
//		// 공공 데이터 포털에서 받은 인증키 포함시키기
//		// 따로 작성하는 이유는 인증키에 "/" 포함되어 제대로 동작 안해서
//		String url = builder.toUriString() + "&serviceKey=" + SERVICE_KEY;
//		System.out.println(url);
//
//		// 헤더 정보를 포함해서 요청 보낼 때 사용. 현재는 보낼 내용이 없음
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//
//		RestTemplate restTemplate = new RestTemplate();
//		HttpEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
//
//		Map<String, Object> result = response.getBody();
//		return result;
//
//	}

	public int insertFavorite(String userId, String code) throws SQLException {
		System.out.println("insertFavorite called" + userId + code);
		// 실행할 쿼리문 작성
		Connection conn = DBUtil.getConnection(DB_NAME);
		String sql = "insert into housedata2.favarea (user_id, dongCode) values(?,?) ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.setString(1, userId);
			stmt.setString(2, code);
			int cnt = stmt.executeUpdate();
			System.out.println(cnt);
			return cnt;
		} finally {
			DBUtil.close(stmt, conn);
		}
	}

	public List<JSONObject> getFavorite(String userId) throws SQLException {
		Connection conn = DBUtil.getConnection(DB_NAME);
		System.out.println("getFavorite called, userId is" + userId);
		String sql = "SELECT distinct * FROM housedata2.dongcode a ,housedata2.favarea f where f.dongCode = a.dongcode and f.user_id=?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		List<JSONObject> favorites = new ArrayList<>();
		try {
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				JSONObject jobj = new JSONObject();
				jobj.put("dongCode", rs.getString("dongcode"));
				jobj.put("sidoName", rs.getString("sidoName"));
//				jobj.put("gugunName", rs.getString("gugunName"));
				jobj.put("dongName", rs.getString("dongName"));
				favorites.add(jobj);
			}
			return favorites;
		} finally {
			DBUtil.close(stmt, conn);
		}
	}

	public JSONObject getAptDealInfo(String aptCode) throws SQLException {
		Connection conn = DBUtil.getConnection(DB_NAME);
		System.out.println("getAptDealInfo " + aptCode);
		String sql = "SELECT * FROM housedata2.housedeal where aptCode like ?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		try {
			stmt.setString(1, aptCode);
			ResultSet rs = stmt.executeQuery();
			JSONObject jobj = new JSONObject();
			while (rs.next()) {
				jobj.put("dealAmount", rs.getString("dealAmount"));
				jobj.put("dealYear", rs.getInt("dealYear"));
				jobj.put("dealMonth", rs.getInt("dealMonth"));
				jobj.put("dealDay", rs.getInt("dealDay"));
				jobj.put("area", rs.getString("area"));
				jobj.put("floor", rs.getString("floor"));
				jobj.put("cancelDealType", rs.getString("cancelDealType"));
				jobj.put("aptCode", rs.getLong("aptCode"));
			}
			return jobj;
		} finally {
			DBUtil.close(stmt, conn);
		}
	}

	public List<Area> getFavorite2(String userId) throws SQLException {
		Connection conn = DBUtil.getConnection(DB_NAME);
		StringBuilder sql = new StringBuilder();
		sql.append("select * \n").append("from  housedata2.favarea as A \n").append("join housedata2.dongcode as B \n")
				.append("on A.dongCode = B.dongCode \n").append("where user_id= ? \n");
//		String sql = "SELECT * FROM housedata2.housedeal hd ,  housedata2.houseinfo hi where hi.aptCode = hd.aptCode and dongCode=1117010100";

		PreparedStatement stmt = conn.prepareStatement(sql.toString());
		List<Area> favorites = new ArrayList<>();
		try {
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Area newArea = new Area();
				newArea.setDongCode(rs.getString("dongCode"));
				newArea.setSidoName(rs.getString("sidoName"));
				newArea.setGugunName(rs.getString("gugunName"));
				newArea.setDongName(rs.getString("dongName"));
				newArea.setLat(rs.getString("lat"));
				newArea.setLng(rs.getString("lng"));
				favorites.add(newArea);
			}
			return favorites.size() > 0 ? favorites : null;
		} finally {

		}
	}
}
