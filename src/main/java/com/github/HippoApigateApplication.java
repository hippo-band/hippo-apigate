package com.github;

import com.github.hippo.framework.WebAPISecurityFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;

@SpringBootApplication
@MapperScan("com.github.hippo.mapper")
public class HippoApigateApplication {

  public static void main(String[] args) {
    SpringApplication.run(HippoApigateApplication.class, args);
  }

}
