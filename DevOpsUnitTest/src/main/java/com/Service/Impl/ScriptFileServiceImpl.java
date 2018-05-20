package com.Service.Impl;

import com.Entity.TestEntity;
import com.Repository.TestRepository;
import com.Service.ApiCallService;
import com.Service.ScriptFileService;
import com.util.FileSearch;
import com.util.ScriptGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptFileServiceImpl implements ScriptFileService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ApiCallService apiCallService;


    public boolean uploadScript(String path,long testId){
        TestEntity test=testRepository.findById(testId);
        String src=test.getSrc();
        String projectId=test.getProject_id();
        String lan=test.getLanguage();
        String branch=test.getBranch();
        String content="";

        if(lan.equals("java")){
            content= ScriptGenerate.javashAll();
            String response=apiCallService.uploadFile(projectId,src+"/exectest.sh",branch,content);
            System.out.println(response);
        }else  if(lan.equals("python")){
            List<String> files=getAllFiles(".py","test",src,path);
            content= ScriptGenerate.pythonsh(files);
            String response=apiCallService.uploadFile(projectId,src+"/exectest.sh",branch,content);
            System.out.println(response);
        }else if(lan.equals("c")){
            List<String> files=getAllFiles(".c","",src,path);
            content= ScriptGenerate.cmakefile(files);
            String response=apiCallService.uploadFile(projectId,src+"/makefile",branch,content);
            System.out.println(response);
            content="make \n ./test \n";
            response=apiCallService.uploadFile(projectId,src+"/exectest.sh",branch,content);
            System.out.println(response);
        }else{
            return false;
        }

        return true;
    }

    public boolean pipelineScript(String group,String project){
        /**
        TestEntity test=testRepository.findByProject_id(project).get(0);
        String src=test.getSrc();

        String lan=test.getLanguage();
         */
        String lan=FileSearch.getLanguage("/project/"+group+"/"+project);

        String projectId=project;
        String branch="master";
        String content="";

        if(lan.equals("java")){
            content= ScriptGenerate.javashPipeline(group,project);
            String response=apiCallService.uploadFile(projectId,"/test.sh",branch,content);
            System.out.println(response);
        }else  if(lan.equals("python")){
            List<String> files=FileSearch.getAllFiles(".py","test","/project/"+group+"/"+project,"");
            content= ScriptGenerate.pyshPipeline(group,project,files);
            String response=apiCallService.uploadFile(projectId,"/test.sh",branch,content);
            System.out.println(response);
        }else if(lan.equals("c")){
            List<String> files=FileSearch.getAllFiles(".c","","/project/"+group+"/"+project,"");
            String makecontent= ScriptGenerate.cmakefile(files);
            String response=apiCallService.uploadFile(projectId,"/makefile",branch,makecontent);
            System.out.println(response);
            content=ScriptGenerate.cshPipeline(group,project);
            response=apiCallService.uploadFile(projectId,"/test.sh",branch,content);
            System.out.println(response);
        }else{
            return false;
        }

        return true;
    }




    private static File createFile(String content,String path){
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }


    private static List<String> getAllFiles(String postfix,String contain,String src, String path){
        String testPath=path+"/"+src;
        File dir=new File(testPath);
        if (!dir.isDirectory()) {
            System.out.println("not a dir");
            return null;
        } else {
            /**
            // 内部匿名类，用来过滤文件类型
            File[] pyList = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().endsWith(postfix) && file.getName().toLowerCase().contains(contain)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            List<String> files = new ArrayList<String>();
            for (int i = 0; i < pyList.length; i++) {
                files.add(pyList[i].getName());
            }
             return files;
             */
            return FileSearch.getAllFiles(postfix,contain,testPath,"");
        }
    }

}
