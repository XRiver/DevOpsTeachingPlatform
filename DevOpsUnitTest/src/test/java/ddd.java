import com.Entity.FaultInfo;
import com.Entity.Report;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */
public class ddd {
    public static void ddd(String[] args){
        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src="C:\\java\\py\\test";
        String disk=src.split(":")[0];
        List<String> file=new ArrayList<String>();
        file.add("py_test1.py");
        file.add("py_test2.py");

        command="cmd /c  "+disk+": && cd "+src+" &&call python -m pytest ";
        for(String s:file){
            command=command+s+" ";
        }

  //      command="cmd /c  C: &&  cd C:\\java\\py\\test  &&python -m pytest py_test1.py";
        command+=" --junitxml="+src+"\\report\\log.xml";
        System.out.println(command);
        String line = null;
        String out="";
        Process process=null;
        try {
            process = runtime.exec(command);
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

        int i= process.exitValue();
        if (i == 0) {
            System.out.println("执行完成.");
        } else {
            System.out.println("执行失败.");
        }

        process.destroy();  //销毁子进程
        process = null;

    }
    private static Report pythonReport(String src){
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
}
