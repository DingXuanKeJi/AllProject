package com.dingxuan.zhixiao.manager.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("PushMessageService")
public interface PushMessageService{
	
	//广播(无URL)
	public Map<String, Object> pushMsgToAll(String title, String description,int messageType,int sid,int appcert);
	
	//广播(根据open_type判断是否取URL参数)
	public Map<String, Object> pushMsgToAllByOpenType(String title, String description,int messageType,int sid,int appcert, Integer open_type, String url);
	 
	//单播
	public Map<String, Object> pushMsgToSingleDevice(String title,String description, int messageType,int sid,int appcert, String channelId, Integer open_type, String url);
	
	//创建用户自定义的标签组
	public Map<String, Object> CreateTag(int sid, int appcert, String tagName);
	
	//删除用户自定义的标签组
	public Map<String, Object> deleteTag(int sid, int appcert, String tagName);
	
	//查询用户定义的标签组信息
	public Map<String, Object> queryTags(int sid, int appcert, String tagName);

	//向标签组中批量添加设备
	public Map<String, Object> addDevicesToTag(int sid, int appcert, String tagName, String[] channelArray);
	
	//从标签组中批量删除设备
	public Map<String, Object> deleteDevicesFromTag(int sid, int appcert, String tagName, String[] channelArray);
	
	//向绑定到tag中的用户推送消息，即普通组播(根据open_type判断是否取URL参数)
	public Map<String, Object> pushMsgToTagByOpenType(String title,String description, int messageType,int sid,int appcert, String tagName, Integer open_type, String url);
	
	//向绑定到tag中的用户推送消息，即普通组播(无URL)
	public Map<String, Object> pushMsgToTag(String title,String description, int messageType,int sid,int appcert, String tagName);

	//设置推送参数,(定时推送功能,增加).
	public Map<String, Object> addPushParam(String title, String description, int messageType, int sid, int appcert, String channelIds, String tagName, int identification, int sendTime, Integer open_type, String url);

	//删除定时推送
	public Map<String, Object> deletePushById(int id);
	
	//根据Id与 identification修改
	public Map<String, Object> updatePushParam(int id, String title, String description, String messageType, int sid, int appcert, String channelIds, String tagName, int identification, int sendTime, Integer open_type, String url);

	//根据 title 与 identification 条件查询 模糊查询
	public List<Map> queryPushParam(String titles, Integer identification);

	//查询从当前时间开始,按当前时间排序
	public Map queryDateTime();



	 
	 
}
