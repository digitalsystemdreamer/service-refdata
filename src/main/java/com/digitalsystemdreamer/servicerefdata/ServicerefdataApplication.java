package com.digitalsystemdreamer.servicerefdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableEnversRepositories
@EnableKafka
public class ServicerefdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicerefdataApplication.class, args);
    }

}
