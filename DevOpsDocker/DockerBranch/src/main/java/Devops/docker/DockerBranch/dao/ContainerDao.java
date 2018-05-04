package Devops.docker.DockerBranch.dao;

import Devops.docker.DockerBranch.Entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContainerDao extends JpaRepository<Container, Integer> {

    @Override
    List<Container> findAll();

    //兼具insert和update
    @Override
    Container save(Container container);

    //optional对象，需要用过isPresent方法检验是否为空，通过get方法获得其中对象，这可真是卧槽了
    @Override
    Optional<Container> findById(Integer id);

    @Override
    void deleteById(Integer var1);

    @Override
    void delete(Container var1);

    @Query(value = "select c.* from container c where c.task_id = ?1 order by container_id",nativeQuery = true)
    List<Container> findContainersByTaskId(int task_id);


}
