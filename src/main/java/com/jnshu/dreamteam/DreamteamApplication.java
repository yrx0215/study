package com.jnshu.dreamteam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jnshu.dreamteam.mapper")
@SpringBootApplication
public class DreamteamApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamteamApplication.class, args);
    }

}
