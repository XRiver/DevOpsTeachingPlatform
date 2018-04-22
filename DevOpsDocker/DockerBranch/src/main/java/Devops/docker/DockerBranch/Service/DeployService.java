package Devops.docker.DockerBranch.Service;

import Devops.docker.DockerBranch.VO.deployHistoryVO;
import Devops.docker.DockerBranch.VO.deployInfoVO;
import Devops.docker.DockerBranch.VO.deployStatusVO;

import java.util.List;

public interface DeployService {

    public List<deployHistoryVO> getHistory(String taskid);

    public deployInfoVO getInfo(String taskid);

//    public deployStatusVO getStatus(String taskid);
}
