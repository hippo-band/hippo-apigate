package com.github;

import com.github.hippo.framework.WebAPISecurityFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;

@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.github.hippo.mapper")
public class HippoApigateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HippoApigateApplication.class, args);
	}


//	/**
//	 * 配置过滤器
//	 * @return
//	 */
//	@Bean
//	public FilterRegistrationBean someFilterRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(webAPISecurityFilter());
//		registration.addUrlPatterns("/*");
//		registration.addInitParameter("paramName", "paramValue");
//		registration.setName("webAPISecurityFilter");
//		return registration;
//	}
//
//	/**
//	 * 创建一个bean
//	 * @return
//	 */
//	@Bean(name = "webAPISecurityFilter")
//	public Filter webAPISecurityFilter() {
//		return new WebAPISecurityFilter();
//	}
}
