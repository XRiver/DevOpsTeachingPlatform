package Devops.docker.DockerBranch.FileOperator;


/**
 * 
 * author:杨关
 * 文件操作抽象工厂的接口
 * 
 * */
public interface FileOperateFactory {
	
	/**
	 * 返回文件读取工具
	 * @param Path 文件路径
	 * @param FileName 文件名字
	 * @param FileType 文件类型
	 * @param isRemote 是否远程操作
	 * @return 根据isRemote参数返回相应的FileReaderTools
	 * */
	public FileReaderTools getReader(String Path,String FileName,String FileType,boolean isRemote);
	
	
	/**
	 * 返回文件写工具
	 * @param isRemote 是否远程操作
	 * @return 根据isRemote参数返回相应的FileReaderTools
	 * */
	public FileWriterTools getWriter(boolean isRemote);
	
	/**
	 * 返回文件删除工具
	 * @param isRemote 是否远程操作
	 * @return 根据isRemote参数返回相应的FileReaderTools
	 * */
	public FileDeleteTools getDelete(boolean isRemote);

}
