package com.interstellar.devopsjenkins.FeignClient;

import com.interstellar.devopsjenkins.vo.BuildInformationVO;
import com.interstellar.devopsjenkins.vo.NodeInformationVO;
import com.interstellar.devopsjenkins.vo.StepVO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(configuration = FeignMultipartSupportConfig.class)
public interface JenkinsFeign {
    @RequestLine("POST /createItem?name={name}")
    @Headers("Context-Type: application/xml")
    void createItem(@RequestBody byte[] file, @Param("name") String name);

    @RequestLine(value = "GET /api/xml")
    String api();

    @RequestLine("POST /job/{name}/build")
    String Build(@Param("name") String name);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/runs/{number}/nodes")
    List<NodeInformationVO> pipelineNodes(@Param("name") String name, @Param("number") String number);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/runs/{number}/nodes/{nodeId}")
    NodeInformationVO pipelineNode(@Param("name") String name, @Param("number") String number, @Param("nodeId") String nodeId);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/runs/{number}/nodes/{nodeId}/steps")
    List<StepVO> pipelineNodeSteps(@Param("name") String name, @Param("number") String number, @Param("nodeId") String nodeId);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/runs/{number}/nodes/{nodeId}/steps/{stepId}")
    StepVO pipelineStep(@Param("name") String name, @Param("number") String number, @Param("nodeId") String nodeId, @Param("stepId") String stepId);

    @RequestLine("GET /job/{name}/{number}")
    BuildInformationVO getBuild(@Param("name") String name, @Param("number") String number);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/runs/{number}/log?start=0")
    @Headers("Context-Type: text/plain; charset=utf-8")
    String downloadLog1(@Param("name") String name, @Param("number") String number);


}
