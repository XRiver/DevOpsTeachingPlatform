package Devops.docker.DockerBranch.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.Monitoring.influxDB.CpuUsageVO;
import Devops.docker.DockerBranch.Monitoring.influxDB.InfluxDBImpl;
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
	public Map<String,List<CpuUsageVO>> getPerContainerCpuUsageRate(@RequestParam String hostId){
		
		List<String> ContainerNames = containerService.getContainersInHost(hostId);
		
		InfluxDBService influxDB = new InfluxDBImpl();
		
		Map<String,List<CpuUsageVO>> resultMap = new HashMap<String,List<CpuUsageVO>>();
		
		for(String ContainerName : ContainerNames) {
			List<CpuUsageVO> tempList = influxDB.PerContainerCpuUsageRate(""
					, "", "", 8086
					, "", "", "", "", null);
			resultMap.put(ContainerName, tempList);
		}
		
		return resultMap;
	}
	
	@RequestMapping("/allContainerCpu")
	public List<CpuUsageVO> getAllCpuUsageRate(@RequestParam String hostId){
		
		InfluxDBService influxDB = new InfluxDBImpl();
		
		List<CpuUsageVO> resultList = influxDB.AllCpuUsageRate(""
				, "", 8086
				, "", "", "", "", null);
		
		return resultList;
	}
	
	@RequestMapping("/perContainerMemory")
	public Map<String,List<MemoryUsageVO>> getPerContainerMemoryUsageRate(@RequestParam String hostId){
		
		List<String> ContainerNames = containerService.getContainersInHost(hostId);
		
		InfluxDBService influxDB = new InfluxDBImpl();
		
		Map<String,List<MemoryUsageVO>> resultMap = new HashMap<String,List<MemoryUsageVO>>();
		
		for(String ContainerName : ContainerNames) {
			try {
				List<MemoryUsageVO> tempList = influxDB.PerContainerMemoryUsageRate(""
						, "", "", 8086
						, "", "", "", "", null);
				resultMap.put(ContainerName, tempList);
			} catch (RemoteOperateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultMap;
	}
	
	@RequestMapping("/allContainerMemory")
	public List<MemoryUsageVO> getAllMemoryUsageRate(@RequestParam String hostId){
		
		InfluxDBService influxDB = new InfluxDBImpl();
		
		try {
			List<MemoryUsageVO> resultList = influxDB.AllMemoryUsageRate(""
					, "", 8086
					, "", "", "", "", null);
			return resultList;
		} catch (RemoteOperateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
