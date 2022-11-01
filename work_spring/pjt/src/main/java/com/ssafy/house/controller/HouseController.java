//package com.ssafy.house.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.ssafy.board.model.BoardDto;
//import com.ssafy.house.model.HouseDealDto;
//import com.ssafy.house.model.HouseDto;
//import com.ssafy.house.model.HouseInfoDto;
//import com.ssafy.house.model.service.HouseDataService;
//import com.ssafy.member.model.MemberDto;
//import com.ssafy.util.Pagenation;
//import com.ssafy.util.ParameterCheck;
//
//public class HouseController {
//	private HouseDataRestController houseDataRestController = new HouseDataRestController();
//	private HouseDataService service = HouseDataService.getHouseDataService();
//	private Map<String, String> map;
//	private Pagenation pagenation = new Pagenation();
//
//	public String list(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		// page
//		int pgNo1 = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//		String key1 = ParameterCheck.nullToBlank(request.getParameter("key"));
//		String word1 = ParameterCheck.nullToBlank(request.getParameter("word"));
//		map = new HashMap<>();
//		map.put("pgno", pgNo1 + "");
//		map.put("key", key1);
//		map.put("word", word1);
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			try {
//				List<HouseInfoDto> list = service.listApt(map);
//				request.setAttribute("apts", list);
//				int listCnt = service.getAptListCnt(map);
//				int pgNo = Integer.parseInt(map.get("pgno"));
//				int range = ParameterCheck.notNumberToOne(request.getParameter("range"));
//				pagenation.pageInfo(pgNo, range, listCnt);
//				request.setAttribute("pagination", pagenation);
//				return "/houseAdmin/list.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택 목록 얻기 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String view(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		/* TODO 관리자 인지 확인 */ 
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			try {
//				String aptCode = request.getParameter("aptCode");
//				HouseDto houseDto = service.getApt(aptCode);
//				request.setAttribute("apt", houseDto);
//				List<HouseDealDto> aptSales = service.getAptSaleList(aptCode);
//				request.setAttribute("aptSales", aptSales);
//				return "/houseAdmin/view.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택 얻기 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String write(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			HouseDto houseDto = new HouseDto();
//			houseDto.setAptCode(request.getParameter("aptCode"));
//			houseDto.setBuildYear(request.getParameter("buildYear"));
//			houseDto.setApartmentName(request.getParameter("apartmentName"));
//			houseDto.setDong(request.getParameter("dong"));
//			houseDto.setJibun(request.getParameter("jibun"));
//			houseDto.setDongCode(request.getParameter("dongCode"));
//			houseDto.setLat(request.getParameter("lat"));
//			houseDto.setLng(request.getParameter("lng"));
//			
//			houseDto.setRoadName(request.getParameter("roadName"));
//			houseDto.setRoadNameBonbun(request.getParameter("roadnamebonbun"));
//			houseDto.setRoadNameBubun(request.getParameter("roadNameBubun"));
//			houseDto.setRoadNameSeq(request.getParameter("roadNameSeq"));
//			houseDto.setRoadNameBasementCode(request.getParameter("roadNameBasementCode"));
//			houseDto.setRoadNameCode(request.getParameter("roadNameCode"));
//			houseDto.setBonbun(request.getParameter("bonbun"));
//			houseDto.setBubun(request.getParameter("bubun"));
//			houseDto.setSigunguCode(request.getParameter("sigunguCode"));
//			houseDto.setEubmyundongCode(request.getParameter("eubmyundongCode"));
//			houseDto.setLandCode(request.getParameter("landCode"));
//			try {
//				service.writeApt(houseDto);
//				return "redirect:" + request.getContextPath() + "/servlet/houseAdmin/list?pgno=1&key=&word=";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택 추가 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public int dongCodeck(HttpServletRequest request, HttpServletResponse response) {
//		String dongCode = request.getParameter("dongCode");
//		try {
//			int count = service.dongCodeck(dongCode);
//			return count;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	public String modify(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			try {
//				HouseDto houseDto = new HouseDto();
//				houseDto.setAptCode(request.getParameter("aptCode"));
//				houseDto.setBuildYear(request.getParameter("buildYear"));
//				houseDto.setApartmentName(request.getParameter("apartmentName"));
//				houseDto.setDong(request.getParameter("dong"));
//				houseDto.setJibun(request.getParameter("jibun"));
//				houseDto.setDongCode(request.getParameter("dongCode"));
//				houseDto.setLat(request.getParameter("lat"));
//				houseDto.setLng(request.getParameter("lng"));
//				
//				houseDto.setRoadName(request.getParameter("roadName"));
//				houseDto.setRoadNameBonbun(request.getParameter("roadnamebonbun"));
//				houseDto.setRoadNameBubun(request.getParameter("roadNameBubun"));
//				houseDto.setRoadNameSeq(request.getParameter("roadNameSeq"));
//				houseDto.setRoadNameBasementCode(request.getParameter("roadNameBasementCode"));
//				houseDto.setRoadNameCode(request.getParameter("roadNameCode"));
//				houseDto.setBonbun(request.getParameter("bonbun"));
//				houseDto.setBubun(request.getParameter("bubun"));
//				houseDto.setSigunguCode(request.getParameter("sigunguCode"));
//				houseDto.setEubmyundongCode(request.getParameter("eubmyundongCode"));
//				houseDto.setLandCode(request.getParameter("landCode"));
//				
//				service.modifyApt(houseDto);
//				return "redirect:" + request.getContextPath() + "/servlet/houseAdmin/list?pgno=1&key=&word=";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택정보 수정 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String mvModify(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			try {
//				String aptCode = request.getParameter("aptCode");
//				HouseDto houseDto = service.getApt(aptCode);
//				String[] jibun = houseDto.getJibun().split(" ");
//				houseDto.setJibun(jibun[jibun.length - 1]);
//				request.setAttribute("apt", houseDto);
//				return "/houseAdmin/modify.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택정보 수정 페이지 이동 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String delete(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			try {
//				String aptCode = request.getParameter("aptCode");
//				service.deleteApt(aptCode);
//				return "redirect:" + request.getContextPath() + "/servlet/houseAdmin/list?pgno=1&key=&word=";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택정보 삭제 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String aptsaleWrite(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			HouseDealDto houseDealDto = new HouseDealDto();
//			String aptCode = request.getParameter("aptCode");
//			houseDealDto.setAptCode(request.getParameter("aptCode"));
//			houseDealDto.setDealAmount(request.getParameter("dealAmount"));
//			houseDealDto.setArea(request.getParameter("area"));
//			houseDealDto.setFloor(request.getParameter("floor"));
//			String[] split = request.getParameter("dealDate").split("-");
//			houseDealDto.setDealYear(split[0]);
//			houseDealDto.setDealMonth(split[1]);
//			houseDealDto.setDealDay(split[2]);
//			
//			
//			try {
//				service.aptsaleWrite(houseDealDto);
//				return "redirect:" + request.getContextPath() + "/servlet/houseAdmin/view?pgno=&key=&word=&aptCode="+ aptCode;
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택 추가 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String aptsaleMvwrite(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			try {
//				String aptCode = request.getParameter("aptCode");
//				request.setAttribute("aptCode", aptCode);
//				return "/houseAdmin/aptsale_write.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택매매정보 추가 페이지 이동 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String aptsaledelete(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			try {
//				String aptCode = request.getParameter("aptCode");
//				String no = request.getParameter("no");
//				System.out.println(no);
//				service.aptsaledelete(no);
//				return "redirect:" + request.getContextPath() + "/servlet/houseAdmin/list?pgno=1&key=&word=";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택매매정보 삭제 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String aptsaleMvmodify(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			try {
//				String no = request.getParameter("no");
//				HouseDealDto houseDealDto = service.getAptsale(no);
//				
//				request.setAttribute("aptSale", houseDealDto);
//				return "/houseAdmin/aptsale_modify.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택매매정보 수정 페이지 이동 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//	public String aptsaleModify(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if(memberDto != null) {
//			try {
//				HouseDealDto houseDealDto = new HouseDealDto();
//				houseDealDto.setNo(request.getParameter("no"));
//				String aptCode = request.getParameter("aptCode");
//				houseDealDto.setAptCode(aptCode);
//				houseDealDto.setDealAmount(request.getParameter("dealAmount"));
//				houseDealDto.setArea(request.getParameter("area"));
//				houseDealDto.setFloor(request.getParameter("floor"));
//				String[] split = request.getParameter("dealDate").split("-");
//				houseDealDto.setDealYear(split[0]);
//				houseDealDto.setDealMonth(split[1]);
//				houseDealDto.setDealDay(split[2]);
//				
//				service.aptsaleModify(houseDealDto);
//				return "redirect:" + request.getContextPath() +  "/servlet/houseAdmin/view?pgno=&key=&word=&aptCode="+ aptCode;
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "주택매매정보 수정 중 에러발생!!!");
//				return "redirect:" + request.getContextPath() + "/error/error.jsp";
//			}
//		} else {
//			return "/user/login.jsp";
//		}
//	}
//
//}
