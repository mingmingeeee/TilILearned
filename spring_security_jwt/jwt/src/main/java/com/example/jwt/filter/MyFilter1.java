//package com.example.jwt.filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class MyFilter1 implements Filter {
//
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//
//        // 토큰: 코스 라고 가정하고 이 토큰이 넘어오면 인증 되게 하고 (Filter 계속 타서)
//        // 아니면 Filter 못 타고 Controller조차 못 가게 하게 하자 ~!
//
//        // id, pw 정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어주고 그걸 응답을 해준다.
//        // 요청할 때마다 header에 Authorization에 value값으로 토큰을 가지고 오겠징?
//        // 그 때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증만 하면 됨 (RSA or HS256)
//        if(req.getMethod().equals("POST")) {
//            String headerAuth = req.getHeader("Authorization");
//            System.out.println(headerAuth);
//
//            if(headerAuth.equals("cos")) {
//                chain.doFilter(req, res);
//            } else {
//                PrintWriter out = res.getWriter();
//                out.println("인증 안 됨");
//            }
//        }
//
//        System.out.println("필터1");
////        chain.doFilter(req, res); // 필터 체인에 등록 => 필터를 거치고 계속 프로세스를 진행하기 위해
//        // 안 걸어주면 그냥 여기서 끝나버림
//    }
//}
