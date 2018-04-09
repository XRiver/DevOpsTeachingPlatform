package module.user.service;

import module.user.vo.UserVO;

/**
 * Created by caosh on 2018/4/9.
 */
public class UserServiceImpl implements UserService{
    public boolean logIn(String username, String password) {
        return false;
    }

    public boolean signUp(String username, String password, int role, String email, String name, int userid) {
        return false;
    }

    public boolean modifyPassword(String username, String oldpassword, String newpassword) {
        return false;
    }

    public UserVO getUserInfo(String username) {
        return null;
    }
}
