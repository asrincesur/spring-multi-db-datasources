package com.std.mdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

public class MdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdbcApplication.class, args);
	}

}
