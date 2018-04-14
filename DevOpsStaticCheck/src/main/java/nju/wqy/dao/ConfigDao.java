package nju.wqy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nju.wqy.entity.ConfigData;



public interface ConfigDao extends JpaRepository<ConfigData, Long> {
	@Query("from ConfigData c where c.projectKey=:projectKey")
	ConfigData getByProjectKey(@Param("projectKey")String projectKey);
}
