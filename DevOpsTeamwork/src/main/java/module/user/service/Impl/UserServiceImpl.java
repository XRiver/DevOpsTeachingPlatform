package module.user.service.Impl;

import module.VO.UserVO;
import module.user.service.UserService;

/**
 * Created by caosh on 2018/4/9.
 */
public class UserServiceImpl implements UserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public boolean logIn(String username, String password) {
        return false;
    }

    /**
     * 注册
     * @param username
     * @param password
     * @param role
     * @param email
     * @param name
     * @param userid
     * @return
     */
    public boolean signUp(String username, String password, int role, String email, String name, int userid) {
        return false;
    }

    /**
     * 修改密码
     * @param username
     * @param oldpassword
     * @param newpassword
     * @return
     */
    public boolean modifyPassword(String username, String oldpassword, String newpassword) {
        return false;
    }


    /**
     * 查看个人信息
     * @param username
     * @return
     */
    public UserVO getUserInfo(String username) {
        return null;
    }
}
