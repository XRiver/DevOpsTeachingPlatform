package teamworkbranch.exception;

/**
 * Created by liying on 2018/4/21.
 */
public class NonprivilegedUserException extends Exception  {
    @Override
    public String getMessage(){

        return "该用户无权限";

    }

}
