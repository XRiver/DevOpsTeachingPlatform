package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;

public class RemoteFileDelete extends FileDeleteTools{

	@Override
	public boolean DeleteFile(String Path, String FileName, String FileType,Host host) 
			throws RemoteOperateException, IOException {
		// TODO Auto-generated method stub
		
		RemoteSignIn sign = getTheConnection(host);
		Connection con = sign.getConnection();
		
		String file = Path + FileName;
		if(FileType!=null) {
			file = file + "."+FileType;
		}
		
		try {
			con.connect();
			boolean isAuthed = con.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(!isAuthed) {
				con.close();
				throw new RemoteOperateException("0","认证失败！请检查账户密码是否正确！");
			}
			
			SFTPv3Client sftpClient = new SFTPv3Client(con);
			sftpClient.rm(file);
			con.close();
			return true;
			
		}catch (IOException e) {
			// TODO: handle exception
			con.close();
			throw e;
		}
	}

	@Override
	public boolean DeleteFiles(String[] filePaths,Host host) throws RemoteOperateException, IOException {
		// TODO Auto-generated method stub
		RemoteSignIn sign = getTheConnection(host);
		Connection con = sign.getConnection();
		
		try {
			con.connect();
			boolean isAuthed = con.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(!isAuthed) {
				con.close();
				throw new RemoteOperateException("0","认证失败！请检查账户密码是否正确！");
			}
			
			SFTPv3Client sftpClient = new SFTPv3Client(con);
			
			for(int i = 0 ; i< filePaths.length;i++) {
				sftpClient.rm(filePaths[i]);
			}
			
			con.close();
			
			return true;
		}catch (IOException e) {
			// TODO: handle exception
			con.close();
			throw e;
		}
	}

	@Override
	public boolean DeleteRmptyDir(String Path, String DirName,Host host) throws RemoteOperateException, IOException {
		// TODO Auto-generated method stub
		RemoteSignIn sign = getTheConnection(host);
		Connection con = sign.getConnection();
		
		try {
			con.connect();
			boolean isAuthed = con.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(!isAuthed) {
				con.close();
				throw new RemoteOperateException("0","认证失败！请检查账户密码是否正确！");
			}
			
			SFTPv3Client sftpClient = new SFTPv3Client(con);
			sftpClient.rmdir(Path+DirName);
			
			con.close();
			
			return true;
			
		}catch(IOException e) {
			con.close();
			throw e;
		}
	}
	
	private RemoteSignIn getTheConnection(Host host) {
		//读取配置文件
		String HostIP = host.getIp();
		int HostPort = 22;
		String HostUserName = host.getHostname();
		String HostPassword = host.getPassword();
		//读取配置文件
		
		RemoteSignIn sign = new RemoteSignIn(HostIP, HostPort, HostUserName, HostPassword);
		
		return sign;
	}

}
