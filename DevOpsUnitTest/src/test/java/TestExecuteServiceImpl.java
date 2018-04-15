

import com.DataVO.ReportVO;
import com.Entity.FaultInfo;
import com.Entity.Report;
import com.Entity.TestEntity;
import com.Repository.TestRepository;
import com.Service.TestExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
@Service
public class TestExecuteServiceImpl {
    @Autowired
    private TestRepository testRepository;


    public ReportVO javaTestAll(Long testId ,String username) {
        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src=testRepository.findById(testId).getSrc();
        String disk=src.split(":")[0];

        command="cmd /c  "+disk+": && cd "+src+" && call mvn test ";

        String line = null;
        String out="";
        try {
            Process process = runtime.exec(command);
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

        Report report=javaReport(src);
        boolean isSuccess=saveReport(report,testId,username);

        return report.toReportVO();
    }


    public ReportVO javaTest(List<String> file, Long testId ,String username) {
        if(file.isEmpty()){
            return null;
        }

        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src=testRepository.findById(testId).getSrc();
        String disk=src.split(":")[0];

        command="cmd /c "+disk+": && cd "+src+" && call mvn -Dtest=";
        for(String s:file){
            command=command+s+",";
        }
        command=command.substring(0,command.lastIndexOf(','));
        command+=" test";

        String line = null;
        String out="";
        try {
            Process process = runtime.exec(command);
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

        Report report=javaReport(src);
        boolean isSuccess=saveReport(report,testId,username);

        return report.toReportVO();
    }


    public ReportVO pythonTest(List<String> file, Long testId ,String username) {
        if(file.isEmpty()){
            return null;
        }

        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src=testRepository.findById(testId).getSrc();
        String disk=src.split(":")[0];

        command="cmd /c "+disk+": && cd "+src+" &&  py.test ";
        for(String s:file){
            command=command+s+" ";
        }
        command+=" --junitxml="+src+"\\report\\log.xml";

        String line = null;
        String out="";
        try {
            Process process = runtime.exec(command);
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

        Report report=pythonReport(src);
        boolean isSuccess=saveReport(report,testId,username);

        return report.toReportVO();
    }


    public ReportVO pythonTestAll(Long testId ,String username) {
        String src=testRepository.findById(testId).getSrc();
        File dir=new File(src);
        if (!dir.isDirectory()) {
            System.out.println("not a dir");
            Report report=new Report();
            report.setError_info("diretory error");
            return report.toReportVO();
        } else {
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
            return pythonTest(files,testId,username);

        }


    }


    public ReportVO cTest(List<String> file, Long testId ,String username) {  //需要报告文件名
        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src=testRepository.findById(testId).getSrc();

        String path=src+="\\makefile";
        File makefile = new File(path);
        if(!makefile.exists()){
            makefile.getParentFile().mkdirs();
        }
        try {
            makefile.createNewFile();
            FileWriter fw = new FileWriter(makefile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            String content="IINC=-I/usr/local/include/CUnit\n" +
                    "LIB=-L/usr/local/lib/\n\n"+"all: ";
            for(String str:file){
                content+=str+" ";
            }
            content=content+"\n"+"\tgcc $^ -o test $(INC) $(LIB) -lcunit -static ";
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        command="make&& ./test "; //to do

        String line = null;
        String out="";
        try {
            Process process = runtime.exec(command);
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

        Report report=cReport(src);
        boolean isSuccess=saveReport(report,testId,username);

        return report.toReportVO();
    }

    private Report javaReport(String src){
        src+="\\target\\surefire-reports";
        File dir=new File(src);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Report report=new Report();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        report.setTime(sdf.format(d));

        int cases=0;
        int failures=0;
        int success=0;
        int error=0;

        if (!dir.isDirectory()) {
            System.out.println("not a dir");

            report.setError_info("diretory error");
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
            for (File f:xmlList) {
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

        }

        return report;
    }

    private Report pythonReport(String src){
        src+="\\report\\log.xml";
        File log=new File(src);
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
            Document document = builder.parse(log);
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

    private Report cReport(String src){  //需要dtd
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Report report=new Report();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        report.setTime(sdf.format(d));
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
        }

        if(log==null){
            report.setError_info("no file");
            return report;
        }

        int cases=0;
        int failures=0;
        int success=0;
        int error=0;

        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(log);
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

    private boolean saveReport(Report report, Long testId ,String username){
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
