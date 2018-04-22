package com.Service.Impl;

import com.DataVO.ReportVO;
import com.Entity.Report;
import com.Entity.TestEntity;
import com.Repository.TestRepository;
import com.Service.TestExecuteService;
import com.util.FileSearch;
import com.util.ReportGenerate;
import com.util.ScriptGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
@Service
public class TestExecuteServiceImpl implements TestExecuteService{
    @Autowired
    private TestRepository testRepository;


    @Override
    public ReportVO javaTestAll(String path,Long testId ,String username) {
        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src=testRepository.findById(testId).getSrc();
        String testPath=path+"/"+src;
        String shPath=testPath+"/exectest.sh";
        File shfile = new File(shPath);
        if(!shfile.exists()){
            shfile.getParentFile().mkdirs();
        }
        try {
            shfile.createNewFile();
            FileWriter fw = new FileWriter(shfile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ScriptGenerate.javashAll());
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        command="cd "+testPath+" && sh ./exectest.sh ";
        String[] commands=new String[]{"/bin/sh","-c",command};
        String line = null;
        String out="";
        try {
            Process process = runtime.exec(commands);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));

            while ((line = bufferedReader.readLine()) != null) {
                out=out+line + "\n";
            }
            process.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(out);

        Report report=javaReport(testPath);
        boolean isSuccess=saveReport(report,testId,username);

        return report.toReportVO();
    }

    @Override
    public ReportVO javaTest(String path,List<String> file, Long testId ,String username) {
        if(file.isEmpty()){
            return null;
        }

        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src=testRepository.findById(testId).getSrc();

        String testPath=path+"/"+src;
        String shPath=testPath+"/exectest.sh";
        File shfile = new File(shPath);
        if(!shfile.exists()){
            shfile.getParentFile().mkdirs();
        }
        try {
            shfile.createNewFile();
            FileWriter fw = new FileWriter(shfile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ScriptGenerate.javash(file));
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        command="cd "+testPath+" && sh ./exectest.sh ";
        String[] commands=new String[]{"/bin/sh","-c",command};
        String line = null;
        String out="";
        try {
            Process process = runtime.exec(commands);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));

            while ((line = bufferedReader.readLine()) != null) {
                out=out+line + "\n";
            }
            process.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(out);

        Report report=javaReport(testPath);
        boolean isSuccess=saveReport(report,testId,username);

        return report.toReportVO();
    }

    @Override
    public ReportVO pythonTest(String path,List<String> file, Long testId ,String username) {
        if(file.isEmpty()){
            return null;
        }

        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src=testRepository.findById(testId).getSrc();

        String testPath=path+"/"+src;
        String shPath=testPath+"/exectest.sh";
        File shfile = new File(shPath);
        if(!shfile.exists()){
            shfile.getParentFile().mkdirs();
        }
        try {
            shfile.createNewFile();
            FileWriter fw = new FileWriter(shfile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ScriptGenerate.pythonsh(file));
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        command="cd "+testPath+" && sh ./exectest.sh ";
        String[] commands=new String[]{"/bin/sh","-c",command};
        String line = null;
        String out="";
        try {
            Process process = runtime.exec(commands);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));

            while ((line = bufferedReader.readLine()) != null) {
                out=out+line + "\n";
            }
            process.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(out);

        Report report=pythonReport(testPath);
        boolean isSuccess=saveReport(report,testId,username);

        return report.toReportVO();
    }

    @Override
    public ReportVO pythonTestAll(String path,Long testId ,String username) {
        String src=testRepository.findById(testId).getSrc();
        String testPath=path+"/"+src;
        File dir=new File(testPath);
        if (!dir.isDirectory()) {
            System.out.println("not a dir");
            Report report=new Report();
            report.setError_info("diretory error");
            return report.toReportVO();
        } else {
            /**
            // 内部匿名类，用来过滤文件类型
            File[] pyList = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().endsWith(".py")&& file.getName().toLowerCase().contains("test")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            List<String> files=new ArrayList<String>();
            for(int i=0;i<pyList.length;i++){
                files.add(pyList[i].getName());
            }

             */
            List<String> files=FileSearch.getAllFiles(".py","test",testPath,"");
            return pythonTest(path,files,testId,username);

        }


    }

    @Override
    public ReportVO cTest(String path,List<String> file, Long testId ,String username) {
        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src=testRepository.findById(testId).getSrc();

        String testPath=path+"/"+src;
        String makePath=testPath+"/makefile";
        File makefile = new File(makePath);
        if(!makefile.exists()){
            makefile.getParentFile().mkdirs();
        }
        try {
            makefile.createNewFile();
            FileWriter fw = new FileWriter(makefile, false);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(ScriptGenerate.cmakefile(file));
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String shPath=testPath+"/exectest.sh";
        File shfile = new File(shPath);
        if(!shfile.exists()){
            shfile.getParentFile().mkdirs();
        }
        try {
            shfile.createNewFile();
            FileWriter fw = new FileWriter(shfile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ScriptGenerate.csh(testPath));
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        command="cd "+testPath+" && sh ./exectest.sh ";
        String[] commands=new String[]{"/bin/sh","-c",command};
        String line = null;
        String out="";
        try {
            Process process = runtime.exec(commands);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));

            while ((line = bufferedReader.readLine()) != null) {
                out=out+line + "\n";
            }
            process.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(out);

        Report report=cReport(testPath);
        boolean isSuccess=saveReport(report,testId,username);

        return report.toReportVO();
    }

    private Report javaReport(String src){
        src+="/target/surefire-reports";
        File dir=new File(src);

        if (!dir.isDirectory()) {
            System.out.println("not a dir");
            Report report=new Report();
            report.setError_info("diretory error");
            return report;
        } else {
            // 内部匿名类，用来过滤文件类型
            File[] xmlList = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().endsWith(".xml")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            return ReportGenerate.javaXmlReport(xmlList);

        }


    }

    @Override
    public ReportVO cTestAll(String path,Long testId ,String username) {
        String src=testRepository.findById(testId).getSrc();
        String testPath=path+"/"+src;
        File dir=new File(testPath);
        if (!dir.isDirectory()) {
            System.out.println("not a dir");
            Report report=new Report();
            report.setError_info("diretory error");
            return report.toReportVO();
        } else {
            /**
            // 内部匿名类，用来过滤文件类型
            File[] pyList = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().endsWith(".c")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            List<String> files=new ArrayList<String>();
            for(int i=0;i<pyList.length;i++){
                files.add(pyList[i].getName());
            }
            */
            List<String> files=FileSearch.getAllFiles(".c","",testPath,"");

            return cTest(path,files,testId,username);

        }


    }

    private Report pythonReport(String src){
        src+="/log.xml";
        File log=new File(src);
        return ReportGenerate.pythonXmlReport(log);
    }

    private Report cReport(String src){
        Report report=new Report();
        File log=null;
        File dir=new File(src);
        if (!dir.isDirectory()) {
            System.out.println("not a dir");

            report.setError_info("diretory error");
        } else {
            // 内部匿名类，用来过滤文件类型
            File[] xmlList = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().endsWith("-Results.xml")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            log=xmlList[0];
            report= ReportGenerate.cXmlReport(log);
        }
        if(log==null){
            report.setError_info("no file");
        }

        return report;
    }

    private boolean saveReport(Report report, Long testId ,String username){
        if(report==null||report.getCase_num()==0){
            return  false;
        }

        TestEntity testEntity=testRepository.findById(testId);
        testEntity.setLatest_time(report.getTime());
        testEntity.setPerform_times(testEntity.getPerform_times()+1);
        testEntity.setLatest_person(username);
        testEntity.addReports(report);
        testRepository.saveAndFlush(testEntity);
        return true;
    }

    private boolean clearFile(){ //清空报告文件

        return false;
    }



}
