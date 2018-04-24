package nju.wqy.service;


import nju.wqy.web.vo.PaginationVO;

public interface ProblemService {
	/**
	 * 
	 * @param type
	 * @param projectKey
	 * @param offset 这页总第几条开始
	 * @param pageSize 这页有几条
	 * @return
	 */
	PaginationVO getProblem(String type,String projectKey,int offset,int pageSize,String fileName,String severity);
}
