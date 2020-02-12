package com.nsa.webapp;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import user.UserService;

@SpringBootApplication
public class Usermanagement {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Server server = ServerBuilder.forPort(9090).addService(new UserService()).build();
		server.start();
		
		System.out.println("Server started at:"+server.getPort());
		
		server.awaitTermination();
		SpringApplication.run(Usermanagement.class, args);
	}

}
