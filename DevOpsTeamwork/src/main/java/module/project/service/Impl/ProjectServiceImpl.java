package module.project.service.Impl;

import module.entity.VO.ProjectVO;
import module.project.service.ProjectService;

import java.util.ArrayList;

/**
 * Created by caosh on 2018/4/9.
 */
public class ProjectServiceImpl implements ProjectService{
    /**
     * 创建项目（选择团队）
     * @param projectName
     * @param info
     * @param managerList
     * @param groupId
     * @return
     */
    public ProjectVO createWithGroup(String projectName, String info, ArrayList<Integer> managerList, int groupId) {
        return null;
    }

    /**
     * 创建项目（无团队）
     * @param projectName
     * @param info
     * @param managerList
     * @param memberList
     * @return
     */
    public ProjectVO createWithoutGroup(String projectName, String info, ArrayList<Integer> managerList, ArrayList<Integer> memberList) {
        return null;
    }

    /**
     * 编辑项目信息
     * @param projectVO
     * @return
     */
    public boolean editProject(ProjectVO projectVO) {
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
