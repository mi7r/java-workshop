package com.workshop.java.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.workshop.java.reader")
@EnableJpaRepositories(basePackages = "com.workshop.java.reader.repository")
@EntityScan(basePackages = "com.workshop.java.reader")
public class ReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReaderApplication.class, args);
    }

}
