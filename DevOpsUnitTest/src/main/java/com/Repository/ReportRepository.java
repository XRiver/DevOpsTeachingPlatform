package com.Repository;

import com.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/3/21.
 */
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findById(Long id);
}
