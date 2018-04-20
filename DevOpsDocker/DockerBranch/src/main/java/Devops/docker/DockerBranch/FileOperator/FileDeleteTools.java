package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Entity.Host;
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
	 * 
	 * @exception FileOperateException 本地文件操作时抛出的错误，errorcode=0:文件不存在,errorcode=1:不是一个文件,errorcode=2:不能操作，被占用,errorcode=3:不是一个目录
	 * */
	public abstract boolean DeleteFile(String Path,String FileName,String FileType,Host host) 
			throws FileOperateException,RemoteOperateException,IOException;
	
	/**
	 * 删除多个文件
	 * @param filePaths 多个文件的路径，包括路径+名字+后缀
	 * @return boolean -> true删除成功，false删除失败
	 * 
	 * @exception FileOperateException 本地文件操作时抛出的错误，errorcode=0:文件不存在,errorcode=1:不是一个文件,errorcode=2:不能操作，被占用,errorcode=3:不是一个目录
	 * */
	public abstract boolean DeleteFiles(String[] filePaths,Host host) 
			throws FileOperateException,RemoteOperateException,IOException;
	
	/**
	 * 删除空的文件夹
	 * @param Path 文件夹路径
	 * @param DirName 文件夹名字
	 * @return boolean -> true删除成功，false删除失败
	 * 
	 * @exception FileOperateException 本地文件操作时抛出的错误，errorcode=0:文件不存在,errorcode=1:不是一个文件,errorcode=2:不能操作，被占用,errorcode=3:不是一个目录
	 * */
	public abstract boolean DeleteRmptyDir(String Path,String DirName,Host host) 
			throws FileOperateException,RemoteOperateException,IOException;

}
