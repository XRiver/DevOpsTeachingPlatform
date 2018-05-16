package com.Service.Impl;

import com.Common.DefaultPath;
import com.Service.ApiCallService;
import com.util.ApiRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/28.
 */
@Service
public class ApiCallServiceImpl implements ApiCallService {
    private String gitUrl=DefaultPath.getGit();

    @Override
    public String getUrl(String projectId) {
        //String url="https://github.com/terminuskyuu/helloTest.git";
        System.out.println(gitUrl+"/project/1");
        String json=ApiRequest.get(gitUrl+"/project/"+projectId,null);
        JSONObject project=new JSONObject(json);
        String url=(String) project.get("http_url_to_repo");

        return url;
    }

    @Override
    public List<String> getAllPath(String projectId) {
        return null;
    }

    @Override
    public String uploadFile(String projectId, String path, String branch, String content) {
        String url=gitUrl+"/file/newfile/"+projectId+"/"+path;
        Map<String,String> paramMap=new HashMap<String,String>();
        paramMap.put("branch",branch);
        paramMap.put("content",content);
        return ApiRequest.put(url,paramMap);
    }

}
