package com.jetherrodrigues.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ReactivePokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivePokedexApplication.class, args);
	}
}
