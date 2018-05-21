package teamworkbranch.exception;

/**
 * Created by liying on 2017/4/24.
 */
public class JenkinsException extends Exception{

    String message;
    @Override
    public String getMessage(){


        return message;
    }

    public void setMessage(String message){
        this.message=message;
    }



}
