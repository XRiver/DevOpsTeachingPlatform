package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.VO.basicImageVO;
import Devops.docker.DockerBranch.VO.containerVO;
import Devops.docker.DockerBranch.VO.imageVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ImageGet {

    @RequestMapping("/getBasicImages")
    public List<basicImageVO> getBasicImages(){
        basicImageVO basicImage = new basicImageVO(1,"tomcat","123");
        List<basicImageVO> list = new ArrayList<>();
        list.add(basicImage);
        return list;
    }
}
