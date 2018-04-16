package Devops.docker.DockerBranch.Service;

import Devops.docker.DockerBranch.VO.basicImageVO;

import java.util.List;

public interface ImageService {

    public List<basicImageVO> getBasicImages();

}
