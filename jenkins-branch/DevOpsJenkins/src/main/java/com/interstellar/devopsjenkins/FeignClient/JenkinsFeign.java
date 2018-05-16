package com.interstellar.devopsjenkins.FeignClient;

import com.interstellar.devopsjenkins.vo.*;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(configuration = FeignMultipartSupportConfig.class)
public interface JenkinsFeign {

    @RequestLine("GET /computer/api/json")
    Computer getComputers();

    @RequestLine("GET /job/{name}/api/json")
    MulitJobInformationVO getJob(@Param("name") String name);

    @RequestLine("GET /job/{name}/job/{branchName}/api/json")
    JobInformationVO getJobBranch(@Param("name") String name, @Param("branchName") String branchName);



    @RequestLine("GET /job/{name}/job/{branchName}/{number}/api/json")
    BuildInformationVO1 getBuild(@Param("name") String name, @Param("branchName") String branchName, @Param("number") String number);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/{branchName}/runs/{number}/nodes")
    List<NodeInformationVO> pipelineNodes(@Param("name") String name, @Param("branchName") String branchName, @Param("number") String number);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/{branchName}/runs/{number}/nodes/{nodeId}")
    NodeInformationVO pipelineNode(@Param("name") String name, @Param("branchName") String branchName, @Param("number") String number, @Param("nodeId") String nodeId);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/{branchName}/runs/{number}/nodes/{nodeId}/steps")
    List<StepVO> pipelineNodeSteps(@Param("name") String name, @Param("branchName") String branchName, @Param("number") String number, @Param("nodeId") String nodeId);

    @RequestLine("GET /blue/rest/organizations/jenkins/pipelines/{name}/{branchName}/runs/{number}/nodes/{nodeId}/steps/{stepId}")
    StepVO pipelineStep(@Param("name") String name, @Param("branchName") String branchName, @Param("number") String number, @Param("nodeId") String nodeId, @Param("stepId") String stepId);

}
