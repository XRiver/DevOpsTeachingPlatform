package Devops.docker.DockerBranch.FileOperator;

import Devops.docker.DockerBranch.Exception.FileOperateException;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;

/**
 * 
 * author:杨关
 * 文件删除父类
 * */
public abstract class FileDeleteTools {
	
	/**
	 * 删除单个文件
	 * @param Path 文件的路径
	 * @param FileName 文件的名字
	 * @param FileType 文件的类型（后缀）
	 * @return boolean -> true删除成功，false删除失败
	 * */
	public abstract boolean DeleteFile(String Path,String FileName,String FileType) throws FileOperateException,RemoteOperateException;
	
	/**
	 * 删除多个文件
	 * @param filePaths 多个文件的路径，包括路径+名字+后缀
	 * @return boolean -> true删除成功，false删除失败
	 * */
	public abstract boolean DeleteFiles(String[] filePaths) throws FileOperateException,RemoteOperateException;
	
	/**
	 * 删除空的文件夹
	 * @param Path 文件夹路径
	 * @param DirName 文件夹名字
	 * @return boolean -> true删除成功，false删除失败
	 * */
	public abstract boolean DeleteRmptyDir(String Path,String DirName) throws FileOperateException,RemoteOperateException;

}
