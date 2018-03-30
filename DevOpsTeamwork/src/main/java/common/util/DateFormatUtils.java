package common.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lisw
 * @since 2017年4月5日 11:45
 *
 */
public class DateFormatUtils
{
	//时间格式化
	public static final SimpleDateFormat DATETIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	//日期格式化
	public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	//日期格式化
	public static final SimpleDateFormat DATEFORMATCHINESE = new SimpleDateFormat("yyyy年MM月dd日");
	
	//日期格式化
	public static final SimpleDateFormat TIMEFORMATCHINESE = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	
	//日期格式化
	public static final SimpleDateFormat TIMESIMPLEFORMATCHINESE = new SimpleDateFormat("yy年MM月dd日 HH:mm");
	
	//时间格式化
	public static final SimpleDateFormat TIMEFORMAT = new SimpleDateFormat("HH:mm");
	
	//时间格式化,精确到秒
	public static final SimpleDateFormat TIMESECONDFORMAT = new SimpleDateFormat("HH:mm:ss");
	
	//VCF 格式
	public static final SimpleDateFormat VCARDTIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date getYesterDay()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
	}
}
