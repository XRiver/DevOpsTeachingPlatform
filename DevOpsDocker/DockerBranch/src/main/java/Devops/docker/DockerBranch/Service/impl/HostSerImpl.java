package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Controller.SocketServer;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.FileTransport;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import Devops.docker.DockerBranch.Service.HostService;
import Devops.docker.DockerBranch.Service.tools.DateTool;
import Devops.docker.DockerBranch.VO.hostVO;
import Devops.docker.DockerBranch.dao.HistoryDao;
import Devops.docker.DockerBranch.dao.HostDao;
import ch.ethz.ssh2.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HostSerImpl implements HostService{

    @Autowired
    HostDao dao;

    private static final Logger logger = LoggerFactory.getLogger(HostSerImpl.class);
    @Override
    public List<hostVO> getHost(String projectid) {
        List<hostVO> result = new ArrayList<>();

        int id = Integer.parseInt(projectid);
        List<Host> list = dao.findAllByProjectid(id);
        int size = list.size();
        for(int i=0;i<size;i++){
            hostVO vo = new hostVO();
            Host host = list.get(i);
            BeanUtils.copyProperties(host,vo);
            result.add(vo);
        }
        return result;
    }

    /**
     * 检验主机是否可用，若可用便添加
     * @param host
     * @return
     */
    @Override
    public int addHost(Host host) {
        int result = testHost(host);
        if(result == 4){
            dao.save(host);
        }
        return result;
    }

    @Override
    public void deleteHost(String hostid) {
        Integer id = Integer.parseInt(hostid);
        dao.deleteById(id);
    }

    @Override
    public int configHost(Host host) {
        Integer id = host.getHostId();
        Host oldHost = dao.findById(id).get();
        host.setIp(oldHost.getIp());
        host.setOpsSystem(oldHost.getOpsSystem());

        host.setDate(DateTool.getTimeNow());
        int result = testHost(host);
        if(result == 4){
            dao.save(host);
        }
        return result;
    }

    @Override
    public int testHost(String hostid) {
        Integer id = Integer.parseInt(hostid);
        Host host = dao.findById(id).get();
        return this.testHost(host);
    }

    public int testHost(Host host){
        Connection connection = null;
        RemoteSignIn remoteSignIn = new RemoteSignIn(host.getIp(),Integer.parseInt(host.getSshPort()),host.getRoot(),host.getPassword());
        try{
            connection= remoteSignIn.ConnectAndAuth(host.getRoot(),host.getPassword());
        }catch (IOException e){
            logger.info("fuck1");
            connection.close();
            return 1;
        }catch(RemoteOperateException e){
            if(e.getErrorCode().equals("0")){
                connection.close();
                return 2;
            }

        }
        if(host.getAuto_installed().equals("true")){
            RemoteExecuteCommand remoteExecuteCommand = new RemoteExecuteCommand();
            StringBuilder version = null;

            try {
                version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo docker version"), connection);
            }catch(IOException e){
                connection.close();
                return 3;
            }
            if(version.length()!=0){
                logger.info("Docker have been installed!");
                connection.close();
                return 4;
            }else{
                //每次执行远程操作，connection都要重新创建，一个connnection仅维持一次远程操作

                try {
                    version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo mkdir /home/admin/docker"), connection);
                }catch(IOException e){
                    connection.close();
                    return 3;
                }


                String str = System.getProperty("user.dir");

                String localPath = str+"\\DevOpsDocker\\DockerBranch\\src\\main\\resources\\shell\\";
                FileTransport fileTransport = null;
                if(host.getOpsSystem().equals("centos")){
                    fileTransport = new FileTransport("docker_centos","sh",localPath,"/home/admin/docker",connection);
                    try{
                        fileTransport.putFile();

                    }catch(IOException e){
                        connection.close();
                        return 3;
                    }

//                    try{
//                        connection= remoteSignIn.ConnectAndAuth(host.getRoot(),host.getPassword());
//                    }catch (IOException e){
//                        logger.info("fuck5");
//                        return 1;
//                    }catch(RemoteOperateException e){
//                        if(e.getErrorCode().equals("0"))
//                            return 2;
//                    }
//
//                    try {
//                        version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo chmod 777 /home/admin/docker/docker_centos.sh"), connection);
//                        logger.info(version.toString()+"012");
//                    }catch(IOException e){
//                        return 3;
//                    }


                    try {
                        version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo bash /home/admin/docker/docker_centos.sh"), connection);
                    }catch(IOException e){
                        logger.info(version.toString()+"012");
                        connection.close();
                        return 3;
                    }


                }else{
                    fileTransport = new FileTransport("docker_ubuntu","sh",localPath,"/home/admin/docker",connection);

                    try{
                        fileTransport.putFile();

                    }catch(IOException e){
                        connection.close();
                        return 3;
                    }

//                    try{
//                        connection= remoteSignIn.ConnectAndAuth(host.getRoot(),host.getPassword());
//                    }catch (IOException e){
//                        logger.info("fuck5");
//                        return 1;
//                    }catch(RemoteOperateException e){
//                        if(e.getErrorCode().equals("0"))
//                            return 2;
//                    }
//
//                    try {
//                        version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo chmod 777 /home/admin/docker/testDocker.sh"), connection);
//                        logger.info(version.toString()+"012");
//                    }catch(IOException e){
//                        return 3;
//                    }




                    try {
                        version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo bash /home/admin/docker/docker_ubuntu.sh"), connection);
                        logger.info(version.toString()+"123");
                    }catch(IOException e){
                        connection.close();
                        return 3;
                    }

//                    try{
//                        connection= remoteSignIn.ConnectAndAuth(host.getRoot(),host.getPassword());
//                    }catch (IOException e){
//                        logger.info("fuck4");
//                        return 1;
//                    }catch(RemoteOperateException e){
//                        if(e.getErrorCode().equals("0"))
//                            return 2;
//                    }
//
//                    try {
//                        version = remoteExecuteCommand.ExecCommand(new StringBuilder("sudo apt-get install docker-ce"), connection);
//                        logger.info(version.toString()+"123");
//                    }catch(IOException e){
//                        return 3;
//                    }
                }
            }
        }
        connection.close();
        return 4;
    }
}
