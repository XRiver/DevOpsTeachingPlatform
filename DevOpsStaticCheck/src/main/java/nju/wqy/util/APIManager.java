package nju.wqy.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.wqy.web.vo.IndexVO;

public class APIManager {

	public static void main(String[] args) {
		String baseUrl="http://localhost:9000/api/";
		Map<String,String> params=new HashMap<String,String>();
		params.put("projectKeys","Student");
		//String result=APIManager.post(baseUrl+"measures/search",params);
		String result=APIManager.get("http://localhost:9000/api/measures/"
				+ "search?projectKeys=Student&metricKeys=bugs%2Ccode_smells%2Cduplicated_lines_density%2C"
				+ "ncloc%2Cncloc_language_distribution%2Cvulnerabilities");
		if(result!=null){  
			JSONObject obj=JSONObject.fromObject(result);      
			result=obj.getString("measures");//得到json格式字符串数组  
			JSONArray arr=JSONArray.fromObject(result);  
			if(arr.size()==6) {
				String bugs=getValue(arr.get(0));
				String code_smells=getValue(arr.get(1));
				String duplicated_lines_density=getValue(arr.get(2));
				String ncloc=getValue(arr.get(3));
				String ncloc_language_distribution=getValue(arr.get(4));
				String vulnerabilities=getValue(arr.get(5));
				int healthDegree=10*Integer.parseInt(bugs)+3*Integer.parseInt(code_smells)+1*Integer.parseInt(vulnerabilities);
				int risk=healthDegree/Integer.parseInt(ncloc)/1000;
				
			}
		} 
	}
	public static IndexVO getIndexVO(JSONArray arr) {
		
		IndexVO vo=new IndexVO();
		
		return null;
	}
	public static String getValue(Object o) {
		System.out.println(o);
		JSONObject obj=JSONObject.fromObject(o); 	
		String value=obj.getString("value");
		System.out.println(value);
		return value;
	}
	/** 
	 * get方法直接调用post方法 
	 * @param url 网络地址 
	 * @return 返回网络数据 
	 */  
	public static String get(String url){  
		return post(url,null);  
	}  
	/** 
	 * 设定post方法获取网络资源,如果参数为null,实际上设定为get方法 
	 * @param url 网络地址 
	 * @param param 请求参数键值对 
	 * @return 返回读取数据 
	 */  
	public static <K, V> String post(String  url,Map<K,V>   param){  
		HttpURLConnection conn=null;  
		try {  
			URL u=new URL(url);  
			conn=(HttpURLConnection) u.openConnection();  
			StringBuffer sb=null;  
			if(param!=null){//如果请求参数不为空  
				sb=new StringBuffer();  
				/*A URL connection can be used for input and/or output.  Set the DoOutput 
				 * flag to true if you intend to use the URL connection for output, 
				 * false if not.  The default is false.*/  
				//默认为false,post方法需要写入参数,设定true  
				conn.setDoOutput(true);  
				//设定post方法,默认get  
				conn.setRequestMethod("POST");  
				//获得输出流  
				OutputStream out=conn.getOutputStream();  
				//对输出流封装成高级输出流  
				BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));  
				//将参数封装成键值对的形式  
				for(Map.Entry s:param.entrySet()){  
					sb.append(s.getKey()).append("=").append(s.getValue()).append("&");  
				}  

				//将参数通过输出流写入  
				writer.write(sb.deleteCharAt(sb.toString().length()-1).toString());  
				writer.close();//一定要关闭,不然可能出现参数不全的错误  
				sb=null;  
			}  
			conn.connect();//建立连接  
			sb=new StringBuffer();  
			//获取连接状态码  
			int recode=conn.getResponseCode();  
			BufferedReader reader=null;  
			if(recode==200){  
				//Returns an input stream that reads from this open connection  
				//从连接中获取输入流  
				InputStream in=conn.getInputStream();  
				//对输入流进行封装  
				reader=new BufferedReader(new InputStreamReader(in));  
				String str=null;  
				sb=new StringBuffer();  
				//从输入流中读取数据  
				while((str=reader.readLine())!=null){  
					sb.append(str).append(System.getProperty("line.separator"));  
				}  
				//关闭输入流  
				reader.close();  
				if (sb.toString().length() == 0) {  
					return null;  
				}  
				return sb.toString().substring(0,  
						sb.toString().length() - System.getProperty("line.separator").length());  
			} else {
				System.out.println(recode);
			} 
		} catch (Exception e) {  
			e.printStackTrace();  
			return null;  
		}finally{  
			if(conn!=null)//关闭连接  
				conn.disconnect();  
		}  
		return null;  
	}  
}
