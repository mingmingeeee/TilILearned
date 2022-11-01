package com.ssafy.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;
import com.ssafy.util.Pagenation;
import com.ssafy.util.ParameterCheck;

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = MemberServiceImpl.getMemberService();
	private Map<String, String> map;
	private Pagenation memberPagenation = new Pagenation();

	/*public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}*/
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		String key = ParameterCheck.nullToBlank(request.getParameter("key"));
		String word = ParameterCheck.nullToBlank(request.getParameter("word"));
		String queryString = "?pgno=" + pgNo + "&key=" + key + "&word=" + word;
		map = new HashMap<>();
		map.put("pgno", pgNo + "");
		map.put("key", key);
		map.put("word", word);
		String userId = ParameterCheck.nullToBlank(request.getParameter("userId"));
		String queryString2 = "?userId=" + userId;
		System.out.println(act);
		String path = "/index.jsp";
		if ("userlist".equals(act)) {
			path = userlist(request, response);
			forward(request, response, path + queryString);
		} else if ("mvjoin".equals(act)) {
			path = "/user/join.jsp";
			redirect(request, response, path);
		} else if ("idcheck".equals(act)) {
			int cnt = idCheck(request, response);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(cnt);
		} else if ("join".equals(act)) {
			path = join(request, response);
			forward(request, response, path);
		} else if ("mvlogin".equals(act)) {
			path = "/user/login.jsp";
			redirect(request, response, path);
		} else if ("login".equals(act)) {
			path = login(request, response);
			forward(request, response, path);
		} else if ("logout".equals(act)) {
			path = logout(request, response);
			forward(request, response, path);
		} else if ("userview".equals(act)) {
			path = userview(request, response);
			forward(request, response, path);
		} else if ("mvusermodify".equals(act)) {
			path = mvusermodify(request, response);
			forward(request, response, path + queryString2);
		} else if ("usermodify".equals(act)) {
			path = usermodify(request, response);
			forward(request, response, path);
		} else if ("delete".equals(act)) {
			path = delete(request, response);
			forward(request, response, path);
		} else if ("registFavarea".equals(act)) {
			path = registFavArea(request, response);
//			forward(request, response, path);
		} else {
			redirect(request, response, path);
			System.out.println("else");
		}
	}*/

	public String registFavArea(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("registFavArea called :: " + request);
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		System.out.println(sido + " " + gugun + " " + dong);
		String code = (sido + gugun + dong).trim();

		if (code != null && !code.isEmpty()) {
			try {
				int cnt = memberService.saveFavorit(code);
				return "redirect:" + request.getContextPath() + "/index.jsp";
			} catch (Exception e) {

				request.setAttribute("msg", "관심지역 추가 중 에러발생");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			request.setAttribute("msg", "지역을 선택해 주세요");
			return "redirect:" + request.getContextPath() + "/index.jsp";
		}
	}

	public String userlist(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//page
		int pgNo1 = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		String key1 = ParameterCheck.nullToBlank(request.getParameter("key"));
		String word1 = ParameterCheck.nullToBlank(request.getParameter("word"));
		map = new HashMap<>();
		map.put("pgno", pgNo1 + "");
		map.put("key", key1);
		map.put("word", word1);
		//
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				List<MemberDto> list = memberService.listUser(map);
				request.setAttribute("users", list);
				int listCnt = memberService.getUserListCnt(map);
				int pgNo = Integer.parseInt(map.get("pgno"));
				int range = ParameterCheck.notNumberToOne(request.getParameter("range"));
				memberPagenation.pageInfo(pgNo, range, listCnt);
				request.setAttribute("pagination", memberPagenation);
				return "/user/userlist.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글목록 얻기 중 에러발생!!!");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			return "redirect:" + request.getContextPath() + "/user/login.jsp";
		}
	}

	public String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				if (!memberDto.getUserId().equals("admin")) {
					String userid = memberDto.getUserId();
					memberService.deleteMember(userid);
					session.invalidate();
				}
				else {
					String userId = request.getParameter("userId");
					memberService.deleteMember(userId);
					if (userId.equals("admin")) {
						session.invalidate();
					}
				}
				
				return "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 삭제 중 에러발생!!!");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			return "redirect:" + request.getContextPath() + "/user/login.jsp";
		}
	}

	public String usermodify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDtobefore = (MemberDto) session.getAttribute("userinfo");
		String userId = request.getParameter("userId");
		MemberDto memberDto = new MemberDto();
		memberDto.setUserId(userId);
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserPwd(request.getParameter("userpwd"));
		memberDto.setEmailId(request.getParameter("emailid"));
		memberDto.setEmailDomain(request.getParameter("emaildomain"));
		try {
			memberService.usermodify(memberDto);
			MemberDto newmemberDto = new MemberDto();
			newmemberDto.setUserId(memberDto.getUserId());
			newmemberDto.setUserName(memberDto.getUserName());
			request.setAttribute("user", newmemberDto);
			if (!memberDtobefore.getUserId().equals("admin")) {
				session.setAttribute("userinfo", newmemberDto);
				return "redirect:" + request.getContextPath() + "/servlet/user/userview";
			}
			if (newmemberDto.getUserId().equals("admin") && memberDtobefore.getUserId().equals("admin")) {
				session.setAttribute("userinfo", newmemberDto);
				return "redirect:" + request.getContextPath() + "/servlet/user/userview";
				
			}
			
			return "redirect:" + request.getContextPath() + "/servlet/user/userlist";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "redirect:" + request.getContextPath() + "/error/error.jsp";
		}
	}

	public String userview(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				String id = request.getParameter("clickuserId");
				MemberDto newmemberDto = null;

				if (id == null) {
					newmemberDto = memberService.viewMember(memberDto.getUserId());
				} else {
					newmemberDto = memberService.viewMember(id);
				}

				request.setAttribute("user", newmemberDto);
				return "/user/userview.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "회원정보 조회 중 에러발생!!!");
				return "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}

	public String mvusermodify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				if (!memberDto.getUserId().equals("admin")) {
					MemberDto newmemberDto = memberService.viewMember(memberDto.getUserId());
					request.setAttribute("user", newmemberDto);
				}else {
					String userId = request.getParameter("userId");
					MemberDto newmemberDto = memberService.viewMember(userId);
					request.setAttribute("user", newmemberDto);
				}
				
				return "/user/usermodify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "회원정보 수정 중 에러발생!!!");
				return "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}

	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
		session.invalidate();
		return "/";
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if (memberDto != null) { // 로그인 성공(id, pwd 일치!!!!)

				String saveid = request.getParameter("saveid");
				if ("ok".equals(saveid)) { // 아이디 저장 체크 O.
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
					cookie.setPath(request.getContextPath());

					response.addCookie(cookie);
				} else {
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if (cookie.getName().equals("ssafy_id")) {
								cookie.setMaxAge(0);
								cookie.setPath(request.getContextPath());

								response.addCookie(cookie);
								break;
							}
						}
					}
				}

				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				String referer = request.getHeader("referer");
				System.out.println(referer);
				return "/";
			} else { // 로그인 실패(id, pwd 불일치!!!!)
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인!!!");
				return "/user/login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 처리중 에러 발생!!!");
			return "redirect:" + request.getContextPath() +  "/error/error.jsp";
		}
	}

	public String join(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();
		memberDto.setUserId(request.getParameter("userid"));
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserPwd(request.getParameter("userpwd"));
		memberDto.setEmailId(request.getParameter("emailid"));
		memberDto.setEmailDomain(request.getParameter("emaildomain"));
		try {
			memberService.joinMember(memberDto);
			return "redirect:" + request.getContextPath() +  "/servlet/user/mvlogin";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "redirect:" + request.getContextPath() + "/error/error.jsp";
		}
	}

	public void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	public void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	/*protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}*/

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int idCheck(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		//System.out.println(userId);
		try {
			int count = memberService.idCheck(userId);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 500;
	}

}
