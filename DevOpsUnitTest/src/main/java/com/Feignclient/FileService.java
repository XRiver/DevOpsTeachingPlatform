package com.Feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */
@Service
public class FileService {

    //public boolean cloneFile(String projectId,String branch);

    public String getUrl(String projectId){
        return null;
    }

    public List<String> getAllPath(String projectId){
        return null;
    }

    public boolean uploadFile(String projectId,String path,String branch,String content){
        return false;
    }

}
