package com.shopforhome.userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableFeignClients("com.order.feignclients")

@ComponentScan({"com.shopforhome.controller", "com.shopforhome.service", "com.shopforhome.userService.util","com.shopforhome.userService.configuration"})
@EntityScan("com.shopforhome.entity")
@EnableJpaRepositories("com.shopforhome.repo")
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
