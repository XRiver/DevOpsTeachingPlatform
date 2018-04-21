package teamworkbranch.module.group.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import teamworkbranch.module.group.model.Group;

/**
 * Created by caosh on 2018/4/12.
 */
@Repository
public interface GroupMapper {
    /**
     * 通过ID获取团队
     * @param id
     * @return Group
     */
    Group selectById(int id);

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
     * @param name
     * @param info
     * @param groupId
     * @return
     */
    int updateGroup(@Param("name") String name, @Param("info") String info,@Param("groupId") int groupId);

    /**
     * 获得最后插入的id
     *
     */
    int selectLastId();
}
