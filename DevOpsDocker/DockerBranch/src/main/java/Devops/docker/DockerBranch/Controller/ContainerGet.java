package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.VO.containerVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContainerGet {

    @RequestMapping("/checkContainerName")
    public int checkContainerName(@RequestParam String hostId,@RequestParam String containerName){

        return 1;
    }

    @RequestMapping("/getContainersInHost")
    public List<String> getContainersInHost(@RequestParam String hostId){

        return null;
    }

    @RequestMapping("/getContainerSpecific")
    public containerVO getContainerSpecific(@RequestParam String hostId,@RequestParam String containerName){

        return null;
    }
}
