package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Entity.Basicimage;
import Devops.docker.DockerBranch.Service.ImageService;
import Devops.docker.DockerBranch.VO.basicImageVO;
import Devops.docker.DockerBranch.dao.BasicImageDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageSerImpl implements ImageService{

    @Autowired
    BasicImageDao dao;

    @Override
    public List<basicImageVO> getBasicImages() {
        List<Basicimage> list = dao.findAll();
        List<basicImageVO> result = new ArrayList<>();
        int size = list.size();
        for(int i=0;i<size;i++){
            basicImageVO vo = new basicImageVO();
            BeanUtils.copyProperties(list.get(i),vo);
            result.add(vo);
        }
        return result;
    }
}
