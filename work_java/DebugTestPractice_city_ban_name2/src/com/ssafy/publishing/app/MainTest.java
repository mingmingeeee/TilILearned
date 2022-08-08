package com.ssafy.publishing.app;

import java.util.Collections;
import java.util.List;

import com.ssafy.publishing.book.Book;
import com.ssafy.publishing.book.BookMgr;
import com.ssafy.publishing.book.BookMgrImpl;
import com.ssafy.publishing.book.DuplicatedException;
import com.ssafy.publishing.book.Megazine;
import com.ssafy.publishing.book.NotFoundException;
import com.ssafy.publishing.book.Novel;
import com.ssafy.publishing.person.Author;
import com.ssafy.publishing.person.Editor;
import com.ssafy.publishing.person.Person;
import com.ssafy.publishing.person.PersonMgr;
import com.ssafy.publishing.person.PersonMgrImpl;
import com.ssafy.publishing.vendor.BookStore;
import com.ssafy.publishing.vendor.Vendor;
import com.ssafy.publishing.vendor.VendorMgr;
import com.ssafy.publishing.vendor.VendorMgrImpl;

public class MainTest {
	public static void main(String[] args) {
		////////////////////////////////////////////////////////////
		// main() 메서드는 주석문 해제와 관련된 부분을 제외하고는 수정하는 부분이 없습니다! //
		////////////////////////////////////////////////////////////
		
		// 00.서술형 문제 //
		//
		//	서술형 문제의 정답은 프로젝트 폴더의 report.txt 파일에 
		//	내용을 작성하여 제출하세요!
		//
		
		

		// 10.도서관리 문제 //
		//
		//	아래 11~13번 주석을 해제하여
		//	"정상 출력 예" 와 같이 출력될 수 있도록 
		//	코드들을 디버깅 하세요!
		//
		System.out.println("10.도서관리(소설,잡지) =================================");
		BookMgr subMgr=new BookMgrImpl();
		System.out.println();		
		
//<- 주석 해제 후 작성 : start ////////////////////////////////
		System.out.println("11.과목 등록");
		// 정상 출력 예: 
		// 11.과목 등록
		// 경제매거진: 등록된 과목입니다.
		try {
			subMgr.add(new Novel("이것은 소설이다", 80, "홍길동"));
			subMgr.add(new Megazine("경제매거진", 150, 2000)); 
			subMgr.add(new Megazine("경제매거진", 260, 1890));
		} catch (DuplicatedException e) { 
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		
		System.out.println("12.등록 도서 전체검색");
		// 정상 출력 예: 
		// 12.등록 과목 전체검색
		// 이것은 소설이다	80	홍길동
		// 경제매거진		150	2000
		
		Book[] subs=subMgr.search();
		for(Book s:subs) {
			System.out.println(s); 
		}
		System.out.println();
		
		
		System.out.println("13.Vue.js도서 조회");
		// 정상 출력 예: 
		// 13.Vue.js도서 조회
		// Vue.js: 미등록 도서입니다.
		try {
			Book s=subMgr.search("Vue.js"); 
			System.out.println(s);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
 //<- 주석 해제 후 작성 : end /////////////////////////////////
		
		
		
		// 20.교육생관리 문제 //
		//
		//	아래 21~25번 주석을 해제하여
		//	"정상 출력 예" 와 같이 출력될 수 있도록 
		//	코드들을 디버깅 하세요!
		//
		System.out.println("20.인력관리(편집장,작가) ==================================");
		PersonMgr pmgr=PersonMgrImpl.getInstance(); 
		System.out.println();		
		
 //<- 주석 해제 후 작성 : start ////////////////////////////////
		System.out.println("21.편집장, 작가 등록");
		// 정상 출력 예: 
		// 21.편집장, 작가 등록
		// 등록정상

		pmgr.add(new Editor("오작가",41,"010-1010-0010","101","스포츠"));
		pmgr.add(new Author("오작가",35,"010-0505-0005","오닉네임","101",true));
		pmgr.add(new Editor("이편집장",42,"010-2020-0020","101","연예"));
		pmgr.add(new Editor("김편집장",43,"010-3030-0030","102","시사"));
		pmgr.add(new Author("이작가",32,"010-0202-0002","이닉네임","901",false));
		pmgr.add(new Author("강작가",23,"010-0303-0003","강닉네임","902",true));
		System.out.println("등록정상");
		System.out.println();
		
		
		System.out.println("22.교육 참여인 전체검색");
		// 정상 출력 예: 수정 내용 없음
		// 22.교육 참여인 전체검색
		// 오작가		41	010-1010-0010	101	스포츠
		// 오작가		35	010-0505-0005	오닉네임	101	true
		// 이편집장		42	010-2020-0020	101	연예
		// 김편집장		43	010-3030-0030	102	시사
		// 이작가		32	010-0202-0002	이닉네임	901	false
		// 강작가		23	010-0303-0003	강닉네임	902	true
		List<Person> people=pmgr.search();
		for(Person p : people) {
			System.out.println(p);
		}
		System.out.println();
		
		
		System.out.println("23.오작가 이름 모두 삭제");
		// 정상 출력 예: 
		// 23.오작가 이름 모두 삭제
		// 이편집장		42	010-2020-0020	101	연예
		// 김편집장		43	010-3030-0030	102	시사
		// 이작가		32	010-0202-0002	이닉네임	901	false
		// 강작가		23	010-0303-0003	강닉네임	902	true
		pmgr.delete("오작가"); 
		for(Person p : pmgr.search()) System.out.println(p);
		System.out.println();
		
		
		System.out.println("24.도서 관련인 정보 people.dat 파일에 저장");
		// 정상 출력 예: 
		// 24.도서 관련인 정보 people.dat 파일에 저장
		// 저장정상
		pmgr.save(); 
		System.out.println("저장정상");
		System.out.println();
		
		
		System.out.println("25.도서 관련인 정보 people.dat 파일에서 로드");
		// 정상 출력 예: 
		// 25.도서 관련인 정보 people.dat 파일에서 로드
		// 이편집장		42	010-2020-0020	101	연예
		// 김편집장		43	010-3030-0030	102	시사
		// 이작가		32	010-0202-0002	이닉네임	901	false
		// 강작가		23	010-0303-0003	강닉네임	902	true
		pmgr.load(); 
		for(Person p : pmgr.search()) System.out.println(p);
		System.out.println();
 //<- 주석 해제 후 작성 : end /////////////////////////////////
		
		
		
		// 30.공급업체관리 문제 //
		//
		//	아래 31~33번 주석을 해제하여
		//	"정상 출력 예" 와 같이 출력될 수 있도록 
		//	코드들을 디버깅 하세요!
		//
		System.out.println("30.공급업체관리(도서관, 서점) ===============================");
 //<- 주석 해제 후 작성 : start ////////////////////////////////
		VendorMgr vmgr=new VendorMgrImpl();
		System.out.println();
		

		System.out.println("31.공급업체 전체검색");
		// 정상 출력 예: 
		// 31.공급업체 전체검색
//		부산시립도서관	20000	부산
//		서울책방	5500	101	500	50
//		대전책방	2400	201	300	100
//		구미책방	1200	301	100	20
//		광주책방	1200	401	150	50
//		부산책방	1200	501	150	50
		List<Vendor> vendors=vmgr.search();
		for(Vendor vendor : vendors) {
			System.out.println(vendor);
		}
		System.out.println();
		
		
		System.out.println("32.수용 가능 인원(capacity) 오름차순 정렬");
		// 정상 출력 예: 
		// 32.수용 가능 인원(capacity) 오름차순 정렬
//		부산책방	1200	501	150	50
//		광주책방	1200	401	150	50
//		구미책방	1200	301	100	20
//		대전책방	2400	201	300	100
//		서울책방	5500	101	500	50
//		부산시립도서관	20000	부산
		Collections.sort(vendors); 
		for(Vendor vendor : vendors) {
			System.out.println(vendor);
		}
		System.out.println();
		
		
		System.out.println("33.구미 서점 조회(서점 이름이 같으면 조회)");
		// 정상 출력 예: 
		// 33.구미 서점 조회(서점 이름이 같으면 조회)
//		구미책방	1200	301	100	20
		Vendor vendor=vmgr.search(new BookStore("구미", 150, "001", 500, 50)); 
		System.out.println(vendor);
		System.out.println();
 //<- 주석 해제 후 작성 : end /////////////////////////////////
		
	}
}
