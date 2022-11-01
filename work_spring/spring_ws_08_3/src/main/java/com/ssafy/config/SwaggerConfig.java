package com.ssafy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	// 우리 서버 주소 /swagger-ui/index.html
	
	@Bean
	public Docket api() {
		final ApiInfo apiInfo = new ApiInfoBuilder()
				.title("SSAFY 도서 관리 API")
				.description("<h3>데일리 실습에서 사용되는 RestApi에 대한 문서를 제공한다.</h3>")
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
				.license("MIT License")
				.version("1.0")
				.build();
		
		return new Docket(DocumentationType.SWAGGER_2) // Swagger 2.0 기반의 문서 작성
				.apiInfo(apiInfo) // 문서에 대한 정보를 설정한다.
				.select() // ApiSelectorBuilder를 반환하여 상세한 설정 처리
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.ws.controller")) // 대상으로 하는 REST API Controller 설정
				.paths(PathSelectors.ant("/bookapi/**")) // Controller에서 Swagger를 지정할 대상 path 설정 => @RequestMapping("/bookapi")
				.build();
	}

}
