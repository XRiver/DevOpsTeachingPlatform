package module.project.dao;

import module.project.model.Project;
import org.springframework.data.convert.ReadingConverter;
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

}
