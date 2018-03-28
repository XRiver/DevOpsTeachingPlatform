package Devops.docker.DockerBranch.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloTest {

	@GetMapping("/hello")
	public String Hello() {
		return "hello";
	}
	
}
