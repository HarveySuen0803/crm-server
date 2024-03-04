package com.harvey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class CrmServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmServerApplication.class, args);
    }
}
