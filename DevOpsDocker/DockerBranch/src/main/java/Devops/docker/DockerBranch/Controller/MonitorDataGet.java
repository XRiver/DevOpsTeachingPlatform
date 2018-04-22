package Devops.docker.DockerBranch.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Devops.docker.DockerBranch.Monitoring.influxDB.CpuUsageVO;
import Devops.docker.DockerBranch.Monitoring.influxDB.InfluxDBService;
import Devops.docker.DockerBranch.Monitoring.influxDB.MemoryUsageVO;

@RestController
public class MonitorDataGet {
	
	@Autowired
	InfluxDBService influxdbservice;
	
	@RequestMapping("/perContainerCpu")
	public List<CpuUsageVO> getPerContainerCpuUsageRate(){
		
		return null;
	}
	
	@RequestMapping("/allContainerCpu")
	public List<CpuUsageVO> getAllCpuUsageRate(){
		
		return null;
	}
	
	@RequestMapping("/perContainerMemory")
	public List<MemoryUsageVO> getPerContainerMemoryUsageRate(){
		
		return null;
	}
	
	@RequestMapping("/allContainerMemory")
	public List<MemoryUsageVO> getAllMemoryUsageRate(){
		
		return null;
	}

}
