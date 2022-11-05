package com.ssafy.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.PageNavigation;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*") // CORS 처리 위함 
// : 모~든 url, 모~든 method로 접속 가능
// 특정 url, domain, 특정 method로 접근하고 싶을 때는??? WebMvcConfiguration => addCorsMapping
public class AdminUserController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	private MemberService memberService;
	private Map<String, String> map = new HashMap<>();

	@Autowired
	public AdminUserController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping(value = {"/users", "/users/{pgno}", "/users/{pgno}/{key}","/users/{pgno}/{key}/{word}"})
	public ResponseEntity<?> userList(@PathVariable("pgno") Optional<String> pgno, @PathVariable("key") Optional<String> key, @PathVariable("word") Optional<String> word) {
		logger.debug("userList call");
		try {
			Map<String, String> map = new HashMap<>();
			if(pgno.isPresent()) 
				map.put("pgno", pgno.get());
			if(key.isPresent())
				map.put("key", key.get());
			if(word.isPresent())
				map.put("word", word.get());
			
			Map<String, Object> data = new HashMap<>();
			List<MemberDto> list = memberService.listMember(map);
			PageNavigation pageNavigation = memberService.makePageNavigation(map);
			data.put("users", list);
			data.put("navigation", pageNavigation);
			if(pgno.isPresent()) 
				data.put("pgno", pgno.get());
			if(key.isPresent())
				data.put("key", key.get());
			if(word.isPresent())
				data.put("word", word.get());

			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	@PostMapping(value = "/user")
	public ResponseEntity<?> userRegister(@RequestBody MemberDto memberDto) {
		logger.debug("userRegister memberDto : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			Map<String, Object> data = new HashMap<>();
			List<MemberDto> list = memberService.listMember(map);
			PageNavigation pageNavigation = memberService.makePageNavigation(map);
			data.put("users", list);
			data.put("navigation", pageNavigation);
			data.put("pgno", map.get("pgno"));
			data.put("key", map.get("key"));
			data.put("word", map.get("word"));

			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			}  else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
//			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	@GetMapping(value = "/user/{userid}")
	public ResponseEntity<?> userInfo(@PathVariable("userid") String userId) {
		logger.debug("userInfo userid : {}", userId);
		try {
			MemberDto memberDto = memberService.getMember(userId);
			if (memberDto != null)
				return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}


	@PutMapping(value = "/user")
	public ResponseEntity<?> userModify(@RequestBody MemberDto memberDto) {
		logger.debug("userModify memberDto : {}", memberDto);
		try {
			memberService.updateMember(memberDto);
			Map<String, Object> data = new HashMap<>();
			List<MemberDto> list = memberService.listMember(map);
			PageNavigation pageNavigation = memberService.makePageNavigation(map);
			data.put("users", list);
			data.put("navigation", pageNavigation);
			data.put("pgno", map.get("pgno"));
			data.put("key", map.get("key"));
			data.put("word", map.get("word"));

			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			}  else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}


	@DeleteMapping(value = "/user/{userid}")
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userId) {
		logger.debug("userDelete userid : {}", userId);
		try {
			memberService.deleteMember(userId);
			Map<String, Object> data = new HashMap<>();
			List<MemberDto> list = memberService.listMember(map);
			PageNavigation pageNavigation = memberService.makePageNavigation(map);
			data.put("users", list);
			data.put("navigation", pageNavigation);
			data.put("pgno", map.get("pgno"));
			data.put("key", map.get("key"));
			data.put("word", map.get("word"));
			

			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			}  else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

