package nju.wqy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "integratedData")
public class IntegratedData {
	@Id
	@Column(name = "projectKey")
	private String projectKey;
	

	@Column(name = "lastAnalysis")
	private String lastAnalysis;
	

	@Column(name = "healthDegree")
	private int healthDegree;
	
	@Column(name = "risk")
	private int risk;
	
	@Column(name = "problemNo")
	private int problemNo;

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getLastAnalysis() {
		return lastAnalysis;
	}

	public void setLastAnalysis(String lastAnalysis) {
		this.lastAnalysis = lastAnalysis;
	}

	public int getHealthDegree() {
		return healthDegree;
	}

	public void setHealthDegree(int healthDegree) {
		this.healthDegree = healthDegree;
	}

	public int getRisk() {
		return risk;
	}

	public void setRisk(int risk) {
		this.risk = risk;
	}

	public int getProblemNo() {
		return problemNo;
	}

	public void setProblemNo(int problemNo) {
		this.problemNo = problemNo;
	}
	
	
}
