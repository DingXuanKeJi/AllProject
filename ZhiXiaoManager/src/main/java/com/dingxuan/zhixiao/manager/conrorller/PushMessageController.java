package com.dingxuan.zhixiao.manager.conrorller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.dingxuan.zhixiao.manager.service.OtherService;
import com.dingxuan.zhixiao.manager.service.PushMessageService;

import net.sf.json.JSONObject;

@Controller  
@RequestMapping(value = "/pushMessageController")
public class PushMessageController{
	
	private static final Logger LOGGER = Logger
			.getLogger(PushMessageController.class);
	
	@Autowired  
    private PushMessageService pushMessageService;  
	
	
    /**
     * 向多个设备推送消息
     * @param method 是指某功能的命名
     * @param title 是指标题文言
     * @param description 是指描述文言
     * @param extra 数组
     * @param uidArray
     * @param sid
	 * @param appcert
	 * @param channelIdArray
     * @return
     */
	@RequestMapping(value="/pushMsgToSingleDevice")
    @ResponseBody  
    private Map<String, Object> pushMsgToSingleDevice(@RequestParam(value = "method")String method,
    		@RequestParam(value = "title")String title, @RequestParam(value = "description")String description,
    		@RequestParam(value = "extra")String extra, @RequestParam(value = "uidArray")String uidArray,
    		@RequestParam(value = "sid")int sid,@RequestParam(value = "appcert")int appcert,
    		@RequestParam(value = "channelIdArray")String channelIdArray,@RequestParam(value = "threadPool") int threadPool,
    		HttpServletRequest req){
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        String[] channelIds = channelIdArray.split(",");
        
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadPool);  
        fixedThreadPool.execute(new Runnable() {  
         public void run() { 
			synchronized ("index1") {  
				//res="试试"+i;
             }  
         }  
        });
        
        for(String channelId:channelIds) {
        	if(jsonMap.get("returnJson")!=null){
        		jsonMap.put("returnJson",jsonMap.get("returnJson")+pushMessageService.pushMsgToSingleDevice(method,title,description,extra,uidArray,sid,appcert,channelId).toString());
	    	}else{
	    		jsonMap.put("returnJson",pushMessageService.pushMsgToSingleDevice(method,title,description,extra,uidArray,sid,appcert,channelId));
	    	}
        	//jsonMap = pushMessageService.pushMsgToSingleDevice(method,title,description,extra,uid);
        }

        return jsonMap;
    } 
	
	/**
     * 推送消息给所有设备，广播推送。
     * @param method 是指某功能的命名
     * @param title 是指标题文言
     * @param description 是指描述文言
     * @param extra 数组
     * @param sidArray
     * @param sid
	 * @param appcert
	 * @param channelId
     * @return
     */
	@RequestMapping(value="/pushMsgToAll")   
    @ResponseBody  
    private Map<String, Object> pushMsgToAll(@RequestParam(value = "method")String method,
    		@RequestParam(value = "title")String title, @RequestParam(value = "description")String description,
    		@RequestParam(value = "extra")String extra, @RequestParam(value = "sidArray")String sidArray,
    		@RequestParam(value = "appcert")int appcert, HttpServletRequest req){  
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        String[] sids = sidArray.split(",");
        
        for(String sid:sids) {
        	if(jsonMap.get("returnJson")!=null){
        		jsonMap.put("returnJson",jsonMap.get("returnJson")+pushMessageService.pushMsgToAll(method,title,description,extra,sid,appcert).toString());
	    	}else{
	    		jsonMap.put("returnJson",pushMessageService.pushMsgToAll(method,title,description,extra,sid,appcert));
	    	}
        	//jsonMap = pushMessageService.pushMsgToSingleDevice(method,title,description,extra,uid);
        }

        return jsonMap;
    }
	
	/**
	 * 向绑定到tag中的用户推送消息，即普通组播。
	 * @param method
	 * @param title
	 * @param description
	 * @param extra
	 * @param cid
	 * @param sid
	 * @param appcert
	 * @param tagName
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/PushMsgToTag")   
    @ResponseBody  
    private Map<String, Object> PushMsgToTag(@RequestParam(value = "method")String method,
    		@RequestParam(value = "title")String title, @RequestParam(value = "description")String description,
    		@RequestParam(value = "extra")String extra, @RequestParam(value = "cid")String cid,
    		@RequestParam(value = "sid")int sid,@RequestParam(value = "appcert")int appcert,
    		@RequestParam(value = "tagName")String tagName,
    		HttpServletRequest req){  
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        jsonMap = pushMessageService.PushMsgToTag(method,title,description,extra,cid,sid,appcert,tagName);

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
	@RequestMapping(value="/CreateTag")   
    @ResponseBody  
    private Map<String, Object> CreateTag(@RequestParam(value = "tagName")String tagName,
    		@RequestParam(value = "sid")int sid,@RequestParam(value = "appcert")int appcert,
    		HttpServletRequest req){  
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();

        jsonMap = pushMessageService.CreateTag(sid,appcert,tagName);

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
	@RequestMapping(value="/addDevicesToTag")   
    @ResponseBody  
    private Map<String, Object> addDevicesToTag(@RequestParam(value = "tagName")String tagName,
    		@RequestParam(value = "sid")int sid,@RequestParam(value = "appcert")int appcert,
    		@RequestParam(value = "channelIds")String channelIds,
    		HttpServletRequest req){  
        
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        String[] channelArray = channelIds.split(",");

        jsonMap = pushMessageService.addDevicesToTag(sid,appcert,tagName,channelArray);

        return jsonMap;
    }
	
}
