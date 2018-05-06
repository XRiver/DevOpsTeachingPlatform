package teamworkbranch.exception;

/**
 * Created by liying on 2018/4/22.
 */
public class ExistedException extends Exception {
    @Override
    public String getMessage(){

        return "已存在";

    }
}
