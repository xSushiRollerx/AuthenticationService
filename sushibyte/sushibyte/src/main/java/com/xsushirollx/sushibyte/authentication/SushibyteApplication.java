package com.xsushirollx.sushibyte.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SushibyteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SushibyteApplication.class, args);
	}

}
