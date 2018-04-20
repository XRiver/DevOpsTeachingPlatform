package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.FileOperateException;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;

public class RemoteFileCreate extends FileCreateTools{

	@Override
	public boolean createDir(String Path, String DirName, int posixPermissions,Host host)
			throws FileOperateException, RemoteOperateException, IOException {
		// TODO Auto-generated method stub
		
		RemoteSignIn sign = getTheConnection(host);
		Connection con = sign.getConnection();
		
		String Dir = Path + DirName;
		
		try {
			con.connect();
			boolean isAuthed = con.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(!isAuthed) {
				con.close();
				throw new RemoteOperateException("0","认证失败！请检查账户密码是否正确！");
			}
			
			SFTPv3Client sftpClient = new SFTPv3Client(con);
			sftpClient.mkdir(Dir, posixPermissions);
			
			con.close();
			return true;
				
		}catch (IOException e) {
			// TODO: handle exception
			con.close();
			throw new RemoteOperateException(e.getMessage());  //这里还要修改
		}
	}

	@Override
	public boolean createFile(String Path, String FileName, String FileType,Host host)
			throws FileOperateException, RemoteOperateException, IOException {
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
			sftpClient.createFile(file);
			
			con.close();
			return true;
			
		}catch (IOException e) {
			// TODO: handle exception
			con.close();
			throw new RemoteOperateException(e.getMessage());
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
