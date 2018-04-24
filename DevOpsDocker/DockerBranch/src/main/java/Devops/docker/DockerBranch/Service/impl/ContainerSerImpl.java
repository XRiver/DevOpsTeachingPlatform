package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Service.ContainerService;
import Devops.docker.DockerBranch.VO.containerVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerSerImpl implements ContainerService{
    @Override
    public int checkContainerName(String hostId, String containerName) {

        return 0;
    }

    @Override
    public List<String> getContainersInHost(String hostId) {
        return null;
    }

    @Override
    public containerVO getContainerSpecific(String hostId, String containerName) {
        return null;
    }
}
