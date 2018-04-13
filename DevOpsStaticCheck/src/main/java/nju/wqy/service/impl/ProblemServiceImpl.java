package nju.wqy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.wqy.service.ProblemService;
import nju.wqy.util.APIManager;
import nju.wqy.web.vo.IndexVO;
import nju.wqy.web.vo.ProblemVO;
@Service
public class ProblemServiceImpl implements ProblemService{

	@Override
	public List<ProblemVO> getProblem(String type, String projectKey) {
		String url="http://localhost:9000/api/issues/search?componentKeys="+projectKey+"&s=FILE_LINE&resolved=false&types="+type+"&ps=100&facets=severities%2Ctypes&additionalFields=_all";
		List<ProblemVO> problems=new ArrayList<ProblemVO>();
		String result=APIManager.get(url);
		if(result!=null){  
			JSONObject obj=JSONObject.fromObject(result);      
			result=obj.getString("issues");//得到json格式字符串数组  
			JSONArray arr=JSONArray.fromObject(result);  
			for(int i=0;i<arr.size();i++) {
				if(getValue(arr.getString(i),"message").equals("1 duplicated blocks of code must be removed.")) {
					break;
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
		} 

		return problems;
	}

	public static String getValue(Object o,String key) {
		JSONObject obj=JSONObject.fromObject(o); 	
		String value=obj.getString(key);
		return value;
	}
}
