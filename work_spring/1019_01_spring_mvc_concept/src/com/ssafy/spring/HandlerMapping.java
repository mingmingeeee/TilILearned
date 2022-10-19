package com.ssafy.spring;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

public class HandlerMapping {

	// handler.prop 파일 내용을 저장할 자료구조
	private Map<String, Object> controllerMap = new HashMap<>();

	public HandlerMapping(ServletContext context) {
		// contextConfigLocation을 이용해서 handler.prop얻어오는 거 가능 => web.xml
		String config = context.getInitParameter("contextConfigLocation");
		// 경로 받아옴
		String configPath = context.getRealPath(config);

		// handler.prop 경로 전달 받아서 파일로 읽음
		try (FileReader fr = new FileReader(configPath)) {
			// Properties: 등호를 이용하여 좌=우 구별지어 맵 형태로 만들어줌
			// 예: user=com.ssafy.board.controller.UserController
			// Key: user, Value: com.ssafy.board.controller.UserController
			Properties prop = new Properties();
			prop.load(fr);

			// Key로 찾음
			for (Object obj : prop.keySet()) {
				////////////////////////////////////////////////////////
				String path = (String) obj; // path = Key의 값
//				System.out.println("path: " + path);
				String controllerName = prop.getProperty(path);
//				System.out.println("controllerName: " + controllerName);

				System.out.println(controllerName);
				// new BoardController(); => 밑에 두 줄은 이와 "reflection"이용해서 객체로 만드는 것
				Class<?> controllerClass = Class.forName(controllerName);
				Object instance = controllerClass.newInstance();
				////////////////////////////////////////////////////////

				// path를 key값으로 하는 모든 컨트롤러 정보 담은게 controllerMap
//				System.out.println("정보 담음");
//				System.out.println("key: " + path);
//				System.out.println("value: " + instance);
				controllerMap.put(path, instance); // 원래는 map이 아닌 스프링 컨테이너에 컨트롤러 빈이 존재하지만, 여기서는 간략하게 만들기 위해 이렇게 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getController(String pathInfo) {
		// 예: apthInfo: /board/regist
//		System.out.println("adaptehr: " +pathInfo);
		for (String key : controllerMap.keySet()) {
			if (pathInfo.startsWith(key)) {
				Object controller = controllerMap.get(key);
				return controller; // BoardController자체가 리턴에 되겠지용 
			}
		}

		return null;
	}

}
