package nju.wqy.service;

import java.util.List;

import nju.wqy.web.vo.ProblemVO;

public interface ProblemService {
List<ProblemVO> getProblem(String type,long id);
}
