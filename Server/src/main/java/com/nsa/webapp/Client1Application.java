package com.nsa.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@EntityScan(basePackages={"com.nsa"})
@EnableJpaRepositories("com.nsa.webapp")
public class Client1Application {

	public static void main(String[] args) {
		SimpleConsumer kafkaconsumer = new SimpleConsumer();
		kafkaconsumer.consumeMessage("LoginKafkaMsg");
		SpringApplication.run(Client1Application.class, args);
	}

}
