package module.project.service.impl;

import module.model.VO.ProjectVO;
import module.project.dao.PManagerMapper;
import module.project.dao.ProjectMapper;
import module.project.model.PManager;
import module.project.model.Project;
import module.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caosh on 2018/4/9.
 */
@Service("projectService")
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
    public boolean editProject(int projectId, String projectName, String info, String applicant) {
        return false;
    }

    /**
     * 项目修改人员
     * @param userId
     * @return
     */
    public boolean editMember(int userId) {
        return false;
    }


    /**
     * 查看项目信息
     * @param projectId
     * @return
     */
    public ProjectVO getGroupInfo(int projectId) {
        return null;
    }
}
