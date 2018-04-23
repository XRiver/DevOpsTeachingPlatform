package teamworkbranch.module.project.service;

import teamworkbranch.exception.ExistedException;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.module.entity.VO.UserVO;

import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
public interface PManagerService {

    int addManager(int projectId,String user,String applicant) throws NonprivilegedUserException, ExistedException;

    int deleteManager(int projectId,String user,String applicant) throws NonprivilegedUserException;

    List<UserVO> getPManagers(int projectId);



}
