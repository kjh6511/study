package com.masjjim.util.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masjjim.util.exception.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//인증 헤더를 실패한 경우를 정의 
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private ObjectMapper objectMappger = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
		res.setContentType("application/json;charset=UTF-8");
		String exception = (String)req.getAttribute("exception");
		ErrorInfo errorInfo = new ErrorInfo();

		if (InsufficientAuthenticationException.class == authException.getClass()) {
			errorInfo.setMessage("Not Logined!");
			errorInfo.setStatus(HttpStatus.UNAUTHORIZED.value());
			errorInfo.setError(HttpStatus.UNAUTHORIZED.name());
			errorInfo.addDetailInfo(authException.getMessage(), authException.toString());
			res.setStatus(HttpStatus.UNAUTHORIZED.value());
	
		}
		else {
			errorInfo.setMessage("Bad Request!");
			errorInfo.setStatus(HttpStatus.BAD_REQUEST.value());
			errorInfo.setError(HttpStatus.BAD_REQUEST.name());
			errorInfo.addDetailInfo(authException.getMessage(), authException.getLocalizedMessage());
			res.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		
		String jsonString = objectMappger.writeValueAsString(errorInfo);
		res.getWriter().write(jsonString);
	}
}
