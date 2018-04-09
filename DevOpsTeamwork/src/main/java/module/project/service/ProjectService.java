package module.project.service;

import module.VO.ProjectVO;

import java.util.ArrayList;

/**
 * Created by caosh on 2018/4/9.
 */
public interface ProjectService {


    /**
     * 创建项目（选择团队）
     * @param projectName
     * @param info
     * @param managerList
     * @param groupId
     * @return
     */
    public ProjectVO createWithGroup(String projectName, String info, ArrayList<Integer> managerList,
                                     int groupId);

    /**
     * 创建项目（无团队）
     * @param projectName
     * @param info
     * @param managerList
     * @param memberList
     * @return
     */
    public ProjectVO createWithoutGroup(String projectName, String info, ArrayList<Integer> managerList,
                                        ArrayList<Integer> memberList);


    /**
     * 编辑项目信息
     * @param projectVO
     * @return
     */
    public boolean editProject(ProjectVO projectVO);


    /**
     * 项目修改人员
     * @param userId
     * @return
     */
    public boolean editMember(int userId);


    /**
     * 查看项目信息
     * @param projectId
     * @return
     */
    public ProjectVO getGroupInfo(int projectId);
}
