package teamworkbranch.exception;

/**
 * Created by liying on 2017/4/24.
 */
public class PasswordErrorException extends Exception{
    @Override
    public String getMessage(){

        return "密码错误";
    }
}
