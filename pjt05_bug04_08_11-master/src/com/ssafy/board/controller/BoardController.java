package com.ssafy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.Pagenation;
import com.ssafy.util.ParameterCheck;

public class BoardController{
	
	private BoardService boardService = BoardServiceImpl.getBoardService();
	private Map<String, String> map;
	private Pagenation boardPagenation = new Pagenation();

	public String list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//page
		int pgNo1 = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		String key1 = ParameterCheck.nullToBlank(request.getParameter("key"));
		String word1 = ParameterCheck.nullToBlank(request.getParameter("word"));
		String sort = ParameterCheck.nullToBlank(request.getParameter("sort"));
		map = new HashMap<>();
		map.put("pgno", pgNo1 + "");
		map.put("key", key1);
		map.put("word", word1);
		map.put("sort", sort);
		
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
			try {
				List<BoardDto> list = boardService.listArticle(map);
				request.setAttribute("articles", list);
				int listCnt = boardService.getBoardListCnt(map);
				int pgNo = Integer.parseInt(map.get("pgno"));
				int range = ParameterCheck.notNumberToOne(request.getParameter("range"));
				boardPagenation.pageInfo(pgNo, range, listCnt);
				request.setAttribute("pagination", boardPagenation);
				return "/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글목록 얻기 중 에러발생!!!");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}

	public String write(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
			BoardDto boardDto = new BoardDto();
			boardDto.setUserId(memberDto.getUserId());
			boardDto.setSubject(request.getParameter("subject"));
			boardDto.setContent(request.getParameter("content"));
			try {
				boardService.writeArticle(boardDto);
				return "redirect:" + request.getContextPath() + "/servlet/board/list?pgno=1&key=&word=";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글작성 중 에러발생!!!");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}
	
	public String view(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				BoardDto boardDto = boardService.getArticle(articleNo);
				boardService.updateHit(articleNo);
				request.setAttribute("article", boardDto);
				return "/board/view.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 얻기 중 에러발생!!!");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}

	public String mvModify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				BoardDto boardDto= boardService.getArticle(articleNo);
				request.setAttribute("article", boardDto);
				return "/board/modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 수정 중 에러발생!!!");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}

	public String modify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				String subject = request.getParameter("subject");
				String content = request.getParameter("content");
				
				BoardDto boardDto = new BoardDto();
				boardDto.setArticleNo(articleNo);
				boardDto.setSubject(subject);
				boardDto.setContent(content);
				
				boardService.modifyArticle(boardDto);
				return "redirect:" + request.getContextPath() + "/servlet/board/list?pgno=1&key=&word=";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 삭제 중 에러발생!!!");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}

	public String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {
			try {
				int articleNo = Integer.parseInt(request.getParameter("articleno"));
				boardService.deleteArticle(articleNo);
				return "redirect:" + request.getContextPath() + "/servlet/board/list?pgno=1&key=&word=";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글 삭제 중 에러발생!!!");
				return "redirect:" + request.getContextPath() + "/error/error.jsp";
			}
		} else {
			return "/user/login.jsp";
		}
	}
	
}
