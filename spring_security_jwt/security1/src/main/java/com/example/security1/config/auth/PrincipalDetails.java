package com.example.security1.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킴
// 로그인 진행이 완료가 되면 session을 만들어줌 => security가 자신만의 session 공간을 가짐
// ============= (Security ContextHolder)에 session 정보 저장시킴
// Security가 가지고 있는 Session에 들어갈 수 있는 오브젝트 정해져있음
// => Authentication 타입 객체
// Authentication 객체 안에 => User정보가 있어야 됨
// Authentication 타입 => User오브젝트의 타입 => "UserDetails"타입 객체

// 1. Security Session 영역에 Session 정보 저장
// 2. Authentication 객체가 저장됨
// 3. Authentication 안의 User 정보: UserDetails
// 4. UserDetails에는 user 객체!
// Security Session => Authentication => UserDetails(==PrincipalDetails)

import com.example.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user; // 콤포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한을 리턴하는 곳!!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료 안 됨!
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 안 잠겼다!
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 비밀번호가 기간 지났니? => 아니용
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 되어있니?
    @Override
    public boolean isEnabled() {

        // 우리 사이트!! 1년동안 회원이 로그인을 안 하면!! 휴면 계정으로 하기로 함.
        // User class => private Timestamp loginDate; => 이거로 봄 ㅇㅇ 지나면 false return
        // 현재 시간 - 로그인 시간 => 1년을 초과하면 return false

        return true;
    }
}
