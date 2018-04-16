package Devops.docker.DockerBranch.Monitoring.influxDB;


public class InfluxDBProperties extends org.springframework.data.influxdb.InfluxDBProperties{
	
	private String url;
	
	private String username;
	
	private String password;
	
	private String database;
	
	private String retentionPolicy;
	
	private int connectTimeout = 10;
	
	private int readTimeout = 30;
	
	private int writeTimeout = 10;
	
	private boolean gzip = false;
	
	public InfluxDBProperties(String url,String username,String password,String database,
			String retentionPolicy) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.database = database;
		this.retentionPolicy = retentionPolicy;
	}
	
	public InfluxDBProperties(String url,String username,String password,String database,
			String retentionPolicy,int connectTimeout,int readTimeout,int writeTimeout,boolean gzip) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.database = database;
		this.retentionPolicy = retentionPolicy;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		this.writeTimeout = writeTimeout;
		this.gzip = gzip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getRetentionPolicy() {
		return retentionPolicy;
	}

	public void setRetentionPolicy(String retentionPolicy) {
		this.retentionPolicy = retentionPolicy;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getWriteTimeout() {
		return writeTimeout;
	}

	public void setWriteTimeout(int writeTimeout) {
		this.writeTimeout = writeTimeout;
	}

	public boolean isGzip() {
		return gzip;
	}

	public void setGzip(boolean gzip) {
		this.gzip = gzip;
	}
	
	
	

}
