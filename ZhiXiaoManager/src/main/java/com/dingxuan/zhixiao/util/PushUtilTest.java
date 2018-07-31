package com.dingxuan.zhixiao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PushUtilTest {
	/**
	 * 请求任务状态
	 */
	public final static String REQUEST_SUCCESS ="success";
	
	public static Map<String, Object> dateFromString(String time){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(PushUtilTest.REQUEST_SUCCESS, false);
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date timeDate = sdf.parse(time);
			cal.setTime(timeDate);
			result.put("year", cal.get(Calendar.YEAR));
			result.put("month", cal.get(Calendar.MONTH));
			result.put("day", cal.get(Calendar.DAY_OF_MONTH));
			result.put("hour", cal.get(Calendar.HOUR_OF_DAY));
			result.put("minute",  cal.get(Calendar.MINUTE));
			result.put("second", cal.get(Calendar.SECOND));
			result.put(PushUtilTest.REQUEST_SUCCESS, true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return result;
	}
	
	private final static int[] keyArr = {30600,30601,30602,30603,30604,
														 30605,30606,30607,30608,30609,
														 30610,30611,30612,30613,30616,
														 30617,30618,30619,30620,30621,
														 30699,40001,40002,40003,40004,
														 40005,40006,40007,40008,40009,
														 40010,40011,40012,41001,41002,
														 41003,41004,41005,50001,50002,
														 50003,50004,50005,50006,50007,
														 50008};  
	private final static String[] valueArr = { "500  服务内部错误", "405  请求方法不允许", "	400  请求参数错误", "403  权限验证失败", "402  配额用尽需要续费",  
	 															  "404  请求数据不存在", "408  请求时间戳验证超时", "408  服务令牌有效期超时", "404  绑定关系未找到或不存在", "404  绑定关系太多",
	 															  "409  重复操作", "404  Tag未找到或不存在", "401  应用被禁止,需要白名单授权", "402  需要先在Push-Console开通推送功能", "401  未审核通过,应用无法使用推送服务",
	 															  "401  应用没有广播推送能力", "401  应用没有单播或组播推送能力", "403  不允许操作default tag","403  一个应用只支持一个设备平台", "400  android包名非法",
	 															  "402  请求太频繁而被临时拒绝或者需要白名单授权", "400  Device Token格式不合法", "400  无效的消息格式","400  APNS认为Device Token已经失效","400  证书错误，连接被APNs拒绝",  
	 															  "400  重复消息", "400  生产版证书无效", "400  开发版证书无效","400  生产版证书过期","400  开发版证书过期",
																  "400  需要开发版证书,错误上传了生产版证书", "400  需要生产版证书，错误上传了生产版证书", "400  iOS证书不合法","400  定时任务不存在","400  定时任务重复",
																  "400  定时任务数超限", "400  定时任务将要执行,不允许取消", "400  定时任务已执行","500  生成CSRF Token失败","400  无效的CSRF Token",
																  "400  CSRF Token已失效", "400  未登录百度账号passport", "400  无效的baidu账户Session","403  不是开发者","403  开发者无效",
																  "400  应用名非法"};
		 
	public static String getErrorCode(int errorCode) {
		String str = "";
		for (int i = 0; i < keyArr.length; i++) {  
			if(errorCode == keyArr[i]) {
				System.out.println(valueArr[i]);
				str = valueArr[i];
			}
		} 
		return str;  
	}


}
