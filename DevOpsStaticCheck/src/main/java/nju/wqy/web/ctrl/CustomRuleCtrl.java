package nju.wqy.web.ctrl;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.hash.Hashing;

import nju.wqy.dao.CustomRuleDao;
import nju.wqy.entity.CustomRuleData;
import nju.wqy.service.CustomRuleService;
import nju.wqy.web.vo.OperationStatus;

@RequestMapping(value = "staticCheck/customRule")
@RestController
@Service
public class CustomRuleCtrl {
	@Autowired
	CustomRuleService customRuleService;
	@Autowired
	CustomRuleDao customRuleDao;
	@RequestMapping(value = "{projectKey}",method = RequestMethod.POST)
	public Map<String, Object> upLoadFile(@PathVariable String projectKey,HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(value="uploadfile") MultipartFile myFile) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			//保存的路径
			String path = "/home/xujianghe/sonarQube/sonarqube-6.7.1/extensions/plugins/";
			String fileName = myFile.getOriginalFilename();
			String newFile=path+fileName;
			myFile.transferTo(new File(newFile));
			System.out.println(newFile);
			json.put("filePath", fileName);
			customRuleDao.save(new CustomRuleData(projectKey,fileName,newFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json ;
	}
	@RequestMapping(value = "{projectKey}",method = RequestMethod.GET)
	public String getCustomRule(@PathVariable String projectKey) {
		CustomRuleData da=customRuleDao.findOne(projectKey);
		if(da==null)
			return null;
		if(da.getFileName().equals("")||da.getFileName().equals(null))
			return null;
		return da.getFileName();
	}
	@RequestMapping(value = "{projectKey}",method = RequestMethod.DELETE)
	public void deleteCustomRule(@PathVariable String projectKey) {
		customRuleDao.delete(projectKey);
		
		
	}
}
