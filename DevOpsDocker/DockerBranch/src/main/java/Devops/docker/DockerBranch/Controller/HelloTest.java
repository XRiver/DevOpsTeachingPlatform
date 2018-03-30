package Devops.docker.DockerBranch.Controller;

import java.util.concurrent.atomic.AtomicLong;

import Devops.docker.DockerBranch.VO.helloVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Devops.docker.DockerBranch.RemoteConnection.FileTransport;

@RestController
public class HelloTest {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public helloVO Hello(@RequestParam(value="name", defaultValue="World") String name) {
//		FileTransport F = new FileTransport("start", "sh", "/root/DockerProject/", "/home/ubuntu");
//		System.out.println(F.putFile());
		return new helloVO(counter.incrementAndGet(),
                String.format(template, name));
	}
	
}
