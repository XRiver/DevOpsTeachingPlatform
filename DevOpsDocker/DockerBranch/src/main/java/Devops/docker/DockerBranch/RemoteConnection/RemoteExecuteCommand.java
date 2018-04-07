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
	
	
	public StringBuilder ExecCommand(StringBuilder Command) {
		//获得主机ip等文件的代码
		//获得主机ip等文件的代码
		StringBuilder returnResult = new StringBuilder();
		
		RemoteSignIn sign = new RemoteSignIn("119.29.88.207", 22, "ubuntu", "abc8879623");
		Connection connection = sign.getConnection(); //通过SignIn方法拿到Connection
		
		try {
			connection.connect();
			boolean isAuthed = connection.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(isAuthed) {
				System.out.println("认证成功");
				
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
//				int ExitCode = sess.getExitStatus();
//				
//				if(ExitCode==0) {
//					returnResult.append("Execute Failed!!");
//				}else {
//					returnResult.append("Execute Success!!");
//				}
				//不一定所有的服务器都会返回状态码 所以暂时去掉
				
				//关闭session
				sess.close();
				//关闭connection
				connection.close();
				
				return returnResult;
			}else {
				System.out.println("认证失败");
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

}
