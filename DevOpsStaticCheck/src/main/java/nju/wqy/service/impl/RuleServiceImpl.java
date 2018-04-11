package nju.wqy.service.impl;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.wqy.service.RuleService;
import nju.wqy.util.APIManager;
import nju.wqy.web.vo.ProblemVO;
import nju.wqy.web.vo.RuleVO;
@Service
public class RuleServiceImpl implements RuleService{

	@Override
	public RuleVO getRuleVO(String key) {
		String result=APIManager.get("http://localhost:9000/api/rules/show?key=squid%3A"+key);
		if(result!=null){  
			JSONObject obj=JSONObject.fromObject(result);      
			result=obj.getString("issues");//得到json格式字符串数组  
			JSONArray arr=JSONArray.fromObject(result);  
	
			 //错误行的内容
			String contents=APIManager.get("http://localhost:9000/api/sources/lines?key=Student%3Asrc%2Fmain%2Fjava%2Fnju%2Fwqy%2Fconstants%2FNameList.java&from=1&to=502");
		//根据错误 行找到啊那行数据
			return null;
	}
		return null;
}
}