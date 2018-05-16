package edu.nju.api;

import edu.nju.config.ConfigBean;
import edu.nju.config.LogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/30.
 */
public class Util {

    @Autowired
    static ConfigBean configBean;

//    static String server ="http://119.29.157.178/api/v4";
    static String server ="http://139.219.66.203/api/v4";

//    static String key = "8Ug1y1avCtRRVJDFqvus";
    static String key = "2jS4Q4V3XHj4mgXHcJEo";
//    ${configBean}
//    static String remoteaddress;
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return String 所代表远程资源的响应结果
     */
    public static String get(String url,String param)
    {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = null;

            if(param == null)
                //urlNameString = configBean.getRemoteaddress()+url;
                //http://119.29.157.178/api/v4
                //139.219.66.203
                //2jS4Q4V3XHj4mgXHcJEo
                urlNameString =server+url;
            else
                urlNameString = server+url + "?" + param;

            //System.out.println("curl http url : " + urlNameString);
            LogBean.log("GET : "+urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            // 设置通用的请求属性
            connection.setRequestProperty("connection","close");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //yseqMqqyo9mBEaiFzmf3
            connection.setRequestProperty("Private-Token",key);
            // 建立实际的连接
            connection.connect();

            /*
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet())
            {
                System.out.println(key + "--->" + map.get(key));
            }
            */

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        } catch (Exception e) {
            LogBean.log("发送 GET请求出现异常！"+e);
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        LogBean.log("get result: " +result);
        return result.equals("") ? null : result;
    }

        public static String post(String url, Map<String,String> paramMap) {
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
                URL realUrl = new URL(server+url);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                conn.setRequestProperty("Private-Token",key);

                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                String param = "";
                if (paramMap != null && paramMap.size() > 0) {
                    Iterator<String> ite = paramMap.keySet().iterator();
                    while (ite.hasNext()) {
                        String key = ite.next();// key
                        String value = paramMap.get(key);
                        param += key + "=" + value + "&";
                    }
                    param = param.substring(0, param.length() - 1);
                }
                System.out.println("param:"+param);
                LogBean.log("POST : "+param);
                out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送 POST 请求出现异常！"+e);
                LogBean.log("发送 POST 请求出现异常！"+e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally{
                try{
                    if(out!=null){
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            System.out.println("result: "  +result);
            LogBean.log("post result: " +result);
            return result;
        }

    public static String put(String urlStr,Map<String,String> paramMap) {
        //GlobalConfig global = GlobalConfig.getInstance();
        //String urlStr = global.sncHost + "/devices/1/acl/aclGroups/aclGroup";

        //create xml info pushed to controller
        //String xmlInfo = createXmlInfo(dst_ip);

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        //establish connection and push policy to snc controller
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //OutputStream os = conn.getOutputStream();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //os.write(xmlInfo.getBytes("utf-8"));
            // 发送请求参数
            String param = "";
            if (paramMap != null && paramMap.size() > 0) {
                Iterator<String> ite = paramMap.keySet().iterator();
                while (ite.hasNext()) {
                    String key = ite.next();// key
                    String value = paramMap.get(key);
                    param += key + "=" + value + "&";
                }
                param = param.substring(0, param.length() - 1);
            }
            System.out.println("param:"+param);
            LogBean.log("PUT : "+param);
            out.print(param);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while( (line =br.readLine()) != null ){
                result += line;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("发送 PUT 请求出现异常！"+e);
            LogBean.log("发送 PUT 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("result: "  +result);
        LogBean.log("put result: " +result);
        return result;
    }
}
