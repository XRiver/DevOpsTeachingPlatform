package com.Repository;

import com.Entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface BugRepository extends JpaRepository<Bug, Long> {
    Bug findById(Long id);

    boolean deleteById(Long id);

    @Query("select p from Bug p where p.project_id=:project_id")
    List<Bug> findByProject_id(@Param("project_id")String project_id);
}
