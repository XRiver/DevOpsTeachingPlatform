package module.project.service;

import module.model.VO.ProjectVO;

import java.util.List;

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
    int createWithGroup(String projectName, String info, List<String> managerList,
                                     int groupId,String creatorName);

    /**
     *
     * @param projectId
     * @param projectName
     * @param info
     * @param applicant
     * @return
     */
    boolean editProject(int projectId,String projectName, String info, String applicant);


    /**
     * 项目修改人员
     * @param userId
     * @return
     */
    boolean editMember(int userId);


    /**
     * 查看项目信息
     * @param projectId
     * @return
     */
    ProjectVO getGroupInfo(int projectId);
}
