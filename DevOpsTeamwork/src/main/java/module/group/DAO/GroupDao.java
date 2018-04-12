package module.group.DAO;

import module.entity.PO.GroupPO;

/**
 * Created by caosh on 2018/4/12.
 */
public interface GroupDao {

    public boolean addGroup(GroupPO groupPO);

    public boolean deleteGroup(int groupId);

    public boolean modifyGroup(GroupPO groupPO);

    public GroupPO getGroup(int groupId);
}
