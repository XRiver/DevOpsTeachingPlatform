package nju.wqy.util;

import java.util.ArrayList;
import java.util.List;

import nju.wqy.web.vo.ProblemVO;

public  class CompositeFilter implements Filter{
	protected List<Filter> filters;
	public CompositeFilter() {
		this.filters=new ArrayList<Filter>();
	}
	public  boolean match(ProblemVO vo) {
		for(Filter filter:filters) {
			if(!filter.match(vo)) {
				return false;
			}
		}
		return true;
	}
	public List<Filter> getFilters() {
		return filters;
	}
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
	public void addFilter(Filter filter) {
		this.filters.add(filter);
	}


}
