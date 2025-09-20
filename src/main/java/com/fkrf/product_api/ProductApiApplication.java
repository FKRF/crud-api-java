package com.fkrf.product_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProductApiApplication {

	public static void main(String[] args) {

        String rawPassword = "123456";
        String encoded = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println(encoded);
        SpringApplication.run(ProductApiApplication.class, args);
	}
}
