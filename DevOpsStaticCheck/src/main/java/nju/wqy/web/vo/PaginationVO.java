package nju.wqy.web.vo;

import java.util.List;

public class PaginationVO {
	private int total;
	private List<ProblemVO> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<ProblemVO> getRows() {
		return rows;
	}
	public void setRows(List<ProblemVO> rows) {
		this.rows = rows;
	}

}
