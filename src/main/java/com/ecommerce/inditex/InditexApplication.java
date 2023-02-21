package com.ecommerce.inditex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Inditex application.
 */
@SpringBootApplication
@EnableJpaRepositories
public class InditexApplication {

    /**
     * Run application.
     * @param args arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(InditexApplication.class, args);
    }

}
