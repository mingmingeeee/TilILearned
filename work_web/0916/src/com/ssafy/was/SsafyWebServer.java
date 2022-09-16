package com.ssafy.was;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

public class SsafyWebServer {
	
	private static final int PORT = 8080;
	
	public static void main(String[] args) {
		
		// 서버 소켓
		ServerSocket serverSocket = null;
		
		// 클라이언트 소켓
		Socket socket = null;
		
		// 소켓 통신에 활용할 노드 스트림
		InputStream is = null;
		OutputStream os = null;
		
		// 소켓 통신에 활용할 보조 스트림
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		while (true) {
			try {
				// 클라이언트 요청을 받을 서버 소켓 생성
				serverSocket = new ServerSocket(PORT);
				System.out.println("SSAFY Web Server is running ... " + PORT);
				
				// 클라이언트 요청 대기
				socket = serverSocket.accept();
				System.out.println("Client is accepted ...");
				
				// 데이터 주고받기 위해 노드 스트림과 보조 스트림 객체 생성
				// 최종적으로 br, bw를 사용
				is = socket.getInputStream();
				os = socket.getOutputStream();
				isr = new InputStreamReader(is);
				osw = new OutputStreamWriter(os);
				br = new BufferedReader(isr);
				bw = new BufferedWriter(osw);
				
				// 클라이언트로부터 요청 받기
				String line = br.readLine();
				if (line != null) {
					// 요청받은 내용 분석
					System.out.println(line);
					
					String[] split = line.split(" ");
					String method = split[0];
					String uri = split[1];
					String protocol = split[2];
					
					if ("GET".equals(method)) {
						switch (uri) {
						default:
							// 서버에 있는 정적파일을 클라이언트로 응답	보내기
							File file = new File("static" + URLDecoder.decode(uri));
							if (file.exists()) {
								// 응답 헤더 작성
								bw.write("HTTP/1.1 200 OK\r\n");
								bw.write("Server:SSAFY Web Server\r\n");
								bw.write("Content-Type:text/html; charset=UTF-8\r\n");
								bw.write("\r\n");  // 헤더 영역과 Payload 영역을 구분하기 위해 한줄 비움

								// 서버에 있는 파일 불러온 뒤, 클라이언트로 보내기
								BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
								while (true) {
									String content = fin.readLine();
									if (content == null) {
										break;
									}
									bw.write(content);
								}
								fin.close();
								
							}
							else {
								bw.write("HTTP/1.1 404 Not Found\r\n");
								bw.write("Content-Type:text/html; charset=UTF-8\r\n");
								bw.write("\r\n");  // 헤더와 Payload 구분하기 위한 줄바꿈
								bw.write("<h1>" + uri + "는 존재하지 않습니다.</h1>");
							}
						}
					}
					else if ("POST".equals(method)) {
						
					}
				}
				
				
			}
			catch (IOException e) {
				e.printStackTrace();
				
			}
			finally {
				try {
					if (bw != null) {
						bw.flush(); // Buffered내용 다 쌓아놔서... 그 걸 보내게??..
						bw.close();
					}
					if (br != null) {
						br.close();
					}
					if (socket != null) {
						socket.close();
					}
					if (serverSocket != null) {
						serverSocket.close();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


