package com.interstellar.devopsjenkins.vo;

import java.util.List;

public class BuildInformationVO1 {
    private boolean building;
    private String description;
    private String displayName;
    private long duration;
    private long estimatedDuration;

    private String fullDisplayName;
    private String id;

    private int number;
    private int queueId;
    private String result;
    private long timestamp;
    private String url;
    private List<artifact> artifacts;
    private BuildVO nextBuild;
    private BuildVO previousBuild;


    public BuildInformationVO1() {
    }

    public BuildInformationVO1(boolean building, String description, String displayName, long duration, long estimatedDuration, String fullDisplayName, String id, int number, int queueId, String result, long timestamp, String url, List<artifact> artifacts, BuildVO nextBuild, BuildVO previousBuild) {
        this.building = building;
        this.description = description;
        this.displayName = displayName;
        this.duration = duration;
        this.estimatedDuration = estimatedDuration;
        this.fullDisplayName = fullDisplayName;
        this.id = id;
        this.number = number;
        this.queueId = queueId;
        this.result = result;
        this.timestamp = timestamp;
        this.url = url;
        this.artifacts = artifacts;
        this.nextBuild = nextBuild;
        this.previousBuild = previousBuild;
    }

    public boolean isBuilding() {
        return building;
    }

    public void setBuilding(boolean building) {
        this.building = building;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(long estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getFullDisplayName() {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName) {
        this.fullDisplayName = fullDisplayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public BuildVO getNextBuild() {
        return nextBuild;
    }

    public void setNextBuild(BuildVO nextBuild) {
        this.nextBuild = nextBuild;
    }

    public BuildVO getPreviousBuild() {
        return previousBuild;
    }

    public void setPreviousBuild(BuildVO previousBuild) {
        this.previousBuild = previousBuild;
    }
}
