package Devops.docker.DockerBranch.FileOperator;

import java.io.File;
import java.io.IOException;

import Devops.docker.DockerBranch.Exception.FileOperateException;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;



public class LocalFileCreate extends FileCreateTools{

	@Override
	public boolean createDir(String Path, String DirName, int posixPermissions)
			throws FileOperateException, RemoteOperateException, IOException {
		// TODO Auto-generated method stub
		String DirS = Path + DirName;
		
		File Dir = new File(DirS);
		
		if(Dir.exists())
			throw new FileOperateException("3","创建" + DirS + "失败，目标文件已经存在！");
		
		if(!DirS.endsWith(File.separator))
			DirS = DirS + File.separator;
		
		if(Dir.mkdir()) {
			return true;
		}else {
			throw new FileOperateException("7","创建" + DirS + "失败");
		}
	}

	@Override
	public boolean createFile(String Path, String FileName, String FileType)
			throws FileOperateException, RemoteOperateException, IOException {
		// TODO Auto-generated method stub
		
		String fileS = Path + FileName;
		if(FileType!="") {
			fileS = fileS + "." + FileType;
		}
		
		File file = new File(fileS);
		
		if(file.exists())
			throw new FileOperateException("3","创建" + fileS + "失败，目标文件已经存在！");
		
		if(fileS.endsWith(File.separator))
			throw new FileOperateException("4","创建" + fileS + "失败，目标文件不能为目录！");
		
		if(!file.getParentFile().exists())
			throw new FileOperateException("5","创建" + fileS + "失败，目标文件所处目录不存在！");
		
		try {
			if(file.createNewFile()) {
				return true;
			}else {
				throw new FileOperateException("6","创建" + fileS + "失败");
			}

		}catch (IOException e) {
			// TODO: handle exception
			throw new FileOperateException("7",e.getMessage());
		}
	}

}
