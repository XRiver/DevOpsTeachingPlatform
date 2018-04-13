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
		String result=APIManager.get("http://localhost:9000/api/rules/show?key="+key);
		if(result!=null){  
			JSONObject obj=JSONObject.fromObject(result);      
			result=obj.getString("rule");//得到json格式字符串  
			RuleVO vo=new RuleVO();
			String description="<h2>修改建议</h2><br />"+getValue(result,"htmlDesc");
			int index=description.indexOf("See");
			if(index>0) {
				description=description.substring(0, index);
			}
			
			//翻译一下
			description=description.replace("Noncompliant Code Example", "错误示例");
			description=description.replace("Compliant Solution", "正确示例");
			description=description.replace("Exceptions", "例外");
			vo.setDescrib(description);
			return vo;
		}
		return null;
	}
	public static String getValue(Object o,String key) {
		JSONObject obj=JSONObject.fromObject(o); 	
		String value=obj.getString(key);
		return value;
	}
}