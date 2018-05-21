package com.interstellar.devopsjenkins.controller;

import com.interstellar.devopsjenkins.FeignClient.JenkinsFeign;
import com.interstellar.devopsjenkins.FeignClient.JenkinsFeign1;
import com.interstellar.devopsjenkins.FeignClient.JenkinsFeign2;
import com.interstellar.devopsjenkins.service.JenkinsService;
import com.interstellar.devopsjenkins.util.ResultMessage;
import com.interstellar.devopsjenkins.util.jenkinsURL;
import com.interstellar.devopsjenkins.vo.Computer;
import com.interstellar.devopsjenkins.vo.JobInformationVO;
import com.interstellar.devopsjenkins.vo.NodeInformationVO;
import com.interstellar.devopsjenkins.vo.StepVO;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/")
public class JenkinsController {
    @Autowired
    JenkinsFeign jenkinsFeign;
    @Autowired
    JenkinsService jenkinsService;

    @Autowired
    JenkinsFeign1 jenkinsFeign1;
    @Autowired
    JenkinsFeign2 jenkinsFeign2;
    @Autowired
    JenkinsServer jenkinsServer;

    @RequestMapping(value = "/job/{name}", method = RequestMethod.GET)
    @ResponseBody
    public JobInformationVO getJob(@PathVariable("name") String name) throws IOException {
        JobInformationVO jobInformationVO = jenkinsService.getJob(name);

        return jobInformationVO;
    }

    @RequestMapping(value = "/job/{name}/build", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage buildJob(@PathVariable("name") String name) {
        String result = jenkinsFeign1.scanning(name);
        return new ResultMessage(true, "扫描成功", result);
    }

    /*@RequestMapping(value = "/job/{name}/create", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage createJob1(@PathVariable("name") String name, String description, String url, String branch, String jenkinsFilePath) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("/home/bastilashan/config.xml")));
            String result = "";
            while ((result = br.readLine()) != null) {
                sb.append(result);

            }
            ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jenkinsFeign2.createItem(sb.toString().getBytes(), name);
        return new ResultMessage(false, "创建失败", null);
    }*/

    @RequestMapping(value = "/job/{name}", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage createJob(@PathVariable("name") String name, String description, String url, String jenkinsFilePath) {
        try {
            return jenkinsService.createJob(name, description, url, jenkinsFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "创建失败", null);
    }

    @RequestMapping(value = "/job/{name}/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage updateJob(@PathVariable("name") String name, String description, String jenkinsFilePath) {
        try {
            return jenkinsService.updateJob(name, description, jenkinsFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "更新失败", null);
    }

    @RequestMapping(value = "/job/{name}/period", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage periodJob(@PathVariable("name") String name, @RequestParam("period") String period) {
        System.out.println("period POST" + " period :" + period + "hours");
        try {
            return jenkinsService.periodJob(name, Integer.parseInt(period));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "更新周期失败", null);
    }

    @RequestMapping(value = "/job/{name}/period", method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage getJobPeriod(@PathVariable("name") String name) {
        try {
            return jenkinsService.getJobPeriod(name);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(false, "获取周期失败", -1);
        }

    }

    @RequestMapping(value = "/job/{name}/doDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage deleteJob(@PathVariable("name") String name) {
        try {
            return jenkinsService.deleteJob(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "删除失败", null);
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/build/{number}/nodes", method = RequestMethod.GET)
    @ResponseBody
    public List<NodeInformationVO> getNodes(@PathVariable("name") String name, @PathVariable("branchName") String branchName, @PathVariable("number") String number) {
        return jenkinsFeign.pipelineNodes(name, branchName, number);
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/build/{number}/nodes/{nodeId}", method = RequestMethod.GET)
    @ResponseBody
    public NodeInformationVO getNode(@PathVariable("name") String name, @PathVariable("branchName") String branchName, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId) {
        return jenkinsFeign.pipelineNode(name, branchName, number, nodeId);
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/build/{number}/nodes/{nodeId}/steps", method = RequestMethod.GET)
    @ResponseBody
    public List<StepVO> getNodeSteps(@PathVariable("name") String name, @PathVariable("branchName") String branchName, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId) {
        return jenkinsFeign.pipelineNodeSteps(name, branchName, number, nodeId);
    }


    @RequestMapping(value = "/job/{name}/branch/{branchName}/build/{number}/nodes/{nodeId}/steps/{stepId}", method = RequestMethod.GET)
    @ResponseBody
    public StepVO getStep(@PathVariable("name") String name, @PathVariable("branchName") String branchName, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId, @PathVariable("stepId") String stepId) {
        return jenkinsFeign.pipelineStep(name, branchName, number, nodeId, stepId);
    }


    @RequestMapping(value = "/job/{name}/branch/{branchName}/build/{number}/log", method = RequestMethod.GET)
    @ResponseBody
    public String getLog(@PathVariable("name") String name, @PathVariable("branchName") String branchName, @PathVariable("number") String number) throws IOException {

        return jenkinsFeign1.getLog(name, branchName, number);
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/build/{number}/log/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadLog(@PathVariable("name") String name, @PathVariable("branchName") String branchName, @PathVariable("number") String number) throws IOException {
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        String fileName = "log.txt";
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpStatus statusCode = HttpStatus.OK;
        String result = jenkinsFeign1.downloadLog(name, branchName, number);
        byte[] bytes = result.getBytes();
        entity = new ResponseEntity<byte[]>(bytes, headers, statusCode);
        return entity;
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/build/{number}/nodes/{nodeId}/steps/{stepId}/log", method = RequestMethod.GET)
    @ResponseBody
    public String getStepLog(@PathVariable("name") String name, @PathVariable("branchName") String branchName, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId, @PathVariable("stepId") String stepId) throws IOException {

        return jenkinsFeign1.stepLog(name, branchName, number, nodeId, stepId);
    }

    /*@RequestMapping(value = "/job/{name}/hasArtifact", method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage hasArtifact(@PathVariable("name") String name) {
        try {
            Build build = jenkinsServer.getJob(name).getLastSuccessfulBuild();
            return new ResultMessage(true, "有制品可以下载", null);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultMessage(false, "暂时没有制品", null);
        }
    }

    @RequestMapping(value = "/job/{name}/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadArtifact(@PathVariable("name") String name) {
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        String fileName = "artifact.zip";
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpStatus statusCode = HttpStatus.OK;
        try {
            jenkinsServer.getJob(name).getLastSuccessfulBuild();
            ResponseEntity<byte[]> bytes = jenkinsFeign2.artifact(name);
            byte[] bytes1 = bytes.getBody();
            entity = new ResponseEntity<byte[]>(bytes1, headers, statusCode);
            return entity;
        } catch (IOException e) {
            e.getMessage();
            return entity;
        }
    }*/

    @RequestMapping(value = "/computer/{name}/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadAgent(@PathVariable("name") String name) {
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        String fileName = name + "-agent.jnlp";
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpStatus statusCode = HttpStatus.OK;

        ResponseEntity<byte[]> bytes = jenkinsFeign2.computer(name);
        byte[] bytes1 = bytes.getBody();
        entity = new ResponseEntity<byte[]>(bytes1, headers, statusCode);
        return entity;

    }

    @RequestMapping(value = "/computers", method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage getComputer() throws IOException {
        Computer computer = jenkinsFeign.getComputers();
        if (computer == null || computer.getComputer() == null || computer.getComputer().size() == 0) {
            return new ResultMessage(false, "获取系统节点失败", null);
        }
        return new ResultMessage(true, "", computer.getComputer());
    }

    @RequestMapping(value = "/createCredentials", method = RequestMethod.POST)
    @ResponseBody
    public void createCre(String id, String username, String privateKey) throws URISyntaxException, IOException {
        JenkinsHttpClient client = new JenkinsHttpClient(new URI(jenkinsURL.getJenkins()), jenkinsURL.getName(), jenkinsURL.getPassword());

        String json = "json={\"\":\"0\",\"credentials\":{\"scope\":\"GLOBAL\",\"id\":\"" + id + "\",\"username\":\"" + username + "\",\"password\":\"\",\"privateKeySource\":{\"stapler-class\":\"com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey$DirectEntryPrivateKeySource\",\"privateKey\":\"" + privateKey + "\",},\"description\":\"\",\"stapler-class\":\"com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey\"}}";
        String req = "json={\"\":\"0\",\"credentials\":{\"scope\":\"GLOBAL\",\"username\":\"" + username + "\",\"password\":\"\",\"id\":\"" + id + "\",\"description\":\"\",\"$class\":\"com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl\"}}";
        //client.post_text("credentials/store/system/domain/_/createCredentials",req, ContentType.APPLICATION_FORM_URLENCODED,true);
        client.post_text("credentials/store/system/domain/_/createCredentials", json, ContentType.APPLICATION_FORM_URLENCODED, false);
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage init(String groupName, String projectName, String projectId, String url, String description, String jenkinsFilePath) throws URISyntaxException, IOException {
        String name = groupName + "-" + projectName;
        //ResultMessage r1 = jenkinsService.createCredentials(name, SSHPrivateKey);
        //if (!r1.isSuccess()) {
        //    return r1;
        //}
        ResultMessage r2 = jenkinsService.uploadFileToGitLab(groupName, projectName, projectId, "Jenkinsfile", "master", "update Jenkinsfile.");
        if (!r2.isSuccess()) {
            return r2;
        }
        ResultMessage r3 = jenkinsService.createJob(name, description, url, jenkinsFilePath);
        if (!r3.isSuccess()) {
            return r3;
        }
        return new ResultMessage(true, "初始化成功，在Jenkins中项目名为" + name, jenkinsFeign.getJob(name));
    }

    @RequestMapping(value = "/job/{name}/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage getJobInfor(@PathVariable("name") String name) {
        if (jenkinsFeign.getJob(name) == null) {
            return new ResultMessage(false, "项目不存在", null);
        }
        return new ResultMessage(true, "", jenkinsFeign.getJob(name));
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/build/{number}", method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage getBuild(@PathVariable("name") String name, @PathVariable("branchName") String branchName, @PathVariable("number") String number) {
        if (jenkinsFeign.getBuild(name, branchName, number) == null) {
            return new ResultMessage(false, "构建不存在", null);
        }
        return new ResultMessage(true, "", jenkinsFeign.getBuild(name, branchName, number));
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/build", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage buildJob(@PathVariable("name") String name, @PathVariable("branchName") String branchName) {
        jenkinsFeign1.build(name, branchName);
        return new ResultMessage(true, "成功发起构建", null);
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage getJobBranch(@PathVariable("name") String name, @PathVariable("branchName") String branchName) {
        if (jenkinsFeign.getJobBranch(name, branchName) == null) {
            return new ResultMessage(false, "分支不存在", null);
        }
        return new ResultMessage(true, "", jenkinsFeign.getJobBranch(name, branchName));
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/hasArtifact", method = RequestMethod.GET)
    @ResponseBody
    public ResultMessage branchHasArtifact(@PathVariable("name") String name, @PathVariable("branchName") String branchName) {
        JobInformationVO jobInformationVO = jenkinsFeign.getJobBranch(name, branchName);
        if (jobInformationVO.getLastSuccessfulBuild() == null) {
            return new ResultMessage(false, "没有制品可供下载", null);
        }
        return new ResultMessage(true, "有制品可供下载", null);
    }

    @RequestMapping(value = "/job/{name}/branch/{branchName}/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadBranchArtifact(@PathVariable("name") String name, @PathVariable("branchName") String branchName) {
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        String fileName = "artifact.zip";
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpStatus statusCode = HttpStatus.OK;
        try {
            jenkinsServer.getJob(name).getLastSuccessfulBuild();
            ResponseEntity<byte[]> bytes = jenkinsFeign2.branchArtifact(name, branchName);
            byte[] bytes1 = bytes.getBody();
            entity = new ResponseEntity<byte[]>(bytes1, headers, statusCode);
            return entity;
        } catch (IOException e) {
            e.getMessage();
            return entity;
        }
    }


}
