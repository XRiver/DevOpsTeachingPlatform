package nju.wqy.service;

import nju.wqy.web.vo.OperationStatus;

public interface CheckService {
	OperationStatus check(String projectKey);
}
