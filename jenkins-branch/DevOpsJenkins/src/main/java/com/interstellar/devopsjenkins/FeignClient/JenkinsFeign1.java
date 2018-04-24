package com.interstellar.devopsjenkins.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(configuration = FeignMultipartSupportConfig.class)
public interface JenkinsFeign1 {

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/runs/{number}/log?start=0")
    @Headers("Context-Type: text/plain; charset=utf-8")
    String downloadLog(@Param("name") String name, @Param("number") String number);
}
