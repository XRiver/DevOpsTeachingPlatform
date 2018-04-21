package teamworkbranch.module.group.service;

/**
 * Created by caosh on 2018/4/20.
 */
public interface GMemberService {

    /**
     * 团队增加成员
     * @param userId
     * @return
     */
    public boolean addMember(int userId);


    /**
     * 团队删除成员
     * @param userId
     * @return
     */
    public boolean removeMember(int userId);
}
