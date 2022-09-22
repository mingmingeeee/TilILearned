package com.ssafy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;

	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}

	// 사용자 요구 받아 어디로 가라 ~ -> Front Controller 패턴 -> 어느 컨트롤러로 보낼지? 이런 거
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");

		String path = "/index.jsp";
		if ("mvjoin".equals(act)) { // act=mvjoin이라면 회원가입 페이지로 이동시켜줘~
			path = "/user/join.jsp";
			// 단순 이동: redirect -> 다~pool경로
			// 뭔가를 가지고 갈 때는?: forward -> 내 프로젝트 안에서만
			redirect(request, response, path);
		} else if ("idcheck".equals(act)) {
			int cnt = idCheck(request, response); // ajax -> 데이터만 넘겨주면 됨 (id check만 하는 거) -> 몇갠지만 넘겨주면 됨
			response.setContentType("text/plain);charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(cnt);
		} else if ("join".equals(act)) {
			path = join(request, response);
			forward(request, response, path); // redirect & forward 둘 다 받을 수 있는 것: forward
		} else if ("mvlogin".equals(act)) {
			path = "/user/login.jsp";
			redirect(request, response, path);
		} else if ("login".equals(act)) {
			path = login(request, response);
			forward(request, response, path);
		} else if ("logout".equals(act)) {
			path = logout(request, response);
			forward(request, response, path);
		} else { // 우리가 정상적인 값을 가져왔을 때는 원하는 곳으로 ㄱ ㄱ
			// 사용자가 장난치거나 잘못된 값이면 path(index page)로 이동
			redirect(request, response, path);
		}
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate(); // 다~지워버리는!
		return "/index.jsp";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");

		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);

			if (memberDto != null) { // 로그인 성공 (id, pwd 일치!!!!)

				// 아이디 저장 기능 -> 로그인 정상적으로 됐을 때 & "아이디 저장" 체크 되어있을 때
				String saveid = request.getParameter("saveid");
				if ("ok".equals(saveid)) { // 아이디 저장 체크 했다면
					Cookie cookie = new Cookie("ssafy_id", userId); // 내 컴퓨터에 저장할 것 -> Cookie!!
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40); // 40년동안 저장
//					cookie.setDomain(pattern);
					cookie.setPath(request.getContextPath()); // localhost/ 뒤에 나오는 것이 path -> 다른 곳에서도 써야 하기 때문에 일단 프로젝트
																// 이름으로 씀
					// -> board3_mvc
					response.addCookie(cookie); // 쿠키 만들었으니 클라이언트한테 보내줘!!
				} else { // 아이디 저장 체크 안 했다면
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if (cookie.getName().equals("ssafy_id")) {
								cookie.setMaxAge(0);
								cookie.setPath(request.getContextPath());

								response.addCookie(cookie); // 쿠키 만들었으니 클라이언트한테 보내줘!!
								break;
							}
						}
					}
				}

				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);

				return "/index.jsp";
			} else { // 로그인 실패 (id, pwd 불일치!!!!)
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인!!!");
				return "/user/login.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "로그인 처리 중 에러 발생!!!"); // req에 담은 게 있으므로 forward
			return "error/error.jsp";
		}
	}

	// path 리턴~
	private String join(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();
		memberDto.setUserId(request.getParameter("userid"));
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserPwd(request.getParameter("userpwd"));
		memberDto.setEmailId(request.getParameter("emailid"));
		memberDto.setEmailDomain(request.getParameter("emaildomain"));

		try {
			memberService.joinMember(memberDto); // req에 담은 게 없으므로 redirect
			return "/user?act=mvlogin"; // 실제로 이동하는 건 딲! 하나만 만들어주는 게 좋음
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "회원 가입 처리 중 에러 발생!!!"); // req에 담은 게 있으므로 forward
			return "error/error.jsp";
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	///////////////////////////////////////////////////////////////////////////////////////////

	// 로직 호출
	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		try {
			int count = memberService.idCheck(userId);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 500;
		// 0, 1: 사용 ㅇㅇ
		// 500 or -1: 에러
	}

}
