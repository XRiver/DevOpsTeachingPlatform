package teamworkbranch.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liying on 2018/5/16.
 */

@Component
public class GitlabInvoker {

    private final static String GITLAB_URL_PREFIX = "http://139.219.66.203:8762/";
    public String initialProject(int groupId,int projectId,String projectName,String description) throws Exception {
        String query="groupid="+groupId+"&projectid="+projectId+"&name="+projectName+"&description="+description;
        URL restURL = new URL(GITLAB_URL_PREFIX+"project/newproject");
        /*
         * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类 的子类HttpURLConnection
         */
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        //请求方式
        conn.setRequestMethod("POST");
        //设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
        conn.setDoOutput(true);
        //allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
        conn.setAllowUserInteraction(false);

        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(query);

        ps.close();

        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line,resultStr="";

        while(null != (line=bReader.readLine()))
        {
            resultStr +=line;
        }
        bReader.close();
        conn.disconnect();

        return resultStr;

    }
    public String initialUser(String id,String name,String username,String password,String email) throws Exception {
        String query="id="+id+"&name="+name+"&username="+username+"&password="+password+"&email="+email;
        URL restURL = new URL(GITLAB_URL_PREFIX+"user/newuser");
        /*
         * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类 的子类HttpURLConnection
         */
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        //请求方式
        conn.setRequestMethod("POST");
        //设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
        conn.setDoOutput(true);
        //allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
        conn.setAllowUserInteraction(false);

        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(query);

        ps.close();

        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line,resultStr="";

        while(null != (line=bReader.readLine()))
        {
            resultStr +=line;
        }
        System.out.println(resultStr);
        bReader.close();
        conn.disconnect();

        return resultStr;

    }

}
