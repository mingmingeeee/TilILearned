package com.ssafy.board;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class BoardController {
	
	private BoardService service = new BoardService();

	public void showMenu() {
		
		while (true) {
			System.out.println("=== MENU ===");
			System.out.println("> list");
			System.out.println("> detail no");
			System.out.println("> new title, content, writer");
			System.out.println("> modify no, title, content");
			System.out.println("> delete no");
			System.out.println("> rollback");
			System.out.println("> exit");
			System.out.println();
			readMenu();
		}
	}

	private void readMenu() {
		System.out.print("> ");
		Scanner scanner = new Scanner(System.in);
		try {
			String line = scanner.nextLine();
			if (line.equals("exit")) {
				System.out.println("bye. ");
				System.exit(0);
			}
			else {
				int idx = line.indexOf(" ");
				String cmd = (idx == -1 ? line : line.substring(0, idx));
				String[] args = line.substring(idx + 1).split(", ");
				execute(cmd, args);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void execute(String cmd, String[] args) {
		DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
		
		switch (cmd) {
		case "list":
			try {
				List<Board> list = service.list();
				System.out.println("=== List ===");
				for (Board b : list) {
					StringBuilder sb = new StringBuilder();
					sb.append(b.getNo()).append("\t")
					.append(b.getTitle()).append("\t")
					.append(b.getId()).append("\t")
					.append(df.format(b.getCreatedAt()));
					
					System.out.println(sb);
				}
				System.out.println();
			}
			catch (SQLException e) {
				System.out.println("글 목록을 불러오지 못했습니다.");
				e.printStackTrace();
			}
			break;
			
		case "detail":
			try {
				if (args != null && args.length == 1) {
					Board detailBoard = service.detail(args[0]);
					System.out.println("=== DETAIL ===");
					System.out.println("글 번호: " + detailBoard.getNo());
					System.out.println("글 제목: " + detailBoard.getTitle());
					System.out.println("글 내용: " + detailBoard.getContent());
					System.out.println("글 작성자ID: " + detailBoard.getId());
					System.out.println("글 작성날짜: " + df.format(detailBoard.getCreatedAt()));
				}
			}
			catch (SQLException e) {
				System.out.println("글 상세정보를 불러오지 못했습니다.");
				e.printStackTrace();
			}
			break;
			
		case "new":
			try {
				if (args != null && args.length == 3) {
					Board newBoard = new Board();
					newBoard.setTitle(args[0]);
					newBoard.setContent(args[1]);
					newBoard.setId(args[2]);
					int cnt = service.newBoard(newBoard);
					System.out.println(cnt + "개의 글이 작성되었습니다.");
				}
			}
			catch (SQLException | BoardException e) {
				System.out.println("새로운 글을 작성하지 못했습니다.");
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				if (args != null && args.length == 3) {
					Board modifyBoard = new Board();
					modifyBoard.setNo(Integer.parseInt(args[0]));
					modifyBoard.setTitle(args[1]);
					modifyBoard.setContent(args[2]);
					int cnt = service.modify(modifyBoard);
					System.out.println(cnt + "개의 글이 수정되었습니다.");
				}
			}
			catch (SQLException | BoardException e) {
				System.out.println("글을 수정하지 못했습니다.");
				e.printStackTrace();
			}
			break;
			
		case "delete":
			try {
				if (args != null && args.length == 1) {
					int cnt = service.remove(args[0]);
					System.out.println(cnt + "개의 글이 삭제되었습니다.");
				}
			}
			catch (SQLException | BoardException e) {
				System.out.println("글을 삭제하지 못했습니다.");
				e.printStackTrace();
			}
			break;
			
		case "rollback":
			try {
				service.testRollback();
			}
			catch (SQLException | BoardException e) {
				System.out.println("rollback 실행");
				e.printStackTrace();
			}
			break;
		}
	}
}
