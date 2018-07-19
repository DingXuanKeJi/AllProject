package com.dingxuan.zhixiao.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dingxuan.zhixiao.dao.DeviceMapper;

@Component
public class DeviceVerification {

	private static String appSecret = null;
	public static DeviceVerification dev;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@PostConstruct
	public void init(){
		dev = this;
	}
	
	public static int allVerification(Map<Object,Object> map,String strOrder){
		int result = 0;
		
		//验证key
		if(map.get("KEY") != null && !map.get("KEY").equals("null")){
			String appKey = (String)map.get("KEY");
			int keyRes = keyVerification(appKey);
			if(keyRes != 0){
				return 2;
			}
		}else{
			return 2;
		}
		
		//验证timestamp
		Long timeStem = Long.parseLong(String.valueOf(map.get("TIMESTAMP")));
		int timeRes = timeVerification(timeStem);
		if(timeRes != 0){
			return timeRes;
		}
		
		//验证sign
		try{
			int signRes = signVerification(map,strOrder);
			if(signRes != 0){
				return signRes;
			}
		}catch(Exception e){
			return 4;
		}
		
		return result;
	}
	

	//验证key值是否正确
	public static int keyVerification(String appKey){
		int result = 0;
		
		List<String> list = dev.deviceMapper.keyVerfication(appKey);
		if(list.size() == 0){
			result = 2;
		}else{
			appSecret = dev.deviceMapper.keyVerfication(appKey).get(0);
			//appSecret = "7339ADAD19A287EFC9356BCED73E50F280107CB0";
			if(appSecret == null && appSecret.equals("null")){
				result = 2;
			}
		}
		return result;
	}
	
	//验证sign是否正确
	@SuppressWarnings("null")
	private static int signVerification(Map<Object,Object> map, String strOrder) {
		int signRes = 0;
		//将排序规则拆分成数组
		//用数组的对应顺序进行map中字符串的拼接
		//将拼接后的字符串进行MD5加密处理，并转换为大写
		//将最终的字符串和入参的sign进行对比
		//其中无论哪个步骤出现问题都将返回4
		String[] order = strOrder.split(",");
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < order.length; i++){
			sb.append(order[i]);
			sb.append("=");
			sb.append(map.get(order[i]));
		}
		StringBuffer sb2 = new StringBuffer(appSecret);
		if(map.get("TIMESTAMP") != null && !map.get("TIMESTAMP").equals("null")){
			sb2.append(map.get("TIMESTAMP")).append(sb.toString());
			String str2 = sb2.toString().trim();
			
			//MD5
			String str3 = MD5Util.MD5(str2);
			str3.toUpperCase();
			String s = (String) map.get("SIGN");
			if(s ==null && s.equals("null") && s.equals("")){
				signRes = 1;
			}else{
				if(!str3.equals(map.get("SIGN"))){
					signRes = 4;
				}
			}
		}else{
			signRes = 1;
		}
		return signRes;
	}
	
	//验证timestamp是否超时
	private static int timeVerification(Long timeStem){
		int timeRes = 0;
		Long nowTime = (System.currentTimeMillis())/1000L;
		if(timeStem + 300 < nowTime){
			timeRes = 5;
		}
		return timeRes;
	}
	
	
	public static void main(String[] args) {
		Map<Object,Object> map = new HashMap<>();
		map.put("DEVICENUM", "867587027687399");
		map.put("STEPS", 500);
		map.put("TIME", "2018-03-26 10:00:00");
		map.put("KEY", "SHANGXUELA");
		map.put("SIGN", "186416F5EDCF07F54D4B4990C5066226");
		map.put("TIMESTAMP", 1497426971);
		
		String str = "DEVICENUM,STEPS,TIME";
		int i = allVerification(map,str);
		System.out.println(i);
		
		//System.out.println(System.currentTimeMillis());
	}
	
}
