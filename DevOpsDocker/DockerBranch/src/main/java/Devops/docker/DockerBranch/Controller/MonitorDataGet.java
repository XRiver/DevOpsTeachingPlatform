package Devops.docker.DockerBranch.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Devops.docker.DockerBranch.Monitoring.influxDB.CpuUsageVO;
import Devops.docker.DockerBranch.Monitoring.influxDB.InfluxDBService;
import Devops.docker.DockerBranch.Monitoring.influxDB.MemoryUsageVO;
import Devops.docker.DockerBranch.Service.ContainerService;

@RestController
public class MonitorDataGet {
	
	@Autowired
	InfluxDBService influxdbservice;
	
    @Autowired
    ContainerService containerService;
	
	@RequestMapping("/perContainerCpu")
	public List<CpuUsageVO> getPerContainerCpuUsageRate(@RequestParam String hostId){
		
		List<String> ContainerName = containerService.getContainersInHost(hostId);
		
		//接下来用map做
		
		return null;
	}
	
	@RequestMapping("/allContainerCpu")
	public List<CpuUsageVO> getAllCpuUsageRate(@RequestParam String hostId){
		
		return null;
	}
	
	@RequestMapping("/perContainerMemory")
	public List<MemoryUsageVO> getPerContainerMemoryUsageRate(@RequestParam String hostId){
		
		return null;
	}
	
	@RequestMapping("/allContainerMemory")
	public List<MemoryUsageVO> getAllMemoryUsageRate(@RequestParam String hostId){
		
		return null;
	}

}
