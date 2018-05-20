package com.Repository;

import com.Entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface TestRepository extends JpaRepository<TestEntity, Long> {
    TestEntity findById(Long id);

    boolean deleteById(Long id);

    @Query("select p from TestEntity p where p.project_id=:project_id")
    List<TestEntity> findByProject_id(@Param("project_id")String project_id);
}
