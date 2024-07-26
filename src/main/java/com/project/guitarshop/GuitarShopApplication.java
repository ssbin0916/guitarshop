package com.project.guitarshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GuitarShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuitarShopApplication.class, args);
	}

}
