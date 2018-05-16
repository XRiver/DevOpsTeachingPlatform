package teamworkbranch.module.user.service;

import teamworkbranch.exception.InvalidUserNameException;
import teamworkbranch.module.user.model.User;

/**
 * Created by caosh on 2018/4/9.
 */
public interface UserService {



    /**
     * 查看个人信息
     * @param username
     * @return
     */
    public User getUserInfo(String username) throws InvalidUserNameException;


    /**
     * 修改个人信息
     * @param user
     * @return
     */
    public boolean editInfo(User user);


}
