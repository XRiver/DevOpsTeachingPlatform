package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Entity.Basicimage;
import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.History;
import Devops.docker.DockerBranch.Service.Test;
import Devops.docker.DockerBranch.VO.deployHistoryVO;
import Devops.docker.DockerBranch.dao.HistoryDao;
import Devops.docker.DockerBranch.dao.testLink;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestImpl implements Test{
    @Autowired
    testLink link;

    @Autowired
    HistoryDao dao;

    public TestImpl() {
    }

    @Override
    public void save(Container image) {
        link.save(image);
    }

    @Override
    public deployHistoryVO getHistoryVO() {
        int t = 1;
        History ori = dao.findById(Integer.parseInt("1")).get();
//        List<deployHistoryVO> voList = new ArrayList<>();
//        int size = list.size();
        deployHistoryVO des = new deployHistoryVO();
        try {
            BeanUtils.copyProperties(des,ori);
            des.setOperatorName(BeanUtils.describe(ori).toString());
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return des;
    }


}
