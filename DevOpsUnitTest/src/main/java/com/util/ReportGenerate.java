package com.util;

import com.Entity.FaultInfo;
import com.Entity.Report;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ReportGenerate {
    public static void main(String[] args){
        File log=new File("C:\\java\\py\\test\\log.xml");
        pythonXmlReport(log);

    }


    public  static Report javaXmlReport(File[] fileList){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Report report=new Report();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        report.setTime(sdf.format(d));

        int cases=0;
        int failures=0;
        int success=0;
        int error=0;

        for (File f:fileList) {
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
                Document document = builder.parse(f);
                Element rootElement = document.getDocumentElement();
                cases+=Integer.parseInt(rootElement.getAttribute("tests"));
                failures+=Integer.parseInt(rootElement.getAttribute("failures"));
                int failnum=Integer.parseInt(rootElement.getAttribute("failures"));
                String name=rootElement.getAttribute("name");
                error=Integer.parseInt(rootElement.getAttribute("errors"));

                if(failnum>0){
                    NodeList failList = rootElement.getElementsByTagName("failure");
                    for(int i=0;i<failnum;i++){
                        Element fail = (Element)failList.item(i);
                        FaultInfo faultInfo=new FaultInfo();
                        faultInfo.setCase_name(name);
                        String funcname=((Element)fail.getParentNode()).getAttribute("name");
                        faultInfo.setFunc_name(funcname);
                        String type=fail.getAttribute("type");
                        if(fail.getAttribute("message")!=null){
                            type=type+"  "+fail.getAttribute("message");
                        }
                        faultInfo.setType(type);
                        String value=fail.getFirstChild().getNodeValue();
                        int line=0;
                        int start=value.indexOf(funcname);
                        int end=value.indexOf(")",start);
                        value=value.substring(start,end);
                        value=value.split(":")[1];
                        line=Integer.parseInt(value);
                        faultInfo.setLine(line);
                        report.addFault_info(faultInfo);

                        System.out.println(name+" "+funcname+" "+line+" "+type);

                    }


                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        success=cases-failures;
        report.setCase_num(cases);
        report.setFail_num(failures);
        report.setSucess_num(success);
        report.setError_info("");
        if(cases==0){
            report.setError_info("result do not exist");
        }

        return report;
    }

    public  static Report pythonXmlReport(File file){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Report report=new Report();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        report.setTime(sdf.format(d));

        int cases=0;
        int failures=0;
        int success=0;
        int error=0;

        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Element rootElement = document.getDocumentElement();
            cases=Integer.parseInt(rootElement.getAttribute("tests"));
            failures=Integer.parseInt(rootElement.getAttribute("failures"));
            int failnum=Integer.parseInt(rootElement.getAttribute("failures"));
            error=Integer.parseInt(rootElement.getAttribute("errors"));

            if(failnum>0){
                NodeList failList = rootElement.getElementsByTagName("testcase");
                for(int i=0;i<failList.getLength();i++){
                    Element fail = (Element)failList.item(i);
                    FaultInfo faultInfo=new FaultInfo();
                    String name=fail.getAttribute("file");
                    faultInfo.setCase_name(name);
                    String funcname=fail.getAttribute("name");
                    faultInfo.setFunc_name(funcname);
                    int line=Integer.parseInt(fail.getAttribute("line"));
                    faultInfo.setLine(line);
                    Element failure=(Element) fail.getFirstChild();
                    if(failure!=null){
                        String type=failure.getAttribute("message");
                        int index=type.toLowerCase().lastIndexOf("use");
                        if(index>0){
                            type=type.substring(0,index);
                        }

                        faultInfo.setType(type);
                        report.addFault_info(faultInfo);

                        System.out.println(name+" "+funcname+" "+line+" "+type);
                    }

                }

            }
            if(error>0){
                NodeList errorList = rootElement.getElementsByTagName("error");
                Element errorTemp=(Element) errorList.item(0);
                String errorInfo=errorTemp.getAttribute("message");
                errorInfo+="\n"+errorTemp.getFirstChild().getNodeValue();
                report.setError_info(errorInfo);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        success=cases-failures;
        report.setCase_num(cases);
        report.setFail_num(failures);
        report.setSucess_num(success);
        report.setError_info("");
        if(cases==0) {
            report.setError_info("result do not exist");
        }

        return report;
    }

    public static Report cXmlReport(File file){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Report report=new Report();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        report.setTime(sdf.format(d));
        int cases=0;
        int failures=0;
        int success=0;
        int error=0;

        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Element rootElement = document.getDocumentElement();

            NodeList sumList = rootElement.getElementsByTagName("CUNIT_RUN_SUMMARY_RECORD");
            Element caseSum = (Element)sumList.item(1);
            String temp=caseSum.getElementsByTagName("RUN").item(0).getFirstChild().getNodeValue().trim();
            System.out.println(temp);
            cases=Integer.parseInt(temp);
            temp=caseSum.getElementsByTagName("FAILED").item(0).getFirstChild().getNodeValue().trim();
            failures=Integer.parseInt(temp);

            if(failures>0){
                NodeList failList = rootElement.getElementsByTagName("CUNIT_RUN_TEST_FAILURE");
                for(int i=0;i<failList.getLength();i++){
                    Element fail = (Element)failList.item(i);
                    FaultInfo faultInfo=new FaultInfo();
                    String name=fail.getElementsByTagName("FILE_NAME").item(0).getFirstChild().getNodeValue().trim();
                    faultInfo.setCase_name(name);
                    String funcname=fail.getElementsByTagName("TEST_NAME").item(0).getFirstChild().getNodeValue().trim();
                    faultInfo.setFunc_name(funcname);
                    int line=Integer.parseInt(fail.getElementsByTagName("LINE_NUMBER").item(0).getFirstChild().getNodeValue().trim());
                    faultInfo.setLine(line);
                    String failure=fail.getElementsByTagName("CONDITION").item(0).getFirstChild().getNodeValue().trim();
                    if(failure!=null){
                        faultInfo.setType(failure);
                        report.addFault_info(faultInfo);

                        System.out.println(name+" "+funcname+" "+line+" "+failure);
                    }

                }

            }
            if(error>0){
                NodeList errorList = rootElement.getElementsByTagName("error");
                Element errorTemp=(Element) errorList.item(0);
                String errorInfo=errorTemp.getAttribute("message");
                errorInfo+="\n"+errorTemp.getFirstChild().getNodeValue();
                report.setError_info(errorInfo);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        success=cases-failures;
        report.setCase_num(cases);
        report.setFail_num(failures);
        report.setSucess_num(success);
        report.setError_info("");
        if(cases==0) {
            report.setError_info("result do not exist");
        }

        return report;
    }
}
