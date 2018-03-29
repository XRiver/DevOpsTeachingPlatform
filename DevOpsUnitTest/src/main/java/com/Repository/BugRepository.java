package com.Repository;

import com.Entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface BugRepository extends JpaRepository<Bug, Long> {
    Bug findById(Long id);

    boolean deleteById(Long id);

    List<Bug> findByProject_id(String project_id);
}
