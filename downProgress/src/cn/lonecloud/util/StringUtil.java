package cn.lonecloud.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtil {
	
	
	public static String getRandomStr(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	/**
	 * 获取时间的路径
	 * @return
	 */
	public static String getDirStr(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/");
		String dateStr = sdf.format(date);
		return dateStr;
	}
}
