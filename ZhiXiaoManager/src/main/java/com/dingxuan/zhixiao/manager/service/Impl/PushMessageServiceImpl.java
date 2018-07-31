package com.dingxuan.zhixiao.manager.service.Impl;

import java.util.ArrayList;
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
import com.baidu.yun.push.model.DeleteDevicesFromTagRequest;
import com.baidu.yun.push.model.DeleteDevicesFromTagResponse;
import com.baidu.yun.push.model.DeleteTagRequest;
import com.baidu.yun.push.model.DeleteTagResponse;
import com.baidu.yun.push.model.DeviceInfo;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.baidu.yun.push.model.QueryTagsRequest;
import com.baidu.yun.push.model.QueryTagsResponse;
import com.baidu.yun.push.model.TagInfo;
import com.dingxuan.zhixiao.dao.PushMessageMapper;
import com.dingxuan.zhixiao.manager.service.PushMessageService;
import com.dingxuan.zhixiao.util.PushUtilTest;
import net.sf.json.JSONObject;


@Service("pushMessageServiceImpl")
public class PushMessageServiceImpl implements PushMessageService {
	
	private static final Logger LOGGER = Logger.getLogger(PushMessageServiceImpl.class);
	
	@Autowired
	private PushMessageMapper pushMessageMapper;
	
	
	/**
     * 推送初始化
     * @return
     */
    private BaiduPushClient initPushClient(int sid,int appcert){
        // 1. get apiKey and secretKey from developer console
        PushKeyPair pair = null;
        Map map = pushMessageMapper.selectPushKey(sid,appcert);
        pair = new PushKeyPair(String.valueOf(map.get("api_key")), String.valueOf(map.get("secret_key")));
//        System.out.println("查询uchome_app_pushkey表 "+map.get("api_key")+" + "+map.get("secret_key"));
//        String api_key = "M8CGZ7EGuxTDn26LijTbjrfZ";
//        String secret_key = "174d1143fe1b7eadacb414e5a7e20b80";
//        pair = new PushKeyPair(api_key,secret_key);

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
	 * 推送消息给所有设备，广播推送。(无URL)
	 * @param title
	 * @param description
	 * @param description
	 * @param messageType
	 * @param sid
	 * @return appcert  
	 * @throws PushClientException
	 * @throws PushServerException
	 */
    public Map<String, Object> pushMsgToAll(String title, String description,int messageType,int sid,int appcert){
       
    	BaiduPushClient pushClient = initPushClient(sid,appcert);//推送初始化
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
            message.put("description",description);//通知文本内容，不能为空。
            message.put("notification_builder_id", 0);//android客户端自定义通知样式，如果没有设置默认为0。
            message.put("notification_basic_style", 4);//只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：
        }else if(deviceType == 4){ //iOS 设备
            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", description);
            jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，指定通知展现时伴随的提醒音文件名
            message.put("aps", jsonAPS);
        }

        try {
            PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMsgExpires(new Integer(3600)) //默认 new Integer(3600)
                    .addMessageType(messageType) // 0:透传消息  1：通知  默认是 0
                    .addMessage(message.toString())//添加透传消息
                    .addDeviceType(deviceType);

            // 5. http request
            PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
            
            // Http请求返回值解析
            LOGGER.debug(String.format("msgId: %s, sendTime: %d",response.getMsgId(), response.getSendTime()));
            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());
//            jsonMap.put("timerId", response.getTimerId()); //推送定时消息时，返回该字段，标识定时任务。
        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    }
    
	/**
	 * 推送消息给所有设备，广播推送。(根据open_type判断是否取URL参数)
	 * @param title
	 * @param description
	 * @param description
	 * @param messageType
	 * @param sid
	 * @return appcert  
	 * @return open_type  
	 * @return url  
	 * @throws PushClientException
	 * @throws PushServerException
	 */
    public Map<String, Object> pushMsgToAllByOpenType(String title, String description,int messageType,int sid,int appcert,Integer open_type,String url){
       
    	BaiduPushClient pushClient = initPushClient(sid,appcert);//推送初始化
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
            message.put("description",description);//通知文本内容，不能为空。
            message.put("notification_builder_id", 0);//android客户端自定义通知样式，如果没有设置默认为0。
            message.put("notification_basic_style", 4);//只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：
            message.put("open_type", open_type);//默认为0    open_type：点击通知后的行为(1：打开Url; 2：自定义行为；)。 open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用
            message.put("url", url);//需要打开的Url地址，open_type为1时才有效
        }else if(deviceType == 4){ //iOS 设备
            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", description);
            jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，指定通知展现时伴随的提醒音文件名
            message.put("aps", jsonAPS);
        }

        try {
            PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMsgExpires(new Integer(3600)) //默认 new Integer(3600)
                    .addMessageType(messageType) // 0:透传消息  1：通知  默认是 0
                    .addMessage(message.toString())//添加透传消息
                    .addDeviceType(deviceType);

            // 5. http request
            PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
            
            // Http请求返回值解析
            LOGGER.debug(String.format("msgId: %s, sendTime: %d",response.getMsgId(), response.getSendTime()));
            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());
//            jsonMap.put("timerId", response.getTimerId()); //推送定时消息时，返回该字段，标识定时任务。
        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
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
	public Map<String, Object> pushMsgToSingleDevice(String title,String description, int messageType,int sid,int appcert,String channelId,Integer open_type, String url){ 
	 	
		BaiduPushClient pushClient = initPushClient(sid,appcert);//推送初始化
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        JSONObject message = new JSONObject();
        int deviceType = 0;
        if(appcert==0) {
        	deviceType = 3;
        }else if(appcert==299 || appcert==99){
        	deviceType = 4;
        }
        if(deviceType == 3){ // Android 设备
            message.put("title", title);//标题
            message.put("description",description);//通知文本内容，不能为空
            message.put("notification_builder_id", 0);//android客户端自定义通知样式，如果没有设置默认为0。
            message.put("notification_basic_style", 4);//只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：
            message.put("open_type", open_type);//默认为0    open_type：点击通知后的行为(1：打开Url; 2：自定义行为；)。 open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用
            message.put("url", url);//需要打开的Url地址，open_type为1时才有效
        }else if(deviceType == 4){ //iOS 设备
            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", description);
            jsonAPS.put("sound", "ttt");// 设置通知铃声样式，指定通知展现时伴随的提醒音文件名
            message.put("aps", jsonAPS);
        }
        try {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                    .addChannelId(channelId)
                    .addMsgExpires(3600) // message有效时间
                    .addMessageType(messageType)// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
                    .addMessage(message.toString())
                    .addDeviceType(deviceType);// deviceType => 3:android, 4:ios

            // 5. http request
            PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());
            
            LOGGER.debug("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
        }catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
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
		
		BaiduPushClient pushClient = initPushClient(sid,appcert);//初始化
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        try {
        	int deviceType = 0;
            if(appcert==0) {
            	deviceType = 3;
            }else if(appcert==299 || appcert==99){
            	deviceType = 4;
            }
            // 4. specify request arguments
            CreateTagRequest request = new CreateTagRequest().addTagName(tagName).addDeviceType(deviceType);
            // 5. http request
            CreateTagResponse response = pushClient.createTag(request);
            jsonMap.put("result", response.getResult());
            
            System.out.println("创建标签组成功");
            System.out.println(String.format("tagName: %s, result: %d",response.getTagName(), response.getResult()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
	}
	
	/**
	 * 删除用户自定义的标签组
	 * @param tagName
	 * @param sid
	 * @param appcert
	 * @param req
	 * @return
	 */
	@Override
	public Map<String, Object> deleteTag(int sid, int appcert, String tagName) {
		
		BaiduPushClient pushClient = initPushClient(sid,appcert);//初始化
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        try {
        	int deviceType = 0;
            if(appcert==0) {
            	deviceType = 3;
            }else if(appcert==299 || appcert==99){
            	deviceType = 4;
            }
            // 4. specify request arguments
            DeleteTagRequest   request = new DeleteTagRequest().addTagName(tagName).addDeviceType(deviceType);
            // 5. http request
            DeleteTagResponse  response = pushClient.deleteTag(request);
            jsonMap.put("result", response.getResult());
            System.out.println("删除标签组成功");
            System.out.println(String.format("tagName: %s, result: %d",response.getTagName(), response.getResult()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
	}
	
	/**
	 * 查询用户定义的标签组信息
	 * @param tagName
	 * @param sid
	 * @param appcert
	 * @param req
	 * @return
	 */
	@Override
	public Map<String, Object> queryTags(int sid, int appcert, String tagName) {
		
		BaiduPushClient pushClient = initPushClient(sid,appcert);//初始化
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        try {
        	int deviceType = 0;
            if(appcert==0) {
            	deviceType = 3;
            }else if(appcert==299 || appcert==99){
            	deviceType = 4;
            }
            // 4. specify request arguments
            QueryTagsRequest    request = new QueryTagsRequest().addTagName(tagName).addDeviceType(deviceType);
            // 5. http request
            QueryTagsResponse   response = pushClient.queryTags(request);
            
            // Http请求返回值解析
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("totalNum: " + response.getTotalNum() + "\n");
            if (null != response) {
                List<?> list = response.getTagsInfo();
                for (int i = 0; i < list.size(); i++) {
                    Object object = list.get(i);
                    if (object instanceof TagInfo) {
                        TagInfo tagInfo = (TagInfo) object;
                        strBuilder.append("List[" + i + "]: " + "tagId="
                                + tagInfo.getTagId() + ",tag="
                                + tagInfo.getTagName() + ",info="
                                + tagInfo.getInfo() + ",type="
                                + tagInfo.getType() + ",creatTime="
                                + tagInfo.getCreateTime() + "\n");
                    }
                }
                System.out.println(strBuilder.toString());
                jsonMap.put("strBuilder",strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
	}

	/**
	 * 向标签组中批量添加设备
	 * @param tagName
	 * @param sid
	 * @param appcert
	 * @param req
	 * @return
	 */
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
            AddDevicesToTagResponse response = pushClient.addDevicesToTag(request);
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
                jsonMap.put("result", strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
	} 
	
	/**
	 * 从标签组中批量删除设备
	 * @param tagName
	 * @param sid
	 * @param appcert
	 * @param req
	 * @return
	 */
	@Override
	public Map<String, Object> deleteDevicesFromTag(int sid, int appcert, String tagName, String[] channelArray) {
		
		BaiduPushClient pushClient = initPushClient(sid,appcert);
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        try {
        	int deviceType = 0;
            if(appcert==0) {
            	deviceType = 3;
            }else if(appcert==299||appcert==99){
            	deviceType = 4;
            }
            DeleteDevicesFromTagRequest  request = new DeleteDevicesFromTagRequest ()
                    .addTagName(tagName).addChannelIds(channelArray)
                    .addDeviceType(deviceType);
            
            // 5. http request
            DeleteDevicesFromTagResponse  response = pushClient.deleteDevicesFromTag(request);
            
            // Http请求返回值解析
            if (null != response) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("devicesInTag：{");
                List<?> devicesInfo = response.getDevicesInfoAfterDel();
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
                jsonMap.put("result", strBuilder.toString());
            }
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
	} 
    
    /**
     * 向绑定到标签中的用户推送消息，即普通组播。(根据open_type判断是否取URL参数)
     * @param method 是指某功能的命名
     * @param title 是指标题文言
     * @param description 是指描述文言
     * @param extra 数组
     * @param uidArray
     * @return
     */											
	public Map<String, Object> pushMsgToTagByOpenType(String title, String description,int messageType,int sid,int appcert,String tagName,Integer open_type, String url){ 
		
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
            message.put("title", title);//标题
            message.put("description",description);//通知文本内容，不能为空
            message.put("notification_builder_id", 0);//android客户端自定义通知样式，如果没有设置默认为0。
            message.put("notification_basic_style", 4);//只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：
            message.put("open_type", open_type);//默认为0    open_type：点击通知后的行为(1：打开Url; 2：自定义行为；)。 open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用
            message.put("url", url);//需要打开的Url地址，open_type为1时才有效
        }else if(deviceType == 4){ //iOS 设备
            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", description);
            jsonAPS.put("sound", "ttt");// 设置通知铃声样式，指定通知展现时伴随的提醒音文件名
            message.put("aps", jsonAPS);
        }

        try {
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName(tagName)
                    .addMsgExpires(new Integer(3600))// message有效时间
                    .addMessageType(messageType)// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
                    .addMessage(message.toString())
                    .addDeviceType(deviceType);// deviceType => 3:android, 4:ios

            // 5. http request
            PushMsgToTagResponse  response = pushClient.pushMsgToTag(request);
            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());

            LOGGER.debug("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
        }catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    }
	
    /**
     * 向绑定到标签中的用户推送消息，即普通组播。(无URL)
     * @param method 是指某功能的命名
     * @param title 是指标题文言
     * @param description 是指描述文言
     * @param extra 数组
     * @param uidArray
     * @return
     */											
	public Map<String, Object> pushMsgToTag(String title, String description,int messageType,int sid,int appcert,String tagName){ 
		
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
            message.put("title", title);//标题
            message.put("description",description);//通知文本内容，不能为空
            message.put("notification_builder_id", 0);//android客户端自定义通知样式，如果没有设置默认为0。
            message.put("notification_basic_style", 4);//只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：
        }else if(deviceType == 4){ //iOS 设备
            JSONObject jsonAPS = new JSONObject();
            jsonAPS.put("alert", description);
            jsonAPS.put("sound", "ttt");// 设置通知铃声样式，指定通知展现时伴随的提醒音文件名
            message.put("aps", jsonAPS);
        }

        try {
            PushMsgToTagRequest request = new PushMsgToTagRequest()
                    .addTagName(tagName)
                    .addMsgExpires(new Integer(3600))// message有效时间
                    .addMessageType(messageType)// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
                    .addMessage(message.toString())
                    .addDeviceType(deviceType);// deviceType => 3:android, 4:ios

            // 5. http request
            PushMsgToTagResponse  response = pushClient.pushMsgToTag(request);
            jsonMap.put("msgId", response.getMsgId());
            jsonMap.put("sendTime",response.getSendTime());

            LOGGER.debug("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
        }catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
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
					e1.printStackTrace();
				}
            } else {
				String errorDescription	 = PushUtilTest.getErrorCode(e.getErrorCode());
				jsonMap.put("errorDescription","错误描述: "+errorDescription);
				jsonMap.put("requestId",e.getRequestId());
				jsonMap.put("errorCode", e.getErrorCode());
				LOGGER.error(String.format("requestId: %d, errorCode: %d, errorMsg: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return jsonMap;
    }
	
	/**
	 * 设置推送参数,(定时推送功能,增加).
	 * @param method
	 * @param title
	 * @param description
	 * @param extra
	 * @param sid
	 * @return
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	@Override
	public Map<String,Object> addPushParam(String title, String description, int messageType, int sid, int appcert, String channelIds, String tagName, int identification, int sendTime,Integer open_type, String url){
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			pushMessageMapper.addPushParam(title,description,messageType,sid,appcert,channelIds,tagName,identification,sendTime,open_type,url);
			jsonMap.put("result", "0");
		}catch(Exception e){
			jsonMap.put("errorCode", e.getMessage());
			e.getMessage();
		}
		return jsonMap;
	}
	
	/**
	 * 删除定时推送
	 */
	@Override
	public Map<String, Object> deletePushById(int id) {
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			pushMessageMapper.deletePushById(id);
			jsonMap.put("rusult", 0);
		}catch(Exception e) {
			jsonMap.put("errorCode", e.getMessage());
			System.out.println(e.getMessage());
		}
		return jsonMap;
	}
	
	/**
	 * 根据Id与 identification修改
	 */
	@Override
	public Map<String, Object> updatePushParam(int id, String title, String description, String messageType, int sid, int appcert, String channelIds, String tagName, int identification, int sendTime,Integer open_type,String url) {
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			pushMessageMapper.updatePushParam(id,title,description,messageType,sid,appcert,channelIds,tagName,identification,sendTime,open_type,url);
			jsonMap.put("result", 0);
		}catch(Exception e) {
			jsonMap.put("errorCode", e.getMessage());
			System.out.println(e.getMessage());
		}
		return jsonMap;
	}
	
	/**
	 * 	根据 title 与 identification 条件查询 模糊查询
	 */
	@Override
	public List<Map> queryPushParam(String title, Integer identification) {
		
		List jsonList = new ArrayList();
		try {
			jsonList = pushMessageMapper.queryPushParam(title,identification);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return jsonList;
	}

	/**
	 * 查询从当前时间开始,按当前时间排序
	 */
	@Override
	public Map queryDateTime() {
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap = pushMessageMapper.queryDateTime();
		return jsonMap;
	}





	


}
