package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Service.DeployService;
import Devops.docker.DockerBranch.Service.impl.TestImpl;
import Devops.docker.DockerBranch.VO.deployHistoryVO;
import Devops.docker.DockerBranch.VO.deployInfoVO;
import Devops.docker.DockerBranch.VO.deployStatusVO;
import Devops.docker.DockerBranch.VO.hostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeployStatus {

    @Autowired
    DeployService deployService;

    @RequestMapping("/getHistory")
    public List<deployHistoryVO> getHistory(@RequestParam String taskid){

        return deployService.getHistory(taskid);
    }

    /**
     * 获得部署任务信息
     * @param taskid
     * @return
     */
    @RequestMapping("/getDeployInfo")
    public deployInfoVO getInfo(@RequestParam String taskid){

        return deployService.getInfo(taskid);
    }

//    /**
//     *
//     * @param taskid
//     * @return 实时部署状态信息，需要轮询获得
//     */
//    @RequestMapping("/getDeployStatus")
//    public deployStatusVO getStatus(@RequestParam String taskid){
//        deployStatusVO vo = new deployStatusVO();
//        vo.setLog("deploying");
//        vo.setStatus(2);
//        vo.setTaskId("123");
//        return vo;
//    }
}
