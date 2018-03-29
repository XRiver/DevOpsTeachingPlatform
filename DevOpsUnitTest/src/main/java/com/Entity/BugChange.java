package com.Entity;
import com.DataVO.BugChangeVO;

import javax.persistence.*;
/**
 * Created by Administrator on 2018/3/16.
 */
@Entity
public class BugChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bug_id")
    private Bug bug;

    private String time;

    private String before;

    private String after;

    private String info;

    private String manager;

    public BugChange() {
    }

    public BugChange(Bug bug, String time, String before, String after, String info, String manager) {
        this.bug = bug;
        this.time = time;
        this.before = before;
        this.after = after;
        this.info = info;
        this.manager = manager;
    }

    public BugChange(BugChangeVO bugChangeVO) {
        if(bugChangeVO.getId()!=null&&bugChangeVO.getId()!=0){
            this.id=bugChangeVO.getId();
        }
        this.time =bugChangeVO.getTime();
        this.before =bugChangeVO.getBefore();
        this.after = bugChangeVO.getAfter();
        this.info = bugChangeVO.getInfo();
        this.manager = bugChangeVO.getManager();
    }

    public BugChangeVO toBugChangeVO(){
        return new BugChangeVO(id,time,before,after,info,manager);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
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
