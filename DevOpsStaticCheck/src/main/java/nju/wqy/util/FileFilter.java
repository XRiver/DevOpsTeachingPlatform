package nju.wqy.util;

import nju.wqy.web.vo.ProblemVO;

//文件名过滤器
public class FileFilter implements Filter{
	private String fileName;
	public FileFilter(String fileName) {
		this.fileName=fileName;
	}
	@Override
	public boolean match(ProblemVO vo) {
		if(vo.getFilePath().contains(fileName)) {
			return true;
		}
		return false;
	}

}
