package com.ssafy.apt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.apt.model.dto.AptInfo;
import com.ssafy.apt.model.dto.Area;
import com.ssafy.apt.model.dto.CategoryDocument;
import com.ssafy.apt.model.dto.CategoryResult;
import com.ssafy.apt.model.dto.Documents;
import com.ssafy.apt.model.dto.KakaoGeoRes;
import com.ssafy.apt.service.AptService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.AptSaxHandler;

//@WebServlet("/apt")
public class AptController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String GEOCODE_URL = "http://dapi.kakao.com/v2/local/search/address.json?query=";
	private static String GEOCODE_USER_INFO = "b885a72c8c58d06cf6d3093377e0bfcc";
	private AptService service = AptService.getInstance();
	private ObjectMapper mapper = new ObjectMapper();
	private static List<Area> list;
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String act =
	 * request.getParameter("act"); Object obj = null; // REST 방식 응답을 위한 객체
	 * 
	 * String path = "/index.jsp"; if ("getAptList".equals(act)) { // 여기서 시도정보 받아와서
	 * getAptList 파라미터로 넘겨준다. System.out.println("heyyyyy!!!");
	 * getAptList(request.getParameter("sidoCode")); } else if ("sido".equals(act))
	 * { obj = getSidoNames(request, response); } else if ("gugun".equals(act)) {
	 * obj = getGuGunNames(request, response); } else if ("houses".equals(act)) {
	 * obj = getHouses(request, response); } else if ("saveFav".equals(act)) { obj =
	 * saveFavorite(request, response); } else if ("getFav".equals(act)) {
	 * getFavorite(request, response); } else {
	 * 
	 * }
	 * 
	 * if (obj != null) { // JSON 문자열로 응답 보내기 List<ApartInfo> list =
	 * (List<ApartInfo>) obj; // System.out.println("list" +list ); try { // create
	 * `ObjectMapper` instance ObjectMapper mapper = new ObjectMapper();
	 * 
	 * ArrayNode arrayNode = mapper.createArrayNode(); for (ApartInfo h : list) {
	 * ObjectNode house = mapper.createObjectNode(); house.put("apartmentName",
	 * h.getApartmentName()); house.put("area", h.getDong()); house.put("lat",
	 * h.getLat()); house.put("lng", h.getLng()); house.put("dong", h.getDong());
	 * arrayNode.add(house); } response.addHeader("Content-Type",
	 * "application/json; charset=UTF-8"); // convert `ArrayNode` to pretty-print
	 * JSON // without pretty-print, use `arrayNode.toString()` method String json =
	 * mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
	 * 
	 * // print json // System.out.println(json); PrintWriter writer =
	 * response.getWriter(); writer.write(json);
	 * 
	 * } catch (Exception ex) { ex.printStackTrace(); } }
	 * 
	 * }
	 */

	public String saveFavorite(HttpServletRequest request, HttpServletResponse response) {
		// db에 사용자가 저장한 관심지역을 저장한다.
		System.out.println("saveFavorite");
		String code = request.getParameter("regionCode");
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		try {

			request.setAttribute("msg", "관심지역 저장 성공");
			service.saveFavorite(memberDto.getUserId(), code);
			request.getRequestDispatcher("/apt/fav_list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}
		return null;
	}

	public List<JSONObject> getFavorite(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("getFavorite");
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		try {
			List<JSONObject> list = service.getFavorite(memberDto.getUserId());
			System.out.println("in getFavorite  code : " + list.get(0));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getFavorite2(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("getFavorite2");
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		try {
			list = service.getFavorite2(memberDto.getUserId());
			// System.out.println("in getFavorite2  code : " + list.get(0));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * request.setCharacterEncoding("utf-8"); doGet(request, response); }
	 */

	public JSONArray getHouses(HttpServletRequest request, HttpServletResponse response) {
		String regionCode = request.getParameter("dongCode");
//		String regionCode = "2614010200";
		String dealYmd = "202112";
		HttpSession session = request.getSession();
		MemberDto memberDtobefore = (MemberDto) session.getAttribute("userinfo");
		System.out.println("getHouses is called : " + request.getParameter("dongCode") + memberDtobefore.getUserId());

		try {

//			Map<String, Object> map = service.getHouses(regionCode, dealYmd);
			JSONArray arr = service.getHouses(regionCode, dealYmd);
			System.out.println("arr" + arr.toString());
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public JSONObject getHouseDealInfo(HttpServletRequest request, HttpServletResponse response) {
		String aptCode = request.getParameter("aptCode");
		JSONObject obj;
		try {
			obj = service.getAptDealInfo(aptCode);
			return obj;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<AptInfo> getAptList(String sidoCode) throws ServletException, IOException {
		String uri = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent?serviceKey=lfEeqXGixAkw5T3ayDXJAh7IxSRht3gDWrIHhL5V7fRDn8ODOz9fYd%2Bpe4cmGh6qf5U4hW%2Bh51MK%2FaL4ngwetQ%3D%3D&LAWD_CD="
				+ sidoCode + "&DEAL_YMD=202112";
		SAXParserFactory factory = SAXParserFactory.newInstance();
		System.out.println("getAptList is called : " + sidoCode);

		try {
			SAXParser parser = factory.newSAXParser();
			AptSaxHandler handler = new AptSaxHandler();
			parser.parse(uri, handler);

			List<AptInfo> list = handler.getAptInfoList();

			for (AptInfo apt : list) {
				// 여기에는 넘겨줄 주소 ( 아파트명 포함) 을 넘긴다.
				Documents doc = getLatLng(apt.getJibun() + " " + apt.getAptName());
				if (doc != null) {
					apt.setLat(doc.getY().toString());
					apt.setLng(doc.getX().toString());
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Documents getLatLng(String keyword) throws Exception {
		URL obj;

		try {
			// 인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
			String address = URLEncoder.encode("서울특별시 종로구 사직로8길", "UTF-8");

			obj = new URL(GEOCODE_URL + address);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "KakaoAK " + GEOCODE_USER_INFO);
			con.setRequestProperty("content-type", "application/json");

			Charset charset = Charset.forName("UTF-8");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			System.out.println(obj.toString());
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			ObjectMapper objectMapper = new ObjectMapper();
			KakaoGeoRes doc = objectMapper.readValue(response.toString(), KakaoGeoRes.class);

			// response 객체를 출력해보자
			System.out.println(response.toString());
			return doc.getDocuments().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getSidoNames(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("getSidoNames");
		try {
			Map<String, String> map = service.selectSidoNames();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getGuGunNames(HttpServletRequest req, HttpServletResponse resp) {
		String sidoCode = req.getParameter("sidoCode").substring(0, 2);
		System.out.println("getGuGunNames : " + sidoCode);
		try {
			Map<String, String> map = service.getGuGunNames(sidoCode);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static CategoryDocument getLocalInfo(HttpServletRequest req, HttpServletResponse resp) {
		URL obj;

//category\_group\_code=PM9&radius=20000
		try {

			String code = req.getParameter("categoryCode");
			String lat = req.getParameter("x");
			String lng = req.getParameter("y");
			// 인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
			String CATEGORY_URL = "https://dapi.kakao.com/v2/local/search/category.json?" + "category_group_code="
					+ code + "&x=" + lat + "&y=" + lng + "&distance=100";
			String encodedQuery = URLEncoder.encode(CATEGORY_URL, "UTF-8");
			obj = new URL(CATEGORY_URL);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "KakaoAK " + GEOCODE_USER_INFO);
			con.setRequestProperty("content-type", "application/json");

			Charset charset = Charset.forName("UTF-8");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			System.out.println(obj.toString());
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			ObjectMapper objectMapper = new ObjectMapper();
			CategoryResult doc = objectMapper.readValue(response.toString(), CategoryResult.class);

			// response 객체를 출력해보자
			System.out.println(response.toString());
			return doc.getDocuments().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getLocalInfo(HttpServletRequest req, HttpServletResponse resp, String x, String y) {
		System.out.println("getLocalInfo");
		URL obj;
//category\_group\_code=PM9&radius=20000
		try {

			String code = req.getParameter("categoryCode");
			// 인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
			String CATEGORY_URL = "https://dapi.kakao.com/v2/local/search/category.json?" + "category_group_code="
					+ code + "&x=" + y + "&y=" + x + "&radius=1000";
			String encodedQuery = URLEncoder.encode(CATEGORY_URL, "UTF-8");
			obj = new URL(CATEGORY_URL);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "KakaoAK " + GEOCODE_USER_INFO);
			con.setRequestProperty("content-type", "application/json");

			Charset charset = Charset.forName("UTF-8");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			System.out.println(obj.toString());
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			ObjectMapper objectMapper = new ObjectMapper();
			CategoryResult doc = objectMapper.readValue(response.toString(), CategoryResult.class);

			return doc.getDocuments().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Object sortHouseInfo(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("sortHouseInfo");
		if (list == null || list.isEmpty())
			getFavorite2(req, resp);

		Area[] areas = new Area[list.size()];
		for (int i = 0; i < Math.min(list.size(), 30); i++) {
			System.out.println(i);
			list.get(i).setOptionCount(getLocalInfo(req, resp, list.get(i).getLat(), list.get(i).getLng()));
			areas[i] = list.get(i);
		}
		QuickSort.sort(areas);
		req.setAttribute("sortHouseList", list);
		return Arrays.asList(areas);
	}

	public static class QuickSort {

		public static void sort(Area[] a) {
			l_pivot_sort(a, 0, a.length - 1);
		}

		/**
		 * 왼쪽 피벗 선택 방식
		 * 
		 * @param a  정렬할 배열
		 * @param lo 현재 부분배열의 왼쪽
		 * @param hi 현재 부분배열의 오른쪽
		 */
		private static void l_pivot_sort(Area[] a, int lo, int hi) {

			/*
			 * lo가 hi보다 크거나 같다면 정렬 할 원소가 1개 이하이므로 정렬하지 않고 return한다.
			 */
			if (lo >= hi) {
				return;
			}

			int pivot = partition(a, lo, hi);

			l_pivot_sort(a, lo, pivot - 1);
			l_pivot_sort(a, pivot + 1, hi);
		}

		private static int partition(Area[] a, int left, int right) {

			int lo = left;
			int hi = right;
			int pivot = a[left].getOptionCount(); // 부분리스트의 왼쪽 요소를 피벗으로 설정

			// lo가 hi보다 작을 때 까지만 반복한다.
			while (lo < hi) {

				/*
				 * hi가 lo보다 크면서, hi의 요소가 pivot보다 작거나 같은 원소를 찾을 떄 까지 hi를 감소시킨다.
				 */
				while (a[hi].getOptionCount() > pivot && lo < hi) {
					hi--;
				}

				/*
				 * hi가 lo보다 크면서, lo의 요소가 pivot보다 큰 원소를 찾을 떄 까지 lo를 증가시킨다.
				 */
				while (a[lo].getOptionCount() <= pivot && lo < hi) {
					lo++;
				}

				// 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
				swap(a, lo, hi);
			}

			/*
			 * 마지막으로 맨 처음 pivot으로 설정했던 위치(a[left])의 원소와 lo가 가리키는 원소를 바꾼다.
			 */
			swap(a, left, lo);

			// 두 요소가 교환되었다면 피벗이었던 요소는 lo에 위치하므로 lo를 반환한다.
			return lo;
		}

		private static void swap(Area[] a, int i, int j) {
			Area temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
}
