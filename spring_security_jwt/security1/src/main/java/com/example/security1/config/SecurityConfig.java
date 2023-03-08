package com.example.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 활성화 => 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화
// @Secured("ROLE_ADMIN") // 특정 메서드에 간단하게 붙일 때!
// prePostEnabled: @PreAuthorize("hasRole('ROLE_MANAGER'))" => 메서드가 실행되기 직전에 실행됨!
// 스프링 시큐리티 필터: SecurityConfig (내가 등록하는게 여기 기본 필터 체인에 등록됨)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // password 암호화 -> Bean으로 등록
    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해줌
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    // => SecurityConfig 파일 생성 후 login/logout 작동 안 함
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 비활성화 => 위조 요청 비활성화
        // csrf 공격 활성화 필요할시 적용해주는 거 따로 만들어야 함

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 이 주소로 들어오면 auth.. 인증이 필요해
                // => 인증만 되면 들어갈 수 있는 주소!!!
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                // authenticated: 인증 필요
                // access: 인증 뿐만 아니라 권한 필요 => 로그인을 했지만 권한이 있어야 들어갈 수 있음
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll() // 설정하지 않은 다른 주소들을 다 가능
                // 권한이 없는 페이지로 들어갈 때는 login페이지로 이동!
                .and()
                .formLogin()
                .loginPage("/loginForm")
 //               .usernameParameter("username2") =>  loadUserByUsername username 파라미터 이름 바꾸고 싶을 때 사용
                .loginProcessingUrl("/login") /// /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행해줌
                .defaultSuccessUrl("/"); // 로그인 되면 /로 가게 함
        // => controller에 login 만들지 않아도 됨

        // 어떤 특정 페이지로 요청해서 로그인하면 그 페이지로 보내줄게 ㅇㅇ
    }
}
