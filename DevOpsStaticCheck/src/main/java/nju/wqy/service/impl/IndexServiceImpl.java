package nju.wqy.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.wqy.service.IndexService;
import nju.wqy.util.APIManager;
import nju.wqy.web.vo.IndexVO;
@Service
public class IndexServiceImpl implements IndexService{

	@Override
	public IndexVO getIndex(String projectKey) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("projectKeys","Student");
		//得到各种问题的数量
		String result=APIManager.get("http://localhost:9000/api/measures/search_history?component="+projectKey
				+"&metrics=bugs%2Cvulnerabilities%2Ccode_smells%2Cduplicated_lines_density%2Cncloc%2Cncloc_language_distribution");
		if(result!=null){  
			JSONObject obj=JSONObject.fromObject(result);      
			result=obj.getString("measures");//得到json格式字符串数组  
			JSONArray arr=JSONArray.fromObject(result);  
			if(arr.size()==6) {
				String bugs=getValue(arr.get(0));
				String code_smells=getValue(arr.get(1));
				String duplicated_lines_density=getValue(arr.get(2));
				String ncloc=getValue(arr.get(3));
				String ncloc_language_distribution=getValue(arr.get(4));
				String vulnerabilities=getValue(arr.get(5));
				//计算健康度和风险度
				int healthDegree=10*Integer.parseInt(bugs)+3*Integer.parseInt(code_smells)+1*Integer.parseInt(vulnerabilities);
				int riskIndex=healthDegree/Integer.parseInt(ncloc)/1000;
				IndexVO vo=new IndexVO();
				vo.setHealthDegree(healthDegree);
				vo.setRiskIndex(riskIndex);
				//				 vo.setLastAnalyse(lastAnalyse);
			}
		} 

		System.out.println(result);
		return null;
	}
	public static String getValue(Object o) {
		System.out.println(o);
		JSONObject obj=JSONObject.fromObject(o); 	
		String value=obj.getString("value");
		System.out.println(value);
		return value;
	}
}
