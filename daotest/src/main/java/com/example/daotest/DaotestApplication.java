package com.example.daotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DaotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaotestApplication.class, args);
    }

}
