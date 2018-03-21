package edu.nju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DevopsGitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsGitApplication.class, args);
	}
}
