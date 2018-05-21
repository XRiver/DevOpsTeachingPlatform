package com.interstellar.devopsjenkins.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

@FeignClient()
public interface JenkinsFeign2 {

    @RequestLine("GET /job/{name}/lastSuccessfulBuild/artifact/*zip*/archive.zip")
    @Headers("Context-Type: text/plain; charset=utf-8")
    ResponseEntity<byte[]> artifact(@Param("name") String name);

    @RequestLine("GET /job/{name}/job/{branchName}/{number}/artifact/*zip*/archive.zip")
    @Headers("Context-Type: text/plain; charset=utf-8")
    ResponseEntity<byte[]> branchArtifact(@Param("name") String name, @Param("branchName") String branchName);

    @RequestLine("GET /computer/{name}/{name}-agent.jnlp")
    @Headers("Context-Type: text/plain; charset=utf-8")
    ResponseEntity<byte[]> computer(@Param("name") String name);


}
