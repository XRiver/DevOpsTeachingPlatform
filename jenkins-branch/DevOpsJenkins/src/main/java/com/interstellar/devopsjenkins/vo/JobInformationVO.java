package com.interstellar.devopsjenkins.vo;

import java.util.List;

public class JobInformationVO {
    private String name;
    private String url;
    private List<BuildVO> builds;
    private String healthReport;
    private BuildVO lastBuild;
    private BuildVO lastFailBuild;
    private BuildVO lastSuccessfulBuild;
    private BuildVO lastStableBuild;
    private String description;


    public JobInformationVO(String name, String url, List<BuildVO> builds, String healthReport, BuildVO lastBuild, BuildVO lastFailBuild, BuildVO lastSuccessfulBuild, BuildVO lastStableBuild, String description) {
        this.name = name;
        this.url = url;
        this.builds = builds;
        this.healthReport = healthReport;
        this.lastBuild = lastBuild;
        this.lastFailBuild = lastFailBuild;
        this.lastSuccessfulBuild = lastSuccessfulBuild;
        this.lastStableBuild = lastStableBuild;
        this.description = description;
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

    public List<BuildVO> getBuilds() {
        return builds;
    }

    public void setBuilds(List<BuildVO> builds) {
        this.builds = builds;
    }

    public String getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(String healthReport) {
        this.healthReport = healthReport;
    }

    public BuildVO getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(BuildVO lastBuild) {
        this.lastBuild = lastBuild;
    }

    public BuildVO getLastFailBuild() {
        return lastFailBuild;
    }

    public void setLastFailBuild(BuildVO lastFailBuild) {
        this.lastFailBuild = lastFailBuild;
    }

    public BuildVO getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public void setLastSuccessfulBuild(BuildVO lastSuccessfulBuild) {
        this.lastSuccessfulBuild = lastSuccessfulBuild;
    }

    public BuildVO getLastStableBuild() {
        return lastStableBuild;
    }

    public void setLastStableBuild(BuildVO lastStableBuild) {
        this.lastStableBuild = lastStableBuild;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
