package Devops.docker.DockerBranch.Service;

import Devops.docker.DockerBranch.VO.containerVO;

import java.util.List;

public interface ContainerService {

    public int checkContainerName(String hostId,String containerName);

    public List<String> getContainersInHost(String hostId);

    public containerVO getContainerSpecific(String hostId,String containerName);
}
