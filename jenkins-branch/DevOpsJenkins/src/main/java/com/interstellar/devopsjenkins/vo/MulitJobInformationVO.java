package com.interstellar.devopsjenkins.vo;

import java.util.List;

public class MulitJobInformationVO {
    private String name;
    private String url;
    private String displayName;
    private String fullDisplayName;
    private String description;
    private List<healthReport> healthReport;
    private List<job> jobs;

    public MulitJobInformationVO(String name, String url, String displayName, String fullDisplayName, String description, List<healthReport> healthReport, List<job> jobs) {
        this.name = name;
        this.url = url;
        this.displayName = displayName;
        this.fullDisplayName = fullDisplayName;
        this.description = description;
        this.healthReport = healthReport;
        this.jobs = jobs;
    }


    public MulitJobInformationVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFullDisplayName() {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName) {
        this.fullDisplayName = fullDisplayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<healthReport> getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(List<healthReport> healthReport) {
        this.healthReport = healthReport;
    }

    public List<job> getJobs() {
        return jobs;
    }

    public void setJobs(List<job> jobs) {
        this.jobs = jobs;
    }


}
