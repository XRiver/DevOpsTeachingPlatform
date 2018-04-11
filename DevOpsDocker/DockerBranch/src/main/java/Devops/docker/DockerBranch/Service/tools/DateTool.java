package Devops.docker.DockerBranch.Service.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

    public static String getTodayDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static String getTimeNow(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

}
