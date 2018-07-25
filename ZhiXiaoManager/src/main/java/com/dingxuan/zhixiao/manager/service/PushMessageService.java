package com.dingxuan.zhixiao.manager.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service("PushMessageService")
public interface PushMessageService {

	public Map<String, Object> pushMsgToSingleDevice(String method,String title, String description,
    		String extra,String uid,int sid,int appcert,String channelId);
	
	public Map<String, Object> pushMsgToAll(String method,String title, String description,
	    	String extra,String sid,int appcert);
	 
	public Map<String, Object> PushMsgToTag(String method,String title, String description,
	    	String extra,String cid,int sid,int appcert,String tagName);

	public Map<String, Object> CreateTag(int sid, int appcert, String tagName);

	public Map<String, Object> addDevicesToTag(int sid, int appcert, String tagName, String[] channelArray);
	 
	 
}
