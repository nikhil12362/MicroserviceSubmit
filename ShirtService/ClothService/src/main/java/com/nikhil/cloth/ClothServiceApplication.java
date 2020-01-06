package com.nikhil.cloth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class ClothServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothServiceApplication.class, args);
	}

}
