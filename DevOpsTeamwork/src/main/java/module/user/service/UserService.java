package module.user.service;

import module.group.model.VO.UserVO;

/**
 * Created by caosh on 2018/4/9.
 */
public interface UserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public boolean logIn(String username,String password);


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
    public boolean signUp(String username,String password,int role,String email,String name,int userid);


    /**
     * 修改密码
     * @param username
     * @param oldpassword
     * @param newpassword
     * @return
     */
    public boolean modifyPassword(String username,String oldpassword,String newpassword);


    /**
     * 查看个人信息
     * @param username
     * @return
     */
    public UserVO getUserInfo(String username);


    /**
     * 修改个人信息
     * @param userVO
     * @return
     */
    public boolean editInfo(UserVO userVO);


}
