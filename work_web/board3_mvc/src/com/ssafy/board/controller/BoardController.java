package com.ssafy.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.ParameterCheck;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService;
	private Map<String, String> map;

	public void init() {
		boardService = BoardServiceImpl.getBoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		
		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		String key = ParameterCheck.nullToBlank(request.getParameter("key"));
		String word = ParameterCheck.nullToBlank(request.getParameter("word"));
		
		String queryString = "?page=" + pgNo + "&key=" + key + "&word=" + word; 

		map = new HashMap<>();
		map.put("pgno", pgNo + ""); // 숫자 => 문자열로~ +""추가~ㅎ
		map.put("key", key);
		map.put("word", word);

		String path = "/index.jsp";
		if ("list".equals(act)) {
			path = list(request, response);
			forward(request, response, path); // path + queryString
		} else if ("mvwrite".equals(act)) {
			path = "/board/write.jsp";
			redirect(request, response, path);
		} else if ("write".equals(act)) {
			path = write(request, response);
			forward(request, response, path);
		} else if ("view".equals(act)) {
			path = view(request, response);
			forward(request, response, path); // // path + queryString
		} else if ("mvmodify".equals(act)) {
			path = mvModify(request, response);
			forward(request, response, path);
		} else if ("modify".equals(act)) {
			path = modify(request, response);
			forward(request, response, path);
		} else if ("delete".equals(act)) {
			path = delete(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response, path);
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

	private String list(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		if (memberDto != null) { // 로그인 했다면
			
			// 맞는 table은 없지만 data는 넘겨줘야해! -> 비슷한 건? Map!!!
			try {
				List<BoardDto> list = boardService.listArticle(map);
				
				request.setAttribute("articles", list);
				
				return "/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 목록 얻기 중 에러 발생!!!");
				return "/error/error.jsp";
			}
			
			
		} else {
			return "/user/login.jsp"; // 얘도 redirect
		}

	}

	private String write(HttpServletRequest request, HttpServletResponse response) {
		// 세션에서 userInfo 들고 오기 => 글쓰기 켜놓고 다른 짓 하느라 session이 끊길 수 있기 때문에 검사해줘야 함
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		if (memberDto != null) { // 로그인 했다면
			BoardDto boardDto = new BoardDto();
			boardDto.setUserId(memberDto.getUserId()); // session에서 로그인한 사용자 정보 꺼냄
			boardDto.setSubject(request.getParameter("subject"));
			boardDto.setContent(request.getParameter("content"));

//			String referer = request.getHeader("referer");

			try {
				// 20초 동안 글 200개 집어넣어라는 뜻..ㅋ
//				for(int i=0; i<200;i++) {
//					boardService.writeArticle(boardDto);
//					Thread.sleep(100);
//				}
				boardService.writeArticle(boardDto);

				return "/board?act=list&pgno=1&key=&word="; // redirect임~ forward로 가면 새로고침하면 글이 계속 써짐~
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 작성 중 에러 발생!!!");
				return "/error/error.jsp";
			}

		} else {
			return "/user/login.jsp"; // 얘도 redirect
		}
	}

	private String view(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		if (memberDto != null) { // 로그인 했다면
			
			
			// 맞는 table은 없지만 data는 넘겨줘야해! -> 비슷한 건? Map!!!
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				BoardDto boardDto = boardService.getArticle(articleNo);
				boardService.updateHit(articleNo);
				request.setAttribute("article", boardDto);
				
				return "/board/view.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 얻기 중 에러 발생!!!"); // 글번호 없는 거 가져오면 에러 !!
				return "/error/error.jsp";
			}
			
			
		} else {
			return "/user/login.jsp"; // 얘도 redirect
		}
	}

	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		if (memberDto != null) { // 로그인 했다면
			
			
			// 맞는 table은 없지만 data는 넘겨줘야해! -> 비슷한 건? Map!!!
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				BoardDto boardDto = boardService.getArticle(articleNo);
				
				request.setAttribute("article", boardDto);
				
				return "/board/modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 삭제 중 에러 발생!!!"); // 글번호 없는 거 가져오면 에러 !!
				return "/error/error.jsp";
			}
			
			
		} else {
			return "/user/login.jsp"; // 얘도 redirect
		}
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		if (memberDto != null) { // 로그인 했다면
			
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				String subject = request.getParameter("subject");
				String content = request.getParameter("content");
				
				BoardDto boardDto = new BoardDto();
				boardDto.setArticleNo(articleNo);
				boardDto.setSubject(subject);
				boardDto.setContent(content);
				
				boardService.modifyArticle(boardDto);
				
				return "/board?act=list&pgno=1&key=&word=";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 삭제 중 에러 발생!!!"); // 글번호 없는 거 가져오면 에러 !!
				return "/error/error.jsp";
			}
			
			
		} else {
			return "/user/login.jsp"; // 얘도 redirect
		}
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		if (memberDto != null) { // 로그인 했다면
			
			
			// 맞는 table은 없지만 data는 넘겨줘야해! -> 비슷한 건? Map!!!
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				boardService.deleteArticle(articleNo);
				
				return "/board?act=list&pgno=1&key=&word="; 
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 삭제 중 에러 발생!!!"); // 글번호 없는 거 가져오면 에러 !!
				return "/error/error.jsp";
			}
			
			
		} else {
			return "/user/login.jsp"; // 얘도 redirect
		}
	}

}
