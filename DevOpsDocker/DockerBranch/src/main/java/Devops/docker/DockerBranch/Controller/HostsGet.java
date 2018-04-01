package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.VO.hostVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HostsGet {

    @RequestMapping(value = "/getHosts",method = RequestMethod.GET)
    public List<hostVO> getHost(@RequestParam String projectid){
        List<hostVO> test = new ArrayList<>();
        hostVO vo = new hostVO(projectid,projectid,projectid,projectid,projectid,projectid);
        test.add(vo);
        return test;
    }
}
