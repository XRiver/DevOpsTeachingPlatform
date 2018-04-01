package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.VO.containerVO;
import Devops.docker.DockerBranch.VO.imageVO;
import Devops.docker.DockerBranch.VO.taskSpecificVO;
import Devops.docker.DockerBranch.VO.taskVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskConfig {

    /**
     *
     * @param name 任务名
     * @param description 描述
     * @param hostId 主机的id，并非ip
     * @param projectId 项目id
     * @param username 创建者名，而不是数据库里面的id
     * @param images 镜像们
     * @param containers 容器们
     * @return 任务id
     */
    @RequestMapping("/createTask")
    public String createTask(@RequestParam String name,
                             String description,
                             @RequestParam String hostId,
                             @RequestParam String projectId,
                             @RequestParam String username,
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
     * @param username 创建者名，而不是数据库里面的id
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
                             @RequestParam String username,
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
        List<taskVO> list = new ArrayList<>();
        taskVO vo = new taskVO("123","alala","12:12:12","success","xiong");
        list.add(vo);
        return list;
    }

    /**
     *
     * @param taskId 任务id
     * @return 任务详细信息供阅读编辑
     */
    @RequestMapping("/getTaskSpecific")
    public taskSpecificVO getTaskSpecific(@RequestParam String taskId){
        taskSpecificVO vo = new taskSpecificVO();
        vo.setHostIp("123.123.123.123");
        vo.setName("123");
        vo.setProjectId("123");
        vo.setUserName("xiong");
        vo.setTaskId("123");
        vo.setDescription("123");
        List<imageVO> list = new ArrayList<>();
        List<containerVO> containerVOList = new ArrayList<>();
        imageVO imageVO = new imageVO("123","123","/user/images/","tomcat","lalalal");
        List<String> conlist = new ArrayList<>();
        conlist.add("lalala");
        conlist.add("hahaha");
        containerVO containerVO = new containerVO("123","xiong","123","123",conlist);
        list.add(imageVO);
        containerVOList.add(containerVO);
        vo.setImages(list);
        vo.setContainers(containerVOList);
        return vo;
    }
}
