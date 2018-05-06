package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Entity.Task;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import Devops.docker.DockerBranch.Service.ContainerService;
import Devops.docker.DockerBranch.VO.containerVO;
import Devops.docker.DockerBranch.dao.ContainerDao;
import Devops.docker.DockerBranch.dao.ContainerLinkDao;
import Devops.docker.DockerBranch.dao.HostDao;
import Devops.docker.DockerBranch.dao.TaskDao;
import ch.ethz.ssh2.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContainerSerImpl implements ContainerService{

    @Autowired
    HostDao hostDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    ContainerDao containerDao;

    @Autowired
    ContainerLinkDao containerLinkDao;

    private static final Logger logger = LoggerFactory.getLogger(ContainerSerImpl.class);
    @Override
    public int checkContainerName(String hostId, String containerName) {
        Host host = hostDao.findById(Integer.parseInt(hostId)).get();
        Connection connection = null;
        RemoteSignIn remoteSignIn = new RemoteSignIn(host.getIp(),Integer.parseInt(host.getSshPort()),host.getRoot(),host.getPassword());
        try{
            connection= remoteSignIn.ConnectAndAuth(host.getRoot(),host.getPassword());
            RemoteExecuteCommand remoteExecuteCommand = new RemoteExecuteCommand();
            StringBuilder version = null;

            try {
                version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo docker ps -a"), connection);
            }catch(IOException e){
                return 3;
            }
            connection.close();
            if(version.length()==0){
                return 0;
            }
            String[] infos = version.toString().split("\n");

            logger.info(version.toString());
            logger.info(infos[1]);
            if(infos.length==1){
                return 1;
            }else{
                for(int i=1;i<infos.length;i++){
                    String container = infos[i];
                    String[] split = container.split("\\s+");
                    int length = split.length;

                    if(split[length-1].equals(containerName)){
                        return 0;
                    }
                }
                return 1;
            }


        }catch (IOException e){
            logger.info("连接失败");
            return 0;
        }catch(RemoteOperateException e){
            if(e.getErrorCode().equals("0")){
                logger.info("登录失败");
            }
        }
        return 0;
    }

    @Override
    public List<String> getContainersInHost(String hostId) {
        List<String> result = new ArrayList<>();
        Host host = hostDao.findById(Integer.parseInt(hostId)).get();
        Connection connection = null;
        RemoteSignIn remoteSignIn = new RemoteSignIn(host.getIp(),Integer.parseInt(host.getSshPort()),host.getRoot(),host.getPassword());
        try{
            connection= remoteSignIn.ConnectAndAuth(host.getRoot(),host.getPassword());
            RemoteExecuteCommand remoteExecuteCommand = new RemoteExecuteCommand();
            StringBuilder version = null;

            try {
                version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo docker ps"), connection);
            }catch(IOException e){
                result.add(new String("连接失败"));
                return result;
            }
            if(version.length()==0){
                result.add(new String("docker未安装"));
                return result;
            }
            connection.close();
            String[] infos = version.toString().split("\n");

            logger.info(infos[0]);
            if(infos.length==1){
                return result;
            }else{
                for(int i=1;i<infos.length;i++){
                    String container = infos[i];
                    String[] split = container.split("\\s+");
                    int length = split.length;
                    result.add(split[length-1]);
                }
                return result;
            }


        }catch (IOException e){
            result.add(new String("连接失败"));
            return result;
        }catch(RemoteOperateException e){
            if(e.getErrorCode().equals("0")){
                result.add(new String("登录失败"));
                return result;
            }
        }
        result.add(new String("未知错误"));
        return result;
    }

    @Override
    public containerVO getContainerSpecific(String hostId, String containerName) {

        containerVO result = new containerVO();

        List<Task> tasks = taskDao.findTasksByHostId(Integer.parseInt(hostId));

        int size = tasks.size();
        for(int i=0;i<size;i++){
            Task task = tasks.get(i);
            int taskid = task.getTaskId();
            List<Container> list = containerDao.findContainersByTaskId(taskid);
            int length = list.size();
            for(int j=0;j<length;j++){
                if(list.get(j).getContainerName().equals(containerName)){
                    Container container = list.get(j);
                    BeanUtils.copyProperties(container,result);
                    result.setContainerId(list.get(i).getContainerId()+"");
                    int id = list.get(i).getContainerId();
                    List<String> linkedlist = containerLinkDao.getLinkedidByMasterid(id);
                    result.setContainerList(linkedlist);
                    return result;
                }
            }

        }
        Host host = hostDao.findById(Integer.parseInt(hostId)).get();

        Connection connection = null;
        RemoteSignIn remoteSignIn = new RemoteSignIn(host.getIp(),Integer.parseInt(host.getSshPort()),host.getRoot(),host.getPassword());
        try {
            connection = remoteSignIn.ConnectAndAuth(host.getRoot(), host.getPassword());
            RemoteExecuteCommand remoteExecuteCommand = new RemoteExecuteCommand();
            StringBuilder version = null;

            try {
                version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo docker ps"), connection);
            } catch (IOException e) {

                return result;
            }
            connection.close();
            String[] infos = version.toString().split("\n");

            logger.info(infos[0]);
            if(infos.length==1){
                return result;
            }else{
                for(int i=1;i<infos.length;i++){
                    String container = infos[i];
                    String[] split = container.split("\\s+");
                    int length = split.length;
                    if(split[length-1].equals(containerName)){
                        result.setContainerName(containerName);
                        result.setContainerId("");
                        result.setContainerList(null);
                        result.setPath("");
                        result.setImage(split[1]);
                        result.setFilename("");
                        result.setCreator("");
                        if(split.length<=10){
                            result.setPort("");
                        }else{
                            result.setPort(split[length-2]);
                        }

                    }
                }
                return result;
            }


        }catch (IOException e){
            return result;
        }catch(RemoteOperateException e){
            if(e.getErrorCode().equals("0")){
                return result;
            }
        }



        return result;
    }
}
