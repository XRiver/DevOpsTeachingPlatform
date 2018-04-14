package nju.wqy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "config")
public class ConfigData {
	
	@Id
	@Column(name = "project_key")
	private String projectKey;
	

	@Column(name = "project_name")
	private String projectName;
	

	@Column(name = "project_version")
	private String projectVersion;
	

	@Column(name = "source")
	private String source;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "source_encoding")
	private String sourceEncoding;

	@Column(name = "java_binary")
	private String javaBinary;

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSourceEncoding() {
		return sourceEncoding;
	}

	public void setSourceEncoding(String sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}

	public String getJavaBinary() {
		return javaBinary;
	}

	public void setJavaBinary(String javaBinary) {
		this.javaBinary = javaBinary;
	}
	
	
	
}
