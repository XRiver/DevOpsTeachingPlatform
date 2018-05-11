package com.util;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class ScriptGenerate{
    public static String javashAll(){
        return "#!/bin/sh \n mvn test ";
    }

    public static String javash(List<String> file){
        String out="#!/bin/sh \n mvn test ";
        out+="-Dtest=";
        for(String s:file){
            out=out+s+",";
        }
        out=out.substring(0,out.lastIndexOf(','));
        out+=" test";
        
        return out;
    }

    public static String pythonshAll(){
        return null;
    }

    public static String pythonsh(List<String> file){
        String out="#!/bin/sh \n py.test ";
        for(String s:file){
            out=out+s+".py"+" ";
        }
        out+=" --junitxml="+"./log.xml";
        return out;
    }

    public  static String cshAll(){
        return null;
    }

    public static String cmakefile(List<String> file){
        String content="IINC=-I/usr/local/include/CUnit\n" +
                "LIB=-L/usr/local/lib/\n\n"+"all: ";
        for(String str:file){
            content+=str+".c"+" ";
        }
        content=content+"\n"+"\tgcc $^ -o test $(INC) $(LIB) -lcunit -static ";
        return content;
    }

    public static String csh(String src){
        String out="make \n ./test \n";
        out+="cp /usr/local/share/CUnit/CUnit-Run.dtd "+src+"/CUnit-Run.dtd";

        return out;
    }

    @Value("${local.url}")
    static String localUrl;
    @Value("${dtd,path}")
    static String dtdPath;

    public static String javashPipeline(String group,String project){
        String out="cp -a ./ /project/"+group+"/"+project+"\n";
        out=out+"cd "+"/project/"+group+"/"+project+"\n";
        out=out+"mvn test \n";
        out=out+"curl "+localUrl+"/starttest?group="+group+"&project="+project+"\n";
        return out;
    }

    public static String pyshPipeline(String group,String project,List<String> file){
        String out="cp -a ./ /project/"+group+"/"+project+"\n";
        out=out+"cd "+"/project/"+group+"/"+project+"\n";
        out=out+"py.test ";
        for(String s:file){
            out=out+s+" ";
        }
        out+=" --junitxml="+"./log.xml";
        out=out+"\n";
        out=out+"curl "+localUrl+"/starttest?group="+group+"&project="+project+"\n";
        return out;
    }

    public static String cshPipeline(String group,String project){
        String out="cp -a ./ /project/"+group+"/"+project+"\n";
        out=out+"cd "+"/project/"+group+"/"+project+"\n";
        out=out+"make \n ./test \n";
        out=out+"cp "+dtdPath+"/project/"+group+"/"+project+"/CUnit-Run.dtd"+"\n";
        out=out+"curl "+localUrl+"/starttest?group="+group+"&project="+project+"\n";
        return out;
    }


}
