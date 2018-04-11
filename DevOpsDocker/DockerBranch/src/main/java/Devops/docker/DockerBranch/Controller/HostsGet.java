package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Service.HostService;
import Devops.docker.DockerBranch.VO.hostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HostsGet {

    @Autowired
    HostService hostService;

    @RequestMapping(value = "/getHosts",method = RequestMethod.GET)
    public List<hostVO> getHost(@RequestParam String projectid){

        return hostService.getHost(projectid);
    }
}
