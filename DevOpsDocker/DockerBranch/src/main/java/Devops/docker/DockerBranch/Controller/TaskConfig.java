package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Service.TaskSer;
import Devops.docker.DockerBranch.VO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskConfig {

    @Autowired
    TaskSer taskSer;

    /**
     *
     * @param task 任务
     * @return 任务id
     */
    @RequestMapping(value = "/createTask", method = RequestMethod.POST)
    public String createTask(@RequestBody TaskCreateVO task){

//        return task.getContainers().get(0).getContainerName()+"321";
//        JSONArray array = JSONArray.fromObject(containers);
        return taskSer.createTask(task);
    }

    /**
     *
     * @return 任务id
     */
    @RequestMapping(value = "/configTask",method = RequestMethod.POST)
    public String configTask(@RequestBody TaskCreateVO task){

        return taskSer.configTask(task);
    }

    /**
     *
     * @param projectId 项目编号
     * @return 任务简要说明列表
     */
    @RequestMapping("/getTasks")
    public List<taskVO> getTasks(@RequestParam String projectId){
//        List<taskVO> list = new ArrayList<>();
//        taskVO vo = new taskVO("123","alala","12:12:12","success","xiong");
//        list.add(vo);
        return taskSer.getTasks(projectId);
    }

    /**
     *
     * @param taskId 任务id
     * @return 任务详细信息供阅读编辑
     */
    @RequestMapping("/getTaskSpecific")
    public taskSpecificVO getTaskSpecific(@RequestParam String taskId){
//        taskSpecificVO vo = new taskSpecificVO();
//        vo.setHostIp("123.123.123.123");
//        vo.setName("123");
//        vo.setProjectId("123");
//        vo.setUserName("xiong");
//        vo.setTaskId("123");
//        vo.setDescription("123");
//        List<imageVO> list = new ArrayList<>();
//        List<containerVO> containerVOList = new ArrayList<>();
//        imageVO imageVO = new imageVO("123","123","/user/images/","tomcat","lalalal");
//        List<String> conlist = new ArrayList<>();
//        conlist.add("lalala");
//        conlist.add("hahaha");
//        containerVO containerVO = new containerVO("123","xiong","xiong","123","123","3306","2014/12/01",conlist);
//        list.add(imageVO);
//        containerVOList.add(containerVO);
//        vo.setContainers(containerVOList);
        return taskSer.getTaskSpecific(taskId);
    }

    /**
     *
     * @param username 启动任务的当前用户的用户名
     * @param taskId 任务id
     * @return 返回1表示已经启动，暂时还不知道会有啥不良状况，启动后去调用getdeploystatus
     */
    @RequestMapping(value = "/startTask",method = RequestMethod.GET)
    public int startTask(@RequestParam String username,@RequestParam String taskId){
        SocketServer.sendMessage("成功连接",taskId);
        return 1;
    }


}
