package nju.wqy.util;

import java.util.ArrayList;
import java.util.List;

import nju.wqy.web.vo.ProblemVO;

public class Pipe {
	//流入的数据
	private List<ProblemVO> vos;
	//过滤后的数据
	private List<ProblemVO> results;
	//过滤条件
	private Filter filters;
	public Pipe(List<ProblemVO> vos, Filter filters) {
		super();
		this.vos = vos;
		this.results = new ArrayList<ProblemVO>();
		this.filters = filters;
	}
	public List<ProblemVO> getResults(){
		filter();
		return this.results;
	}
	private void filter() {
		for(ProblemVO vo:vos) {
			if(filters.match(vo)==true) {
				this.results.add(vo);
			}
		}
	}

}
