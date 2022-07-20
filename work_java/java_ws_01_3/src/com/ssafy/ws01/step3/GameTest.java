package com.ssafy.ws01.step3;

import java.util.Scanner;

public class GameTest {
	
	public static void main(String[] args) {
		System.out.println("가위바위보 게임을 시작합니다.");
		System.out.println("1. 5판 3승");
		System.out.println("2. 3판 2승");
		System.out.println("3. 1판 1승");
		System.out.print("번호를 입력하세요.");
		
		Scanner scanner = new Scanner(System.in);
		
		int menu = scanner.nextInt();
		int loop; // 메뉴에 따라 게임 반복 횟수 저장 변수
		
		switch(menu) {
		
		case 1:
			loop = 5;
			break;
			
		case 2:
			loop = 3;
			break;
			
		case 3:
			loop = 1;
			break;
			
		default: 
			System.out.println("번호를 잘못 입력하셨습니다.");
			return;
				
		}
		
		int userWinCount = 0;
		int comWinCount = 0;
		
		do {
			System.out.println("가위바위보 중 하나 입력: ");
			String input = scanner.next();
			int user = -1;
			switch(input) {
			case "가위":
				user = 1;
				break;
				
			case "바위":
				user = 2;
				break;
				
			case "보":
				user = 3;
				break;
				
			default:
				System.out.println("잘못 입력하셨습니다. 졌습니다!!!");
				comWinCount++;
				continue;
			}
			
			int com = (int) (Math.random() * 3) +1;
			
			boolean case1 = user == 1 && com == 2; // 컴퓨터 승
			boolean case2 = user ==1 && com ==3; // 사용자 승
			boolean case3 = user == 2 && com == 1; // 사용자 승
			boolean case4 = user == 2 && com == 3; // 컴퓨터 승
			boolean case5 = user == 3 && com == 1; // 컴퓨터 승
			boolean case6 = user == 3 && com == 2; // 사용자 승
			
			if(case2 || case3 || case6) {
				userWinCount++;
				System.out.println("이겼습니다!!!");
			}
			else if(case1 || case4 || case5) {
				comWinCount++;
				System.out.println("졌습니다!!!");
			}
			else {
				System.out.println("비겼습니다!!!");
			}
			
			if(menu == 1 && userWinCount > 2 || comWinCount>2) {
				break;
			}
			else if(menu == 2 && userWinCount > 1 || comWinCount>1) {
				break;
			}
			
		}while(--loop != 0);
		
		if(userWinCount > comWinCount) {
			System.out.println("### 유저 승!!!");
		}
		else if(userWinCount > comWinCount) {
			System.out.println("### 컴퓨터 승!!!");
		}
		else {
			System.out.println("### 무승부!!!");
		}
		
	}

}
