package com.ssafy.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// swagger 설정할 때
// 1. pom.xml 추가
// 2. configuration 설정
// 3. rest-controller에 가서 => @Api 설정해주면 됨

// swagger 설정 파일
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

//  접속 방식
//	Swagger-UI 2.x 확인
//	http://localhost[:8080]/{your-app-root}/swagger-ui.html
//	Swagger-UI 3.x 확인
//	http://localhost[:8080]/{your-app-root}/swagger-ui/index.html

	private String version = "V1";
	private String title = "SSAFY Board API " + version;
	
	// FE => BE , BE => FE 서로 뭘 주려고 보려고 만드는 것 (문서) => JSON OR XML 
	// => 일반 Controller는 사용 X
	// ​1. 레스트 컨트롤러로 만든 것들이 어디에 있는가?
	// 2. 접근할 때 어떤 식으로 주소에 쓰니?
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
					.apiInfo(apiInfo()).groupName(version).select()
					.apis(RequestHandlerSelectors.basePackage("com.ssafy.admin.controller"))
					// "com.ssafy.admin.controller": RestController가 어디에 적용되었는지 알려줘야 함
					.paths(regex("/admin/.*")).build() // 얘네 접속할 때 url에 어떻게 접속하는지에 대한 설정 
					.useDefaultResponseMessages(false); // 나머지는 그대로 쓰면 됨~
	}
	
	// 나한테 다시 넘겨줄 때의 설정
	// 근데 그대로~쓰면 됨 
	private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
//      consumes.add("application/xml;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    // Swagger 윗 부분
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title)
				.description("<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 Board API<br><img src=\"/assets/img/ssafy_logo.png\" width=\"150\">") 
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
				.license("SSAFY License")
				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
				.version("1.0").build();
	}

}
