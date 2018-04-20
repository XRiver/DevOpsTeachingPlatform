package Devops.docker.DockerBranch.Monitoring.influxDB;


/**
 * author:杨关
 * 作用:与InfluxDB交互的接口
 * 
 * */
public interface InfluxDBService {
	
	/**
	 * 以json格式返回每个容器的CPU使用率
	 * @param String ContainerName 容器名，用来查找固定的容器
	 * @param String TimeScale 返回的数据量（day、week、month、all）
	 * @return String 以json格式返回
	 * */
	public String PerContainerCpuUsageRate(String ContainerName,String TimeScale);
	
	/**
	 * 以json格式返回每个容器的内存使用率
	 * @param String ContainerName 容器名，用来查找固定的容器
	 * @param String TimeScale 返回的数据量（day、week、month、all）
	 * @return String 以json格式返回
	 * */
	public String PerContainerMemoryUsageRate(String ContainerName,String TimeScale);
	
	/**
	 * 以json格式返回总的的CPU使用率
	 * @param String TimeScale 返回的数据量（day、week、month、all）
	 * @return String 以json格式返回
	 * */
	public String AllCpuUsageRate(String TimeScale);
	
	/**
	 * 以json格式返回总的内存使用率
	 * @param String TimeScale 返回的数据量（day、week、month、all）
	 * @return String 以json格式返回
	 * */
	public String AllMemoryUsageRate(String TimeScale);
 
	//select * from cpu_usage_per_cpu where container_name = 'mymysql' cpu_usage_total 3923224848
}
