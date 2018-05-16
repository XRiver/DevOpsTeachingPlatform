package com.interstellar.devopsjenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude  = {DataSourceAutoConfiguration.class})
public class DevopsjenkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsjenkinsApplication.class, args);
	}

}
