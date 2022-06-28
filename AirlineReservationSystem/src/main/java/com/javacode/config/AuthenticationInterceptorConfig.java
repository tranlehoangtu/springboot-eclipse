package com.javacode.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javacode.interceptor.adminAuthenticationInterceptor;
import com.javacode.interceptor.siteAuthenticationInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration @RequiredArgsConstructor
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

	private final adminAuthenticationInterceptor adminAuthenticationInterceptor;
	private final siteAuthenticationInterceptor siteAuthenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/admin/**");
		registry.addInterceptor(siteAuthenticationInterceptor).addPathPatterns("/site/**");
	}

}
