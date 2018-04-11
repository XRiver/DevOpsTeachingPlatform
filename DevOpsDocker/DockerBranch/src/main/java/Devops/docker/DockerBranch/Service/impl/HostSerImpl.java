package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.Service.HostService;
import Devops.docker.DockerBranch.Service.tools.DateTool;
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

    /**
     * 检验主机是否可用，若可用便添加
     * @param host
     * @return
     */
    @Override
    public int addHost(Host host) {
        int result = testHost(host);
        if(result == 4){
            dao.save(host);
        }
        return result;
    }

    @Override
    public void deleteHost(String hostid) {
        Integer id = Integer.parseInt(hostid);
        dao.deleteById(id);
    }

    @Override
    public int configHost(Host host) {
        Integer id = host.getHostId();
        Host oldHost = dao.findById(id).get();
        host.setIp(oldHost.getIp());
        host.setOpsSystem(oldHost.getOpsSystem());

        host.setDate(DateTool.getTimeNow());
        int result = testHost(host);
        if(result == 4){
            dao.save(host);
        }
        return result;
    }

    @Override
    public int testHost(String hostid) {
        Integer id = Integer.parseInt(hostid);
        Host host = dao.findById(id).get();
        return this.testHost(host);
    }

    public int testHost(Host host){
        return 4;
    }
}
