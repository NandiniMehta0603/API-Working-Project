package com.finance.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TransactionsApplication {
	public static void main(String[] args) {

		SpringApplication.run(TransactionsApplication.class, args);
	}

}
