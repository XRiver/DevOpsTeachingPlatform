package Devops.docker.DockerBranch.Monitoring.influxDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;
import org.influxdb.dto.QueryResult.Series;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;

public class InfluxDBImpl implements InfluxDBService{

	@Override
	public String PerContainerCpuUsageRate(String ContainerName, String TimeScale, String Ip, int DataBasePort,
			String DataBaseUserName, String DataBasePassword, String database, String type, Host host){
		// TODO Auto-generated method stub
		String connection = "http://"+Ip+":"+DataBasePort;
		
		//新建一个自己自定义的Properties
		InfluxDBProperties pro = 
				new InfluxDBProperties(connection, DataBaseUserName, DataBasePassword, database, type);
		
		InfluxDBConnectionFactory factory = new InfluxDBConnectionFactory(pro); //用连接工厂去连接
		
		InfluxDB db = factory.getConnection(); //获得influxDB的驱动类
		
//		QueryGenerator qg = new QueryGenerator();
		
		String q = "SELECT derivative(\"value\",1s)/1000000000 FROM \"cpu_usage_total\" WHERE ("+
				" \"container_name\" = '"+ContainerName+"' ) AND time >= now() - " + TimeScale +" ;";
		
		System.out.println(q);
		
		Query query = new Query(q, database); //Query是查询语句
		
		QueryResult result = db.query(query); //执行查找
		
		List<Result> resultSet = result.getResults(); //获得result的集合
		Result rs = resultSet.get(0); //拿到第一个result
		
		List<Series> seriesSet = rs.getSeries(); //拿到series集合
		Series ss = seriesSet.get(0);//拿到第一个series
		
		List<List<Object>> points = ss.getValues(); //拿到series里面的point
		
		List<CpuUsageVO> cpuList = new ArrayList<CpuUsageVO>();
		
		for(int i = 0 ; i < points.size();i++) {
			CpuUsageVO temp = new CpuUsageVO(
					points.get(i).get(0).toString(), 
					ContainerName, 
					points.get(i).get(1).toString());
			
			cpuList.add(temp);
		}
		
		for(CpuUsageVO c:cpuList) {
			System.out.println(c.getTime() + "  "+c.getContainer_name() + "  " + c.getCpuPersentage());
		}
		
		return "ss";
	}

	@Override
	public String PerContainerMemoryUsageRate(String ContainerName, String TimeScale, String Ip, int DataBasePort,
			String DataBaseUserName, String DataBasePassword, String database, String type, Host host) 
					throws RemoteOperateException, IOException {
		// TODO Auto-generated method stub
		
		String connection = "http://"+Ip+":"+DataBasePort;
		
		//新建一个自己自定义的Properties
		InfluxDBProperties pro = 
				new InfluxDBProperties(connection, DataBaseUserName, DataBasePassword, database, type);
		
		InfluxDBConnectionFactory factory = new InfluxDBConnectionFactory(pro); //用连接工厂去连接
		
		InfluxDB db = factory.getConnection(); //获得influxDB的驱动类
		
		QueryGenerator qg = new QueryGenerator();
		
		String q = qg.addDisplayColumn("*").addTableName("memory_usage").
				addCondition(new Condition("container_name", "=", "'"+ContainerName+"'")).And().
				addCondition(new Condition("time", ">", "now() - 1"+TimeScale)).SelectQuery();
		
//		System.out.println(q);
		
		Query query = new Query(q, database); //Query是查询语句
		
		QueryResult result = db.query(query); //执行查找
		
		List<Result> resultSet = result.getResults(); //获得result的集合
		Result rs = resultSet.get(0); //拿到第一个result
		
		List<Series> seriesSet = rs.getSeries(); //拿到series集合
		Series ss = seriesSet.get(0);//拿到第一个series
		
		List<List<Object>> points = ss.getValues(); //拿到series里面的point
		
		double totalMemory = getTotalMemory(host);
//		System.out.println(totalMemory);
		
		List<MemoryUsageVO> MemoryVOList = new ArrayList<MemoryUsageVO>();
		
		for(int i = 0 ; i < points.size();i++) { //通过两个for循环来加载VO
			
			double persentage =  Double.parseDouble(points.get(i).get(3).toString())/1024/totalMemory;
			
			MemoryUsageVO temp = new MemoryUsageVO(
					points.get(i).get(0).toString(), 
					points.get(i).get(1).toString(), 
					String.valueOf(persentage));
			
			MemoryVOList.add(temp);
		}
		
		for(MemoryUsageVO m:MemoryVOList) {
			System.out.println(m.getTime()+"  "+m.getContainer_name()+"  "+m.getMemoryPersentage());
		}
		
		return "sss";
	}

	@Override
	public String AllCpuUsageRate(String TimeScale, String Ip, int DataBasePort, String DataBaseUserName,
			String DataBasePassword, String database, String type, Host host) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AllMemoryUsageRate(String TimeScale, String Ip, int DataBasePort, String DataBaseUserName,
			String DataBasePassword, String database, String type, Host host) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	private double getTotalMemory(Host host) throws RemoteOperateException, IOException {
		
//		System.out.println(host.getHostname());
//		System.out.println(host.getIp());
//		System.out.println(host.getPassword());
		
		GetRemoteLinuxTotalMemory get = new GetRemoteLinuxTotalMemory(
				host.getHostname(), host.getPassword(), host.getIp(), 22);
		
		String totalMemory = get.GetMemory();
		
		double result = Double.parseDouble(totalMemory);
		
		return result;
		
	}



}
