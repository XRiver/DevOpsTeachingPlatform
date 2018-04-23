package teamworkbranch.module.project.service;

import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.module.project.model.Project;

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
                        int groupId, String creatorName);

    /**
     *
     * @param projectId
     * @param projectName
     * @param info
     * @param applicant
     * @return
     */
    boolean editProject(int projectId, String projectName, String info, String applicant) throws NonprivilegedUserException;



    /**
     * 查看项目信息
     * @param projectId
     * @return
     */
    Project getProjectInfo(int projectId);

    /**
     * 查看用户所属项目
     * @param username
     * @return
     */
    List<Project> getProjectsByUser(String username);
}
