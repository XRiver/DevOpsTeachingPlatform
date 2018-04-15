package com.Service.Impl;

import com.Entity.TestEntity;
import com.Feignclient.FileService;
import com.Repository.TestRepository;
import com.Service.ScriptFileService;
import com.util.ScriptGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptFileServiceImpl implements ScriptFileService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private FileService fileService;


    public boolean uploadScript(long testId){
        TestEntity test=testRepository.findById(testId);
        String src=test.getSrc();
        String projectId=test.getProject_id();
        List<String> fileList=fileService.getAllPath(projectId);
        String lan=test.getLanguage();
        String branch=test.getBranch();
        String content="";

        if(lan.equals("java")){
            content= ScriptGenerate.javashAll();
            fileService.uploadFile(projectId,src+"/exectest.sh",branch,content);
        }else  if(lan.equals("python")){
            List<String> files=getAllFiles(".py","test",src,fileList);
            content= ScriptGenerate.pythonsh(files);
            fileService.uploadFile(projectId,src+"/exectest.sh",branch,content);
        }else if(lan.equals("c")){
            List<String> files=getAllFiles(".c","",src,fileList);
            content= ScriptGenerate.cmakefile(files);
            fileService.uploadFile(projectId,src+"/makefile",branch,content);
            content="make \n ./test \n";
            fileService.uploadFile(projectId,src+"/exectest.sh",branch,content);
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


    private static List<String> getAllFiles(String postfix,String contain,String src, List<String>files){
        int len=src.length();
        if(src.endsWith("/")){

        }else{
            len++;
        }
        List<String> fileList=new ArrayList<String>();
        for(String s:files){
            if(s.contains(src)&&s.endsWith(postfix)&&s.contains(contain)) {
                fileList.add(s.substring(len));
            }

        }
        return fileList;
    }

}
