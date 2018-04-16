package Devops.docker.DockerBranch.Service;

import Devops.docker.DockerBranch.VO.TaskCreateVO;
import Devops.docker.DockerBranch.VO.taskSpecificVO;
import Devops.docker.DockerBranch.VO.taskVO;
import net.sf.json.JSONArray;

import java.util.List;

public interface TaskSer {

    public String createTask(TaskCreateVO vo);

    public String configTask(TaskCreateVO vo);

    public List<taskVO> getTasks(String projectId);

    public taskSpecificVO getTaskSpecific(String taskId);

    public int startTask(String username,String taskId);

}
