package Devops.docker.DockerBranch.Service.impl;

import Devops.docker.DockerBranch.Entity.Basicimage;
import Devops.docker.DockerBranch.Service.Test;
import Devops.docker.DockerBranch.dao.testLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestImpl implements Test{
    @Autowired
    testLink link;

    public TestImpl() {
    }

    @Override
    public void save(Basicimage image) {
        link.save(image);
    }
}
