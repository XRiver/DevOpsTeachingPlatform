package com.interstellar.devopsjenkins.vo;

public class BuildInformationVO {
    private boolean building;
    private String description;
    private String displayName;
    private long duration;
    private long estimateDuration;

    private String fullDisplayName;
    private String id;

    private int number;
    private int queueId;
    private String result;
    private long timestamp;
    private String url;


    public BuildInformationVO() {
    }

    public BuildInformationVO(boolean building, String description, String displayName, long duration, long estimateDuration, String fullDisplayName, String id, int number, int queueId, String result, long timestamp, String url) {
        this.building = building;
        this.description = description;
        this.displayName = displayName;
        this.duration = duration;
        this.estimateDuration = estimateDuration;

        this.fullDisplayName = fullDisplayName;
        this.id = id;

        this.number = number;
        this.queueId = queueId;
        this.result = result;
        this.timestamp = timestamp;
        this.url = url;

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

    public long getEstimateDuration() {
        return estimateDuration;
    }

    public void setEstimateDuration(long estimateDuration) {
        this.estimateDuration = estimateDuration;
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
}
