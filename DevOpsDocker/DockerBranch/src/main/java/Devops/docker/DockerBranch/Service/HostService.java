package Devops.docker.DockerBranch.Service;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.VO.hostVO;

import java.util.List;

public interface HostService {

    public List<hostVO> getHost(String projectid);

    public int addHost(Host host);

    public void deleteHost(String hostid);

    public int configHost(Host host);

}
