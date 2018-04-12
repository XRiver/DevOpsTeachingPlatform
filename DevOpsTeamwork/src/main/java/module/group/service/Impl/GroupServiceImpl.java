package module.group.service.Impl;

import module.entity.VO.GroupVO;
import module.entity.VO.UserVO;
import module.group.GroupService;

import java.util.ArrayList;

/**
 * Created by caosh on 2018/4/9.
 */
public class GroupServiceImpl implements GroupService {
    /**
     * 创建团队
     * @param groupName
     * @param info
     * @param memberList
     * @param managerList
     * @param creatorId
     * @return
     */
    public GroupVO createGroup(String groupName, String info, ArrayList<Integer> memberList
            , ArrayList<Integer> managerList, int creatorId) {
        return null;
    }

    /**
     * 删除团队
     * @param groupId
     * @return
     */
    public boolean deleteGroup(int groupId) {
        return false;
    }

    /**
     * 编辑团队信息
     * @param groupVO
     * @return
     */
    public boolean editGroup(GroupVO groupVO) {
        return false;
    }

    /**
     * 团队修改人员
     * @param userId
     * @return
     */
    public boolean editMember(int userId) {
        return false;
    }

    /**
     * 查看团队信息
     * @param groupId
     * @return
     */
    public GroupVO getGroupInfo(int groupId) {
        return null;
    }

    /**
     * 查看团队成员
     * @param groupId
     * @return
     */
    public ArrayList<UserVO> getMemberList(int groupId) {
        return null;
    }

    /**
     * 查看所属团队
     * @param userId
     * @return
     */
    public ArrayList<GroupVO> getGroupList(int userId) {
        return null;
    }
}
