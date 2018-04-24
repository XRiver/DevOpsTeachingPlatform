package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;

public class RemoteFileWriter extends FileWriterTools{

	@Override
	public boolean WriteFile(String Path, String FileName, String FileType, StringBuilder containt,
			Connection connection) throws  IOException {
		// TODO Auto-generated method stub
		
		String file = Path + FileName;
		if(FileType!=null) {
			file = file + "."+FileType;
		}
		
		try {
			
			SFTPv3Client sftpClient = new SFTPv3Client(connection);
			SFTPv3FileHandle sftpHandle = sftpClient.openFileRW(file);
			
			byte[] bs = containt.toString().getBytes();
			int length = bs.length;
			long offset = 0;
			
			sftpClient.write(sftpHandle, offset, bs, 0, length);
			
			connection.close();
			return true;
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}

}
