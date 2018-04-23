package teamworkbranch.module.group.dao;

import org.springframework.stereotype.Repository;
import teamworkbranch.module.group.model.Group;

import java.util.List;

/**
 * Created by caosh on 2018/4/12.
 */
@Repository
public interface GroupMapper {
    /**
     * 通过ID获取团队
     * @param groupId
     * @return Group
     */
    Group selectById(int groupId);

    /**
     * 通过姓名获取所属团队
     * @param memberName
     * @return Group
     */
    List<Group> selectMyGroups(String memberName);

    /**
     * 新增团队
     * @param group
     * @return
     */
    int insertGroup(Group group);

    /**
     * 删除团队
     * @param groupId
     * @return
     */
    int deleteGroup(int groupId);

    /**
     * 更新团队
     * @param group
     * @return
     */
    int updateGroup(Group group);

    /**
     * 获得最后插入的id
     *
     */
    int selectLastId();
}
