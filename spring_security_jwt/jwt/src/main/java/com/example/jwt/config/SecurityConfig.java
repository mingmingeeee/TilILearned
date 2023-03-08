package com.example.jwt.config;

import com.example.jwt.config.jwt.JwtAuthenticationFilter;
import com.example.jwt.config.jwt.JwtAuthorizationFilter;
import com.example.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 필터 걸기
        // SecurityFilter만 등록이 되기 때문에... 이거의 타입이 Filter이기 때문에
        // SpringFilter Chain의 Filter들 중~~~의 이전이나 이후에 걸어 !!!
//        http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class);

//        http.addFilterBefore(new MyFilter1(), SecurityContextPersistenceFilter.class);

        http.csrf().disable();
        // session 사용하지 않겠다는 뜻 - stateless server로 만들겠다는 뜻
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter) // 모든 요청은 이 filter를 탐
                .formLogin().disable() // formLogin ㄴㄴ
                .httpBasic().disable()

                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                 // 쿠키 정책: 동일 도메인에서 요청이 올 때 발급함
                // header에 "Authorization"에 인증 정보를 넣고 가는 방식 (ID, PW) => Token을 넣는 방식으로! => 노출이 된다 해도 ID, PW가 아니기 때문에 위험 부담이 적음 == Bearer 방식
                // Basic 방식 = ID, PW 들고 가는 방식
                // == "http Basic" => 이것도 안 쓸 거임! => Bearer 방식이다!
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}

// 모든 Controller에 CrossOrigin 걸면...
// 인증이 필요한 요청에는 해당이 안 됨
// 인증 필요 없는 요청에만 해당 됨
// @CrossOrigin(인증이 없을 때)
// 시큐리티 필터 등록 => 인증 있을 때 !!!