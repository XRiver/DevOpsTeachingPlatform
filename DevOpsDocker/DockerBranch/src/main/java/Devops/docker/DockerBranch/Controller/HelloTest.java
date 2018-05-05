package Devops.docker.DockerBranch.Controller;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import Devops.docker.DockerBranch.VO.helloVO;
import ch.ethz.ssh2.Connection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.FileOperateException;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.FileOperator.FileCreateTools;
import Devops.docker.DockerBranch.FileOperator.FileDeleteTools;
import Devops.docker.DockerBranch.FileOperator.FileOperateFacInstance;
import Devops.docker.DockerBranch.FileOperator.FileOperateFactory;
import Devops.docker.DockerBranch.FileOperator.FileWriterTools;
import Devops.docker.DockerBranch.FileOperator.RemoteFileReader;
import Devops.docker.DockerBranch.FileOperator.RemoteFileWriter;
import Devops.docker.DockerBranch.GenerateAndConnect.DockerfileGenerate;
import Devops.docker.DockerBranch.Monitoring.influxDB.GetRemoteLinuxTotalMemory;
import Devops.docker.DockerBranch.Monitoring.influxDB.InfluxDBImpl;
import Devops.docker.DockerBranch.Monitoring.influxDB.InfluxDBService;
import Devops.docker.DockerBranch.Monitoring.influxDB.testInfuxDB;
import Devops.docker.DockerBranch.RemoteConnection.FileTransport;
import Devops.docker.DockerBranch.RemoteConnection.RemoteExecuteCommand;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;

@RestController
public class HelloTest {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public helloVO Hello(@RequestParam(value="name", defaultValue="World") String name) {
		
//		RemoteSignIn sign = new RemoteSignIn("119.29.88.207", 22, "ubuntu", "abc8879623");
//		Connection connection = null;
//		try {
//			connection = sign.ConnectAndAuth(sign.getUSER(), sign.getPASSWORD());
//		} catch (RemoteOperateException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		
//		FileTransport F = new FileTransport("test", "txt", "D:\\360Downloads\\", "/home/ubuntu",connection);
//		try {
//			F.putFile();
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		------------------------------修改后的RemoteFileReader--------------------------
//		RemoteSignIn sign = new RemoteSignIn("119.29.88.207", 22, "ubuntu", "abc8879623");
//		Connection connection = null;
//		try {
//			connection = sign.ConnectAndAuth(sign.getUSER(), sign.getPASSWORD());
//		} catch (RemoteOperateException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			RemoteFileReader test = new RemoteFileReader("/home/ubuntu/", "start", "sh");
//			StringBuilder st;
//			st = test.ReadFile("/home/ubuntu/", "start", "sh",connection);
//			name = st.toString();
//			System.out.println(name);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		------------------------------修改后的RemoteFileReader--------------------------
		
		
		
//		------------------------------修改后的RemoteFileWriter--------------------------
//		FileOperateFacInstance a = new FileOperateFacInstance();
//		FileWriterTools test = a.getWriter(true);
//		StringBuilder t = new StringBuilder("how to solve!!   \r\n   look up on web!!  \r\n  test the factory!!! ok");
//		try {
//			System.out.println(test.WriteFile("/home/ubuntu/", "test", "sh", t,connection));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileOperateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		------------------------------修改后的RemoteFileWriter--------------------------


		
//		------------------------------修改后的RemoteExecuteConnman--------------------------
//		RemoteExecuteCommand t = new RemoteExecuteCommand();
//		StringBuilder r=null;
//		try {
//			r = t.ExecCommand(new StringBuilder("./home/ubuntu/exec/docker_ubuntu.sh"),connection);
//			r = t.ExecShell(new StringBuilder("/home/ubuntu/exec/docker_ubuntu.sh"),connection);
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(r.toString());
//		name = r.toString();
//		------------------------------修改后的RemoteExecuteConnman--------------------------
		
		
		
//		------------------------------修改后的Delete--------------------------
//		FileOperateFacInstance a = new FileOperateFacInstance();
//		FileDeleteTools d = a.getDelete(true);
//		try {
//			d.DeleteFile("/home/ubuntu/", "tt", "test",connection);
//		} catch (FileOperateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		------------------------------修改后的Delete--------------------------
		
		
		
//		------------------------------修改后的Create--------------------------
//		FileOperateFacInstance a = new FileOperateFacInstance();
//		FileCreateTools c = a.getCreate(true);
//		try {
//			c.createDir("/home/ubuntu/", "testCreateDir", 0700,connection);
//			c.createFile("/home/ubuntu/testCreateDir/", "testCreateFile", "sh",connection);
//		} catch (FileOperateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		------------------------------修改后的Create--------------------------
		
		
		
		
//		testInfuxDB t = new testInfuxDB();
//		name = t.testGetData();
		
//		GetRemoteLinuxTotalMemory g = new GetRemoteLinuxTotalMemory("ubuntu", "abc8879623", "119.29.88.207", 22);
//		try {
//			name = g.GetMemory();
//		} catch (RemoteOperateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		InfluxDBService test = new InfluxDBImpl();
//		Host h = new Host();
//		h.setIp("119.29.88.207");h.setHostname("ubuntu");h.setPassword("abc8879623");
//		test.AllCpuUsageRate("2m", "119.29.88.207", 8086, 
//				"root", "root", "cadvisor", "autogen", h);
				
//		try {
//			test.AllMemoryUsageRate("2m", "119.29.88.207", 8086, 
//					"root", "root", "cadvisor", "autogen", h);
//		} catch (RemoteOperateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return new helloVO(counter.incrementAndGet(),
                String.format(template, name));
	}
	
}
