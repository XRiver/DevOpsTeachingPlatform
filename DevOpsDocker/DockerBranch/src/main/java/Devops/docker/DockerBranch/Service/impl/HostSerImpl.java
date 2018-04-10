package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Service.HostService;
import Devops.docker.DockerBranch.VO.hostVO;
import Devops.docker.DockerBranch.dao.HistoryDao;
import Devops.docker.DockerBranch.dao.HostDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HostSerImpl implements HostService{

    @Autowired
    HostDao dao;

    @Override
    public List<hostVO> getHost(String projectid) {
        List<hostVO> result = new ArrayList<>();

        int id = Integer.parseInt(projectid);
        List<Host> list = dao.findAllByProjectid(id);
        int size = list.size();
        for(int i=0;i<size;i++){
            hostVO vo = new hostVO();
            Host host = list.get(i);
            BeanUtils.copyProperties(host,vo);
            result.add(vo);
        }
        return result;
    }

    @Override
    public int addHost(Host host) {
        return 0;
    }

    @Override
    public void deleteHost(String hostid) {
        Integer id = new Integer(Integer.parseInt(hostid));
        dao.deleteById(id);
    }

    @Override
    public int configHost(Host host) {
        return 0;
    }
}
