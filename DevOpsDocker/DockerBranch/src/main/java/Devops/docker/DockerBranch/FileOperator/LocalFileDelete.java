package Devops.docker.DockerBranch.FileOperator;

import java.io.File;

import Devops.docker.DockerBranch.Exception.FileOperateException;
public class LocalFileDelete extends FileDeleteTools{

	@Override
	public boolean DeleteFile(String Path, String FileName, String FileType) throws FileOperateException {
		// TODO Auto-generated method stub
		
		String file = Path + FileName;
		if(FileType!=null) {
			file = file + "."+FileType;
		}
		
		File ffile = new File(file);
		
		if(!ffile.exists())
			throw new FileOperateException(file + ":不存在！！");
		
		if(!ffile.isFile())
			throw new FileOperateException(file + ":不是文件");
		
		if(ffile.delete()) {
			return true;
		}else {
			throw new FileOperateException(file + "删除失败，请检查是否有进程占用");
		}
	}

	@Override
	public boolean DeleteFiles(String[] filePaths) throws FileOperateException {
		// TODO Auto-generated method stub
		File file;
		
		for(String filePath:filePaths) {
			file = new File(filePath);
			
			if(!file.exists())
				throw new FileOperateException(filePath + ":不存在！！");
			
			if(!file.isFile())
				throw new FileOperateException(filePath + ":不是文件");
			
			if(file.delete()) {
			}else {
				throw new FileOperateException(filePath + "删除失败，请检查是否有进程占用");
			}
			
		}
		
		return true;
	}

	@Override
	public boolean DeleteRmptyDir(String Path, String DirName) throws FileOperateException {
		// TODO Auto-generated method stub
		
		File dirFile = new File(Path+DirName);
		
		if(!dirFile.exists())
			throw new FileOperateException(Path+DirName + ":不存在！！");
		
		if(!dirFile.isDirectory())
			throw new FileOperateException(Path+DirName + ":不是文件夹");
		
		if(dirFile.delete()) {
			return true;
		}else {
			throw new FileOperateException(Path+DirName + "删除失败，请检查是否有进程占用");
		}
	}

}
