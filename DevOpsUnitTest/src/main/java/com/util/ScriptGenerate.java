package com.util;

import com.Service.ScriptService;
import org.springframework.stereotype.Service;

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
            out=out+s+" ";
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
            content+=str+" ";
        }
        content=content+"\n"+"\tgcc $^ -o test $(INC) $(LIB) -lcunit -static ";
        return content;
    }

    public static String csh(String src){
        String out="make \n ./test \n";
        out+="cp /usr/local/share/CUnit/CUnit-Run.dtd "+src+"/CUnit-Run.dtd";

        return out;
    }

}
