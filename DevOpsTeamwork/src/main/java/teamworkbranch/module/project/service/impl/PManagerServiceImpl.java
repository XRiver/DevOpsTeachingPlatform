package teamworkbranch.module.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.exception.ExistedException;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.module.project.dao.PManagerMapper;
import teamworkbranch.module.project.model.PManager;
import teamworkbranch.module.project.service.PManagerService;
import teamworkbranch.module.user.model.User;

import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
@Service("pManagerService")
@Transactional
public class PManagerServiceImpl implements PManagerService {

    @Autowired
    PManagerMapper pManagerMapper;
    @Override
    public int addManager(int projectId,String user, String applicant) throws NonprivilegedUserException, ExistedException {
        verify(projectId,applicant);
        PManager pManager=pManagerMapper.getPManager(projectId,user);
        if(pManager!=null){
            throw new ExistedException();
        }
        pManagerMapper.insertPManager(new PManager(projectId,user));
        return 0;
    }

    @Override
    public int deleteManager(int projectId,String user, String applicant) throws NonprivilegedUserException {
        verify(projectId,applicant);
        pManagerMapper.deletePManager(projectId,user);
        return 0;
    }

    @Override
    public List<PManager> getPManagers(int projectId) {
        return pManagerMapper.getPManagerList(projectId);
    }

    /**
     * 验证applicant是否有权限
     * @param projectId
     * @param applicant
     * @return
     * @throws NonprivilegedUserException
     */
    private boolean verify(int projectId,String applicant) throws NonprivilegedUserException{
        PManager pManager=pManagerMapper.getPManager(projectId,applicant);
        if(pManager==null){
            throw new NonprivilegedUserException();
        }

        return false;

    }


}
