package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.VO.hostVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HostsGet {

    @RequestMapping(value = "/hostGet",method = RequestMethod.GET)
    public List<hostVO> getHost(String projectid){

        return null;
    }
}
