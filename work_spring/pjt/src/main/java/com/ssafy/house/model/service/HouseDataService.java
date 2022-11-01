//package com.ssafy.house.model.service;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.ssafy.board.model.BoardDto;
//import com.ssafy.board.model.dao.BoardDao;
//import com.ssafy.board.model.dao.BoardDaoImpl;
//import com.ssafy.board.model.service.BoardService;
//import com.ssafy.board.model.service.BoardServiceImpl;
//import com.ssafy.house.model.HouseDealDto;
//import com.ssafy.house.model.HouseDto;
//import com.ssafy.house.model.HouseInfoDto;
//import com.ssafy.house.model.dao.HouseDataDao;
//import com.ssafy.util.DBUtil;
//import com.ssafy.util.SizeConstant;
//
//public class HouseDataService {
//	private static final String DB_NAME = "housedata2";
//
//	private static HouseDataService service = new HouseDataService();
//	private HouseDataDao dao;
//
//	public static HouseDataService getHouseDataService() {
//		return service;
//	}
//
//	private DBUtil dbUtil;
//
//	private HouseDataService() {
//		dbUtil = DBUtil.getInstance();
//		dao = HouseDataDao.getHouseDataDao();
//	}
//
//	public Map<String, String> selectSidoNames() throws SQLException {
//		Connection conn = null;
//		Map<String, String> map = null;
//
//		try {
//			conn = dbUtil.getConnection(DB_NAME);
//			map = dao.selectSidoNames(conn);
//		} finally {
//			dbUtil.close(conn);
//		}
//		return map;
//	}
//
//	public Map<String, String> selectSidoNames(String sidoCode) throws SQLException {
//		Connection conn = null;
//		Map<String, String> map = null;
//
//		try {
//			conn = dbUtil.getConnection(DB_NAME);
//			map = dao.selectGuGunNames(conn, sidoCode);
//		} finally {
//			dbUtil.close(conn);
//		}
//		return map;
//	}
//
//	public Map<String, String> selectGugunNames(String gugunCode) throws SQLException {
//		Connection conn = null;
//		Map<String, String> map = null;
//
//		try {
//			conn = dbUtil.getConnection(DB_NAME);
//			map = dao.selectDongNames(conn, gugunCode);
//		} finally {
//			dbUtil.close(conn);
//		}
//		return map;
//	}
//
//	public List<HouseDto> selectAllaptlistDeal(String sidoName, String gugunName, String dongName, String dongCode)
//			throws SQLException {
//		Connection conn = null;
//		List<HouseDto> list = null;
//
//		try {
//			conn = dbUtil.getConnection(DB_NAME);
//			list = dao.selectAllaptlistDeal(conn, sidoName, gugunName, dongName, dongCode);
//		} finally {
//			dbUtil.close(conn);
//		}
//		return list;
//	}
//
//	public Map<String, Object> getRowHouseTrade(String regionCode, String dealYmd) {
//
//		Map<String, Object> response = dao.requestRowHouseTrade(regionCode, dealYmd);
//		return response;
//	}
//
//	public List<HouseInfoDto> listApt(Map<String, String> map) throws SQLException {
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int spl = SizeConstant.SIZE_PER_LIST;
//		
//		int start = (pgno - 1) * spl;
//		map.put("start", start + "");
//		map.put("spl", spl + "");
//		
//		return dao.listApt(map);
//	}
//
//	public int getAptListCnt(Map<String, String> map) throws SQLException {
//		return dao.getAptListCnt(map);
//	}
//
//	public HouseDto getApt(String aptCode) throws SQLException {
//		return dao.getApt(aptCode);
//	}
//
//	public int writeApt(HouseDto houseDto) throws Exception {
//		return dao.writeApt(houseDto);		
//	}
//
//	public int dongCodeck(String dongCode) throws SQLException {
//		return dao.dongCodeck(dongCode);
//	}
//
//	public void modifyApt(HouseDto houseDto) throws Exception {
//		dao.modifyApt(houseDto);
//	}
//
//	public void deleteApt(String aptCode) throws SQLException {
//		dao.deleteApt(aptCode);
//	}
//
//	public List<HouseDealDto> getAptSaleList(String aptCode) throws SQLException {
//		Connection conn = null;
//		List<HouseDealDto> aptSaleList = null;
//
//		try {
//			conn = dbUtil.getConnection();
//			aptSaleList = dao.getAptSaleList(conn, aptCode);
//		} finally {
//			dbUtil.close(conn);
//		}
//		return aptSaleList;
//	}
//
//	public int aptsaleWrite(HouseDealDto houseDealDto) throws SQLException {
//		return dao.aptsaleWrite(houseDealDto);	
//	}
//
//	public void aptsaledelete(String no) throws SQLException {
//		dao.aptsaledelete(no);
//	}
//
//	public HouseDealDto getAptsale(String no) throws SQLException {
//		return dao.getAptsale(no);
//	}
//
//	public void aptsaleModify(HouseDealDto houseDealDto) throws SQLException {
//		dao.aptsaleModify(houseDealDto);
//	}
//
//}
