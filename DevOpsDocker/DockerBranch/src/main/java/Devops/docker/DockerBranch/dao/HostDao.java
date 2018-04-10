package Devops.docker.DockerBranch.dao;

import Devops.docker.DockerBranch.Entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface HostDao extends JpaRepository<Host, Integer> {

    @Override
    List<Host> findAll();

    //兼具insert和update
    @Override
    Host save(Host host);

    //optional对象，需要用过isPresent方法检验是否为空，通过get方法获得其中对象，这可真是卧槽了
    @Override
    Optional<Host> findById(Integer id);

    @Override
    void deleteById(Integer var1);

    @Override
    void delete(Host var1);

    @Query(value = "select h.* from host h where h.projectId = ?1",nativeQuery = true)
    List<Host> findAllByProjectid(int projectid);

}
