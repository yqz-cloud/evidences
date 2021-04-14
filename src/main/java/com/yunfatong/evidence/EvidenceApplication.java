package com.yunfatong.evidence;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yunfatong.evidence.mapper")
public class EvidenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvidenceApplication.class, args);
    }

}

