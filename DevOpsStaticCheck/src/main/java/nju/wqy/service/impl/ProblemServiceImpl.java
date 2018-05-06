package nju.wqy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import nju.wqy.util.Filter;
import nju.wqy.util.Pipe;
import nju.wqy.util.SeverityFilter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.wqy.service.ProblemService;
import nju.wqy.util.APIManager;
import nju.wqy.util.CompositeFilter;
import nju.wqy.util.FileFilter;
import nju.wqy.web.vo.IndexVO;
import nju.wqy.web.vo.PaginationVO;
import nju.wqy.web.vo.ProblemVO;
@Service
public class ProblemServiceImpl implements ProblemService{

	private List<ProblemVO> pagination(List<ProblemVO> vo,int offset,int pageSize){
		List<ProblemVO> results=new ArrayList<ProblemVO>();
		int end=Math.min((offset+pageSize-1), vo.size());//最后一页应该是两者中小的那个
		for(int i=offset-1;i<end;i++) {
			results.add(vo.get(i));
		}
		return results;
	}

	public List<ProblemVO> getProblems(String result,String type) {
		List<ProblemVO> problems=new ArrayList<ProblemVO>();
		JSONObject obj=JSONObject.fromObject(result);      
		result=obj.getString("issues");//得到json格式字符串数组  
		JSONArray arr=JSONArray.fromObject(result);  
		for(int i=0;i<arr.size();i++) {
			if(getValue(arr.getString(i),"message").equals("1 duplicated blocks of code must be removed.")) {
				continue;
			}	
			ProblemVO vo=new ProblemVO();
			vo.setFilePath(getValue(arr.getString(i),"component"));
			vo.setLineNo(getValue(arr.getString(i),"line"));
			vo.setMessage(getValue(arr.getString(i),"message"));
			vo.setRule(getValue(arr.getString(i),"rule"));
			vo.setType(type);
			vo.setSeverity(getValue(arr.getString(i),"severity"));
			problems.add(vo);
		}
		return problems;
	}
	@Override
	public PaginationVO getProblem(String type, String projectKey ,int offset,int pageSize,String fileName,String severity) {
		String urlBug="http://localhost:9000/api/issues/search?componentKeys="+projectKey+"&s=FILE_LINE&resolved=false&types="
	+"BUG"+"&ps=100&facets=severities%2Ctypes&additionalFields=_all";
		String urlVul="http://localhost:9000/api/issues/search?componentKeys="+projectKey+"&s=FILE_LINE&resolved=false&types="
				+"VULNERABILITY"+"&ps=100&facets=severities%2Ctypes&additionalFields=_all";
		String urlCode="http://localhost:9000/api/issues/search?componentKeys="+projectKey+"&s=FILE_LINE&resolved=false&types="
				+"CODE_SMELL"+"&ps=100&facets=severities%2Ctypes&additionalFields=_all";
		List<ProblemVO> problems=new ArrayList<ProblemVO>();
		if(type.equals("BUG")) {
			String result=APIManager.get(urlBug);
			problems=getProblems(result,"BUG");
		}else if(type.equals("VULNERABILITY")) {
			String result=APIManager.get(urlVul);
			problems=getProblems(result,"VULNERABILITY");
		}else if(type.equals("CODE_SMELL")) {
			String result=APIManager.get(urlCode);
			problems=getProblems(result,"CODE_SMELL");
		}else {
			
		}
		
	
		//过滤
		CompositeFilter filter=new CompositeFilter();
		Filter nameFilter=new FileFilter(fileName);
		filter.addFilter(nameFilter);
		if(!severity.equals("ALL")) {
			Filter severityFiter=new SeverityFilter(severity);
			filter.addFilter(severityFiter);
		}
		Pipe pipe=new Pipe(problems,filter);
		List<ProblemVO> resultList=pipe.getResults();
		//分页
		PaginationVO paginationVO=new PaginationVO();
		paginationVO.setTotal(resultList.size());
		resultList=pagination(resultList,offset,pageSize);
		paginationVO.setRows(resultList);
		return paginationVO;
	}

	public static String getValue(Object o,String key) {
		JSONObject obj=JSONObject.fromObject(o); 	
		String value=obj.getString(key);
		return value;
	}
}
