package Devops.docker.DockerBranch.Monitoring.influxDB;

/**
 * 
 * author:杨关
 * QueryGenerator的查询条件类  跟在where 子句后面
 * 
 * */
public class Condition {
	
	private String ConditionName;
	
	private String ConditionType;
	
	private String ConditionValue;
	

	public String getConditionName() {
		return ConditionName;
	}

	public void setConditionName(String conditionName) {
		ConditionName = conditionName;
	}

	public String getConditionType() {
		return ConditionType;
	}

	public void setConditionType(String conditionType) {
		ConditionType = conditionType;
	}

	public String getConditionValue() {
		return ConditionValue;
	}

	public void setConditionValue(String conditionValue) {
		ConditionValue = conditionValue;
	}

	public Condition(String conditionName, String conditionType, String conditionValue) {
		super();
		ConditionName = conditionName;
		ConditionType = conditionType;
		ConditionValue = conditionValue;
	}

	public Condition() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCondition() {
		return ConditionName + " " + ConditionType + " " + ConditionValue + " ";
	}

}
