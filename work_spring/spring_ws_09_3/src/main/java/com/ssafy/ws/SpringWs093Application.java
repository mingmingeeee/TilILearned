package com.ssafy.ws;

import java.util.Arrays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.ws.interceptor.SessionInterceptor;

// 지금은 static이 root 경로라고 생각하면 됨 
// 정적리소스는 기본적으로 루트(/**)로 매핑이 됨
// 바꾸려면 application.properties에서 static-path-pattern로 설정

@SpringBootApplication
@Configuration // 설정 파일임을 알려줌 
@ComponentScan(basePackages={"com.ssafy"}) // component-scan bean 등록
@EnableAspectJAutoProxy // auto proxy
@MapperScan(basePackages= {"com.ssafy.**.dao"}) // mapper bean 
public class SpringWs093Application implements WebMvcConfigurer {
	// WebMvcConfiurer: default 생성자라 구현하라고 안 생김

	// SessionInterceptor 객체로 가져와야 함 => Interceptor 사용하기 위해
	@Autowired
	private SessionInterceptor sessionInterceptor;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringWs093Application.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(sessionInterceptor).addPathPatterns(Arrays.asList("/regist"));
		// .excludePathPatterns(patterns): 이 경로는 제외하고 해라~~
	}

}

// # resource-mapping: static으로 별다른 설정 없이 이용 가능 
// Log level의 기본 설정: info, com.ssafy package에 있는 것들을 debug로 설정됨 
