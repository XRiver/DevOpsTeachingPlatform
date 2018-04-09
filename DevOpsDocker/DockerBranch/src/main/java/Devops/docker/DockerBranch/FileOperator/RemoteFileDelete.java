package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;

public class RemoteFileDelete extends FileDeleteTools{

	@Override
	public boolean DeleteFile(String Path, String FileName, String FileType) {
		// TODO Auto-generated method stub
		
		RemoteSignIn sign = getTheConnection();
		Connection con = sign.getConnection();
		
		String file = Path + FileName;
		if(FileType!=null) {
			file = file + "."+FileType;
		}
		
		try {
			con.connect();
			boolean isAuthed = con.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(!isAuthed)
				throw new RemoteOperateException("认证失败！请检查账户密码是否正确！");
			
			SFTPv3Client sftpClient = new SFTPv3Client(con);
			sftpClient.rm(file);
			con.close();
			return true;
			
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("************文件:" + file + "不存在!************"); 
			e.printStackTrace();
			
		}finally {
			con.close();
		}
		
		return false;
	}

	@Override
	public boolean DeleteFiles(String[] filePaths) {
		// TODO Auto-generated method stub
		RemoteSignIn sign = getTheConnection();
		Connection con = sign.getConnection();
		
		try {
			con.connect();
			boolean isAuthed = con.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(!isAuthed)
				throw new RemoteOperateException("认证失败！请检查账户密码是否正确！");
			
			SFTPv3Client sftpClient = new SFTPv3Client(con);
			
			for(int i = 0 ; i< filePaths.length;i++) {
				sftpClient.rm(filePaths[i]);
			}
			
			con.close();
			
			return true;
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean DeleteRmptyDir(String Path, String DirName) {
		// TODO Auto-generated method stub
		RemoteSignIn sign = getTheConnection();
		Connection con = sign.getConnection();
		
		try {
			con.connect();
			boolean isAuthed = con.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(!isAuthed)
				throw new RemoteOperateException("认证失败！请检查账户密码是否正确！");
			
			SFTPv3Client sftpClient = new SFTPv3Client(con);
			sftpClient.rmdir(Path+DirName);
			
			con.close();
			
			return true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private RemoteSignIn getTheConnection() {
		//读取配置文件
		String HostIP = "119.29.88.207";
		int HostPort = 22;
		String HostUserName = "ubuntu";
		String HostPassword = "abc8879623";
		//读取配置文件
		
		RemoteSignIn sign = new RemoteSignIn(HostIP, HostPort, HostUserName, HostPassword);
		
		return sign;
	}

}
