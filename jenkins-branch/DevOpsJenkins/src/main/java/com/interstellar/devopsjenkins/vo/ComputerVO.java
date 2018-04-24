package com.interstellar.devopsjenkins.vo;

public class ComputerVO {
    private String displayName;
    private int executors;
    private boolean offline;

    public ComputerVO(String displayName, int executors, boolean offline) {
        this.displayName = displayName;
        this.executors = executors;
        this.offline = offline;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getExecutors() {
        return executors;
    }

    public void setExecutors(int executors) {
        this.executors = executors;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }
}
