package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Entity.Task;
import Devops.docker.DockerBranch.Service.TaskSer;
import Devops.docker.DockerBranch.VO.*;
import Devops.docker.DockerBranch.dao.TaskDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskConfig {

    @Autowired
    TaskSer taskSer;

    @Autowired
    TaskDao taskDao;

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
     * @param projectid 项目id，绑定taskid，一一对应
     * @return 返回1表示已经启动，暂时还不知道会有啥不良状况，启动后去调用getdeploystatus
     */
    @RequestMapping(value = "/startTask",method = RequestMethod.GET)
    public String startTask(@RequestParam String projectid){
        Task task = taskDao.findAllByProjectId(projectid).get(0);
        taskSer.cleanTask(task.getTaskId()+"");
        int result = taskSer.startTask(task.getTaskId()+""," ");
        if(result==0){
            return "Failed!";
        }else if(result==1){
            return "Failed to link the remote end!";
        }else if(result==2){
            return "Failed to generate related script!";
        }else if(result==3){
            return "Failed when deploying in the remote!";
        }else if(result==4){
            return "Deployed successfully!";
        }else{
            return "Unknown errors";
        }
    }



}
