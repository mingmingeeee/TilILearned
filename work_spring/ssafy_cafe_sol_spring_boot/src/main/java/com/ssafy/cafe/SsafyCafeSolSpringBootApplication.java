package com.ssafy.cafe;

import java.util.Arrays;

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

import com.ssafy.cafe.interceptor.SessionInterceptor;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.ssafy.*.model.mapper" })
public class SsafyCafeSolSpringBootApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SsafyCafeSolSpringBootApplication.class, args);
	}

	@Autowired
	private SessionInterceptor sessionInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor)
			.addPathPatterns(Arrays.asList("/assets/*"))
			.excludePathPatterns(Arrays.asList("/assets/index.html"));
	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedOrigins("*")
//			.allowedMethods(
//					HttpMethod.GET.name(),
//					HttpMethod.POST.name(),
//					HttpMethod.PUT.name(),
//					HttpMethod.DELETE.name(),
//					HttpMethod.HEAD.name(),
//					HttpMethod.OPTIONS.name(),
//					HttpMethod.PATCH.name())
//			.maxAge(1800);
//	}
}
