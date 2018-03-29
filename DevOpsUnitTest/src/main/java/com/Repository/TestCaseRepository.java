package com.Repository;

import com.Entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/3/21.
 */
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    TestCase findById(Long id);

}
