package com.Service.Impl;

import com.Service.ApiCallService;
import com.util.ApiRequest;
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
    @Value("${git.url}")
    private static String gitUrl;

    @Override
    public String getUrl(String projectId) {
        String url=ApiRequest.get(gitUrl+"/repository/"+projectId,null);
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
