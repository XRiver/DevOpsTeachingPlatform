package nju.wqy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.wqy.service.CodeService;
import nju.wqy.util.APIManager;
import nju.wqy.web.vo.CodeVO;
import nju.wqy.web.vo.ProblemVO;
@Service
public class CodeServiceImpl implements CodeService{

	@Override
	public CodeVO getCodes(String key) {
		String url="http://139.219.66.203:9000/api/sources/lines?"+key+"&from=1&to=502";
		System.out.println(url);
		List<CodeVO> code=new ArrayList<CodeVO>();
		String result=APIManager.get(url);
		CodeVO c=new CodeVO();
		if(result!=null){  
			JSONObject obj=JSONObject.fromObject(result);      
			result=obj.getString("sources");//得到json格式字符串数组  
			JSONArray arr=JSONArray.fromObject(result); 
			List<String> co=new ArrayList<String>();
			for(int i=0;i<arr.size();i++) {
				String codeLine=getValue(arr.getString(i),"code");
				//用&nbsp替换空格 
			//	codeLine=codeLine.replaceAll(" ", "&nbsp;");
				co.add(codeLine);
			}
			c.setCode(co);
		} 
		return c;
	}

	public static String getValue(Object o,String key) {
		JSONObject obj=JSONObject.fromObject(o); 	
		String value=obj.getString(key);
		return value;
	}
}
