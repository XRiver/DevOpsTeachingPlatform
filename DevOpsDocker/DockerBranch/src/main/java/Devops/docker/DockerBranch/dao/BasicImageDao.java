package Devops.docker.DockerBranch.dao;

import Devops.docker.DockerBranch.Entity.Basicimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BasicImageDao extends JpaRepository<Basicimage, Integer> {

    @Override
    List<Basicimage> findAll();

    //兼具insert和update
    @Override
    Basicimage save(Basicimage basicimage);

    //optional对象，需要用过isPresent方法检验是否为空，通过get方法获得其中对象，这可真是卧槽了
    @Override
    Optional<Basicimage> findById(Integer id);

    @Override
    void deleteById(Integer var1);

    @Override
    void delete(Basicimage var1);

}
