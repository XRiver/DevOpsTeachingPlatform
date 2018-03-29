package com.Common;

/**
 * Created by Administrator on 2018/3/20.
 */
public enum BugState {
    newbuilt("新建"),
    open("打开"),
    assign("指派"),
    test("测试"),
    verified("确认"),
    deferred("延期"),
    reopened("重新打开"),
    duplicate("重复"),
    rejected("拒绝"),
    closed("关闭");

    private String name;

    private BugState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BugState strToState(String str){
        for(BugState st: BugState.values()){
            if(st.toString().toLowerCase().equals(str.toLowerCase())){
                return st;
            }
        }
        return newbuilt;
    }
}
