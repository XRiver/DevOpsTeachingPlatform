package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Service.ContainerService;
import Devops.docker.DockerBranch.VO.containerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContainerGet {

    @Autowired
    ContainerService containerService;

    @RequestMapping("/checkContainerName")
    public int checkContainerName(@RequestParam String hostId,@RequestParam String containerName){

        return containerService.checkContainerName(hostId,containerName);
    }

    @RequestMapping("/getContainersInHost")
    public List<String> getContainersInHost(@RequestParam String hostId){

        return containerService.getContainersInHost(hostId);
    }

    @RequestMapping("/getContainerSpecific")
    public containerVO getContainerSpecific(@RequestParam String hostId,@RequestParam String containerName){

        return containerService.getContainerSpecific(hostId,containerName);
    }
}
