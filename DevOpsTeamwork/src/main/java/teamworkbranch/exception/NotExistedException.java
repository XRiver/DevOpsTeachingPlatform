package teamworkbranch.exception;

/**
 * Created by caosh on 2018/4/23.
 */
public class NotExistedException extends Exception {
    @Override
    public String getMessage(){

        return "不存在";

    }
}
