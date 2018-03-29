package Devops.docker.DockerBranch.RemoteConnection;

import java.io.IOException;

import ch.ethz.ssh2.Connection;

/**
 * 
 * author:杨关
 * 该类的作用：用java通过SSH登录到远程服务器
 * 输入：主机IP、端口号、用户名、密码
 * 输出：一个connection类，需要调用connect方法连接并且 调用 isAuth来验证用户密码是否正确才能进行下一步操作
 * 
 * */
public class RemoteSignIn {
	
	private String HostIP = ""; //登录的主机地址
	private int PORT = 22 ; //监听的端口号
	private String USER = "ROOT"; //登录用的用户名
	private String PASSWORD = ""; //登录用的密码
	private Connection connection; //java的ssh连接类
	

	public RemoteSignIn(String HostIP,int PORT,String USER,String PASSWORD) {
		this.HostIP = HostIP;
		if(PORT != -1) {
			this.PORT = PORT;
		}
		this.USER = USER;
		this.PASSWORD = PASSWORD;
		this.connection = new Connection(this.HostIP, this.PORT);
	}
	
	
	
	public boolean isAuth(String user,String password) {
		try {
			return connection.authenticateWithPassword(user, password);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Connection getConnection() {
		return connection;
	}



	public String getHostIP() {
		return HostIP;
	}



	public void setHostIP(String hostIP) {
		HostIP = hostIP;
	}



	public int getPORT() {
		return PORT;
	}



	public void setPORT(int pORT) {
		PORT = pORT;
	}



	public String getUSER() {
		return USER;
	}



	public void setUSER(String uSER) {
		USER = uSER;
	}



	public String getPASSWORD() {
		return PASSWORD;
	}



	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
	
	
	
}
