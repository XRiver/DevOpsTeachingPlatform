package Devops.docker.DockerBranch.Service;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.VO.deployHistoryVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Test {
    public void save(Container image);

    public deployHistoryVO getHistoryVO();
}
