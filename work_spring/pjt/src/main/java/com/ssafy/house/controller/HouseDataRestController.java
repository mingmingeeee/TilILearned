//package com.ssafy.house.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.Charset;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.apt.model.dto.CategoryDocument;
//import com.ssafy.apt.model.dto.CategoryResult;
//import com.ssafy.house.model.HouseDto;
//import com.ssafy.house.model.service.HouseDataService;
//
////@WebServlet("/house")
//public class HouseDataRestController extends HttpServlet {
//
//	private HouseDataService service = HouseDataService.getHouseDataService();
//
//	private static String GEOCODE_USER_INFO = "b885a72c8c58d06cf6d3093377e0bfcc";
//	private static List<HouseDto> list;
//
//	public Object getSidoNames(HttpServletRequest req, HttpServletResponse resp) {
//		try {
//			Map<String, String> map = service.selectSidoNames();
//			return map;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public Object getGuGunNames(HttpServletRequest req, HttpServletResponse resp) {
//		String sidoCode = req.getParameter("sidoCode").substring(0, 2);
//		try {
//			Map<String, String> map = service.selectSidoNames(sidoCode);
//			return map;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public Object getDongNames(HttpServletRequest req, HttpServletResponse resp) {
//		String gugunCode = req.getParameter("gugunCode").substring(0, 5);
//		try {
//			Map<String, String> map = service.selectGugunNames(gugunCode);
//			return map;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public Object getRowHouseTrade(HttpServletRequest req, HttpServletResponse resp) {
//
//		// 클라이언트로 부터 전달받은 지역코드(동코드 5자리), 거래년월을 받는다.
//		String regionCode = req.getParameter("regionCode");
//		String dealYmd = req.getParameter("dealYmd");
//
//		Map<String, Object> response = service.getRowHouseTrade(regionCode, dealYmd);
//		return response;
//	}
//
//	public Object getaptsalelist(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		String sidoName = req.getParameter("sidoName");
//		String gugunName = req.getParameter("gugunName");
//		String dongName = req.getParameter("dongName");
//		String dongCode = req.getParameter("dongCode");
//		try {
//			list = service.selectAllaptlistDeal(sidoName, gugunName, dongName, dongCode);
//
//			// System.out.println(list);
//			req.setAttribute("HousesaleList", list);
//			req.setAttribute("sidoName", sidoName);
//
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//
//
//}
