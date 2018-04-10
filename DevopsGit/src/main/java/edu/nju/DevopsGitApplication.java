package edu.nju;

import edu.nju.config.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class DevopsGitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsGitApplication.class, args);
	}
}
