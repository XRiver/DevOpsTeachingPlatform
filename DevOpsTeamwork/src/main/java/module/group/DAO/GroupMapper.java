package module.group.DAO;

import module.model.Group;
import org.springframework.stereotype.Repository;

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
     * 插入团队
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
