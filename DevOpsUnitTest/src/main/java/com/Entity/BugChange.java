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

    private String time;

    private String before_state;

    private String after_state;

    private String info;

    private String manager;

    @ManyToOne
    @JoinColumn(name = "bug_id")
    private Bug bug;


    public BugChange() {
    }

    public BugChange(Bug bug, String time, String before_state, String after_state, String info, String manager) {
        this.bug = bug;
        this.time = time;
        this.before_state = before_state;
        this.after_state = after_state;
        this.info = info;
        this.manager = manager;
    }

    public BugChange(BugChangeVO bugChangeVO) {
        if(bugChangeVO.getId()!=null&&bugChangeVO.getId()!=0){
            this.id=bugChangeVO.getId();
        }
        this.time =bugChangeVO.getTime();
        this.before_state =bugChangeVO.getBefore_state();
        this.after_state = bugChangeVO.getAfter_state();
        this.info = bugChangeVO.getInfo();
        this.manager = bugChangeVO.getManager();
    }

    public BugChangeVO toBugChangeVO(){
        return new BugChangeVO(id,time,before_state,after_state,info,manager);
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
