package com.example.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.config.auth.PrincipalDetails;
import com.example.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

// Spring Security에서 UsernamePasswordAuthenticationFilter가 있음
// /login 요청해서 username, password 전송하면 (post로)
// UsernmaePasswordAuthenticationFilter가 동작함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

   private final AuthenticationManager authenticationManager;

    // /login요청을 하면, 로그인 시도를 위해 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");
        // username과 password를 받아서 정상인지 로그인 시도를 해본다
        try {
//            BufferedReader br = request.getReader();
//
//            String input = null;
//            while((input = br.readLine()) != null){
//                System.out.println(input);

            // json 데이터 파싱하기
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);

            // 토큰 만들기
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            // PrincipalDetailsService의 loadUserByUsername()함수가 실행됨. 정상이면 authentication이 리턴됨 (username의 정보만 가져가고, password정보는 스프링이 알아서 처리해줌)
            // db에 있는 username과 password가 일치한다는 뜻!!
            // authenticationManager에 토큰을 넣어 던지고, 인증을 받는다. authentication에 내가 로그인한 정보가 담긴다!
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);


            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료, 유저이름: " + principalDetails.getUser().getUsername());

            // authentication객체를 session영역에 저장한다(권한 관리를 시큐리티가 해주기 위해서 -> 편하게 하려고)
            return authentication;  // 세션에 저장된다

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    // 2. 정상인지 로그인 시도 => authenticationManager로 로그인 시도 하면 PrincipalDetailsService가 호출 됨
    // => loadUserByusername이 자동으로 실행 됨


    // 3. PrincipalDetails를 세션에 담고
    // => 담는 이유: 안 담으면 권한 관리가 안 됨 => 세션에 있어야 권한 관리를 해줌

    //// 여기까진 위에서 함

    // 4. JWT 토큰을 만들어서 응답해주면 됨



    // attemptAuthentication 실행 후 인증이 정상적으로 완료되면 successfulAuthentication 메서드가 실행된다.
    // 해당 메서드에서 jwt 토큰을 만들어서 요청한 사용자에게 jwt 토큰으로 응답해주면 된다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // RSA 가 아닌 Hash 암호 방식
        // withClaim: key + value
        String jwtToken = JWT.create()
                .withSubject("cos token")//토큰 이름
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 10)))//10분간 토큰 유효
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos")); // 서버만 알고 있는 암호화를 위한 값

        response.addHeader("Authorization","Bearer "+jwtToken);//Bearer 다음에 한 칸 띄워야 함.

    }
}

// 1. username + password 로그인 완료
// 2. 서버가 JWT 토큰 생성하여
//      클라이언트로 JWT 토큰 응답
// 3. 요청할 때마다 JWT 토큰을 가지고 요청
//