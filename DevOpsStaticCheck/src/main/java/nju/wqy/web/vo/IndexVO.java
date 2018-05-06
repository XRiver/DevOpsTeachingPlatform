package nju.wqy.web.vo;

import java.util.Date;

public class IndexVO {
	private double healthDegree;
	private double riskIndex;
	private int unresolvedProblems;
	private String lastAnalyse;
	public double getHealthDegree() {
		return healthDegree;
	}
	public void setHealthDegree(double healthDegree) {
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
