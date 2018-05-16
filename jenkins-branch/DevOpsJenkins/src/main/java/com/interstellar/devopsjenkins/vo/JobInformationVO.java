package com.interstellar.devopsjenkins.vo;

import java.util.List;

public class JobInformationVO {
    private String name;
    private String displayName;
    private String fullDisplayName;
    private String url;
    private List<BuildVO> builds;
    private List<healthReport> healthReport;
    private BuildVO lastBuild;
    private BuildVO lastFailedBuild;
    private BuildVO lastSuccessfulBuild;
    private BuildVO lastStableBuild;
    private String description;
    private boolean buildable;


    public JobInformationVO() {
    }

    public JobInformationVO(String name, String displayName, String fullDisplayName, String url, List<BuildVO> builds, List<com.interstellar.devopsjenkins.vo.healthReport> healthReport, BuildVO lastBuild, BuildVO lastFailedBuild, BuildVO lastSuccessfulBuild, BuildVO lastStableBuild, String description, boolean buildable) {
        this.name = name;
        this.displayName = displayName;
        this.fullDisplayName = fullDisplayName;
        this.url = url;
        this.builds = builds;
        this.healthReport = healthReport;
        this.lastBuild = lastBuild;
        this.lastFailedBuild = lastFailedBuild;
        this.lastSuccessfulBuild = lastSuccessfulBuild;
        this.lastStableBuild = lastStableBuild;
        this.description = description;
        this.buildable = buildable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<com.interstellar.devopsjenkins.vo.healthReport> getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(List<com.interstellar.devopsjenkins.vo.healthReport> healthReport) {
        this.healthReport = healthReport;
    }

    public BuildVO getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(BuildVO lastBuild) {
        this.lastBuild = lastBuild;
    }

    public BuildVO getLastFailedBuild() {
        return lastFailedBuild;
    }

    public void setLastFailedBuild(BuildVO lastFailedBuild) {
        this.lastFailedBuild = lastFailedBuild;
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

    public boolean isBuildable() {
        return buildable;
    }

    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }
}
