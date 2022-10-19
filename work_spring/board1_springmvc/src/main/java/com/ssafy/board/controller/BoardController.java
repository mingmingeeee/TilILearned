package com.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.member.model.MemberDto;

@Controller
// 이렇게 하면 /board/board 됨 => board로 설정했기 때문 (상위 package를?)
@RequestMapping("/board")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 스프링 4.2버전 까지는 아래와 같은 방식으로 작성
	// @RequestMapping(value = "/write", method = RequestMethod.GET)
	
	@GetMapping("/write") // 스프링 4.3버전 이상부터 가능
	public String write() {
		logger.debug("write call");
		return "board/write";
	}

	// @RequestMapping(value = "/write", method = RequestMethod.POST)
	@PostMapping("/write")
	public String write(BoardDto boardDto, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		// boardDto를 통해 client에서 data 전달받음
		// Dto => Annotation 붙여주지 않아도 스프링에서 "아~~~~~~~~~얘가 담아야 하는 녀석이구나^^" 하면서 data 담아줌
		// 주의 ** : jsp에서 name이라고 적힌 부분이 "dto 필드랑 일치"해야 함 => 장점: 디버깅땐 좋은데 아니면 게속
		// dto만들어줘야함
		// Map 통해 받을지? Dto 통해 받을지? 선택하삼
		
		logger.debug("write boardDto : {}", boardDto);
		
		// Session 객체에서 userinfo 꺼내옴
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo"); 
		
		// 작성자 값 채워주고
		boardDto.setUserId(memberDto.getUserId());
		
		// service로 넘김
		boardService.writeArticle(boardDto);
		
		// ?aa=aaa 어쩌구 하면서 쿼리스트링으로 넘겨주지 않고 이렇게 넘겨줄 수 있음  => 훨씬 깔꼼
		redirectAttributes.addAttribute("pgno", "1");
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		
		return "redirect:/board/list";
	}
	
	// 이렇게도 쓸 수 있당
	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		
		// int a = 1 / 0; => 예외 발생 에러페이지 
		
		logger.debug("list parameter pgno : {}", map.get("pgno"));
		ModelAndView mav = new ModelAndView();
		
		List<BoardDto> list = boardService.listArticle(map);
		mav.addObject("articles", list);
		mav.addObject("pgno", map.get("pgno"));
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("board/list");
		
		return mav;
	}

	// 이렇게 쓰는 식으로 해보긔
	// Model 객체: Controller 에서 생성된 데이터를 담아 View 로 전달할 때 사용하는 객체
//	@GetMapping("/list")
//	public String list(@RequestParam Map<String, String> map, Model model) throws Exception {
//		logger.debug("list parameter pgno : {}", map.get("pgno"));
//		
//		List<BoardDto> list = boardService.listArticle(map);
//		model.addAttribute("articles", list);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		
//		return "board/list";
//	}
	
	@GetMapping("/view")
	public String view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model) throws Exception {
		logger.debug("view articleNo : {}", articleNo);
		BoardDto boardDto = boardService.getArticle(articleNo);
		model.addAttribute("article", boardDto);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		
		return "board/view";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model) throws Exception {
		logger.debug("modify articleNo : {}", articleNo);
		BoardDto boardDto = boardService.getArticle(articleNo);
		model.addAttribute("article", boardDto);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(BoardDto boardDto, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("modify boardDto : {}", boardDto);
		boardService.modifyArticle(boardDto);
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("delete articleNo : {}", articleNo);
		boardService.deleteArticle(articleNo);
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
		
		return "redirect:/board/list";
	}
	
}
