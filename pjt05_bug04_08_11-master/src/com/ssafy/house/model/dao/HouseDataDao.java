package com.ssafy.house.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ssafy.house.model.HouseDealDto;
import com.ssafy.house.model.HouseDto;
import com.ssafy.house.model.HouseInfoDto;
import com.ssafy.util.DBUtil;

public class HouseDataDao {
	private static final String SERVICE_KEY = "1CCpQofcu+F732VXbRualdEBulu+XJO9eWA4Y69MOKFgF2RC2mEJ8VGNS4xoDPgLGfH0AU9h0ED1l8s121pL0A==";
	private static final String URL = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade";

	private static DBUtil dbUtil;

	private HouseDataDao() {
		dbUtil = DBUtil.getInstance();
	}

	private static HouseDataDao houseDataDao = new HouseDataDao();

	public static HouseDataDao getHouseDataDao() {
		return houseDataDao;
	}

	public Map<String, String> selectSidoNames(Connection conn) throws SQLException {

		// 실행할 쿼리문 작성
		String sql = "SELECT dongCode, sidoName FROM dongcode WHERE dongcode LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "__00000000");

		ResultSet result = stmt.executeQuery();

		Map<String, String> map = new TreeMap<>(); // TreeMap을 사용하는 이유는 키 값 기준 오름차순 정렬을 위해
		while (result.next()) {
			map.put(result.getString("dongCode"), result.getString("sidoName"));
		}

		dbUtil.close(result);
		dbUtil.close(stmt);

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

		dbUtil.close(result);
		dbUtil.close(stmt);

		return map;
	}

	public Map<String, String> selectDongNames(Connection conn, String gugunCode) throws SQLException {
		// 실행할 쿼리문 작성
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT dongCode, dongName \n");
		sb.append("FROM dongcode \n");
		sb.append("WHERE dongcode LIKE ? \n");
		sb.append("AND dongName IS NOT NULL \n");

		String sql = sb.toString();

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, gugunCode + "_____");

		ResultSet result = stmt.executeQuery();

		Map<String, String> map = new TreeMap<>();
		while (result.next()) {
			map.put(result.getString("dongCode"), result.getString("dongName"));
		}

		dbUtil.close(result);
		dbUtil.close(stmt);

		return map;
	}

	public List<HouseDto> selectAllaptlistDeal(Connection conn, String sidoName, String gugunName, String dongName,
			String dongCode) throws SQLException {
		// 실행할 쿼리문 작성
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT *  \n");
		sb.append("FROM housedeal as a  \n");
		sb.append("INNER JOIN houseinfo as b \n");
		sb.append("ON a.aptCode = b.aptCode \n");
		sb.append("Where b.dongCode like ? \n");

		String sql = sb.toString();

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, dongCode);

		ResultSet result = stmt.executeQuery();

		List<HouseDto> list = new ArrayList<>();
		while (result.next()) {
			HouseDto houseDto = new HouseDto();
			houseDto.setApartmentName(result.getString("apartmentName")); // 아파트 이름
			// 거래금액
			houseDto.setDealAmount(result.getString("dealAmount"));
			// 면적
			houseDto.setArea(result.getString("area"));
			// 거래년도
			houseDto.setDealYear(result.getString("dealYear"));
			// 거래 달
			houseDto.setDealMonth(result.getString("dealMonth"));
			// 거래 날짜
			houseDto.setDealDay(result.getString("dealDay"));
			// 층
			houseDto.setFloor(result.getString("floor"));
			houseDto.setDong(result.getString("dong"));
			// 위도, 경도
			houseDto.setLat(result.getString("lat"));
			houseDto.setLng(result.getString("lng"));
			list.add(houseDto);
		}
		dbUtil.close(result);
		dbUtil.close(stmt);

		return list;
	}

	public Map<String, Object> requestRowHouseTrade(String regionCode, String dealYmd) {
		HttpHeaders headers = new HttpHeaders();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL).queryParam("pageNo", 1) // 페이지 번호
				.queryParam("numOfRows", 9999) // 한 페이지 결과 수 (9999는 한번에 모든 정보 가져오려고)
				.queryParam("LAWD_CD", regionCode) // 지역코드 (동코드 5자리)
				.queryParam("DEAL_YMD", dealYmd); // 계약월

		// 공공 데이터 포털에서 받은 인증키 포함시키기
		// 따로 작성하는 이유는 인증키에 "/" 포함되어 제대로 동작 안해서
		String url = builder.toUriString() + "&serviceKey=" + SERVICE_KEY;
		System.out.println(url);

		// 헤더 정보를 포함해서 요청 보낼 때 사용. 현재는 보낼 내용이 없음
		HttpEntity<?> entity = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

		Map<String, Object> result = response.getBody();
		return result;
	}

	public List<HouseInfoDto> listApt(Map<String, String> map) throws SQLException {
		List<HouseInfoDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select aptCode, buildYear, apartmentName, dongCode \n");
			sql.append("from houseinfo \n");
			String key = map.get("key");
			String word = map.get("word");
			if (!word.isEmpty()) {
				if ("subject".equals(key))
					sql.append("where subject like ? \n");
				else
					sql.append("where user_id = ? \n");
			}
			sql.append("order by aptCode desc limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if (!word.isEmpty()) {
				if ("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else
					pstmt.setString(++idx, word);
			}
			pstmt.setInt(++idx, Integer.parseInt(map.get("start")));
			pstmt.setInt(++idx, Integer.parseInt(map.get("spl")));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseInfoDto houseInfoDto = new HouseInfoDto();
				houseInfoDto.setAptCode(rs.getString("aptCode"));
				houseInfoDto.setBuildYear(rs.getString("buildYear"));
				houseInfoDto.setApartmentName(rs.getString("apartmentName"));
				houseInfoDto.setDongCode(rs.getString("dongCode"));
				list.add(houseInfoDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public int getAptListCnt(Map<String, String> map) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(aptCode) cnt \n");
			sql.append("from houseinfo \n");
			String key = map.get("key");
			String word = map.get("word");
			if (!word.isEmpty()) {
				if ("subject".equals(key))
					sql.append("where subject like ? \n");
				else
					sql.append("where user_id = ? \n");
			}
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if (!word.isEmpty()) {
				if ("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else
					pstmt.setString(++idx, word);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {

				return rs.getInt("cnt");
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return 0;
	}

	public HouseDto getApt(String aptCode) throws SQLException {
		HouseDto houseDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(
					"select aptCode, buildYear, apartmentName, sidoName, gugunName, dongName, jibun, A.dongCode, A.lng as lng, A.lat as lat, \n");
			sql.append(
					"roadName, roadnamebonbun, roadNameBubun, roadNameSeq, roadNameBasementCode, roadNameCode, bonbun, bubun, sigunguCode, eubmyundongCode, landCode \n");
			sql.append("from  housedata2.houseinfo as A \n");
			sql.append("join housedata2.dongcode as B \n");
			sql.append("on A.dongCode = B.dongCode \n");
			sql.append("where aptCode = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, aptCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				houseDto = new HouseDto();
				houseDto.setAptCode(aptCode);
				houseDto.setBuildYear(rs.getString("buildYear"));
				houseDto.setApartmentName(rs.getString("apartmentName"));
				StringBuilder address = new StringBuilder();
				address.append(rs.getString("sidoName")).append(" ");
				address.append(rs.getString("gugunName")).append(" ");
				address.append(rs.getString("dongName")).append(" ");
				address.append(rs.getString("jibun"));
				houseDto.setJibun(address.toString());
				houseDto.setDongCode(rs.getString("dongCode"));
				houseDto.setLat(rs.getString("lat"));
				houseDto.setLng(rs.getString("lng"));

				houseDto.setRoadName(rs.getString("roadName"));
				houseDto.setRoadNameBonbun(rs.getString("roadnamebonbun"));
				houseDto.setRoadNameBubun(rs.getString("roadNameBubun"));
				houseDto.setRoadNameSeq(rs.getString("roadNameSeq"));
				houseDto.setRoadNameBasementCode(rs.getString("roadNameBasementCode"));
				houseDto.setRoadNameCode(rs.getString("roadNameCode"));
				houseDto.setBonbun(rs.getString("bonbun"));
				houseDto.setBubun(rs.getString("bubun"));
				houseDto.setSigunguCode(rs.getString("sigunguCode"));
				houseDto.setEubmyundongCode(rs.getString("eubmyundongCode"));
				houseDto.setLandCode(rs.getString("landCode"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return houseDto;
	}

	public int writeApt(HouseDto houseDto) throws SQLException, Exception {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql0 = new StringBuilder();
			sql0.append("select max(aptCode)+1 as maxcode \n");
			sql0.append("from houseinfo \n");
			pstmt0 = conn.prepareStatement(sql0.toString());
			rs = pstmt0.executeQuery();
			if (rs.next()) {
				houseDto.setAptCode(rs.getString("maxcode"));
			}

			StringBuilder sql = new StringBuilder();
			sql.append("insert into houseinfo (aptCode, buildYear, apartmentName, dong, jibun, dongCode, lng, lat, ");
			sql.append("roadName, roadnamebonbun, roadNameBubun, roadNameSeq, roadNameBasementCode, "
					+ "roadNameCode, bonbun, bubun, sigunguCode, eubmyundongCode, landCode) \n");
			sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, ?) \n");

			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, houseDto.getAptCode());
			pstmt.setString(++idx, houseDto.getBuildYear());
			pstmt.setString(++idx, houseDto.getApartmentName());
			pstmt.setString(++idx, houseDto.getDong());
			pstmt.setString(++idx, houseDto.getJibun());
			pstmt.setString(++idx, houseDto.getDongCode());
			pstmt.setString(++idx, houseDto.getLat());
			pstmt.setString(++idx, houseDto.getLng());

			pstmt.setString(++idx, houseDto.getRoadName());
			pstmt.setString(++idx, houseDto.getRoadNameBonbun());
			pstmt.setString(++idx, houseDto.getRoadNameBubun());
			pstmt.setString(++idx, houseDto.getRoadNameSeq());
			pstmt.setString(++idx, houseDto.getRoadNameBasementCode());

			pstmt.setString(++idx, houseDto.getRoadNameCode());
			pstmt.setString(++idx, houseDto.getBonbun());
			pstmt.setString(++idx, houseDto.getBubun());
			pstmt.setString(++idx, houseDto.getSigunguCode());
			pstmt.setString(++idx, houseDto.getEubmyundongCode());
			pstmt.setString(++idx, houseDto.getLandCode());

			cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(rs, pstmt0, pstmt, conn);
		}
		return cnt;
	}

	public int dongCodeck(String dongCode) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(dongCode) \n");
			sql.append("from houseinfo \n");
			sql.append("where dongCode = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dongCode);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	public void modifyApt(HouseDto houseDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update houseinfo \n");
			sql.append("set buildYear = ?, apartmentName = ?, dong = ?,  jibun = ?, dongCode = ?, lng = ?, lat= ?, \n");
			sql.append(
					"roadName = ?, roadnamebonbun = ?, roadNameBubun = ?,  roadNameSeq = ?, roadNameBasementCode = ?, \n");
			sql.append(
					"roadNameCode = ?, bonbun = ?, bubun = ?,  sigunguCode = ?, eubmyundongCode = ?,  landCode= ? \n");
			sql.append("where aptCode = ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, houseDto.getBuildYear());
			pstmt.setString(++idx, houseDto.getApartmentName());
			pstmt.setString(++idx, houseDto.getDong());
			pstmt.setString(++idx, houseDto.getJibun());
			pstmt.setString(++idx, houseDto.getDongCode());
			pstmt.setString(++idx, houseDto.getLat());
			pstmt.setString(++idx, houseDto.getLng());

			pstmt.setString(++idx, houseDto.getRoadName());
			pstmt.setString(++idx, houseDto.getRoadNameBonbun());
			pstmt.setString(++idx, houseDto.getRoadNameBubun());
			pstmt.setString(++idx, houseDto.getRoadNameSeq());
			pstmt.setString(++idx, houseDto.getRoadNameBasementCode());

			pstmt.setString(++idx, houseDto.getRoadNameCode());
			pstmt.setString(++idx, houseDto.getBonbun());
			pstmt.setString(++idx, houseDto.getBubun());
			pstmt.setString(++idx, houseDto.getSigunguCode());
			pstmt.setString(++idx, houseDto.getEubmyundongCode());
			pstmt.setString(++idx, houseDto.getLandCode());
			pstmt.setString(++idx, houseDto.getAptCode());
			pstmt.executeUpdate();

		} finally {
			dbUtil.close(pstmt, conn);
		}

	}

	public void deleteApt(String aptCode) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			conn = dbUtil.getConnection();

			StringBuilder sql2 = new StringBuilder();
			sql2.append("delete from housedeal \n");
			sql2.append("WHERE aptCode= ?");
			pstmt2 = conn.prepareStatement(sql2.toString());
			pstmt2.setString(1, aptCode);
			pstmt2.executeUpdate();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from houseinfo \n");
			sql.append("where aptCode = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, aptCode);

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt2, pstmt, conn);
		}

	}

	public List<HouseDealDto> getAptSaleList(Connection conn, String aptCode) throws SQLException {
		// 실행할 쿼리문 작성
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * \n");
		sql.append("FROM housedata2.housedeal  \n");
		sql.append("WHERE aptCode = ? \n");

		PreparedStatement stmt = conn.prepareStatement(sql.toString());
		stmt.setString(1, aptCode);
		ResultSet result = stmt.executeQuery();

		List<HouseDealDto> list = new ArrayList<>();
		while (result.next()) {
			HouseDealDto houseDealDto = new HouseDealDto();
			houseDealDto.setNo(result.getString("no"));
			houseDealDto.setAptCode(aptCode);
			// 거래금액
			houseDealDto.setDealAmount(result.getString("dealAmount"));
			// 면적
			houseDealDto.setArea(result.getString("area"));
			// 거래년도
			houseDealDto.setDealYear(result.getString("dealYear"));
			// 거래 달
			houseDealDto.setDealMonth(result.getString("dealMonth"));
			// 거래 날짜
			houseDealDto.setDealDay(result.getString("dealDay"));
			// 층
			houseDealDto.setFloor(result.getString("floor"));
			// 위도, 경도
			list.add(houseDealDto);
		}
		dbUtil.close(result);
		dbUtil.close(stmt);

		return list;
	}

	public int aptsaleWrite(HouseDealDto houseDealDto) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql0 = new StringBuilder();
			sql0.append("select max(no)+1 as maxno \n");
			sql0.append("from housedeal \n");
			pstmt0 = conn.prepareStatement(sql0.toString());
			rs = pstmt0.executeQuery();
			if (rs.next()) {
				houseDealDto.setNo(rs.getString("maxno"));
			}

			StringBuilder sql = new StringBuilder();
			sql.append("insert into housedeal (no, dealAmount, dealYear, dealMonth, dealDay, area, floor, aptCode) \n");
			sql.append("values (?, ?, ?, ?, ?, ?, ?, ?) \n");

			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, houseDealDto.getNo());
			pstmt.setString(++idx, houseDealDto.getDealAmount());
			pstmt.setString(++idx, houseDealDto.getDealYear());
			pstmt.setString(++idx, houseDealDto.getDealMonth());
			pstmt.setString(++idx, houseDealDto.getDealDay());
			pstmt.setString(++idx, houseDealDto.getArea());
			pstmt.setString(++idx, houseDealDto.getFloor());
			pstmt.setString(++idx, houseDealDto.getAptCode());

			cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(rs, pstmt0, pstmt, conn);
		}
		return cnt;
	}

	public void aptsaledelete(String no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			conn = dbUtil.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from housedeal \n");
			sql.append("WHERE no = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, no);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	public HouseDealDto getAptsale(String no) throws SQLException {
		HouseDealDto houseDealDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select * \n");
			sql.append("from housedeal \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				houseDealDto = new HouseDealDto();
				houseDealDto.setNo(no);
				houseDealDto.setDealAmount(rs.getString("dealAmount"));
				houseDealDto.setDealYear(rs.getString("dealYear"));
				houseDealDto.setDealMonth(rs.getString("dealMonth"));
				houseDealDto.setDealDay(rs.getString("dealDay"));
				houseDealDto.setArea(rs.getString("area"));
				houseDealDto.setFloor(rs.getString("floor"));
				houseDealDto.setAptCode(rs.getString("aptCode"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return houseDealDto;
	}

	public void aptsaleModify(HouseDealDto houseDealDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update housedeal \n");
			sql.append("set dealAmount = ?, dealYear = ?, dealMonth = ?,  dealDay = ?, area = ?, floor = ? \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, houseDealDto.getDealAmount());
			pstmt.setString(++idx, houseDealDto.getDealYear());
			pstmt.setString(++idx, houseDealDto.getDealMonth());
			pstmt.setString(++idx, houseDealDto.getDealDay());
			pstmt.setString(++idx, houseDealDto.getArea());
			pstmt.setString(++idx, houseDealDto.getFloor());
			pstmt.setString(++idx, houseDealDto.getNo());

			pstmt.executeUpdate();

		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
