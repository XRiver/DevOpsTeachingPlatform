package nju.wqy.web.vo;



import lombok.Data;

@Data
public class ConfigVO {


	private String projectKey;



	private String projectName;


	private String projectVersion;



	private String source;


	private String language;

	private String sourceEncoding;

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
