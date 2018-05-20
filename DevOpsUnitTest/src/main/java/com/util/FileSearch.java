package com.util;

import com.Common.Language;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {
    public static void main(String[] args){
        String s="C:\\java\\py";
        //List<String> fileList=getAllFiles(".py","",s,"");
       // for(String temp:fileList){
       //     System.out.println(temp);
       // }
        System.out.println(getLanguage(s));


    }


    public static List<String> getAllFiles(String postfix, String content, String path,String pre){
        List<String> fileList=new ArrayList<String>();
        File dir=new File(path);
        File[] allList = dir.listFiles();
        for(File f:allList){
            if(f.isDirectory()){
                if(pre!=null&&!pre.equals("")){
                    List<String> tempList=getAllFiles(postfix,content,f.getPath(),pre+"/"+f.getName());
                    for(String s:tempList){
                        fileList.add(s);
                    }
                }else{
                    List<String> tempList=getAllFiles(postfix,content,f.getPath(),f.getName());
                    for(String s:tempList){
                        fileList.add(s);
                    }
                }

            }

            if(f.isFile() && f.getName().endsWith(postfix)&&f.getName().toLowerCase().contains(content)){
                if(pre!=null&&!pre.equals("")){
                    fileList.add(pre+"/"+f.getName());
                }else{
                    fileList.add(f.getName());
                }

            }

        }
        return fileList;
    }


    public static String getLanguage(String path){
        if(!getAllFiles(".java","",path,"").isEmpty()){
            return Language.java.toString();
        }else if(!getAllFiles(".py","",path,"").isEmpty()){
            return Language.python.toString();
        }else if(!getAllFiles(".c","",path,"").isEmpty()){
            return Language.c.toString();
        }else{
            return Language.other.toString();
        }


    }

}
