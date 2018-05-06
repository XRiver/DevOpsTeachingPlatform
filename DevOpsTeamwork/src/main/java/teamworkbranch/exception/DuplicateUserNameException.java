package teamworkbranch.exception;

/**
 * Created by liying on 2017/4/24.
 */
public class DuplicateUserNameException extends Exception{

    @Override
    public String getMessage(){


        return "该用户名已存在";
    }
}
