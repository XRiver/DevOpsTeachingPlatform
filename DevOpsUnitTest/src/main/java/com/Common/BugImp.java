package com.Common;

/**
 * Created by Administrator on 2018/3/20.
 */
public enum BugImp {
    essential("关键"),
    important("重要"),
    common("一般"),
    notice("提示");

    private String name;

    private BugImp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BugImp strToImp(String str){
        for(BugImp st: BugImp.values()){
            if(st.toString().toLowerCase().equals(str.toLowerCase())){
                return st;
            }
        }
        return notice;
    }
}
