package Devops.docker.DockerBranch.GenerateAndConnect;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import Devops.docker.DockerBranch.dao.ContainerLinkDao;
import ch.ethz.ssh2.Connection;

/**
 * 
 * author:杨关
 * */
public class GenerateAndConnecte {
	
	@Autowired
    ContainerLinkDao containerLinkDao;
	
	/**
	 * 
	 * */
	public String Generate(Container con,Host host,int connectedType) {
		
		GenerateContainersService g = new GenerateContainerImpl();
		
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
					re.ExecCommand(c1, conn);
					StringBuilder run = ContainerLink(connectedType, con,"3306");
					re.ExecCommand(run, conn);
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
					re.ExecCommand(c1, conn);
					StringBuilder run = ContainerLink(connectedType, con,"8070");
					re.ExecCommand(run, conn);
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					return "创建Tomacat镜像时，连接断开";
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
			link.append("sudo docker run -p "+con.getPort()+":"+internalPort+" --name "+con.getContainerName()+
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
	
}
