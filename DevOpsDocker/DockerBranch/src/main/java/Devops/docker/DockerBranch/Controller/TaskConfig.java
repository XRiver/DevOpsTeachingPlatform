package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Entity.Task;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.FileTransport;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import Devops.docker.DockerBranch.Service.TaskSer;
import Devops.docker.DockerBranch.VO.*;
import Devops.docker.DockerBranch.dao.ContainerDao;
import Devops.docker.DockerBranch.dao.HostDao;
import Devops.docker.DockerBranch.dao.TaskDao;
import ch.ethz.ssh2.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskConfig {

    @Autowired
    TaskSer taskSer;

    @Autowired
    TaskDao taskDao;

    @Autowired
    HostDao hostDao;

    @Autowired
    ContainerDao containerDao;

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
    public String startTask(@RequestParam String projectid,@RequestParam String branchname){
        List<Task> tasks = taskDao.findAllByProjectId(projectid);

        int tasksize = tasks.size();

        Task task = null;
        for(int i=0;i<tasksize;i++){
            Task one = tasks.get(i);
            if(one.getBranchName().equals(branchname)){
                task = one;
            }
        }
        if(task==null){
            return "该分支对应容器尚未创建";
        }
        int hostid = task.getHostId();
        //获得指定主机
        Host host = hostDao.findById(hostid).get();
        //获得容器
        int taskid = task.getTaskId();
        List<Container> list = containerDao.findContainersByTaskId(taskid);
        Container container = null;
        //获得文件
        String localPath = "/home/xujianghe/projects/"+task.getGroupName()+"/"+task.getProjectName()+"/"+branchname+"/target/";
        File file = new File(localPath);

        File[] fileList = file.listFiles();
        List<File> wjList = new ArrayList<File>();//新建一个文件集合
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {//判断是否为文件
                wjList.add(fileList[i]);
            }
        }
        if(wjList.size()==0){
            return "目标文件夹下没有文件";
        }
        //获取文件名
        File one = null;
        for(int i=0;i<wjList.size();i++){
            File the = wjList.get(i);
            String fileName = the.getName();
            String[] array = fileName.split(".");
            int size1 = array.length;

            String fileType = "";
            if(size1==2){
                fileType = array[size1-1];
            }
            if(fileType=="war"||fileType=="jar"){
                one = the;
            }
        }
        if(one==null){
            return "目标文件夹下找不到文件";
        }
        String fileName = one.getName();
        String[] array = fileName.split(".");
        int size1 = array.length;

        String fileType = "";
        if(size1>=1){
            fileType = array[size1-1];
        }

        //确认对应容器
        int size = list.size();
        for(int i=0;i<size;i++){
            if(list.get(i).getFilename().equals(branchname)||list.get(i).getFilename().equals(fileName)){
                container = list.get(i);
            }
        }
        if(container==null){
            return "未找到文件部署位置";
        }
        //上传文件到指定位置
        Connection connection = null;

        RemoteSignIn remoteSignIn = new RemoteSignIn(host.getIp(),Integer.parseInt(host.getSshPort()),host.getRoot(),host.getPassword());
        try{
            connection= remoteSignIn.ConnectAndAuth(host.getRoot(),host.getPassword());
        }catch (IOException e){
            connection.close();
            return "连接失败";
        }catch(RemoteOperateException e){
            if(e.getErrorCode().equals("0")){
                connection.close();
                return "登录失败";
            }

        }
        FileTransport fileTransport = new FileTransport(fileName,fileType,localPath,container.getPath(),connection);
        try {
            fileTransport.putFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return "上传失败";
        }
        connection.close();

        container.setFilename(fileName);
        containerDao.save(container);

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
