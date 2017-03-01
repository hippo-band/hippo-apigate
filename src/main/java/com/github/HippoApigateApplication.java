package com.github;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.github.hippo.mapper")
public class HippoApigateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HippoApigateApplication.class, args);
	}
}
