package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Service.ImageService;
import Devops.docker.DockerBranch.VO.basicImageVO;
import Devops.docker.DockerBranch.VO.containerVO;
import Devops.docker.DockerBranch.VO.imageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ImageGet {

    @Autowired
    ImageService imageService;

    @RequestMapping("/getBasicImages")
    public List<basicImageVO> getBasicImages(){

        return imageService.getBasicImages();
    }
}
