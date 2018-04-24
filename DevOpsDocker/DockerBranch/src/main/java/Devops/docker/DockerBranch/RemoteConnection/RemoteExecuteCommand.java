package Devops.docker.DockerBranch.RemoteConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 
 * author:杨关
 * 作用：远程执行linux命令并且获得执行结果的类
 * 
 * */
public class RemoteExecuteCommand {
	
	
	/**
	 *远程执行linux命令并获得执行结果的方法 
	 * @param StringBuilder Command 需要执行的命令
	 * @param Connection connection 连接类
	 * @return StringBuilder 返回的结果以StringBuilder返回
	 * 
	 * */
	public StringBuilder ExecCommand(StringBuilder Command,Connection connection) throws IOException {

//		int pport = 0;
//		if(port == -1) {
//			pport = 22;
//		}else {
//			pport = port;
//		}
		
		StringBuilder returnResult = new StringBuilder();
//		
//		RemoteSignIn sign = new RemoteSignIn(Ip, pport, UserName, password);
//		Connection connection = sign.ConnectAndAuth(UserName, password);
		
		try {
				
			//打开一个session,执行linux命令
			Session sess = connection.openSession();
			
			sess.execCommand(Command.toString());
			
			//接受目标服务器上的控制台返回结果，输出结果
			InputStream stdout = new StreamGobbler(sess.getStdout());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
			
			String line = "";
			while((line = reader.readLine()) != null) {
				returnResult.append(line+"\r\n");
			}
				
			//不一定所有的服务器都会返回状态码 所以暂时去掉
//			int ExitCode = sess.getExitStatus();
//			
//			if(ExitCode==0) {
//				returnResult.append("Execute Failed!!");
//			}else {
//				returnResult.append("Execute Success!!");
//			}
			//不一定所有的服务器都会返回状态码 所以暂时去掉
					
			//关闭session
			sess.close();
			//关闭connection
			connection.close();
			
			return returnResult;
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}

}
