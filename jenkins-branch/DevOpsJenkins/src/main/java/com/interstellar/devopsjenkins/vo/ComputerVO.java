package com.interstellar.devopsjenkins.vo;

public class ComputerVO {
    private String displayName;
    private boolean jnlpAgent;
    private boolean idle;
    private int numExecutors;
    private boolean offline;
    private String offlineCauseReason;
    private String description;

    public ComputerVO() {
    }

    public ComputerVO(String displayName, boolean jnlpAgent, boolean idle, int numExecutors, boolean offline, String offlineCauseReason, String description) {
        this.displayName = displayName;
        this.jnlpAgent = jnlpAgent;
        this.idle = idle;
        this.numExecutors = numExecutors;
        this.offline = offline;
        this.offlineCauseReason = offlineCauseReason;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isJnlpAgent() {
        return jnlpAgent;
    }

    public void setJnlpAgent(boolean jnlpAgent) {
        this.jnlpAgent = jnlpAgent;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
