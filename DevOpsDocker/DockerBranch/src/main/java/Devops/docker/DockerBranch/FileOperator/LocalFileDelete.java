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
			throw new FileOperateException("0",file + ":不存在！！");
		
		if(!ffile.isFile())
			throw new FileOperateException("1",file + ":不是一个文件");
		
		if(ffile.delete()) {
			return true;
		}else {
			throw new FileOperateException("2",file + "删除失败，请检查是否有进程占用");
		}
	}

	@Override
	public boolean DeleteFiles(String[] filePaths) throws FileOperateException {
		// TODO Auto-generated method stub
		File file;
		
		for(String filePath:filePaths) {
			file = new File(filePath);
			
			if(!file.exists())
				throw new FileOperateException("0",filePath + ":不存在！！");
			
			if(!file.isFile())
				throw new FileOperateException("1",filePath + ":不是一个文件");
			
			if(file.delete()) {
			}else {
				throw new FileOperateException("2",filePath + "删除失败，请检查是否有进程占用");
			}
			
		}
		
		return true;
	}

	@Override
	public boolean DeleteRmptyDir(String Path, String DirName) throws FileOperateException {
		// TODO Auto-generated method stub
		
		File dirFile = new File(Path+DirName);
		
		if(!dirFile.exists())
			throw new FileOperateException("0",Path+DirName + ":不存在！！");
		
		if(!dirFile.isDirectory())
			throw new FileOperateException("1",Path+DirName + ":不是一个文件夹");
		
		if(dirFile.delete()) {
			return true;
		}else {
			throw new FileOperateException("3",Path+DirName + "删除失败，请检查是否有进程占用");
		}
	}

}
