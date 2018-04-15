package com.util;

import com.Common.DefaultPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CloneManager {
    public static void main(String[] args){
        String s=cloneRepo("https://github.com/terminuskyuu/helloTest.git","master");
        System.out.println(s);
    }

    public static String cloneRepo(String url,String branch){
        String path=DefaultPath.getHome();
        int last=url.lastIndexOf("/");
        String name=url.substring(last+1);
        String dir=name.split("\\.git")[0];
        path=path+"/"+dir;

        String cmd="git clone "+"-b " +branch+" "+url+" "+path;

        try {
            Process process=Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",cmd});
            int exitValue=process.waitFor();


            if(0!=exitValue) {
                System.out.println("fail to call shell "+cmd+exitValue);
                return null;
            }else {
                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line="";
                while((line=br.readLine())!=null) {
                    System.out.println(line);
                }
                br.close();
                return path;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
