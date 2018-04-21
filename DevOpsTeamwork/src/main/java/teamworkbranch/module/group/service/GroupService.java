package teamworkbranch.module.group.service;

import teamworkbranch.module.entity.VO.GroupVO;
import teamworkbranch.module.entity.VO.UserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caosh on 2018/4/9.
 */
public interface GroupService {

    /**
     * 创建团队
     * @param groupName
     * @param info
     * @param creatorName
     * @param memberList
     * @return
     */
    public int createGroup(String groupName, String info, String creatorName, List<String> memberList);


    /**
     * 删除团队
     * @param groupId
     * @return
     */
    public boolean deleteGroup(int groupId);


    /**
     * 编辑团队信息
     * @param name
     * @param info
     * @param groupId
     * @return
     */
    public boolean editGroup(String name,String info,int groupId);





    /**
     * 查看团队信息
     * @param groupId
     * @return
     */
    public GroupVO getGroupInfo(int groupId);


    /**
     * 查看团队成员
     * @param groupId
     * @return
     */
    public ArrayList<UserVO> getMemberList(int groupId);


    /**
     * 查看所属团队
     * @param userId
     * @return
     */
    public ArrayList<GroupVO> getGroupList(int userId);





}
