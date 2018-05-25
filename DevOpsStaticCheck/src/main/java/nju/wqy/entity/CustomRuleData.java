package nju.wqy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customRule")
public class CustomRuleData {

	public CustomRuleData() {}

	@Id
	@Column(name = "project_key")
	private String projectKey;
	

	@Column(name = "file_name")
	private String fileName;
	

	@Column(name = "file_path")
	private String filePath;


	
	


	public String getProjectKey() {
		return projectKey;
	}


	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public CustomRuleData(String projectKey, String fileName, String filePath) {
		super();
		this.projectKey = projectKey;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
}
