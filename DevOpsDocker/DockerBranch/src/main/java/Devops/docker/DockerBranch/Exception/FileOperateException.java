package Devops.docker.DockerBranch.Exception;

public class FileOperateException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误编码
	 * */
	private String errorCode;
	
	
	/**
	 * 构造一个基本异常
	 * 
	 * */
	public FileOperateException (String message) {
		super(message);
	}
	
	
	/**
	 * 构造一个基本异常
	 * 
	 * @param errorCode 错误编码
	 * 
	 * @param message 信息描述
	 * */
	public FileOperateException (String errorCode, String message) {
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
