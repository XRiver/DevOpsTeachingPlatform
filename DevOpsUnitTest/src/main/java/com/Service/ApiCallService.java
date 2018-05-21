package com.Service;

import java.util.List;

public interface ApiCallService {
    public String getUrl(String projectId);

    public List<String> getAllPath(String projectId);

    public String uploadFile(String projectId,String path,String branch,String content);

}
