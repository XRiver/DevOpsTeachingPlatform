package Devops.docker.DockerBranch.dao;

import Devops.docker.DockerBranch.Entity.Basicimage;
import Devops.docker.DockerBranch.Entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface testLink extends JpaRepository<Container, Long> {

    @Override
    List<Container> findAll();

    @Override
    Container save(Container image);

    @Override
    void flush();


}
