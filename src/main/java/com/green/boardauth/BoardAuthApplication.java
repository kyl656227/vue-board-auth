package com.green.boardauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan //얘가 있어야 ConfiguratironPorperties 에노태이션 쓸수있음
public class BoardAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardAuthApplication.class, args);
    }

}
