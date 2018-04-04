package Devops.docker.DockerBranch.FileOperator;


/**
 * 
 * author:杨关
 * 文件操作抽象工厂的接口
 * 
 * */
public interface FileOperateFactory {
	
	public FileReaderTools getReader(String Path,String FileName,String FileType,boolean isRemote);
	
	public FileWriterTools getWriter(boolean isRemote);

}
