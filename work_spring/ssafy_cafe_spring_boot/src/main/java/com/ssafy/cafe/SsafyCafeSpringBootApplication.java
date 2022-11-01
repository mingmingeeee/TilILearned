package com.ssafy.cafe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.ssafy"})
@MapperScan(basePackages = {"com.ssafy.*.model.mapper"})
public class SsafyCafeSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsafyCafeSpringBootApplication.class, args);
	}

}
