package com.interstellar.devopsjenkins.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(configuration = FeignMultipartSupportConfig.class)
public interface JenkinsFeign1 {

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/{branchName}/runs/{number}/log?start=0")
    @Headers("Context-Type: text/plain; charset=utf-8")
    String getLog(@Param("name") String name, @Param("branchName") String branchName, @Param("number") String number);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/{branchName}/runs/{number}/log?start=0&download=true")
    String downloadLog(@Param("name") String name, @Param("branchName") String branchName, @Param("number") String number);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/{branchName}/runs/{number}/nodes/{nodeId}/steps/{stepId}/log")
    @Headers("Context-Type: text/plain; charset=utf-8")
    String stepLog(@Param("name") String name, @Param("branchName") String branchName, @Param("number") String number, @Param("nodeId") String nodeId, @Param("stepId") String stepId);

    @RequestLine("POST /job/{name}/build?delay=0")
    String scanning(@Param("name") String name);

    @RequestLine("POST /job/{name}/job/{branchName}/build")
    String build(@Param("name") String name, @Param("branchName") String branchName);


}
