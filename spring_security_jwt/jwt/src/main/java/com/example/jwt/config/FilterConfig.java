//package com.example.jwt.config;
//
//import com.example.jwt.filter.MyFilter1;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    // Filter는 SecurityFilter가 우선!!!!
//    // SecurityFilter Chain에 등록하면 우리가 직접 생성한 Filter보다 더 먼저 실행 됨
//    @Bean
//    public FilterRegistrationBean<MyFilter1> filter1() {
//        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
//        bean.addUrlPatterns("/*"); // 모~든 요청에서 다 해라
//        bean.setOrder(0); // 낮은 번호가 필터 중에서 가장 먼저 실행 됨
//        return bean;
//    }
//
//}
