package com.ssafy.board.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.model.service.HouseDataService;

public class HouseDataRestController {

	private HouseDataService service = new HouseDataService();

	public Object getSidoNames(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> map = service.selectSidoNames();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getGuGunNames(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String sidoCode = req.getParameter("sidoCode").substring(0, 2);
		// ex: 서울 -> "110000000000"
		// 앞자리 두 개 뽑아옴 -> 시도 코드!
		
		try {
			Map<String, String> map = service.getGuGunNames(sidoCode);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getDongNames(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String sidoCode = req.getParameter("sidoCode").substring(0, 2);
		String gugunCode = req.getParameter("gugunCode").substring(2, 5);
		// ex: 서울 -> "110000000000"
		// 앞자리 두 개 뽑아옴 -> 시도 코드!
		
		try {
			Map<String, String> map = service.getDongNames(sidoCode, gugunCode);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
