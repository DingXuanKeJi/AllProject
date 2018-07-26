package com.dingxuan.zhixiao.job;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.dingxuan.zhixiao.dao.DeviceMapper;
import com.dingxuan.zhixiao.util.CallShangXueLe;
import com.dingxuan.zhixiao.util.CardConstants;
import com.dingxuan.zhixiao.util.DeviceUtil;
import com.dingxuan.zhixiao.util.MD5Util;


public class PushSchoolCenterJob implements Callable<String> {

	private String taskData;
	public static PushSchoolCenterJob pus;
	
	private DeviceMapper deviceMapper;
	
	public PushSchoolCenterJob(String taskData,DeviceMapper deviceMapper) {
		this.taskData = taskData;
		this.deviceMapper = deviceMapper;
	}
	
	@Override
	public String call() throws Exception {
		
		//获取厂商的所属密码
		String appSecret = deviceMapper.keyVerfication(CardConstants.APP_KEY).get(0);
		//由前台所得的变量转Map格式
		Map<Object,Object> jsonMap = DeviceUtil.jsonToMap(this.taskData);
		
		//StringBuffer sb = new StringBuffer(appSecret);
		String strOrder = "DEVICENUM,LO,LA,RADIUS,SCHOOLID,SCHOOLRFIDS,SCHOOLNAME,OPTTYPE";
		//将map中的变量进行组装
		String splicingStr = DeviceUtil.SplicingString(jsonMap, strOrder, appSecret);
		//MD5得到SIGN
		String getSign = MD5Util.MD5(splicingStr);
		getSign.toUpperCase();
		jsonMap.put("KEY", CardConstants.APP_KEY);
		jsonMap.put("SIGN", getSign);
		//将Map转换为json串后再转为String
		String jsonRes = JSONUtils.toJSONString(jsonMap).toString();
		//调用平台端的接口，传递数据
		String platformRes = "";
		platformRes = CallShangXueLe.callShangxuela(CardConstants.PLATFORM_URL, "PushSchoolCenter", jsonRes);
		
		Map<Object,Object> platformMap = DeviceUtil.jsonToMap(platformRes);
		if(platformMap.get("RESULT") != null && !platformMap.get("RESULT").equals("null") && platformMap.get("RESULT").equals("0")){
			//判断返回的RESULT = 0 ,即调用成功时,将这组数据同步到知校端DB中 并将返回的String返回给前台，否则将返回的String直接返回给前台
			deviceMapper.PushSchoolCenter(jsonMap);
		}
		return platformRes;
	}

}
