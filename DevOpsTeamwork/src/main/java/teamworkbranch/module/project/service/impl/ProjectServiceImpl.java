package teamworkbranch.module.project.service.impl;

import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.module.entity.VO.ProjectVO;
import teamworkbranch.module.project.dao.PManagerMapper;
import teamworkbranch.module.project.dao.ProjectMapper;
import teamworkbranch.module.project.model.PManager;
import teamworkbranch.module.project.model.Project;
import teamworkbranch.module.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caosh on 2018/4/9.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    PManagerMapper pManagerMapper;


    /**
     * 创建项目（选择团队）
     * @param projectName
     * @param info
     * @param managerList
     * @param groupId
     * @return
     */
    @Override
    public int createWithGroup(String projectName, String info, List<String> managerList, int groupId,String creatorName) {
        Project project = new Project(creatorName,projectName,info,groupId);
        projectMapper.insertProject(project);
        int projectId=projectMapper.selectLastId();
        for(String manager:managerList){
            PManager pManager=new PManager(projectId,manager);
            pManagerMapper.insertPManager(pManager);
        }
        return 0;
    }




    @Override
    public boolean editProject(int projectId, String projectName, String info, String applicant) throws NonprivilegedUserException {
        PManager p=pManagerMapper.getPManager(projectId,applicant);
        if(p==null){
            throw new NonprivilegedUserException();
        }
        Project project=projectMapper.selectById(projectId);
        project.setName(projectName);
        project.setInfo(info);

        projectMapper.updateProject(project);
        return true;
    }


    /**
     * 查看项目信息
     * @param projectId
     * @return
     */
    public Project getProjectInfo(int projectId) {
        return projectMapper.selectById(projectId);
    }



    @Override
    public List<Project> getProjectsByUser(String username) {
        return projectMapper.getProjectsByUser(username);
    }
}
