package com.yhr.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yhr.eduservice.mapper")
@ComponentScan(basePackages = "com.yhr")
public class EduApplication {

    public static void main(String[] args) {

        SpringApplication.run(EduApplication.class, args);

    }
}
