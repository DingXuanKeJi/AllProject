package com.dingxuan.zhixiao.manager.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.AddDevicesToTagRequest;
import com.baidu.yun.push.model.AddDevicesToTagResponse;
import com.baidu.yun.push.model.CreateTagRequest;
import com.baidu.yun.push.model.CreateTagResponse;
import com.baidu.yun.push.model.DeviceInfo;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.dingxuan.zhixiao.dao.PushMessageMapper;
import com.dingxuan.zhixiao.manager.service.PushMessageService;

import net.sf.json.JSONObject;

@Service("pushMessageServiceImpl")
public class PushMessageServiceImpl implements PushMessageService {
	
	private static final Logger LOGGER = Logger
			.getLogger(PushMessageServiceImpl.class);
	
	@Autowired
	private PushMessageMapper pushMessageMapper;
	
	
	/**
     * 推送初始化
     * @return
     */
    private BaiduPushClient initPushClient(int sid,int appcert){
        // 1. get apiKey and secretKey from developer console
        PushKeyPair pair = null;
        Map<?, ?> map = pushMessageMapper.selectPushuser(sid,appcert);
        System.out.println(map);
        pair = new PushKeyPair(String.valueOf(map.get("api_key")), String.valueOf(map.get("secret_key")));

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,"api.tuisong.baidu.com");

        // 3. register a YunLogHandler to get detail interacting information
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println("BaiduPushClient"+event.getMessage());
            }
        });
        return  pushClient;
    }
    
    
    /**
     * 向单个设备推送消息
     * @param method 是指某功能的命名
     * @param title 是指标题文言
     * @param description 是指描述文言
     * @param extra 数组
     * @param uidArray
     * @return
     */
	public Map<String, Object> pushMsgToSingleDevice(String method,String title, String description,
    		String extra,String uid,int sid,int appcert,String channelId){ 
	 	BaiduPushClient pushClient = initPushClient(sid,appcert);
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        JSONObject message = new JSONObject();
        int deviceType = 0;
        if(appcert==0) {
        	deviceType = 3;
        }else if(appcert==299||appcert==99){
        	deviceType = 4;
        }

        if(deviceType == 3){ // Android 设备
            message.put("title", title);
            message.put("description",description);
            message.put("notification_builder_id", 0);
            message.put("notification_basic_style", 4);
            message.put("open_type", 2); //默认 1
            //message.put("url", url);

        }else if(deviceType == 4){ //iOS 设备

            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", description);
            jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
            message.put("aps", jsonAPS);
        }


        try {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                    .addChannelId(channelId)
                    .addMsgExpires(3600) // message有效时间
                    .addMessageType(1)// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
                    .addMessage(message.toString())
                    .addDeviceType(deviceType);// deviceType => 3:android, 4:ios


            // 5. http request
            PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);

            LOGGER.debug("msgId: " + response.getMsgId() + ",sendTime: "
                  + response.getSendTime());
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime());
            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());

        }catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                LOGGER.error("推送消息给所有设备出现异常 " + e.getMessage());
//	                System.out.println("推送消息给所有设备出现异常 " + e.getMessage());
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
            	LOGGER.error(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    } 
	
	/**
	 * 推送消息给所有设备，广播推送。
	 * @param method
	 * @param title
	 * @param description
	 * @param extra
	 * @param sid
	 * @return
	 * @throws PushClientException
	 * @throws PushServerException
	 */
    public Map<String, Object> pushMsgToAll(String method,String title, String description,
    		String extra,String sid,int appcert){
        BaiduPushClient pushClient = initPushClient(Integer.parseInt(sid),appcert);
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        JSONObject message = new JSONObject();

        int deviceType = 0;
        if(appcert==0) {
        	deviceType = 3;
        }else if(appcert==299||appcert==99){
        	deviceType = 4;
        }
        if(deviceType == 3){ // Android 设备
            message.put("title", title);
            message.put("description",description);
            message.put("notification_builder_id", 0);
            message.put("notification_basic_style", 4);
            message.put("open_type", 2); //默认 1
//            message.put("url", url);

//            JSONObject jsonCustormCont = new JSONObject();
//
//            if(StringUtils.isNotBlank(obj.toString())){
//                for (int i = 0; i < obj.length; i++) {
//                    jsonCustormCont.put("key"+ (i+1), obj[i]); //自定义内容，key-value
//                }
//            }
//
//            message.put("custom_content", jsonCustormCont);

        }else if(deviceType == 4){ //iOS 设备

            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", description);
            jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
            message.put("aps", jsonAPS);

//            if(StringUtils.isNotBlank(obj.toString())){
//                for (int j = 0; j < obj.length; j++) {
//                    message.put("key" + (j+1), obj[j]);
//                }
//            }
        }

        try {
            PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMsgExpires(3600) //默认 new Integer(3600)
                    .addMessageType(1) // 0:透传消息  1：通知  默认是 0
                    .addMessage(message.toString())//添加透传消息
                    .addSendTime(60) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送   System.currentTimeMillis() / 1000 + 120
                    .addDeviceType(deviceType);

            // 5. http request
            PushMsgToAllResponse response = pushClient.pushMsgToAll(request);

            // Http请求返回值解析
            LOGGER.debug(String.format("msgId: %s, sendTime: %d",response.getMsgId(), response.getSendTime()));
            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());
            jsonMap.put("timerId", response.getTimerId()); //推送定时消息时，返回该字段，标识定时任务。

        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                LOGGER.error("推送消息给所有设备出现异常 " + e.getMessage());
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
            	LOGGER.error(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    }
    
    /**
     * 向单个设备推送消息
     * @param method 是指某功能的命名
     * @param title 是指标题文言
     * @param description 是指描述文言
     * @param extra 数组
     * @param uidArray
     * @return
     */
	public Map<String, Object> PushMsgToTag(String method,String title, String description,
    		String extra,String cid,int sid,int appcert,String tagName){ 
	 	BaiduPushClient pushClient = initPushClient(sid,appcert);
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        JSONObject message = new JSONObject();
        int deviceType = 0;
        if(appcert==0) {
        	deviceType = 3;
        }else if(appcert==299||appcert==99){
        	deviceType = 4;
        }

        if(deviceType == 3){ // Android 设备
            message.put("title", title);
            message.put("description",description);
            message.put("notification_builder_id", 0);
            message.put("notification_basic_style", 4);
            message.put("open_type", 2); //默认 1
            //message.put("url", url);

        }else if(deviceType == 4){ //iOS 设备

            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", description);
            jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
            message.put("aps", jsonAPS);
        }


        try {
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName(tagName)
                    .addMsgExpires(new Integer(3600))// message有效时间
                    .addMessageType(1)// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
                    // .addSendTime(System.currentTimeMillis() / 1000 + 70).
                    .addMessage(message.toString())
                    .addDeviceType(deviceType);// deviceType => 3:android, 4:ios

            // 5. http request
            PushMsgToTagResponse  response = pushClient.pushMsgToTag(request);

            LOGGER.debug("msgId: " + response.getMsgId() + ",sendTime: "
                  + response.getSendTime());
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime());
            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());

        }catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                LOGGER.error("推送消息给所有设备出现异常 " + e.getMessage());
//	                System.out.println("推送消息给所有设备出现异常 " + e.getMessage());
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
            	LOGGER.error(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    }


	/**
	 * 创建用户自定义的标签组
	 * @param tagName
	 * @param sid
	 * @param appcert
	 * @param req
	 * @return
	 */
	@Override
	public Map<String, Object> CreateTag(int sid, int appcert, String tagName) {
		BaiduPushClient pushClient = initPushClient(sid,appcert);
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        try {
        	int deviceType = 0;
            if(appcert==0) {
            	deviceType = 3;
            }else if(appcert==299||appcert==99){
            	deviceType = 4;
            }
            // 4. specify request arguments
            CreateTagRequest request = new CreateTagRequest().addTagName(
            		tagName).addDeviceType(deviceType);
            // 5. http request
            CreateTagResponse response = pushClient.createTag(request);
            System.out.println(String.format("tagName: %s, result: %d",
                    response.getTagName(), response.getResult()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushClientException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
	}


	@Override
	public Map<String, Object> addDevicesToTag(int sid, int appcert, String tagName, String[] channelArray) {
		BaiduPushClient pushClient = initPushClient(sid,appcert);
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        try {
        	int deviceType = 0;
            if(appcert==0) {
            	deviceType = 3;
            }else if(appcert==299||appcert==99){
            	deviceType = 4;
            }

            AddDevicesToTagRequest request = new AddDevicesToTagRequest()
                    .addTagName(tagName).addChannelIds(channelArray)
                    .addDeviceType(deviceType);
            // 5. http request
            AddDevicesToTagResponse response = pushClient
                    .addDevicesToTag(request);
            // Http请求返回值解析
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("devicesInTag：{");
                List<?> devicesInfo = response.getDevicesInfoAfterAdded();
                for (int i = 0; i < devicesInfo.size(); i++) {
                    Object object = devicesInfo.get(i);
                    if (i != 0) {
                        strBuilder.append(",");
                    }
                    if (object instanceof DeviceInfo) {
                        DeviceInfo deviceInfo = (DeviceInfo) object;
                        strBuilder.append("{channelId:"
                                + deviceInfo.getChannelId() + ",result:"
                                + deviceInfo.getResult() + "}");
                    }
                }
                strBuilder.append("}");
                System.out.println(strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushClientException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
	} 

}
