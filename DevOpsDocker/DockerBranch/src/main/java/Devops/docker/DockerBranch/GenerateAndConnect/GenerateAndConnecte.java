package Devops.docker.DockerBranch.GenerateAndConnect;

import java.io.IOException;
import java.util.List;

import Devops.docker.DockerBranch.Controller.SocketServer;
import Devops.docker.DockerBranch.Service.impl.TaskSerImpl;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import Devops.docker.DockerBranch.dao.ContainerLinkDao;
import ch.ethz.ssh2.Connection;
import org.springframework.stereotype.Service;

/**
 * 
 * author:杨关
 * */
@Service
public class GenerateAndConnecte {
	
	@Autowired
    ContainerLinkDao containerLinkDao;

	@Autowired
	GenerateContainersService g;
	
	/**
	 * 
	 * */
	private static final Logger logger = LoggerFactory.getLogger(GenerateAndConnecte.class);
	public String Generate(Container con,Host host,int connectedType,String taskid) {

		JSONObject dvo = null;
		String image = con.getImage();

//		String fileName = con.getFilename();
//		String[] temp = fileName.split(".");
		
		int resultCode = 0;
		
		Connection conn = getConnection(host);
		
		if(image.equals("mysql")) {
			resultCode = g.GenerateMysql(con,host,conn);
			if(resultCode == 1) {
				RemoteExecuteCommand re = new RemoteExecuteCommand();
				StringBuilder c1 = new StringBuilder("sudo docker build -t "+con.getContainerName()+" "+
						con.getPath());
				try {
					String temp = re.ExecCommand(c1, conn).toString();
					logger.info(temp);
					dvo = getDvo(taskid, "3", temp);
					SocketServer.sendMessage(dvo.toString(),taskid);
					StringBuilder run = ContainerLink(connectedType, con,"3306");
					String temp1 = re.ExecCommand(run, conn).toString();
					dvo = getDvo(taskid, "3", "容器id:"+temp1);
					SocketServer.sendMessage(dvo.toString(),taskid);
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					return "创建mysql镜像时，连接断开";
				}
			}else {
				return "远程主机创建mysql失败";
			}
		}else if(image.equals("tomcat")){
			resultCode = g.GenerateTomcat(con,host,conn);
			if(resultCode == 1) {
				RemoteExecuteCommand re = new RemoteExecuteCommand();
				StringBuilder c1 = new StringBuilder("sudo docker build -t "+con.getContainerName()+" "+
						con.getPath());
				try {
					String temp = re.ExecCommand(c1, conn).toString();
					dvo = getDvo(taskid, "3", temp);
					SocketServer.sendMessage(dvo.toString(),taskid);
					StringBuilder run = ContainerLink(connectedType, con,"8080");
					logger.info(run.toString());
					String temp1 = re.ExecCommand(run, conn).toString();
					dvo = getDvo(taskid, "3", "容器id:"+temp1);
					SocketServer.sendMessage(dvo.toString(),taskid);
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					return "创建Tomcat镜像时，连接断开";
				}
				
			}else if(resultCode == -1) {
				return "远程主机创建Dockerfile失败";
			}else{
				return "远程主机写Dockerfile失败";
			}
		}
		
		conn.close();
		
		return "启动容器"+con.getContainerName()+"成功！";
	}
	
	private StringBuilder ContainerLink(int connectedType,Container con,String internalPort) {
		
		StringBuilder link = new StringBuilder();
		
		if(connectedType==0) {
			link.append("sudo docker run -d --net host --name "+con.getContainerName()+
					" "+con.getContainerName());
		}else {
			List<String> linkC = getLinkList(con.getContainerId());
			link.append("sudo docker run -d -p "+con.getPort()+":"+internalPort+" --name "+con.getContainerName()+
					" "); //接下来是--link
			for(String linki:linkC) {
				link.append("--link "+linki+" ");
			}
			link.append(con.getContainerName());
		}
		return link;
	}
	
	
	private List<String> getLinkList(int id){
		return containerLinkDao.getLinkedidByMasterid(id);
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

	private JSONObject getDvo(String taskid, String status , String log) {
		JSONObject temp = new JSONObject();
		temp.put("taskId", taskid);
		temp.put("status", status);
		temp.put("log", log);
		return temp;
	}
}
