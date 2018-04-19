package teamworkbranch.module.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.module.entity.VO.UserVO;
import teamworkbranch.module.project.service.PManagerService;

import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
@Service("pManagerService")
@Transactional
public class PManagerServiceImpl implements PManagerService {

    @Override
    public int addManager() {
        return 0;
    }

    @Override
    public int deleteManager() {
        return 0;
    }

    @Override
    public List<UserVO> getPManagers(int projectId) {
        return null;
    }
}
