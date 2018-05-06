package teamworkbranch.module.user.service;

import teamworkbranch.exception.DuplicateUserNameException;
import teamworkbranch.exception.InvalidUserNameException;
import teamworkbranch.exception.PasswordErrorException;
import teamworkbranch.module.user.model.User;
import teamworkbranch.module.user.vo.LoginInfo;

import java.io.IOException;

/**
 * Created by liying on 2018/4/23.
 */
public interface IdentificationService {
    /**
     * 注册
     * @param user
     * @return
     * @throws DuplicateUserNameException
     */
    boolean signUp(User user) throws DuplicateUserNameException;

    /**
     * 登录
     * @param loginInfo
     * @return
     * @throws PasswordErrorException
     */
    boolean logIn(LoginInfo loginInfo) throws PasswordErrorException, InvalidUserNameException;


}

