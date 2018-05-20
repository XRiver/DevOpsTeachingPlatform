package com.Service.Impl;

import com.Common.DefaultPath;
import com.DataVO.MyResponseData;
import com.DataVO.ReportVO;
import com.Entity.Report;
import com.Entity.TestEntity;
import com.Repository.TestRepository;
import com.Service.PipelineService;
import com.Service.TestService;
import com.util.FileSearch;
import com.util.ReportGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;

@Service
public class PipelineServiceImpl implements PipelineService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestService testService;

    @Override
    public MyResponseData<ReportVO> pipelineReport(String path, String projectId) {
        String src=DefaultPath.getHome()+ path;
        TestEntity test=testRepository.findByProject_id(projectId).get(0);
        Long testId=test.getId();
        String lan=FileSearch.getLanguage(src);
        Report report=new Report();
        if(lan.equals("java")){
           report=javaReport(src);
        }else  if(lan.equals("python")){
            report=pythonReport(src);
        }else if(lan.equals("c")){
            report=cReport(src);
        }else{
        }
        if (report!=null){
            boolean isSuccess=saveReport(report,testId,"pipeline");
            return new MyResponseData<ReportVO>("success", new String[]{"测试文件解析成功！"}, report.toReportVO());
        }else{
            return new MyResponseData<ReportVO>("failed", new String[]{"测试文件不存在！"}, null);
        }
    }


    @Override
    public Report javaReport(String src) {
        src+="/target/surefire-reports";
        File dir=new File(src);

        if (!dir.isDirectory()) {
            System.out.println("not a dir");
            Report report=new Report();
            report.setError_info("diretory error");
            return null;
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
            Report report=ReportGenerate.javaXmlReport(xmlList);
            return report;

        }

    }

    @Override
    public Report pythonReport(String src) {
        src+="/log.xml";
        File log=new File(src);
        if(!log.exists()){
            return null;
        }
        Report report=ReportGenerate.pythonXmlReport(log);
        return report;
    }

    @Override
    public Report cReport(String src) {
        Report report=new Report();
        File log=null;
        File dir=new File(src);
        if (!dir.isDirectory()) {
            System.out.println("not a dir");
            report.setError_info("diretory error");
            return null;
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
            if(xmlList==null||xmlList.length==0){
                return null;
            }
            log=xmlList[0];
            report= ReportGenerate.cXmlReport(log);
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

}
