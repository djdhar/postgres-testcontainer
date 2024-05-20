package com.example.postgrestestcontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PostgresTestcontainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgresTestcontainerApplication.class, args);
	}

}
