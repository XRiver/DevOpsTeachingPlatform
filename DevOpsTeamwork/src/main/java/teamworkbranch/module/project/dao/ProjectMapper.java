package teamworkbranch.module.project.dao;

import teamworkbranch.module.project.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by liying on 2018/4/13.
 */
@Repository
public interface ProjectMapper {
    /**
     * 通过ID获取项目
     * @param id
     * @return Project
     */
    Project selectById(int id);

    /**
     * 插入项目
     * @param project
     * @return
     */
    int insertProject(Project project);

    /**
     * 删除项目
     * @param projectId
     * @return
     */
    int deleteProject(int projectId);

    /**
     * 更新项目
     * @param project
     * @return
     */
    int updateProject(Project project);

    /**
     * 获得最后插入的id
     *
     */
    int selectLastId();


    /**
     * 获得用户参与的所有项目
     * @return
     */
    List<Project> getProjectsByUser(String username);

}
