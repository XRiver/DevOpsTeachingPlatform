package module.test.web;

import com.alibaba.fastjson.JSONObject;
import common.util.StringUtils;
import module.test.model.Test;
import module.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	/**
	 * 查询数据，指定为必须get请求，其他请求方式（例如post），将请求不通
	 * @return
	 */
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public JSONObject test(String name){
		JSONObject rtnJson = new JSONObject();
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){//根据name进行模糊查询
			params.put("name",name);
		}
		List<Test> testList = testService.selectId(params);
		rtnJson.put("data", testList);
		
		return rtnJson;
	}
}
