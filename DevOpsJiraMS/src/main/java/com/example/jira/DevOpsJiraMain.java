package com.example.jira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DevOpsJiraMain {

	public static void main(String[] args) {
		SpringApplication.run(DevOpsJiraMain.class, args);
	}
}
