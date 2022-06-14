package com.javacode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javacode.interceptor.adminAuthenticationInterceptor;
import com.javacode.interceptor.siteAuthenticationInterceptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

//	private adminAuthenticationInterceptor adminAuthenticationInterceptor;
//	private siteAuthenticationInterceptor siteAuthenticationInterceptor;
//
//	@Autowired
//	public AuthenticationInterceptorConfig(adminAuthenticationInterceptor adminAuthenticationInterceptor,
//			siteAuthenticationInterceptor siteAuthenticationInterceptor) {
//		this.adminAuthenticationInterceptor = adminAuthenticationInterceptor;
//		this.siteAuthenticationInterceptor = siteAuthenticationInterceptor;
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/admin/**");
//		registry.addInterceptor(siteAuthenticationInterceptor).addPathPatterns("/site/**");
//	}

}
