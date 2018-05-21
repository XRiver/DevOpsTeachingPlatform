package teamworkbranch.exception;

/**
 * Created by liying on 2017/4/24.
 */
public class InvalidUserNameException extends Exception{
    @Override
    public String getMessage(){


        return "用户名不存在";
    }
}
