package com.interstellar.devopsjenkins.vo;

public class ComputerVO {
    private String displayName;

    private int numExecutors;
    private boolean offline;
    private String offlineCauseReason;

    public ComputerVO() {
    }

    public ComputerVO(String displayName, int numExecutors, boolean offline, String offlineCauseReason) {
        this.displayName = displayName;

        this.numExecutors = numExecutors;
        this.offline = offline;
        this.offlineCauseReason = offlineCauseReason;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }



    public int getNumExecutors() {
        return numExecutors;
    }

    public void setNumExecutors(int numExecutors) {
        this.numExecutors = numExecutors;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }

    public String getOfflineCauseReason() {
        return offlineCauseReason;
    }

    public void setOfflineCauseReason(String offlineCauseReason) {
        this.offlineCauseReason = offlineCauseReason;
    }
}
