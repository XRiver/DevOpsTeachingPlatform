package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.VO.containerVO;
import Devops.docker.DockerBranch.VO.imageVO;
import Devops.docker.DockerBranch.VO.taskSpecificVO;
import Devops.docker.DockerBranch.VO.taskVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskConfig {

    /**
     *
     * @param name 任务名
     * @param description 描述
     * @param hostId 主机的id，并非ip
     * @param projectId 项目id
     * @param userId 用户id
     * @param images 镜像们
     * @param containers 容器们
     * @return 任务id
     */
    @RequestMapping("/createTask")
    public String createTask(@RequestParam String name,
                             String description,
                             @RequestParam String hostId,
                             @RequestParam String projectId,
                             @RequestParam String userId,
                             @RequestParam List<imageVO> images,
                             @RequestParam List<containerVO> containers
                             ){
        return null;
    }

    /**
     *
     * @param taskId 任务id
     * @param name 任务名
     * @param description 描述
     * @param hostId 主机的id，并非ip
     * @param projectId 项目id
     * @param userId 用户id
     * @param images 镜像们
     * @param containers 容器们
     * @return 任务id
     */
    @RequestMapping("/configTask")
    public String configTask(@RequestParam String taskId,
                             @RequestParam String name,
                             String description,
                             @RequestParam String hostId,
                             @RequestParam String projectId,
                             @RequestParam String userId,
                             @RequestParam List<imageVO> images,
                             @RequestParam List<containerVO> containers
                             ){

        return null;
    }

    /**
     *
     * @param projectId 项目编号
     * @return 任务简要说明列表
     */
    @RequestMapping("/getTasks")
    public List<taskVO> getTasks(@RequestParam String projectId){
        return null;
    }

    /**
     *
     * @param taskId 任务id
     * @return 任务详细信息供阅读编辑
     */
    @RequestMapping("/getTaskSpecific")
    public taskSpecificVO getTaskSpecific(@RequestParam String taskId){
        return null;
    }
}
