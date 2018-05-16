package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.History;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Entity.Task;
import Devops.docker.DockerBranch.Service.DeployService;
import Devops.docker.DockerBranch.Service.tools.DateTool;
import Devops.docker.DockerBranch.VO.deployHistoryVO;
import Devops.docker.DockerBranch.VO.deployInfoVO;
import Devops.docker.DockerBranch.VO.deployStatusVO;
import Devops.docker.DockerBranch.VO.hostVO;
import Devops.docker.DockerBranch.dao.ContainerDao;
import Devops.docker.DockerBranch.dao.HistoryDao;
import Devops.docker.DockerBranch.dao.HostDao;
import Devops.docker.DockerBranch.dao.TaskDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeploySerImpl implements DeployService {

    @Autowired
    HistoryDao historyDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    HostDao hostDao;

    @Autowired
    ContainerDao containerDao;

    @Override
    public List<deployHistoryVO> getHistory(String taskid) {

        List<deployHistoryVO> result = new ArrayList<>();

        List<History> list = historyDao.findHistoriesByTaskId(Integer.parseInt(taskid));
        int size = list.size();

        for(int i=0;i<size;i++){
            deployHistoryVO vo = new deployHistoryVO();
            BeanUtils.copyProperties(list.get(i),vo);
            vo.setHistoryId(list.get(i).getHistoryId()+"");
            result.add(vo);
        }
        return result;
    }

    @Override
    public deployInfoVO getInfo(String taskid) {

        deployInfoVO vo = new deployInfoVO();

        Task task = taskDao.findById(Integer.parseInt(taskid)).get();
        vo.setTaskId(taskid);
        vo.setDate(DateTool.getTodayDate());

        int hostid = task.getHostId();
        Host host = hostDao.findById(hostid).get();

        BeanUtils.copyProperties(task,vo);
        hostVO hostVO = new hostVO();
        BeanUtils.copyProperties(host,hostVO);
        hostVO.setHostId(host.getHostId()+"");
        vo.setHost(hostVO);

        List<Container> list = containerDao.findContainersByTaskId(Integer.parseInt(taskid));
        int size = list.size();
        for(int i=0;i<size;i++){
            Container container = list.get(i);
            if(container.getFilename().equals(task.getSoftware())){
                vo.setSoftware(task.getSoftware());
                vo.setPath(container.getPath());
                break;
            }
        }

        return vo;
    }

//    @Override
//    public deployStatusVO getStatus(String taskid) {
//        return null;
//    }
}
