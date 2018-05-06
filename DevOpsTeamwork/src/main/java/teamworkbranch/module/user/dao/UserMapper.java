package teamworkbranch.module.user.dao;

import org.springframework.stereotype.Repository;
import teamworkbranch.module.project.model.Project;
import teamworkbranch.module.user.model.User;

import java.util.List;


/**
 * Created by liying on 2018/4/13.
 */
@Repository
public interface UserMapper {
    /**
     * 通过username获取用户
     * @param username
     * @return Project
     */
    User selectByUserName(String username);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 删除用户
     * @param username
     * @return
     */
    int deleteUser(String username);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);


}
