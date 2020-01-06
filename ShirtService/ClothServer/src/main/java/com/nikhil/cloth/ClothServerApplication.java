package com.nikhil.cloth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ClothServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothServerApplication.class, args);
	}

}
