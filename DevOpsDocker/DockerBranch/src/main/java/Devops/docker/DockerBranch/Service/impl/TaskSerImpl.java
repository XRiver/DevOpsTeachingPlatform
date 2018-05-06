package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Controller.SocketServer;
import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Containerlink;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Entity.Task;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.GenerateAndConnect.GenerateAndConnecte;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import Devops.docker.DockerBranch.Service.TaskSer;
import Devops.docker.DockerBranch.Service.tools.DateTool;
import Devops.docker.DockerBranch.VO.TaskCreateVO;
import Devops.docker.DockerBranch.VO.containerVO;
import Devops.docker.DockerBranch.VO.taskSpecificVO;
import Devops.docker.DockerBranch.VO.taskVO;
import Devops.docker.DockerBranch.dao.*;
import ch.ethz.ssh2.Connection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Autowired
    HistoryDao historyDao;

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
        task.setLinkmethod(vo.getLinkmethod());

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
        this.cleanTask(vo.getTaskId());
        Task task = taskDao.findById(Integer.parseInt(vo.getTaskId())).get();
        task.setName(vo.getName());
        task.setCreator(vo.getUsername());
        task.setDescription(vo.getDescription());
        task.setHostId(Integer.parseInt(vo.getHostId()));
        task.setLastDate(DateTool.getTimeNow());
        task.setStatus("ready");
        task.setProjectId(vo.getProjectId());
        task.setLinkmethod(vo.getLinkmethod());
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
        vo.setLinkmethod(task.getLinkmethod());
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
    	
//    	StringBuilder shell = new StringBuilder();
    	
    	Task t = taskDao.findById(Integer.parseInt(taskid)).get();
    	
    	Host host = hostDao.findById(t.getHostId()).get();  //拿到host
    	
    	List<Container> ContainersOrder = containerDao.findContainersByTaskId(Integer.parseInt(taskid)); //拿到Container的顺序
    	GenerateAndConnecte ge = new GenerateAndConnecte();
    	
    	for(int i = 0 ; i < ContainersOrder.size(); i++) { //按顺序build和run
    		Container tempContainer = ContainersOrder.get(i); //拿到Container  新建一个接口
    		int linkmethod = t.getLinkmethod();
    		String tempResult = ge.Generate(tempContainer, host, linkmethod);
    		SocketServer.sendMessage(tempResult,taskid);
    		logger.info(tempContainer.getContainerName() + "成功启动");
    	}
    	
		RemoteExecuteCommand re = new RemoteExecuteCommand();
		StringBuilder c1 = new StringBuilder("sudo docker run -d -p 8083:8083 -p 8086:8086 --expose 8090 --expose 8099 --name"
				+ " influxsrv -e PRE_CREATE_DB=cadvisor tutum/influxdb");
		
		Connection monitoring = getConnection(host);
		
		try {
			re.ExecCommand(c1, monitoring);
			StringBuilder run = new StringBuilder("docker run --volume=/:/rootfs:ro --volume=/var/run:/var/run:rw --volume=/sys:/sys:ro --volume=/var/lib/docker/:/var/lib/docker:ro "
					+ "--publish=8088:8088 --detach=true --link influxsrv:influxsrv --name=cadvisor google/cadvisor"
					+ " -storage_driver=influxdb -storage_driver_db=cadvisor -storage_driver_host=influxsrv:8086");
			re.ExecCommand(run, monitoring);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "创建Tomacat镜像时，连接断开";
		}
		monitoring.close();
		
        SocketServer.sendMessage("Docker监控安装成功",taskid);
        logger.info("成功启动");
        
//        historyDao.save(history);
        
        
        return 0;
    }

    @Override
    public void cleanTask(String taskid) {

        Task task = taskDao.findById(Integer.parseInt(taskid)).get();
        int hostid = task.getHostId();
        Host host = hostDao.findById(hostid).get();
        List<Container> list = containerDao.findContainersByTaskId(Integer.parseInt(taskid));

        Connection connection = getConnection(host);
        RemoteExecuteCommand re = new RemoteExecuteCommand();
        int length = list.size();

        for(int i=0;i<length;i++){
            Container container = list.get(i);
            String containername = container.getContainerName();
            StringBuilder stop = new StringBuilder("sudo docker stop "+containername);
            StringBuilder rm = new StringBuilder("sudo docker rm "+containername);
            StringBuilder rmi = new StringBuilder("sudo docker rmi "+containername);
            try {
                re.ExecCommand(stop,connection);
                re.ExecCommand(rm,connection);
                re.ExecCommand(rmi,connection);
            } catch (IOException e) {
                logger.info("what the fuck");
                e.printStackTrace();
            }
        }
        connection.close();
    }

    private Connection getConnection(Host host) {
		RemoteSignIn sign = new RemoteSignIn(host.getIp(), Integer.parseInt(host.getSshPort()), host.getRoot(), host.getPassword());
		Connection connection = null;
		try {
			connection = sign.ConnectAndAuth(sign.getUSER(), sign.getPASSWORD());
		} catch (RemoteOperateException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			return null;
		}
		return connection;
	}

}
