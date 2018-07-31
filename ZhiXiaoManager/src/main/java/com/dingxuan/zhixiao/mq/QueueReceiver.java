package com.dingxuan.zhixiao.mq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dingxuan.zhixiao.manager.service.PushMessageService;
import com.dingxuan.zhixiao.util.DeviceUtil;

/**
 * 
 * @author liang
 * @description  队列消息监听器
 * 
 */
@Component
public class QueueReceiver implements MessageListener {

	private static QueueReceiver que;
	@Autowired
	private PushMessageService pushMessageService; 
	
	@PostConstruct
	private void init() {
		que = this;
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			//System.out.println("QueueReceiver接收到消息:"+((TextMessage)message).getText());
			//接收到的message为要推送的数据，其中channelIds为所有要推送用户的ID（形式为String，需要用,分割）
			//创建一个新的标签组，并将所有用户加入到这个标签组中（注：每次只能加入10个ID到标签组）
			//将这个标签组数据和其他的数据一起传递给推送方法
			Map<String, Object> resultMap = new HashMap<>();
			
			String str = ((TextMessage)message).getText();
			Map<Object, Object> map = DeviceUtil.jsonToMap(str);
//			for(Object key : map.keySet()) {
//				System.out.println(key + ">>>>" + map.get(key));
//			}
			int open_type = 0;
			if(null != map.get("open_type")) {
				open_type = Integer.parseInt(map.get("open_type").toString());
			}
			int sid = Integer.parseInt(map.get("sid").toString());
			int appcert = Integer.parseInt(map.get("appcert").toString());
			if(null != map.get("channelIds") && !map.get("channelIds").equals("")) {
				String[] channels = (map.get("channelIds").toString()).split(",");
				
				String tagName = UUID.randomUUID().toString();
				Map<String, Object> jsonMap = que.pushMessageService.CreateTag(sid,appcert,tagName);
				if(null != jsonMap.get("result")) {
					for(int i = 0 ;i < channels.length; i+=10) {
						String[] newData = null;
						if(i+9<channels.length) {
							newData = Arrays.copyOfRange(channels, i, i+10);
						}else {
							newData = Arrays.copyOfRange(channels, i, i +(channels.length%10));
						}
						que.pushMessageService.addDevicesToTag(sid,appcert,tagName,newData);
					}
				}
				
				if(open_type == 1) {
					resultMap = que.pushMessageService.pushMsgToTagByOpenType(map.get("title").toString(),map.get("description").toString(),
							Integer.parseInt(map.get("messageType").toString()),sid,appcert,
							tagName,open_type,map.get("url").toString());
				}else {
					resultMap = que.pushMessageService.pushMsgToTag(map.get("title").toString(),map.get("description").toString(),
							Integer.parseInt(map.get("messageType").toString()),sid,appcert,tagName);
				}
				que.pushMessageService.deleteTag(sid, appcert, tagName);
				//System.out.println(resultMap.toString());
			}else {
				if(open_type == 1) {
					resultMap = que.pushMessageService.pushMsgToAllByOpenType(map.get("title").toString(), map.get("description").toString(),
							Integer.parseInt(map.get("messageType").toString()), sid, appcert, open_type, map.get("url").toString());
				}else {
					resultMap = que.pushMessageService.pushMsgToAll(map.get("title").toString(),map.get("description").toString(),
							Integer.parseInt(map.get("messageType").toString()),sid,appcert);
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
