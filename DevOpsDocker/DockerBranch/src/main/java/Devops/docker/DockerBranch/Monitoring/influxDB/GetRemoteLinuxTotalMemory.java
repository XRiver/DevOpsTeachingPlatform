package Devops.docker.DockerBranch.Monitoring.influxDB;

import java.io.IOException;

import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import ch.ethz.ssh2.Connection;

/**
 * 
 * author:杨关
 * 作用:获得指定远程主机上的总内存
 * 
 * */
public class GetRemoteLinuxTotalMemory {
	
	private String UserName;
	
	private String Password;
	
	private String HostIp;
	
	private int port;

	public GetRemoteLinuxTotalMemory(String userName, String password, String hostIp, int port) {
		super();
		UserName = userName;
		Password = password;
		HostIp = hostIp;
		this.port = port;
	}
	
	/**
	 * 返回远程linux主机总内存的方法
	 * @return String 总内存
	 * 
	 * */
	public String GetMemory() throws RemoteOperateException, IOException {
		
		StringBuilder command = new StringBuilder("head /proc/meminfo");
		StringBuilder result = new StringBuilder();
		
		RemoteExecuteCommand exe = new RemoteExecuteCommand();
		
		//这里添加Connection  还要考虑放这里是否合适
		RemoteSignIn sign = new RemoteSignIn(HostIp, 22, UserName, Password);
		Connection connection = null;
		try {
			connection = sign.ConnectAndAuth(sign.getUSER(), sign.getPASSWORD());
		} catch (RemoteOperateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//这里添加Connection  还要考虑放这里是否合适
		
		result = exe.ExecCommand(command,connection);
		
		String[] a = result.toString().split("\\\r\\\n");
		
		String[] b = a[0].split(" ");
		
		String TotalMemory = "";
		
		for(int i = 0 ; i< b.length;i++) {
			if(isNumber(b[i])) {
				TotalMemory = b[i];
				break;
			}
		}
		
		return TotalMemory;
	}
	
	private boolean isNumber(String in) {
		if(in.equals("")) {
			return false;
		}else {
			for(int i = 0 ; i< in.length();i++) {
				if(!(in.charAt(i)<='9'&&in.charAt(i)>='0')) {
					return false;
				}
			}
		}
		return true;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getHostIp() {
		return HostIp;
	}

	public void setHostIp(String hostIp) {
		HostIp = hostIp;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	

}
