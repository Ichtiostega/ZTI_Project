package com.zti.bountyHunter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
* Main class of Spring Boot application
*/
@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
public class BountyHunterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BountyHunterApplication.class, args);
	}

}
