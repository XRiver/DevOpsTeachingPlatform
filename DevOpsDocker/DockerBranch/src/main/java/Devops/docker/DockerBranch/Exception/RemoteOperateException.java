package Devops.docker.DockerBranch.Exception;


/**
 * 
 * author:杨关
 * 文件远程操作的自定义异常类
 * 
 * */
public class RemoteOperateException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误编码
	 * */
	private String errorCode;
	
	
	/**
	 * 构造一个基本异常
	 * 
	 * */
	public RemoteOperateException (String message) {
		super(message);
	}
	

}
