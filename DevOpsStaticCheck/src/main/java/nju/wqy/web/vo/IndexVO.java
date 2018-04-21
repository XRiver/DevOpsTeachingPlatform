package nju.wqy.web.vo;

import java.util.Date;

public class IndexVO {
	private int healthDegree;
	private double riskIndex;
	private int unresolvedProblems;
	private String lastAnalyse;
	public int getHealthDegree() {
		return healthDegree;
	}
	public void setHealthDegree(int healthDegree) {
		this.healthDegree = healthDegree;
	}
	public double getRiskIndex() {
		return riskIndex;
	}
	public void setRiskIndex(double riskIndex) {
		this.riskIndex = riskIndex;
	}
	public int getUnresolvedProblems() {
		return unresolvedProblems;
	}
	public void setUnresolvedProblems(int unresolvedProblems) {
		this.unresolvedProblems = unresolvedProblems;
	}
	public String getLastAnalyse() {
		return lastAnalyse;
	}
	public void setLastAnalyse(String lastAnalyse) {
		this.lastAnalyse = lastAnalyse;
	}


}
