package Devops.docker.DockerBranch.dao;

import Devops.docker.DockerBranch.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskDao extends JpaRepository<Task, Integer> {
    @Override
    List<Task> findAll();

    //兼具insert和update
    @Override
    Task save(Task task);

    //optional对象，需要用过isPresent方法检验是否为空，通过get方法获得其中对象，这可真是卧槽了
    @Override
    Optional<Task> findById(Integer id);

    @Override
    void deleteById(Integer var1);

    @Override
    void delete(Task var1);

    @Query(value = "select t.* from task t where t.host_id = ?1",nativeQuery = true)
    List<Task> findTasksByHostId(int hostid);

    @Query(value = "select t.* from task t where t.project_id = ?1",nativeQuery = true)
    List<Task> findAllByProjectId(String project_id);
}
