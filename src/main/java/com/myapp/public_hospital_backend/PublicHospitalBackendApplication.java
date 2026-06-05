package com.myapp.public_hospital_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PublicHospitalBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(PublicHospitalBackendApplication.class, args);
    }
}