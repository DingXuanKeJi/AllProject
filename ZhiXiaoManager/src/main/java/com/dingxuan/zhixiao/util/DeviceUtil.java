package com.dingxuan.zhixiao.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

public class DeviceUtil {

	//按指定顺序将对应变量进行拼接
	public static String SplicingString(Map<Object,Object> jsonMap,String strOrder,String appSecret){
		String strRes = "";
		String[] order = strOrder.split(",");
		StringBuffer sb = new StringBuffer(appSecret);
		if(jsonMap.get("TIMESTAMP") != null && !jsonMap.get("TIMESTAMP").equals("null")){
			sb.append(jsonMap.get("TIMESTAMP"));
			for(int i = 0; i < order.length; i++){
				sb.append(order[i]).append("=").append(jsonMap.get(order[i]));
			}
			strRes = sb.toString().trim();
		}
		return strRes;
	}
	
	//JSON模式的String转map方法
	public static Map<Object, Object> jsonToMap(String jsonStr){
		//将jsonStr转换成json格式
		//再将json转换成Map,并将json中的数据装载到Map中
		JSONObject json = JSONObject.fromObject(jsonStr);
		Map<Object,Object> map = new HashMap<>();
		for(Iterator<?> iter = json.keys(); iter.hasNext();){
			String key = (String)iter.next();
			map.put(key, json.get(key));
		}
		return map;
	}
	
	
}
