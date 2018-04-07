package Devops.docker.DockerBranch.dao;

import Devops.docker.DockerBranch.Entity.Basicimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface testLink extends JpaRepository<Basicimage, Long> {

    @Override
    List<Basicimage> findAll();

    @Override
    Basicimage save(Basicimage image);

    @Override
    void flush();


}
