package nju.wqy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nju.wqy.entity.ConfigData;
import nju.wqy.entity.CustomRuleData;

public interface CustomRuleDao extends JpaRepository<CustomRuleData, String>{

}
