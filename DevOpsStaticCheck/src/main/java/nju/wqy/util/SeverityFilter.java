package nju.wqy.util;

import nju.wqy.web.vo.ProblemVO;
//严重度过滤器
public class SeverityFilter implements Filter{
	private String severity;
	public SeverityFilter(String severity) {
		this.severity=severity;
	}
	@Override
	public boolean match(ProblemVO vo) {
		String severi=vo.getSeverity();
		if(severi.equals(severity)) {
			return true;
		}
		return false;
	}

}
