package com.itwh.a0520erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.itwh.a0520erp.mapper"})
public class A0520erpApplication {

    public static void main(String[] args) {
        SpringApplication.run(A0520erpApplication.class, args);
    }

}
