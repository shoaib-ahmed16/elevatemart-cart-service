package com.elevatemartcartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackages = "com.elevatemartcartservice.domain")
public class ElevatemartCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElevatemartCartServiceApplication.class, args);
	}

}
