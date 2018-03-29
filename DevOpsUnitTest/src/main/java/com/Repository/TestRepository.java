package com.Repository;

import com.Entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface TestRepository extends JpaRepository<TestEntity, Long> {
    TestEntity findById(Long id);

    boolean deleteById(Long id);

    List<TestEntity> findByProject_id(String project_id);
}
