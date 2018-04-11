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
	public List<ProblemVO> getProblem(String type, long id) {
		List<ProblemVO> problems=new ArrayList<ProblemVO>();
		String result=APIManager.get("http://localhost:9000/api/issues/search?componentKeys=Student&s=FILE_LINE&resolved=false&ps=100&facets=severities%2Ctypes&additionalFields=_all");
		if(result!=null){  
			JSONObject obj=JSONObject.fromObject(result);      
			result=obj.getString("issues");//得到json格式字符串数组  
			JSONArray arr=JSONArray.fromObject(result);  
			for(int i=0;i<arr.size();i++) {
				ProblemVO vo=new ProblemVO();
				vo.setFilePath(getValue(arr.getString(i),"component"));
				vo.setLineNo(getValue(arr.getString(i),"line"));
				vo.setMessage(getValue(arr.getString(i),"message"));
				vo.setRule(getValue(arr.getString(i),"rule"));
				problems.add(vo);
			}
		} 

		return problems;
	}
	public static String getValue(Object o,String key) {
		System.out.println(o);
		JSONObject obj=JSONObject.fromObject(o); 	
		String value=obj.getString(key);
		System.out.println(value);
		return value;
	}
}
