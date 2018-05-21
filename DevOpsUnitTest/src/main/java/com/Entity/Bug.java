package com.Entity;

import com.DataVO.BugChangeVO;
import com.DataVO.BugVO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */
@Entity
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String project_id;

    private String name;

    private String importance;

    private String info;

    private String state;

    @OneToMany(mappedBy = "bug", cascade = {CascadeType.ALL})
    private List<BugChange> bug_change= new ArrayList<BugChange>();

    public Bug() {
    }

    public Bug(String project_id, String name, String importance, String info, String state, List<BugChange> bug_change) {
        this.project_id = project_id;
        this.name = name;
        this.importance = importance;
        this.info = info;
        this.state = state;
        this.bug_change = bug_change;
    }

    public Bug(BugVO bugVO){
        if(bugVO.getId()!=null&&bugVO.getId()!=0){
            this.id=bugVO.getId();
        }
        this.project_id=bugVO.getProject_id();
        this.name = bugVO.getName();
        this.importance = bugVO.getImportance();
        this.info = bugVO.getInfo();
        this.state = bugVO.getState();
        if(bugVO.getBug_change()!=null){
            for(BugChangeVO vo:bugVO.getBug_change()){
                addBug_change(new BugChange(vo));
            }
        }
    }

    public BugVO toBugVO(){
        List<BugChangeVO> bug_changeList =new ArrayList<BugChangeVO>();
        for(BugChange bugChange:this.bug_change){
            bug_changeList.add(bugChange.toBugChangeVO());
        }
        return new BugVO(id,project_id,name,importance,info,state,bug_changeList);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<BugChange> getBug_change() {
        return bug_change;
    }

    public void setBug_change(List<BugChange> bug_change) {
        this.bug_change = bug_change;
    }

    public void addBug_change(BugChange bug_change) {
        bug_change.setBug(this);
        this.bug_change.add(bug_change);
    }

}
