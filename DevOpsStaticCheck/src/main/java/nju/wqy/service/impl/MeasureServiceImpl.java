package nju.wqy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.wqy.service.MeasureService;
import nju.wqy.util.APIManager;
import nju.wqy.web.vo.HistoryVO;
import nju.wqy.web.vo.IndexVO;
import nju.wqy.web.vo.MeasureVO;
@Service
public class MeasureServiceImpl implements MeasureService{

	@Override
	public MeasureVO getMeasure(String projectKey) {
		//得到各种问题的数量
		String result=APIManager.get("http://localhost:9000/api/measures/component?additionalFields=periods&componentKey="+projectKey+"&metricKeys=bugs%2Cclasses%2Ccode_smells%2Ccomment_lines%2Ccomment_lines_density%2Cvulnerabilities%2Cfunctions%2Cfiles%2Clines%2Cncloc");
		MeasureVO vo=null;
		if(result!=null){  
			Gson gs = new Gson();  
			System.out.println(result);
			JSONObject obj=JSONObject.fromObject(result);      
			obj=obj.getJSONObject("component"); 
			JSONArray arr=obj.getJSONArray("measures");
			vo=getVo(arr);
		}
		return vo;
	}
	public static MeasureVO getVo(JSONArray arr) {
		MeasureVO vo=new MeasureVO();
		for(int i=0;i<arr.size();i++) {
			Object o=arr.get(i);
			String metric=getValue(o,"metric");
			switch(metric){
			case "comment_lines":
				//注释行
				vo.setCommentsLines(getIntegerValue(o));
				break;
			case "lines":
				//总行数
				vo.setLine(getIntegerValue(o));
				break;
			case "comment_lines_density":
				//注释比
				vo.setCommentRate(getDoubleValue(o));
				break;
			case "classes":
				//类
				vo.setClasses(getIntegerValue(o));
				break;	
			case "files":
				//文件
				vo.setFile(getIntegerValue(o));
				break;
			case "functions":
				//方法
				vo.setFunctions(getIntegerValue(o));
				break;
			case "ncloc":
				//逻辑代码
				vo.setLoc(getIntegerValue(o));
				break;
			case "vulnerabilities":
				vo.setVulnerability(getIntegerValue(o));
				break;
			case "code_smells":
				vo.setCodeSmell(getIntegerValue(o));
				break;
			case "bugs":
				vo.setBug(getIntegerValue(o));
				break;
			}
		}
		return vo;
	}
	public static int getIntegerValue(Object o) {	
		return Integer.parseInt(getValue(o));
	}
	public static double getDoubleValue(Object o) {	
		return Double.parseDouble(getValue(o));
	}
	public static String getValue(Object o) {
		JSONObject obj=JSONObject.fromObject(o); 	
		String value=obj.getString("value");
		return value;
	}
	public static String getValue(Object o,String value) {

		JSONObject obj=JSONObject.fromObject(o); 	
		return obj.getString(value);

	}
	@Override
	public List<HistoryVO> getHistory(String projectKey, String type) {
		String result=APIManager.get("http://localhost:9000/api/measures/search_history?component="+projectKey+"&metrics="+type+"&ps=1000");
		List<HistoryVO> lists=new ArrayList<HistoryVO>();
		if(result!=null){  
			JSONObject obj=JSONObject.fromObject(result);      
			JSONArray arr=obj.getJSONArray("measures");
			JSONArray history=arr.getJSONObject(0).getJSONArray("history");
			for(int i=0;i<history.size();i++) {
				HistoryVO vo=new HistoryVO();
				System.out.println(history.getString(i));
				vo.setDate(getValue(history.getString(i),"date"));
				vo.setValue(Integer.parseInt(getValue(history.getString(i),"value")));
				lists.add(vo);
			}
		}
		return lists;
	}
}
