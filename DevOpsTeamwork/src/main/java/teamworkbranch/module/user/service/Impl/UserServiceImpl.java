package teamworkbranch.module.user.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.exception.InvalidUserNameException;
import teamworkbranch.module.entity.VO.UserVO;
import teamworkbranch.module.user.dao.UserMapper;
import teamworkbranch.module.user.model.User;
import teamworkbranch.module.user.service.UserService;

/**
 * Created by caosh on 2018/4/9.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 查看个人信息
     * @param username
     * @return
     */
    public User getUserInfo(String username) throws InvalidUserNameException {
        User user=userMapper.selectByUserName(username);
        if(user==null){
            throw new InvalidUserNameException();
        }
        return user;

    }

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    public boolean editInfo(User user) {
        userMapper.updateUser(user);
        return true;
    }
}
