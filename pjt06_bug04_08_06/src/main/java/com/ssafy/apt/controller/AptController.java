package com.ssafy.apt.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apt.model.service.AptServiceImpl;
import com.ssafy.house.model.dto.DongCode;
import com.ssafy.house.model.dto.HouseDeal;
import com.ssafy.house.model.dto.HouseInfo;
import com.ssafy.member.model.dto.MemberDto;

@RestController
@RequestMapping("/apt")
public class AptController {

	@Autowired
	private AptServiceImpl service;

	@GetMapping("/aptlist")
	public ResponseEntity<?> getHouses(@RequestParam("dongCode" ) String regionCode) {
//		String regionCode = "2614010200";
		String dealYmd = "202112";
		try {
			List<HouseInfo> arr = service.getHouses(regionCode, dealYmd);
			System.out.println("arr" + arr.toString());
			return new ResponseEntity<List<HouseInfo>>(arr, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/aptDealInfo")
	public ResponseEntity<?> getAptDealInfo(HttpServletRequest request, HttpServletResponse response) {
		String aptCode = request.getParameter("aptCode");
		List<HouseDeal> arr;
		try {
			arr = service.getAptDealInfo(aptCode);
			return new ResponseEntity<List<HouseDeal>>(arr, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getSido")
	public ResponseEntity<?> getSidoNames() {
		System.out.println("getSidoNames");
		List<DongCode> arr = service.selectSidoNames("__00000000");
		return new ResponseEntity<List<DongCode>>(arr, HttpStatus.OK);
	}

	@GetMapping("/getGugun")
	public ResponseEntity<?> getGuGunNames(@RequestParam("dongCode" ) String regionCode) {
		String sidoCode = regionCode.substring(0, 2);
		System.out.println("getGuGunNames : " + sidoCode);
		List<DongCode> arr = service.getGuGunNames(sidoCode + "___00000");
		return new ResponseEntity<List<DongCode>>(arr, HttpStatus.OK);
	}
	
	@GetMapping("/getDong")
	public ResponseEntity<?> getDongNames(@RequestParam("dongCode") String regionCode) {
		String gugunCode = regionCode.substring(0, 5);
		System.out.println("getSidoNames");
		List<DongCode> arr = service.selectDongNames(gugunCode+"_____");
		return new ResponseEntity<List<DongCode>>(arr, HttpStatus.OK);
	}

}
