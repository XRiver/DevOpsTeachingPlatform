package Devops.docker.DockerBranch.Monitoring.influxDB;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class EncodeRestTemplate extends RestTemplate{
	
	@Override
	public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor, Object... uriVariables) throws RestClientException {
		// TODO Auto-generated method stub
		
		String Oldurl = getUriTemplateHandler().expand(url, uriVariables).toString();
		
//		Oldurl = Oldurl.replace(";", "%3B");
//		Oldurl = Oldurl.replace("<", "%3C");
//		Oldurl = Oldurl.replace("=", "%3D");
//		Oldurl = Oldurl.replace(">", "%3E");
//		Oldurl = Oldurl.replace("*", "%2A");
//		Oldurl = Oldurl.replace("+", "%2B");
//		Oldurl = Oldurl.replace("-", "%2D");
		
		URI expanded = null;
		
		try {
			expanded = new URI(Oldurl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(expanded.toString());
		
		return doExecute(expanded, method, requestCallback, responseExtractor);
	}

}
