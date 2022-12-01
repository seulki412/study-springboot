package com.koreait.mylogin.loginweb.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koreait.mylogin.loginweb.interceptor.LogInterceptor;
import com.koreait.mylogin.loginweb.interceptor.LoginCheckInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/error");
		
		registry.addInterceptor(new LoginCheckInterceptor())
				.order(2)
				.addPathPatterns("/**")
				.excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**");
	}

	//파일로 따로 데이터 입력할때 메서드 필요
//	@Bean
	public FilterRegistrationBean<Filter> logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		
		filterRegistrationBean.setFilter(new LogFilter());	// LogFilter등록
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*"); //모든 url 다 적용 -> 모든 요청에 다 필터 적용
		return filterRegistrationBean;
	}
	
//	@Bean
	public FilterRegistrationBean<Filter> loginCheckFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		
		filterRegistrationBean.setFilter(new LoginCheckFilter());	// LoginCheckFilter등록
		filterRegistrationBean.setOrder(2);	// 먼저 등록할 필터 순서
		filterRegistrationBean.addUrlPatterns("/*"); //모든 url 다 적용 -> 모든 요청에 다 필터 적용
		return filterRegistrationBean;
	}
	
	
}
