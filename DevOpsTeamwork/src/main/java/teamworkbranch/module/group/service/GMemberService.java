package teamworkbranch.module.group.service;

import teamworkbranch.exception.ExistedException;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.NotExistedException;

/**
 * Created by caosh on 2018/4/20.
 */
public interface GMemberService {

    /**
     * 团队增加成员
     * @param groupId
     * @param userName
     * @param is_manager
     * @return
     */
    public boolean addMember(int groupId,String userName,int is_manager,String memberName) throws ExistedException, NonprivilegedUserException;


    /**
     * 团队删除成员
     * @param userName
     * @param groupId
     * @return
     */
    public boolean removeMember(int groupId,String userName,String memberName) throws NotExistedException, NonprivilegedUserException;

    /**
     * 修改团队成员管理权限
     * @param groupId
     * @param userName
     * @param is_manager
     * @return
     */
    public boolean editMember(int groupId,String userName,int is_manager,String memberName) throws NotExistedException, NonprivilegedUserException;


    /**
     * 修改团队成员管理权限
     * @param groupId
     * @param userName
     * @return
     */
    public boolean leaveGroup(int groupId,String userName) throws  NotExistedException;
}
