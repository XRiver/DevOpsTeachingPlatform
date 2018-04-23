package teamworkbranch.module.group.service;

import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.group.model.GMember;
import teamworkbranch.module.group.model.Group;

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
     * @param memberName
     * @return
     */
    public boolean deleteGroup(int groupId,String memberName) throws NotExistedException, NonprivilegedUserException;


    /**
     * 编辑团队信息
     * @param name
     * @param info
     * @param groupId
     * @param memberName
     * @return
     */
    public boolean editGroup(String name,String info,int groupId,String memberName) throws NotExistedException, NonprivilegedUserException;





    /**
     * 查看团队信息
     * @param groupId
     * @return
     */
    public Group getGroupInfo(int groupId);


    /**
     * 查看团队成员
     * @param groupId
     * @return
     */
    public List<GMember> getMemberList(int groupId);


    /**
     * 查看所属团队
     * @param memberName
     * @return
     */
    public List<Group> getGroupList(String memberName);





}
