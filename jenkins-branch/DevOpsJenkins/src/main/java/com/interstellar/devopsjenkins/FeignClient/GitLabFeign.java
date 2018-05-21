package com.interstellar.devopsjenkins.FeignClient;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;

@FeignClient()
public interface GitLabFeign {


    @RequestLine("PUT /file/newfile/{projectid}/{file_path}")
    String upload(@Param("projectid") String projectid, @Param("file_path") String file_path, Map<String, String> map);
}
