package com.ssafy.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ssafy.apt.controller.AptController;
import com.ssafy.apt.model.dto.AptInfo;
import com.ssafy.apt.model.dto.Area;
import com.ssafy.board.controller.BoardController;
import com.ssafy.house.controller.HouseController;
import com.ssafy.house.controller.HouseDataRestController;
import com.ssafy.house.model.HouseDto;
import com.ssafy.member.controller.MemberController;

@WebServlet(urlPatterns = { "/servlet/*" })
public class DispatcherServlet extends HttpServlet {
	private BoardController boardController = new BoardController();

	private AptController aptController = new AptController();
	private HouseController houseController = new HouseController();
	private MemberController userController = new MemberController();
	private HouseDataRestController houseDataRestController = new HouseDataRestController();

	// 객체를 JSON 문자열로 변경하거나 반대로 JSON문자열을 객체로 변경해주는 객체
	private ObjectMapper mapper = new ObjectMapper();

	private Map<String, String> map;
	// private Pagenation memberPagenation = new Pagenation();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("getPathInfo: " + req.getPathInfo());

		SsafyInterceptor interceptor = new SsafyInterceptor();
		boolean isPass = interceptor.preHandle(req, resp);

		String path = null; // 경로
		Object obj = null; // REST 방식 응답을 위한 객체
		Object obj2 = null;

		if (req.getPathInfo() == null || req.getPathInfo().equals("/") || isPass == false) {
			path = "/index.jsp";
			RequestDispatcher disp = req.getRequestDispatcher(path);
			disp.forward(req, resp);
			return;
		}

		switch (req.getPathInfo()) {
		// user
		case "/user/userlist":
			path = userController.userlist(req, resp);
			break;
		case "/user/mvjoin":
			path = "redirect:" + req.getContextPath() + "/user/join.jsp";
			break;
		case "/user/idcheck":
			int cnt = userController.idCheck(req, resp);
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println(cnt);
			break;
		case "/user/join":
			path = userController.join(req, resp);
			break;
		case "/user/mvlogin":
			path = "redirect:" + req.getContextPath() + "/user/login.jsp";
			break;
		case "/user/login":
			path = userController.login(req, resp);
			break;
		case "/user/logout":
			path = userController.logout(req, resp);
			break;
		case "/user/userview":
			path = userController.userview(req, resp);
			break;
		case "/user/mvusermodify":
			path = userController.mvusermodify(req, resp);
			break;
		case "/user/usermodify":
			path = userController.usermodify(req, resp);
			break;
		case "/user/delete":
			path = userController.delete(req, resp);
			break;
		case "/user/registFavarea":
			path = userController.registFavArea(req, resp);
			break;

		// board
		case "/board/list":
			path = boardController.list(req, resp);
			break;
		case "/board/mvwrite":
			path = "redirect:" + req.getContextPath() + "/board/write.jsp";
			break;
		case "/board/write":
			path = boardController.write(req, resp);
			break;
		case "/board/view":
			path = boardController.view(req, resp);
			break;
		case "/board/mvmodify":
			path = boardController.mvModify(req, resp);
			break;
		case "/board/modify":
			path = boardController.modify(req, resp);
			break;
		case "/board/delete":
			path = boardController.delete(req, resp);
			break;

		// 매매 정보
		case "/housetwo/getpage":
			path = "redirect:" + req.getContextPath() + "/apt/aptlist.jsp";
		case "/housetwo/getSIDO":
			obj2 = houseDataRestController.getSidoNames(req, resp);
			System.out.println(obj2);
			break;
		case "/housetwo/getGUGUN":
			obj2 = houseDataRestController.getGuGunNames(req, resp);
			break;
		case "/housetwo/getDONG":
			obj2 = houseDataRestController.getDongNames(req, resp);
			break;
		case "/housetwo/aptlist":
			obj2 = houseDataRestController.getaptsalelist(req, resp);
			List<HouseDto> list = (List<HouseDto>) obj2;

			try {
				// create `ObjectMapper` instance
				ObjectMapper mapper = new ObjectMapper();

				ArrayNode arrayNode = mapper.createArrayNode();
				for (HouseDto h : list) {
					ObjectNode house = mapper.createObjectNode();
					house.put("apartmentName", h.getApartmentName());
					house.put("dealAmount", h.getDealAmount());
					house.put("area", h.getArea());
					house.put("dealYear", h.getDealYear());
					house.put("dealMonth", h.getDealMonth());
					house.put("dealDay", h.getDealDay());
					house.put("lat", h.getLat());
					house.put("lng", h.getLng());
					house.put("floor", h.getFloor());
					house.put("dong", h.getDong());
					arrayNode.add(house);
				}
				obj2 = arrayNode;

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case "/house/search_form":
			path = "redirect:" + req.getContextPath() + "/house/search_form.jsp";
			break;
		case "/rest/house/sido":
			obj2 = houseDataRestController.getSidoNames(req, resp);
			break;
		case "/rest/house/gugun":
			obj2 = houseDataRestController.getGuGunNames(req, resp);
			break;
		case "/rest/house/row-house/trade": // 연립 다세대 API 요청 처리
			obj2 = houseDataRestController.getRowHouseTrade(req, resp);
			break;
		case "/house/getLocalInfo":
			obj2 = aptController.sortHouseInfo(req, resp);
			List<Area> list2 = (List<Area>) obj2;

			try {
				// create `ObjectMapper` instance
				ObjectMapper mapper = new ObjectMapper();

				ArrayNode arrayNode = mapper.createArrayNode();
				for (Area h : list2) {
					ObjectNode house = mapper.createObjectNode();
					house.put("dongCode", h.getDongCode());
					house.put("sidoName", h.getSidoName());
					house.put("gugunName", h.getGugunName());
					house.put("dongName", h.getDongName());
					house.put("lat", h.getLat());
					house.put("lng", h.getLng());
					house.put("optionCount", h.getOptionCount());
					arrayNode.add(house);
				}
				obj2 = arrayNode;

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;

		// fav_list
		case "/apt/mvfav_list":
			path = "redirect:" + req.getContextPath() + "/apt/fav_list.jsp";
			break;
		case "/apt/getFav":
			obj = aptController.getFavorite2(req, resp);
			List<Area> list3 = (List<Area>) obj;

			try {
				ObjectMapper mapper = new ObjectMapper();

				ArrayNode arrayNode = mapper.createArrayNode();
				for (Area a : list3) {
					ObjectNode area = mapper.createObjectNode();
					area.put("dongCode", a.getDongCode());
					area.put("sidoName", a.getSidoName());
					area.put("gugunName", a.getGugunName());
					area.put("dongName", a.getDongName());
					area.put("lat", a.getLat());
					area.put("lng", a.getLng());

					arrayNode.add(area);
				}
				obj = arrayNode;

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case "/apt/getFav2":
			List<JSONObject> code = aptController.getFavorite(req, resp);
			req.setAttribute("favorites", code);
			System.out.println(code.size());
			req.getRequestDispatcher("/apt/fav_list.jsp").forward(req, resp);
			break;
		case "/apt/getAptDealInfo":
			obj = aptController.getHouseDealInfo(req, resp);
			break;
		case "/apt/mvfav_list2":
			try {
				obj = aptController.getHouses(req, resp);
				System.out.println(obj.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			break;
		case "/apt/getAptList":
			// 여기서 시도정보 받아와서 getAptList 파라미터로 넘겨준다.
			System.out.println("heyyyyy!!!");
			aptController.getAptList(req.getParameter("sidoCode"));
			break;
		case "/apt/sido":
			obj = aptController.getSidoNames(req, resp);
			break;
		case "/apt/gugun":
			obj = aptController.getGuGunNames(req, resp);
			break;
		case "/apt/houses":
			obj = aptController.getHouses(req, resp);
			break;
		case "/apt/saveFav":
			obj = aptController.saveFavorite(req, resp);
			break;

		// 관리자: 주택관리 (주택목록, 주택 삭제, 주택 추가, 주택 수정)
		case "/houseAdmin/list":
			path = houseController.list(req, resp);
			break;
		case "/houseAdmin/view":
			path = houseController.view(req, resp);
			break;
		case "/houseAdmin/mvwrite":
			path = "redirect:" + req.getContextPath() + "/houseAdmin/write.jsp";
			break;
		case "/houseAdmin/write":
			path = houseController.write(req, resp);
			break;
		case "/houeAdmin/dongCodeck":
			int cnt2 = houseController.dongCodeck(req, resp);
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter out2 = resp.getWriter();
			out2.println(cnt2);
			break;
		case "/houseAdmin/mvmodify":
			path = houseController.mvModify(req, resp);
			break;
		case "/houseAdmin/modify":
			path = houseController.modify(req, resp);
			break;
		case "/houseAdmin/delete":
			path = houseController.delete(req, resp);
			break;

		// 아파트 주택매매정보
		case "/houseAdmin/aptsale/mvwrite":
			path = houseController.aptsaleMvwrite(req, resp);
			// path = "redirect:" + req.getContextPath() + "/houseAdmin/aptsale_write.jsp";
			break;
		case "/houseAdmin/aptsale/write":
			path = houseController.aptsaleWrite(req, resp);
			break;
		case "/houseAdmin/aptsale/delete":
			path = houseController.aptsaledelete(req, resp);
			break;
		case "/houseAdmin/aptsale/mvmodify":
			path = houseController.aptsaleMvmodify(req, resp);
			break;
		case "/houseAdmin/aptsale/modify":
			path = houseController.aptsaleModify(req, resp);
			break;
		}

		if (path != null && path.startsWith("redirect:")) {
			resp.sendRedirect(path.split(":")[1]);
		} else if (path != null) {
			RequestDispatcher disp = req.getRequestDispatcher(path);
			disp.forward(req, resp);
		} else if (obj != null) { // JSON 문자열로 응답 보내기
			// 응답 헤더 작성 (Header)
			resp.addHeader("Content-Type", "application/json; charset=UTF-8");
			// 응답 Payload 작성
			// 1. 객체를 JSON 문자열로 직렬화
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj.toString());
			// 2. 직렬화 한 문자열을 응답
			resp.getWriter().print(json);
			System.out.println(json);
		} else if (obj2 != null) {
			resp.addHeader("Content-Type", "application/json; charset=UTF-8");
			String json2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj2);
			resp.getWriter().write(json2);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}
}
