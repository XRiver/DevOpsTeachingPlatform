package Devops.docker.DockerBranch.Monitoring.influxDB;


import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;

public class testInfuxDB {
	
	String url = "http://119.29.88.207:8086/query";
	
	EncodeRestTemplate rest;
	
	public testInfuxDB() {
		rest = new EncodeRestTemplate();
	}
	
	public String testGetData() {
		String tt = "";
		tt = url+ "?pretty=true&q=select * from memory_usage where time <= '2018-04-13T14:16:06Z'&db=cadvisor";
		
		System.out.println(tt);
//		
//		String points = rest.getForObject(tt, String.class);
		
		
		InfluxDBProperties pro = new InfluxDBProperties("http://119.29.88.207:8086", "root", "root", "cadvisor", "autogen");
		
		InfluxDBConnectionFactory factory = new InfluxDBConnectionFactory(pro);
		
		InfluxDB db = factory.getConnection();
		
		Query q = new Query("select * from memory_usage where time <= '2018-04-13T14:16:06Z'", "cadvisor");
		
		QueryResult result = db.query(q);
		
		System.out.println(result.toString() + "新部件");
		
		return result.toString();
	}

}
