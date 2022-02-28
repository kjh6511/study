package com.masjjim.util.security.jwt.filter;

import biz.m24365.commonUtil.security.domain.CustomUser;
import biz.m24365.commonUtil.security.jwt.constants.SecurityConstants;
import biz.m24365.commonUtil.security.jwt.filter.provider.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;

        this.jwtTokenProvider = jwtTokenProvider;

        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    //1. 전달받은 아이디와 비밀번호로 인증을 시도
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	
    	if(!request.getMethod().equals("POST")) {
    		throw new AuthenticationServiceException(
    		"Authentication method not supported: " + request.getMethod());
    		}  
    	String username = request.getParameter("mbrId");
    	String password = request.getParameter("mbrPw");

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    //2. MemberMapper의 readByUserId에서 회원정보를 조회한다
    //3. 인증이 성고적으로 이루어지면 토큰을 생성하여 응답헤더에 추가
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
    	//3-1.CustomUser에서 user정보 조회
        CustomUser user = ((CustomUser) authentication.getPrincipal());
        Integer mbrNo = user.getMbrNo();
        String mbrId = user.getMbrId();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        
        //3-2. jwtTokenProvid에서 토큰 생성  //1000L*60*60*12 12시간
        String token = jwtTokenProvider.createToken(request, mbrNo, mbrId, roles, 1000L*60*60*12);

        //3-3. 헤더에 정보를 넣어 토큰을 보내줌
        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);

    }
}
