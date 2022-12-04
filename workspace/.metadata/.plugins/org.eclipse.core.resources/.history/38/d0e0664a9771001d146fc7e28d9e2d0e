package com.koreait.mylogin.loginweb.filter;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.PatternMatchUtils;

import com.koreait.mylogin.loginweb.session.SessionConst;

public class LoginCheckFilter implements Filter{

	private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"}; 
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String requestURI = httpRequest.getRequestURI();
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		System.out.println("인증 체크 필터 시작");
		
		if(isLoginCheckPath(requestURI)) {
			System.out.println("인증 체크 로직 실행 : " + requestURI);
			HttpSession session = httpRequest.getSession(false);
			if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
				System.out.println("미 인증 사용자 요청");
				httpResponse.sendRedirect("/login?redirectURL=" + requestURI);  // 가려고했던 redirect
				return; // 미인증 사용자는 다음으로 진행하지 않고 끝낸다.
			}
			
		}
		
		// 다음 단계로 넘어간다.
		chain.doFilter(request, response);
	}
	/*
	 * 화이트 리스트의 경우 인증 체크 x	
	 * simpleMatch 	: 파라미터 문자열이 특정 패턴에 매칭되는지를 검사함.
	 */
	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
		
	}
	
	
}
