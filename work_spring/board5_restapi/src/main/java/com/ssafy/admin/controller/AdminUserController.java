package com.ssafy.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

// url 단 두 개 admin/user, admin/user/{id} => 기능은 5개 => method까지 결합이 되어야 함! uri+method 적절히 합쳐진 것: rest다 

// 그냥 Controller가 아니라 RestConroller로 해버리면 
// @ResponseBody를 해주지 않아도 모~든  String의 return은 ResponseBody로 인식됨  
// (String은 Client에게 넘겨주는 data의 원본이다 라고 인식)

// 넘겨야 하는 data 3가지: data, header(ContentType...), status가 있어야 함
// data: JSON
// header: application/json => 내가 넘겨주려는 데이터는 json이야~
// data의 타입 지정 가능 ResponseEntity<List<MemberDto>> 
//  ResponseEntity<?> : 모든 타입 지정 가능 => 모~든 타입 들어올 수 있어~

// 다 넘기려면 ResponseEntity라는 타입으로 넘겨줌 => "status"가 있음
// public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status)
// boday: data, headers: content: application/json ... key-value 형식 => map형식, status

// status: 정상적 처리 됐어.. 아니야... 페이지 없는데... 에러났는데.. 이런 거 
@RestController
@RequestMapping("/admin")
public class AdminUserController {
	
	// 중복 코드: alt+shift+m으로 return값... 알아서 똑같이 method로 빼줌

	private MemberService memberService;

	// 하나만 생성하면 @Autowired 자동으로 붙음
	public AdminUserController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	// @ResponseBody: view의 이름이 아니라 이거 자체가 응답의 데이터다~라는 것을 알려주는 것
	// public @ResponseBody String list()
	@GetMapping("/user")
	public ResponseEntity<?> userList() {
		// list~~ modify~~ 이렇게 맞추면 좋은 점: aop 설정 가능
		try {
			// 나중에 map 보내서 페이징 처리 하기!!
			List<MemberDto> list = memberService.listMember(null); // >> JSON Array로 바꿔야 함 => "List"니까!
			// 정상적으로 list 얻어 왔다면 우리는 string으로 return해야 함
			// JSON Object만들고 JSON Array만들어서 넘겨줘야 함...
			// list memberDto 하나 당 json object만들고 그걸 array에 추가시켜라... 그래서 toJsonString해서 변환해서
			// 보냈음 옛날엔..

			// 어차피 올 때 json으로 보냈기 때문에 갈 때도 json으로 보낼 거
			if (list != null && !list.isEmpty()) { // 회원 목록이 있다면
				// list가 OK와 함께 넘어감 => 저쪽에서는 status가 200이라면 404라면 500이라면 if문같은 거 돌려서 어디로 가라~ 라고
				// 말할 수 있음
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK); // MemberDto가 들어있는 List의 타입으로 넘겨줄거야!라고
																					// 설정함
			} else {
				// Void 타입으로 넘길거야~ -> 객체가 없으니
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Exception이 났으면? 머 리턴? => 그냥 String 보내주면 됨 => 그래서 위에 타입을 지정하지 않고 ?를 한 거임.
			return exceptionHandling(e);
		}
	}
	
	// @RequestParam은 알아서 매핑 됐었는데
	// 넘어오는 string json은 @RequestBody로 매칭시켜줌
	// url은 같지만 method의 형식, 종류를 가지고 여러가지를 똑같은 주소로 하고 있다!
	@PostMapping("/user")
	public ResponseEntity<?> userJoin(@RequestBody MemberDto memberDto) {
		try {
			System.out.println(memberDto.getUserId());
			memberService.joinMember(memberDto);
			List<MemberDto> list = memberService.listMember(null); 
			if (list != null && !list.isEmpty()) { 
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK); 
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/user")
	public ResponseEntity<?> userModify(@RequestBody MemberDto memberDto) {
		try {
			memberService.modifyMember(memberDto);
			List<MemberDto> list = memberService.listMember(null); 
			if (list != null && !list.isEmpty()) { 
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK); 
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}


	// parameter: @RequestParam
	// url상으로 오는 것: @PathVariable("id") String a 여기 id 값을 a로 받겠다는 뜻! (경로상의 변수라는 뜻)
	// @GetMapping("/user/{userId}") => 같으면 @PathVariable("userId") 생략해도 됨 => 내가
	// 정해주는 거임
	// /user/{userid}?no=12 ~~~~~~~~~~~~~~~~~~~ => RequestParam
	// public ResponseEntity<?> userDelete(@PathVariable String userId)
	// 두개 보낼 경우: @GetMapping("/user/{userId}/{articleno}") (@PathVariable1 userId,
	// @PathVariable2...)
	@DeleteMapping("/user/{userid}") // CRUD Mapping!!!! 
	public ResponseEntity<?> userDelete(@PathVariable("userid") String userId) {
		try {
			memberService.deleteMember(userId);
			List<MemberDto> list = memberService.listMember(null);

			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		// 외래키: user 삭제할 때 이 놈들 무시하거나 글을 다 지우거나
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<?> userView(@PathVariable("userid") String userId) {
		try {
			MemberDto memberDto = memberService.getMember(userId);

			if (memberDto != null) { // 한 사람의 정보를 얻어왔으면
				return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK); // 만족하면 json 하나를~ 
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	// rest: uri안에 data가 넘어가기도 하면서 행위를 알려주는 것 -> method  

}
