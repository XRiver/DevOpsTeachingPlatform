package com.DataVO;

/**
 * Created by Administrator on 2018/3/16.
 */
public class BugChangeVO {
    private Long id;

    private String time;

    private String before_state;

    private String after_state;

    private String info;

    private String manager;

    public BugChangeVO() {
    }

    public BugChangeVO(Long id, String time, String before_state, String after_state, String info, String manager) {
        this.id = id;
        this.time = time;
        this.before_state = before_state;
        this.after_state = after_state;
        this.info = info;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBefore_state() {
        return before_state;
    }

    public void setBefore_state(String before_state) {
        this.before_state = before_state;
    }

    public String getAfter_state() {
        return after_state;
    }

    public void setAfter_state(String after_state) {
        this.after_state = after_state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
