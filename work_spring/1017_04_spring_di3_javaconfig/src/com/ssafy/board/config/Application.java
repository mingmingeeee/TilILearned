package com.ssafy.board.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration  // 스프링 설정파일 이라고 명시
@ComponentScan(basePackages = {"com.ssafy"})
public class Application {

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource source = new SimpleDriverDataSource();
		source.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		source.setUrl("jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8");
		source.setUsername("ssafy");
		source.setPassword("ssafy");
		
		return source;
	}
}
