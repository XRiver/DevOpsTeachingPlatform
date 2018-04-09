package Devops.docker.DockerBranch.Exception;


/**
 * 
 * author:杨关
 * 文件远程操作的自定义异常类
 * 
 * */
public class RemoteOperateException extends RuntimeException{
public class RemoteOperateException extends Exception{
	
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
	
	
	/**
	 * 构造一个基本异常
	 * 
	 * @param errorCode 错误编码
	 * 
	 * @param message 信息描述
	 * */
	public RemoteOperateException (String errorCode, String message) {
		this(message);
		this.errorCode = errorCode;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
