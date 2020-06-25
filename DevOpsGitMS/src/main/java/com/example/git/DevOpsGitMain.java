package com.example.git;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DevOpsGitMain {

	public static void main(String[] args) {
		SpringApplication.run(DevOpsGitMain.class, args);
	}
}
