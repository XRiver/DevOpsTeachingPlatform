package common.job;

import org.springframework.stereotype.Component;

/**
 * 配置定时器
 * @author lisw
 * @date 2017年4月5日下午16:07:27
 *
 */
@Component
public class TestJob {
	
	
	 //@Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次   
     public void test(){
		 System.out.println("测试");
	 }

}
