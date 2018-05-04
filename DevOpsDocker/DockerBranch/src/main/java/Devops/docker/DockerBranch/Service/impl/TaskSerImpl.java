package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Controller.SocketServer;
import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Containerlink;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Entity.Task;
import Devops.docker.DockerBranch.Service.TaskSer;
import Devops.docker.DockerBranch.Service.tools.DateTool;
import Devops.docker.DockerBranch.VO.TaskCreateVO;
import Devops.docker.DockerBranch.VO.containerVO;
import Devops.docker.DockerBranch.VO.taskSpecificVO;
import Devops.docker.DockerBranch.VO.taskVO;
import Devops.docker.DockerBranch.dao.ContainerDao;
import Devops.docker.DockerBranch.dao.ContainerLinkDao;
import Devops.docker.DockerBranch.dao.HostDao;
import Devops.docker.DockerBranch.dao.TaskDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskSerImpl implements TaskSer{

    @Autowired
    TaskDao taskDao;

    @Autowired
    ContainerDao containerDao;

    @Autowired
    ContainerLinkDao containerLinkDao;

    @Autowired
    HostDao hostDao;

    private static final Logger logger = LoggerFactory.getLogger(TaskSerImpl.class);

    @Override
    public String createTask(TaskCreateVO vo) {

        Task task = new Task();
        task.setName(vo.getName());
        task.setCreator(vo.getUsername());
        task.setDescription(vo.getDescription());
        task.setHostId(Integer.parseInt(vo.getHostId()));
        task.setLastDate(DateTool.getTimeNow());
        task.setStatus("ready");
        task.setProjectId(vo.getProjectId());
        task.setSoftware("待定");//得看jenkins传过来的是啥，另外还得搞清楚是从gitlabci拿还是jenkins拿
        task.setProjectName(vo.getProjectname());
        task.setGroupName(vo.getGroupname());

        List<Task> taskList = taskDao.findAllByProjectId(vo.getProjectId());
        if(taskList.size()!=0){
            return "该项目下已存在任务，无法创建";
        }

        Task result = taskDao.save(task);

        List<containerVO> list = vo.getContainers();
        int size = list.size();
//        System.out.println(size);
        for(int i=0;i<size;i++){
            Container tmp = new Container();
            BeanUtils.copyProperties(list.get(i),tmp);
            tmp.setDate(DateTool.getTodayDate());
            tmp.setTaskId(result.getTaskId());
            tmp.setContainerId(Integer.parseInt(list.get(i).getContainerId()));
            Container container = containerDao.save(tmp);

            List<String> list1 = list.get(i).getContainerList();
            int length = list1.size();
            for(int t=0;t<length;t++){
                Containerlink containerlink = new Containerlink();
                containerlink.setLinkid(0);
                containerlink.setMasterid(container.getContainerId());
                containerlink.setLinkedname(list1.get(t));
                containerLinkDao.save(containerlink);
            }


        }
        return result.getTaskId()+"";
    }

    @Override
    public String configTask(TaskCreateVO vo){
        Task task = taskDao.findById(Integer.parseInt(vo.getTaskId())).get();
        task.setName(vo.getName());
        task.setCreator(vo.getUsername());
        task.setDescription(vo.getDescription());
        task.setHostId(Integer.parseInt(vo.getHostId()));
        task.setLastDate(DateTool.getTimeNow());
        task.setStatus("ready");
        task.setProjectId(vo.getProjectId());
        Task result = taskDao.save(task);

        List<containerVO> list = vo.getContainers();
        int size = list.size();

        for(int i=0;i<size;i++){
            Container tmp = new Container();
            BeanUtils.copyProperties(list.get(i),tmp);
            tmp.setContainerId(Integer.parseInt(list.get(i).getContainerId()));
            tmp.setDate(DateTool.getTodayDate());
            tmp.setTaskId(result.getTaskId());
            Container container = containerDao.save(tmp);
            int containerId = container.getContainerId();
            List<String> linkedlist = containerLinkDao.getLinkedidByMasterid(containerId);

            List<String> list1 = list.get(i).getContainerList();
            int length = list1.size();
            HashMap<String,Integer> map = new HashMap<>();

            for(int k=0;k<length;k++){
                map.put(linkedlist.get(k),0);
            }

            for(int t=0;t<length;t++){
                if(!map.containsKey(list1.get(t))){
                    Containerlink containerlink = new Containerlink();

                    containerlink.setLinkid(0);
                    containerlink.setMasterid(containerId);
                    containerlink.setLinkedname(list1.get(t));
                    containerLinkDao.save(containerlink);
                }else{
                    map.put(list1.get(t),1);
                }
                for(String key:map.keySet()){
                    if(map.get(key)==0){
                        containerLinkDao.deleteByMasteridAndLinkedname(containerId,key);
                    }
                }
            }

        }
        return result.getTaskId()+"";
    }

    @Override
    public List<taskVO> getTasks(String projectId) {
        List<taskVO> result = new ArrayList<>();

        List<Task> list = taskDao.findAllByProjectId(projectId);
        int size = list.size();
        for(int i=0;i<size;i++){
            taskVO vo = new taskVO();
            BeanUtils.copyProperties(list.get(i),vo);
            vo.setTaskId(list.get(i).getTaskId()+"");
            vo.setTaskName(list.get(i).getName());
            result.add(vo);
        }
        return result;
    }

    @Override
    public taskSpecificVO getTaskSpecific(String taskId) {
        Task task = taskDao.findById(Integer.parseInt(taskId)).get();

        taskSpecificVO vo = new taskSpecificVO();
        BeanUtils.copyProperties(task,vo);
        vo.setTaskId(task.getTaskId()+"");
        vo.setHostId(task.getHostId()+"");
        vo.setUserName(task.getCreator());

        Host host = hostDao.findById(task.getHostId()).get();
        vo.setHostIp(host.getIp());

        List<Container> list = containerDao.findContainersByTaskId(Integer.parseInt(taskId));
        List<containerVO> containers = new ArrayList<>();
        int size = list.size();
        for(int i=0;i<size;i++){
            containerVO containerVO = new containerVO();
            BeanUtils.copyProperties(list.get(i),containerVO);
            containerVO.setContainerId(list.get(i).getContainerId()+"");
            int id = list.get(i).getContainerId();
            List<String> linkedlist = containerLinkDao.getLinkedidByMasterid(id);
            containerVO.setContainerList(linkedlist);
            containers.add(containerVO);
        }
        vo.setContainers(containers);
        return vo;
    }

    @Override
    public int startTask(String taskid, String username) {
    	
    	StringBuilder shell = new StringBuilder();
    	
    	Task t = taskDao.findById(Integer.parseInt(taskid)).get();
    	
//    	Host host = 
    	
    	List<Container> ContainersOrder = containerDao.findContainersByTaskId(Integer.parseInt(taskid)); //拿到Container的顺序
    	
    	for(int i = 0 ; i < ContainersOrder.size(); i++) { //按顺序build和run
    		Container tempContainer = ContainersOrder.get(i); //拿到Container  新建一个接口
    	}

        SocketServer.sendMessage("","");
        logger.info("成功启动");
        
        
        return 0;
    }

}
