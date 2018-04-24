package teamworkbranch.module.user.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.exception.DuplicateUserNameException;
import teamworkbranch.exception.InvalidUserNameException;
import teamworkbranch.exception.PasswordErrorException;
import teamworkbranch.module.user.dao.UserMapper;
import teamworkbranch.module.user.model.User;
import teamworkbranch.module.user.service.IdentificationService;
import teamworkbranch.module.user.vo.LoginInfo;


/**
 * Created by liying on 2018/4/23.
 */
@Service
@Transactional
public class IdentificationServiceImpl implements IdentificationService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean signUp(User user) throws DuplicateUserNameException {
        if (userMapper.selectByUserName(user.getUsername()) == null)
            userMapper.insertUser(user);
        else

            throw new DuplicateUserNameException();
        return true;
    }

    @Override
    public boolean logIn(LoginInfo loginInfo) throws PasswordErrorException, InvalidUserNameException {
        User user = userMapper.selectByUserName(loginInfo.getName());
        if (user == null)
            throw new InvalidUserNameException();
        if (!loginInfo.getPassword().equals(user.getPassword())) {
            throw new PasswordErrorException();
        }
        return true;
    }


}
