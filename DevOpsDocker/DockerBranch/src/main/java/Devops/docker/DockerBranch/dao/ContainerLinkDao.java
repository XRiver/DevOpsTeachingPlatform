package Devops.docker.DockerBranch.dao;

import Devops.docker.DockerBranch.Entity.Containerlink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContainerLinkDao extends JpaRepository<Containerlink,Integer>{

    @Override
    List<Containerlink> findAll();

    //兼具insert和update
    @Override
    Containerlink save(Containerlink containerlink);

    //optional对象，需要用过isPresent方法检验是否为空，通过get方法获得其中对象，这可真是卧槽了
    @Override
    Optional<Containerlink> findById(Integer id);

    @Override
    void deleteById(Integer var1);

    @Override
    void delete(Containerlink var1);

    @Query(value = "select distinct c.linkedname from containerlink c where c.masterid = ?1",nativeQuery = true)
    List<String> getLinkedidByMasterid(int masterid);

    @Query(value = "delete from containerlink c where c.masterid = ?1 and c.linkedname = ?2",nativeQuery = true)
    @Modifying
    void deleteByMasteridAndLinkedname(int masterid,String linkedname);
}
