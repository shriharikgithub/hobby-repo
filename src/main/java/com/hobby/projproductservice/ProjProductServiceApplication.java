package com.hobby.projproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjProductServiceApplication.class, args);
    }

}
