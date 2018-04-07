package Devops.docker.DockerBranch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
public class DockerBranchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerBranchApplication.class, args);
	}

}
