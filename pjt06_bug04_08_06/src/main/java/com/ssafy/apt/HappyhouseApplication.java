package com.ssafy.apt;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.interceptor.AdminInterceptor;
import com.ssafy.interceptor.SessionInterceptor;

@SpringBootApplication
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.ssafy"})
@MapperScan(basePackages = { "com.ssafy.*.model.mapper" })
public class HappyhouseApplication implements WebMvcConfigurer {
	
	private final List<String> user_patterns = Arrays.asList("/board/*");
	private final List<String> admin_patterns = Arrays.asList("/user/userlist");
	
	@Autowired
	private SessionInterceptor sessionInterceptor;
	@Autowired
	private AdminInterceptor adminInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(HappyhouseApplication.class, args);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
				.maxAge(1800);
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor).addPathPatterns(user_patterns);
		registry.addInterceptor(adminInterceptor).addPathPatterns(admin_patterns);
	}
	
}
