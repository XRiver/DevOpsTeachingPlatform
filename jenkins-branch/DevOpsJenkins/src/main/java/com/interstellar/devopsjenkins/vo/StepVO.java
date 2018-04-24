package com.interstellar.devopsjenkins.vo;

public class StepVO {
    private String displayDescription;
    private String displayName;
    private Integer durationInMillis;
    private String id;
    private String input;
    private String result;
    private String startTime;
    private String state;
    private String type;

    public StepVO() {
    }

    public StepVO(String displayDescription, String displayName, Integer durationInMillis, String id, String input, String result, String startTime, String state, String type) {
        this.displayDescription = displayDescription;
        this.displayName = displayName;
        this.durationInMillis = durationInMillis;
        this.id = id;
        this.input = input;
        this.result = result;
        this.startTime = startTime;
        this.state = state;
        this.type = type;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(Integer durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
