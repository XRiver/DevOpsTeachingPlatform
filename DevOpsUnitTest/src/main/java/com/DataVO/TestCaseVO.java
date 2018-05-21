package com.DataVO;

/**
 * Created by Administrator on 2018/3/16.
 */
public class TestCaseVO {
    private Long id;

    private String name;

    private String caseId;

    private String file;

    private String info;

    public TestCaseVO() {
    }

    public TestCaseVO(Long id, String name, String caseId, String file, String info) {
        this.id = id;
        this.name = name;
        this.caseId = caseId;
        this.file = file;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
