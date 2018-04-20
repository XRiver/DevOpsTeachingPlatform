package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;

public class RemoteFileWriter extends FileWriterTools{

	@Override
	public boolean WriteFile(String Path, String FileName, String FileType, StringBuilder containt,Host host) 
			throws RemoteOperateException, IOException {
		// TODO Auto-generated method stub
		
		//这里的填写配置文件相关的读远程linux文件的IP、Port、Username、Password
		String HostIP = host.getIp();
		int HostPort = 22;
		String HostUserName = host.getHostname();
		String HostPassword = host.getPassword();
		//这里的填写配置文件相关的读远程linux文件的IP、Port、Username、Password
		
		RemoteSignIn sign = new RemoteSignIn(HostIP, HostPort, HostUserName, HostPassword);
		Connection connection = sign.getConnection(); //通过SignIn方法拿到Connection
		
		String file = Path + FileName;
		if(FileType!=null) {
			file = file + "."+FileType;
		}
		
		try {
			connection.connect();
			boolean isAuthed = connection.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			
			if(isAuthed) {
				SFTPv3Client sftpClient = new SFTPv3Client(connection);
				SFTPv3FileHandle sftpHandle = sftpClient.openFileRW(file);
				
				byte[] bs = containt.toString().getBytes();
				int length = bs.length;
				long offset = 0;
				
				sftpClient.write(sftpHandle, offset, bs, 0, length);
				
				connection.close();
				return true;
			}else {
				System.out.println("认证失败");
				connection.close();
				throw new RemoteOperateException("0","认证失败！请检查账户密码是否正确！");
			}
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}

}
