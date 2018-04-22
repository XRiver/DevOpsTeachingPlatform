package Devops.docker.DockerBranch.Monitoring.influxDB;

import java.io.IOException;
import java.util.List;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;

/**
 * author:杨关
 * 作用:与InfluxDB交互的接口
 * 
 * */
public interface InfluxDBService {
	
	/**
	 * 以json格式返回每个容器的CPU使用率
	 * @param String ContainerName 容器名，用来查找固定的容器
	 * @param String TimeScale 返回的数据量（最近1min:m,最近1hour:h）
	 * @param String Ip influxdb主机的ip
	 * @param int DataBasePort 数据库的监听端口
	 * @return String 以json格式返回
	 * */
	public List<CpuUsageVO> PerContainerCpuUsageRate(String ContainerName,String TimeScale,
			String Ip,int DataBasePort,String DataBaseUserName,String DataBasePassword,String database,String type,
			Host host);
	
	/**
	 * 以json格式返回每个容器的内存使用率
	 * @param String ContainerName 容器名，用来查找固定的容器
	 * @param String TimeScale 返回的数据量（最近1min:m,最近1hour:h）
	 * @return String 以json格式返回
	 * */
	public List<MemoryUsageVO> PerContainerMemoryUsageRate(String ContainerName,String TimeScale,
			String Ip,int DataBasePort,String DaseBaseUserName,String DataBasePassword,String database,String type,
			Host host)throws RemoteOperateException, IOException;
	
	/**
	 * 以json格式返回总的的CPU使用率
	 * @param String TimeScale 返回的数据量（最近1min:m,最近1hour:h）
	 * @return String 以json格式返回
	 * */
	public List<CpuUsageVO> AllCpuUsageRate(String TimeScale,
			String Ip,int DataBasePort,String DataBaseUserName,String DataBasePassword,String database,String type,
			Host host);
	
	/**
	 * 以json格式返回总的内存使用率
	 * @param String TimeScale 返回的数据量（最近1min:m,最近1hour:h）
	 * @return String 以json格式返回
	 * */
	public List<MemoryUsageVO> AllMemoryUsageRate(String TimeScale,
			String Ip,int DataBasePort,String DataBaseUserName,String DataBasePassword,String database,String type,
			Host host)throws RemoteOperateException, IOException;
 
	//select * from cpu_usage_per_cpu where container_name = 'mymysql' cpu_usage_total 3923224848
}
