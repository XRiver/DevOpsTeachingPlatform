package Devops.docker.DockerBranch.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.Monitoring.influxDB.CpuUsageVO;
import Devops.docker.DockerBranch.Monitoring.influxDB.InfluxDBService;
import Devops.docker.DockerBranch.Monitoring.influxDB.MemoryUsageVO;
import Devops.docker.DockerBranch.Service.ContainerService;
import Devops.docker.DockerBranch.dao.HostDao;

@RestController
public class MonitorDataGet {
	
	@Autowired
	InfluxDBService influxdbservice;
	
    @Autowired
    ContainerService containerService;
    
    @Autowired
    HostDao host;
	
	@RequestMapping("/perContainerCpu")
	public Map<String,List<CpuUsageVO>> getPerContainerCpuUsageRate(@RequestParam String hostId){
		
		List<String> ContainerNames = this.containerService.getContainersInHost(hostId);
		
		Optional<Host> h = this.host.findById(Integer.valueOf(hostId));
		
		Map<String,List<CpuUsageVO>> resultMap = new HashMap<String,List<CpuUsageVO>>();
		
		for(String ContainerName : ContainerNames) {
			List<CpuUsageVO> tempList = this.influxdbservice.PerContainerCpuUsageRate(ContainerName
					, "1m", h.get().getIp(), 8086
					, "root", "root", "cadvidor", "autogen", h.get());
			resultMap.put(ContainerName, tempList);
		}
		
		return resultMap;
	}
	
	@RequestMapping("/allContainerCpu")
	public List<CpuUsageVO> getAllCpuUsageRate(@RequestParam String hostId){
		
		Optional<Host> h = this.host.findById(Integer.valueOf(hostId));
		
		List<CpuUsageVO> resultList = this.influxdbservice.AllCpuUsageRate("1m"
				, h.get().getIp(), 8086
				, "root", "root", "cadvidor", "autogen", h.get());
		
		return resultList;
	}
	
	@RequestMapping("/perContainerMemory")
	public Map<String,List<MemoryUsageVO>> getPerContainerMemoryUsageRate(@RequestParam String hostId){
		
		List<String> ContainerNames = this.containerService.getContainersInHost(hostId);
		
		Map<String,List<MemoryUsageVO>> resultMap = new HashMap<String,List<MemoryUsageVO>>();
		
		Optional<Host> h = this.host.findById(Integer.valueOf(hostId));
		
		for(String ContainerName : ContainerNames) {
			try {
				List<MemoryUsageVO> tempList = this.influxdbservice.PerContainerMemoryUsageRate(ContainerName
						, "1m", h.get().getIp(), 8086
						, "root", "root", "cadvidor", "autogen", h.get());
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
		
		Optional<Host> h = this.host.findById(Integer.valueOf(hostId));
		
		try {
			List<MemoryUsageVO> resultList = this.influxdbservice.AllMemoryUsageRate("1m"
					, h.get().getIp(), 8086
					, "root", "root", "cadvidor", "autogen", h.get());
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
