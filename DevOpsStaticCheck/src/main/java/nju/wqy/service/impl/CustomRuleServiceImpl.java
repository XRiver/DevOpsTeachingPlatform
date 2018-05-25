package nju.wqy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nju.wqy.dao.CustomRuleDao;
import nju.wqy.service.CustomRuleService;
@Service
public class CustomRuleServiceImpl implements CustomRuleService{
@Autowired
CustomRuleDao customRuleDao;
}
