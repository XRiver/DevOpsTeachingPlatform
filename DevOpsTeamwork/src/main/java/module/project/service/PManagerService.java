package module.project.service;

import module.entity.VO.UserVO;

import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
public interface PManagerService {

    int addManager();

    int deleteManager();

    List<UserVO> getPManagers(int projectId);

}
