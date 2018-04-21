package Devops.docker.DockerBranch.Monitoring.influxDB;


/**
 * author:杨关
 * 作用:将从influxdb数据库里抽出来的数据放在这个实体里
 * */
public class CpuUsageVO {
	
	private String Time;
	
	private String Container_name;
	
	private String CpuPersentage;

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getContainer_name() {
		return Container_name;
	}

	public void setContainer_name(String container_name) {
		Container_name = container_name;
	}

	public String getCpuPersentage() {
		return CpuPersentage;
	}

	public void setCpuPersentage(String cpuPersentage) {
		CpuPersentage = cpuPersentage;
	}

	public CpuUsageVO(String time, String container_name, String cpuPersentage) {
		super();
		Time = time;
		Container_name = container_name;
		CpuPersentage = cpuPersentage;
	}

	public CpuUsageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
