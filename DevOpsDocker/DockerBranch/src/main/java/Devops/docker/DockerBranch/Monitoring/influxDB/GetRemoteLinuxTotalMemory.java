package Devops.docker.DockerBranch.Monitoring.influxDB;

import java.io.IOException;

import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;

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
		
		result = exe.ExecCommand(command, HostIp, UserName, Password, port);
		
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

}
