package nju.wqy.util;

import nju.wqy.web.vo.ProblemVO;

public interface Filter {
	public boolean match(ProblemVO vo);
}
