package Devops.docker.DockerBranch.Monitoring.influxDB;


import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;
import org.influxdb.dto.QueryResult.Series;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;

public class testInfuxDB {
	
	String url = "http://119.29.88.207:8086/query";
	
	
	public String testGetData() {
		String tt = "";
		tt = url+ "?pretty=true&q=select * from memory_usage where time <= '2018-04-13T14:16:06Z'&db=cadvisor";
		
		System.out.println(tt);
//		
//		String points = rest.getForObject(tt, String.class);
		
		
		InfluxDBProperties pro = new InfluxDBProperties("http://119.29.88.207:8086", "root", "root", "cadvisor", "autogen");
		
		InfluxDBConnectionFactory factory = new InfluxDBConnectionFactory(pro);
		
		InfluxDB db = factory.getConnection();
		
		Query q = new Query("select * from memory_usage limit 5", "cadvisor");
		
		QueryResult result = db.query(q);
		
		List<Result> rs = result.getResults();
		
		Result test = rs.get(0);
		
		List<Series> ss = test.getSeries();
		
		Series s = ss.get(0);
		
		List<List<Object>> rrrr = s.getValues();
		
		for(int i = 0 ; i < rrrr.size();i++) {
			for(int j = 0 ; j < rrrr.get(i).size();j++) {
				System.out.print(rrrr.get(i).get(j).toString()+"  ");
			}
			System.out.println();
		}
		
		System.out.println(result.toString() + "新部件");
		
		return result.toString();
	}

}
