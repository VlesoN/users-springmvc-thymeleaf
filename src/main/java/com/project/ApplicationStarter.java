package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.project")
public class ApplicationStarter extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
