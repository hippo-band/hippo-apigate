package com.github;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.hippo.mapper")
public class HippoApigateApplication {

  public static void main(String[] args) {
    SpringApplication.run(HippoApigateApplication.class, args);
  }

}
