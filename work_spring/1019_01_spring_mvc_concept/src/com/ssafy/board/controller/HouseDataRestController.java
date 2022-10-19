package com.ssafy.board.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.dto.realestate.Item;
import com.ssafy.board.model.service.HouseDataService;
import com.ssafy.spring.reflection.SsafyRequestMapping;

public class HouseDataRestController {

	private HouseDataService service = new HouseDataService();
	
	@SsafyRequestMapping(value = "/house/search_form")
	public String getSearchForm(HttpServletRequest req, HttpServletResponse resp) {
		return "redirect:" + req.getContextPath() + "/house/search_form.jsp";
	}

	@SsafyRequestMapping(value = "/rest/house/sido")
	public Map<String, String> getSidoNames(HttpServletRequest req, HttpServletResponse resp) {

		try {
			Map<String, String> map = service.selectSidoNames(req);
			return map;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SsafyRequestMapping(value = "/rest/house/gugun")
	public Object getGuGunNames(HttpServletRequest req, HttpServletResponse resp) {

		String sidoCode = req.getParameter("sidoCode").substring(0, 2);

		try {
			Map<String, String> map = service.getGuGunNames(sidoCode);
			return map;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SsafyRequestMapping(value = "/rest/house/apt/trade")
	public Object getAptTrade(HttpServletRequest req, HttpServletResponse resp) {

		// String regionCode = "11110";
		// String dealYmd = "202112";

		// 클라이언트로 부터 전달받은 지역코드(법정동 앞 5자리, 구/군 단위), 거래년월을 받는다.
		String regionCode = req.getParameter("regionCode");
		String dealYmd = req.getParameter("dealYmd");

		List<Map<String, Object>> items = service.getAptTrade(regionCode, dealYmd);
		return items;
	}

	@SsafyRequestMapping(value = "/rest/house/row-hose/trade")
	public Object getRowHouseTrade(HttpServletRequest req, HttpServletResponse resp) {

		// String regionCode = "11110";
		// String dealYmd = "202112";

		// 클라이언트로 부터 전달받은 지역코드(법정동 앞 5자리, 구/군 단위), 거래년월을 받는다.
		String regionCode = req.getParameter("regionCode");
		String dealYmd = req.getParameter("dealYmd");

		List<Item> items = service.getRowHouseTrade(regionCode, dealYmd);
		return items;
	}

}
