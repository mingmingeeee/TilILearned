package com.ssafy.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.ssafy.interceptor.ConfirmInterceptor;

// annotation을 붙이기 위한 (설정을 위한 클래스)

// 1. component-scan
// 2. autoproxy
// 3.... 설정 다시 고 
@Configuration
@EnableAspectJAutoProxy // autoproxy
@MapperScan(basePackages = { "com.ssafy.**.mapper" }) // mapper scan!!!!!!! 어떤 패키지던간에 mapper라는 패키지에 있는 모든 것들 처리해라!
// .*. : 패키지 하나 
// .**. : 패키지 여러개 (depth 여러개 가능)
public class WebMvcConfiguration implements WebMvcConfigurer {

	private final List<String> patterns = Arrays.asList("/board/*", "/admin", "/user/list");

	// 주입 => @Component 등록
	@Autowired
	private ConfirmInterceptor confirmInterceptor;

	private final String uploadFilePath;

	// @Value: EL처럼.. ${}~~ => properties파일에서 읽어올 수 있음
	// file.path.upload-files: 아까 설정한 폴더가 되는 거임!!!
	public WebMvcConfiguration(@Value("${file.path.upload-files}") String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	// implements WebMvcConfigurer:
	// web에서 할 수 있는 설정들을 method로 제공해줌 
	// => 각각에 맞는 method를 override해주면 됨
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
		// registry.mapping 추가 => /**: 모든 걸, allowedOrigins: "*"모든~ 도메인 뒤에 뭐라고 하든지간에 허용하겠다~~~
		// 밑에는 특정!!! 도메인^^ http://localhost:8080, http://localhost:8081만 쓸 수 있게 된다~
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
		// 밑에는 특정!! method^^
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
//				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
				.maxAge(1800);
	}

	// Interceptor 설정 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
		// 설정에 대한 registry에다가 comfirmInterceptor를 적용할건데, pathpatternsr가 patterns인 것 (board의 모든 것~!에 적용할 것이다.)
		// private final List<String> patterns = Arrays.asList("/board/*", "/admin", "/user/list");
	}

	// resources-mapping
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/file/**").addResourceLocations("file:///" + uploadFilePath + "/")
				.setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
		// ResourceHandler: /upload/file/** =>(연결이 됨)=> "file:///" + uploadFilePath + "/"
		// file:/// :::: protocol
		
		// addResourceHandler: 리소스와 연결될 URL path
		// addResourceLocations: 실제 리소스가 존재하는 외부 경로를 지정
	}

}
