package com.ssafy.corona.app;

/**
 * 1. Singleton
 * 2. 생성자
 * 3. 정렬 (Comparable) -> Class 안에 
 * 4. 예외 처리 
 * 5. 파일 저장 & 불러 오기
 */

import java.lang.reflect.Array;
import java.util.*;
import com.ssafy.corona.virus.*;

public class MainTest {
	public static void main(String[] args) {
	

		// 10.질병관리 문제 //
		//
		//	아래 11~13번 주석을 해제하여
		//	"정상 출력 예" 와 같이 출력될 수 있도록 
		//	코드들을 디버깅 하세요!
		//
		System.out.println("10.질병관리(코로나,메르스) =================================");
		VirusMgr vmgr = VirusMgrImpl.getInstance();
		VirusMgr vmgr2 = VirusMgrImpl.getInstance();
		if(vmgr == vmgr2) {
			System.out.println("싱글턴 성공!!");
		}
		System.out.println();		
		
//<- 주석 해제 후 작성 : start ////////////////////////////////
		System.out.println("11.코로나19 등록");
		// 정상 출력 예: 
		// 11.코로나19 등록
		// 코로나19: 등록된 바이러스입니다.
		try {
			vmgr.add(new Mers(  "메르스15", 2, 1.5));
			vmgr.add(new Corona("코로나19", 3, 2));
			// vmgr.add(new Corona("코로나19", 2, 2)); // duplicated면
			vmgr.add(new Mers("메르스19", 10, 3));
			vmgr.add(new Corona("메르스 20", 11, 3));
			vmgr.add(new Corona("코로나22", 2, 2));
		} catch (DuplicatedException e) { // 바로 넘어감 예외로 
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		
		System.out.println("12.바이러스 전체검색");
		// 정상 출력 예: 
		// 12.바이러스 전체검색
		// 메르스15	2	1.5
		// 코로나19	3	2
		Virus[] virus=vmgr.search();
		// Collections 쓰기 위해 List로 바꿔줌
		List<Virus> asList = Arrays.asList(virus);
		// 정렬 호출
		Collections.sort(asList);
		
		for(Virus v:asList) {
			System.out.println(v);
		}
		System.out.println();
		
		
		System.out.println("13.코로나15 조회");
		// 정상 출력 예: 
		// 13.코로나15 조회
		// 코로나15: 미등록 바이러스입니다.
		try {
			Virus v=vmgr.search("코로나15");
			System.out.println(v);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
//<- 주석 해제 후 작성 : end /////////////////////////////////
		
		try {
			vmgr.save();
			vmgr.load(); // 불러 오기
			for(Virus v : vmgr.search()) { // 가져오기
				System.out.println(v);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("파일 관련 예외 발생!!!");
		}

	}
}
