package Devops.docker.DockerBranch.Controller;

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
    TestImpl test;

    @RequestMapping("/getHistory")
    public List<deployHistoryVO> getHistory(@RequestParam String taskid){
        List<deployHistoryVO> list = new ArrayList<>();
        list.add(test.getHistoryVO());
        return list;
    }

    /**
     * 获得部署任务信息
     * @param taskid
     * @return
     */
    @RequestMapping("/getDeployInfo")
    public deployInfoVO getInfo(@RequestParam String taskid){
        deployInfoVO vo = new deployInfoVO();
        vo.setTaskId(taskid);
        vo.setCreator("xiong");
        vo.setDate("2014:12:12");
        vo.setDescription("xionga");
        vo.setLastDate("2016:12:12");
        vo.setPath("/user/xiong");
        vo.setSoftware("exam");
        hostVO host = new hostVO("123","123","1","123:123:123:123","xiong","true","2013:12:12");
        vo.setHost(host);
        return vo;
    }

    /**
     *
     * @param taskid
     * @return 实时部署状态信息，需要轮询获得
     */
    @RequestMapping("/getDeployStatus")
    public deployStatusVO getStatus(@RequestParam String taskid){
        deployStatusVO vo = new deployStatusVO();
        vo.setLog("deploying");
        vo.setStatus(2);
        vo.setTaskId("123");
        return vo;
    }
}
