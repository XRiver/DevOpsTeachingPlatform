package Devops.docker.DockerBranch.Monitoring.influxDB;


/**
 * author:杨关
 * 作用:将从influxdb数据库里抽出来的数据放在这个实体里
 * */
public class MemoryUsageVO {
	
	private String Time;
	
	private String Container_name;
	
	private String MemoryPersentage;

	public MemoryUsageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemoryUsageVO(String time, String container_name, String memoryPersentage) {
		super();
		Time = time;
		Container_name = container_name;
		MemoryPersentage = memoryPersentage;
	}

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

	public String getMemoryPersentage() {
		return MemoryPersentage;
	}

	public void setMemoryPersentage(String memoryPersentage) {
		MemoryPersentage = memoryPersentage;
	}
	
	

}
