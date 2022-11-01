package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.Pagenation;
import com.ssafy.util.ParameterCheck;

@Controller
@RequestMapping("/user")
public class MemberController {

	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	private Map<String, String> map;
	private Pagenation memberPagenation = new Pagenation();

	@PostMapping("/registFavarea")
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
				return "index";
			} catch (Exception e) {

				request.setAttribute("msg", "관심지역 추가 중 에러발생");
				return "error/error";
			}
		} else {
			request.setAttribute("msg", "지역을 선택해 주세요");
			return "index";
		}
	}

	@GetMapping("/userlist")
	public String userlist(@RequestParam Map<String, String> request, Model model) {
		// page
		int pgNo1 = ParameterCheck.notNumberToOne(request.get("pgno"));
		String key1 = ParameterCheck.nullToBlank(request.get("key"));
		String word1 = ParameterCheck.nullToBlank(request.get("word"));
		map = new HashMap<>();
		map.put("pgno", pgNo1 + "");
		map.put("key", key1);
		map.put("word", word1);
		//

		try {
			List<MemberDto> list = memberService.listUser(map);
			model.addAttribute("users", list);
			int listCnt = memberService.getUserListCnt(map);
			int pgNo = Integer.parseInt(map.get("pgno"));
			int range = ParameterCheck.notNumberToOne(request.get("range"));
			memberPagenation.pageInfo(pgNo, range, listCnt);
			model.addAttribute("pagination", memberPagenation);
			return "/user/userlist";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 목록 얻기 중 에러발생!!!");
			return "error/error";
		}

	}

	@PostMapping("/delete")
	public String delete(HttpSession session, @RequestParam("userId") String userId, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		try {
			if (!memberDto.getUserId().equals("admin")) {
				String userid = memberDto.getUserId();
				memberService.deleteMember(userid);
				session.invalidate();
			} else {
				memberService.deleteMember(userId);
				if (userId.equals("admin")) {
					session.invalidate();
				}
			}

			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 삭제 중 에러발생!!!");
			return "error/error";
		}
	}

	@PostMapping("/usermodify")
	public String usermodify(HttpSession session, @RequestParam Map<String, String> map, Model model,
			RedirectAttributes redirectAttributes) {
		MemberDto memberDtobefore = (MemberDto) session.getAttribute("userinfo");

		try {
			MemberDto memberDto = new MemberDto();
			memberDto.setUserId(map.get("userid"));
			memberDto.setUserName(map.get("username"));
			memberDto.setUserPwd(map.get("userpwd"));
			memberDto.setEmailId(map.get("emailid"));
			memberDto.setEmailDomain(map.get("emaildomain"));

			memberService.usermodify(memberDto);
			
			MemberDto newmemberDto = new MemberDto();
			newmemberDto.setUserId(memberDto.getUserId());
			newmemberDto.setUserName(memberDto.getUserName());

			if (!memberDtobefore.getUserId().equals("admin")) {
				session.setAttribute("userinfo", newmemberDto);
				return "redirect:/user/userview";
			}
			if (newmemberDto.getUserId().equals("admin") && memberDtobefore.getUserId().equals("admin")) {
				session.setAttribute("userinfo", newmemberDto);
				return "redirect:/user/userview";

			}

			return "redirect:/user/userlist";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 수정 처리중 에러 발생!!!");
			return "error/error";
		}
	}

	@GetMapping("/userview")
	public String userview(HttpSession session, @RequestParam("clickuserId") String id, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				MemberDto newmemberDto = null;

				if (id == null) {
					newmemberDto = memberService.viewMember(memberDto.getUserId());
				} else {
					newmemberDto = memberService.viewMember(id);
				}

				model.addAttribute("user", newmemberDto);
				return "user/userview";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "회원정보 조회 중 에러발생!!!");
				return "error/error";
			}
		} else {
			return "user/login";
		}
	}

	@PostMapping("/mvusermodify")
	public String mvusermodify(HttpSession session, Model model, @RequestParam("userId") String userId) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			try {
				if (!memberDto.getUserId().equals("admin")) {
					MemberDto newmemberDto = memberService.viewMember(memberDto.getUserId());
					model.addAttribute("user", newmemberDto);
				} else {
					MemberDto newmemberDto = memberService.viewMember(userId);
					model.addAttribute("user", newmemberDto);
				}

				return "user/usermodify";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "회원정보 수정 중 에러발생!!!");
				return "error/error";
			}
		} else {
			return "user/login";
		}

	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
//		session.removeAttribute("userinfo");
		session.invalidate();
		return "index";
	}

	@PostMapping("/login")
	public String login(HttpSession session, @RequestParam Map<String, String> map, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = map.get("userid");
		String userPwd = map.get("userpwd");
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if (memberDto != null) { // 로그인 성공(id, pwd 일치!!!!)

				String saveid = map.get("saveid");
				if ("ok".equals(saveid)) { // 아이디 저장 체크 O.
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
					cookie.setPath("/");

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

				session.setAttribute("userinfo", memberDto);
				String referer = request.getHeader("referer");
				return "index";
			} else { // 로그인 실패(id, pwd 불일치!!!!)
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인!!!");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 처리중 에러 발생!!!");
			return "error/error";
		}
	}

	@PostMapping("/join")
	public String join(@RequestParam Map<String, String> map, Model model) {
		MemberDto memberDto = new MemberDto();
		memberDto.setUserId(map.get("userid"));
		memberDto.setUserName(map.get("username"));
		memberDto.setUserPwd(map.get("userpwd"));
		memberDto.setEmailId(map.get("emailid"));
		memberDto.setEmailDomain(map.get("emaildomain"));
		try {
			memberService.joinMember(memberDto);
			return "redirect:/user/mvlogin";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "error/error";
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/idCheck")
	public int idCheck(@RequestParam("userid") String userId) {
		// System.out.println(userId);
		try {
			int count = memberService.idCheck(userId);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 500;
	}

}
