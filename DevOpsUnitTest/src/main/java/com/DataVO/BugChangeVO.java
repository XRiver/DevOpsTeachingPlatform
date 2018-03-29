package com.DataVO;

/**
 * Created by Administrator on 2018/3/16.
 */
public class BugChangeVO {
    private Long id;

    private String time;

    private String before;

    private String after;

    private String info;

    private String manager;

    public BugChangeVO() {
    }

    public BugChangeVO(Long id, String time, String before, String after, String info, String manager) {
        this.id = id;
        this.time = time;
        this.before = before;
        this.after = after;
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

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
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
