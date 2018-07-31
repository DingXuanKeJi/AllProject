package com.dingxuan.zhixiao.manager.conrorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dingxuan.zhixiao.manager.service.PushMessageService;


@Controller  
@RequestMapping(value = "/pushMessageController")
public class PushMessageController {
	
	private static final Logger LOGGER = Logger.getLogger(PushMessageController.class);
	
	@Autowired  
    private PushMessageService pushMessageService; 
	
	/**
     * 推送消息给所有设备，广播推送。(无URL)
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
    public Map<String, Object> pushMsgToAll(
    		@RequestParam(value = "title")String title, //标题
    		@RequestParam(value = "description")String description,//描述
    		@RequestParam(value = "messageType")int messageType,//0:透传消息  1:通知
    		@RequestParam(value = "sid")int sid,//学校Id
    		@RequestParam(value = "appcert")int appcert,//根据appcert 与 sid 查询   apiKey secretKey 值
    		HttpServletRequest req
    ){
        Map<String, Object> jsonMap = new HashMap<String,Object>();
    	jsonMap = pushMessageService.pushMsgToAll(title,description,messageType,sid,appcert);
        
        return jsonMap;
    }
	
	/**
     * 推送消息给所有设备，广播推送。(根据open_type判断是否取URL参数)
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
	@RequestMapping(value="/pushMsgToAllByOpenType")
    @ResponseBody  
    public Map<String, Object> pushMsgToAllByOpenType(
    		@RequestParam(value = "title")String title, //标题
    		@RequestParam(value = "description")String description,//描述
    		@RequestParam(value = "messageType")int messageType,//0:透传消息  1:通知
    		@RequestParam(value = "sid")int sid,//学校Id
    		@RequestParam(value = "appcert")int appcert,//根据appcert 与 sid 查询   apiKey secretKey 值
    		@RequestParam(value = "open_type") Integer open_type,//open_type：点击通知后的行为(1：打开Url; 2：自定义行为；)。 open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用。
    		@RequestParam(value = "url") String url,//需要打开的Url地址，open_type为1时才有效
    		HttpServletRequest req
    ){
        Map<String, Object> jsonMap = new HashMap<String,Object>();
    	jsonMap = pushMessageService.pushMsgToAllByOpenType(title,description,messageType,sid,appcert,open_type,url);
        
        return jsonMap;
    }
	
    /**
     * 向单个设备推送消息
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
	@RequestMapping(value="/pushMsgToSingleDevice",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody  
    public Map<String, Object> pushMsgToSingleDevice(
    		@RequestParam(value = "title")String title,  //标题
    		@RequestParam(value = "description")String description,//描述
    		@RequestParam(value = "messageType")int messageType,//0:透传消息  1:通知
    		@RequestParam(value = "sid")int sid,//用户学校Id
    		@RequestParam(value = "appcert")int appcert,//根据appcert 与 sid 查询   apiKey secretKey 值
    		@RequestParam(value = "channelIdArray")String channelIdArray,//唯一手机Id
    		@RequestParam(value = "open_type",required=false)Integer open_type,//open_type：点击通知后的行为(1：打开Url; 2：自定义行为；)。 open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用。
    		@RequestParam(value = "url",required=false) String url,//需要打开的Url地址，open_type为1时才有效
    		HttpServletRequest req
    ){  
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        String[] channelIds = channelIdArray.split(",");
       
       	for(int i=0;i<channelIds.length;i++) {
       		jsonMap = pushMessageService.pushMsgToSingleDevice(title,description,messageType,sid,appcert,channelIds[i],open_type,url);
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
	@RequestMapping(value="/createTag")   
    @ResponseBody  
    public Map<String, Object> createTag(
    		@RequestParam(value = "tagName")String tagName,//标签名
    		@RequestParam(value = "sid")int sid,
    		@RequestParam(value = "appcert")int appcert,
    		HttpServletRequest req
    ){  
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        jsonMap = pushMessageService.CreateTag(sid,appcert,tagName);
        
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
	@RequestMapping(value="/deleteTag")   
    @ResponseBody  
    public Map<String, Object> deleteTag(
    		@RequestParam(value = "tagName")String tagName,//标签名
    		@RequestParam(value = "sid")int sid,
    		@RequestParam(value = "appcert")int appcert,
    		HttpServletRequest req
    ){  
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        jsonMap = pushMessageService.deleteTag(sid,appcert,tagName);

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
	@RequestMapping(value="/queryTags")   
    @ResponseBody  
    public Map<String, Object> queryTags(
    		@RequestParam(value = "tagName",required=false)String tagName,//标签名
    		@RequestParam(value = "sid")int sid,
    		@RequestParam(value = "appcert")int appcert,
    		HttpServletRequest req
    ){  
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        jsonMap = pushMessageService.queryTags(sid,appcert,tagName);
        
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
    public Map<String, Object> addDevicesToTag(
    		@RequestParam(value = "tagName")String tagName,//标签名
    		@RequestParam(value = "sid")int sid,
    		@RequestParam(value = "appcert")int appcert,
    		@RequestParam(value = "channelIds")String channelIds,//channelIds  数量最多是10个
    		HttpServletRequest req
    ){  
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        String[] channelArray = channelIds.split(",");
        jsonMap = pushMessageService.addDevicesToTag(sid,appcert,tagName,channelArray);
        
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
	@RequestMapping(value="/deleteDevicesFromTag")   
    @ResponseBody  
    public Map<String, Object> deleteDevicesFromTag (
    		@RequestParam(value = "tagName")String tagName,//标签名
    		@RequestParam(value = "sid")int sid,
    		@RequestParam(value = "appcert")int appcert,
    		@RequestParam(value = "channelIds")String channelIds,//channelIds  数量最多是10个
    		HttpServletRequest req
    ){  
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        String[] channelArray = channelIds.split(",");
        jsonMap = pushMessageService.deleteDevicesFromTag(sid,appcert,tagName,channelArray);

        return jsonMap;
	}
	
	/**
	 * 向绑定到标签中的用户推送消息，即普通组播。(根据open_type判断是否取URL参数)
	 * @param title
	 * @param description
	 * @param messageType
	 * @param sid
	 * @param appcert
	 * @param tagName
	 * @param open_type
	 * @param url
	 * @return
	 */
	@RequestMapping(value="/pushMsgToTagByOpenType")   
    @ResponseBody  
    public Map<String, Object> pushMsgToTagByOpenType(
    		@RequestParam(value = "title")String title,  //标题
    		@RequestParam(value = "description")String description,//描述
    		@RequestParam(value = "messageType")int messageType,//0:透传消息  1:通知
    		@RequestParam(value = "sid")int sid,//用户学校Id
    		@RequestParam(value = "appcert")int appcert,//根据appcert 与 sid 查询   apiKey secretKey 值
    		@RequestParam(value = "tagName")String tagName,//标签名
    		@RequestParam(value = "open_type")Integer open_type,//open_type：点击通知后的行为(1：打开Url; 2：自定义行为；)。 open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用。
    		@RequestParam(value = "url") String url,//需要打开的Url地址，open_type为1时才有效
    		HttpServletRequest req
    ){  
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        jsonMap = pushMessageService.pushMsgToTagByOpenType(title,description,messageType,sid,appcert,tagName,open_type,url);

        return jsonMap;
    }
	
	/**
	 * 向绑定到标签中的用户推送消息，即普通组播。(无URL)
	 * @param title
	 * @param description
	 * @param messageType
	 * @param sid
	 * @param appcert
	 * @param tagName
	 * @param open_type
	 * @param url
	 * @return
	 */
	@RequestMapping(value="/pushMsgToTag")   
    @ResponseBody  
    public Map<String, Object> pushMsgToTag(
    		@RequestParam(value = "title")String title,  //标题
    		@RequestParam(value = "description")String description,//描述
    		@RequestParam(value = "messageType")int messageType,//0:透传消息  1:通知
    		@RequestParam(value = "sid")int sid,//用户学校Id
    		@RequestParam(value = "appcert")int appcert,//根据appcert 与 sid 查询   apiKey secretKey 值
    		@RequestParam(value = "tagName")String tagName,//标签名
    		HttpServletRequest req
    ){  
        Map<String, Object> jsonMap = new HashMap<String,Object>();
        jsonMap = pushMessageService.pushMsgToTag(title,description,messageType,sid,appcert,tagName);

        return jsonMap;
    }
	
	/**
	 * 设置推送参数,(定时推送功能,增加).
	 * @param title
	 * @param description
	 * @param messageType
	 * @param sid
	 * @param appcert
	 * @param endTime
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/addPushParam")
	@ResponseBody
	public Map<String,Object>addPushParam(
    		@RequestParam(value = "title")String title, //标题
    		@RequestParam(value = "description")String description,//描述
    		@RequestParam(value = "messageType")int messageType,//0:透传消息  1:通知
    		@RequestParam(value = "sid")int sid,//学校Id
    		@RequestParam(value = "appcert")int appcert,//根据appcert 与 sid 查询   apiKey secretKey 值
    		@RequestParam(value = "channelIds",required=false)String channelIds,//手机
    		@RequestParam(value = "tagName",required=false)String tagName,//标签名字(组播)
    		@RequestParam(value = "identification")int identification,//区分推送类型   0广播推送 1单条推送 2组推
    		@RequestParam(value = "sendTime")int sendTime,//设置推送时间
    		@RequestParam(value = "open_type",required=false) Integer open_type,//open_type：点击通知后的行为(1：打开Url; 2：自定义行为；)。 open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用。
    		@RequestParam(value = "url",required=false) String url,//需要打开的Url地址，open_type为1时才有效
    		HttpServletRequest req
	){
		Map <String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap = pushMessageService.addPushParam(title,description,messageType,sid,appcert,channelIds,tagName,identification,sendTime,open_type,url);
		
		return jsonMap;
	}
	
	/**
	 * 删除定时推送
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletePushParam")
	@ResponseBody
	public Map<String,Object>deletePushParam(@RequestParam(value = "id")int id){
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap = pushMessageService.deletePushById(id);
		return jsonMap;
	}
	
	/**
	 * 根据Id与identification 修改
	 * @param id
	 * @param title
	 * @param description
	 * @param messageType
	 * @param sid
	 * @param appcert
	 * @param channelIds
	 * @param tagName
	 * @param identification
	 * @param sendTime
	 * @return
	 */
	@RequestMapping(value = "/updatePushParam")
	@ResponseBody
	public Map<String,Object> updatePushParam(
			@RequestParam(value = "id") int id,//id
			@RequestParam(value = "title") String title,//标题
			@RequestParam(value = "description") String description,//描述
			@RequestParam(value = "messageType") String messageType,//0透传消息 1通知
			@RequestParam(value = "sid") int sid,//学校
			@RequestParam(value = "appcert") int appcert,//根据appcert与sid查询 apiKey secretKey 值
			@RequestParam(value = "channelIds",required = false) String channelIds,//手机
			@RequestParam(value = "tagName",required = false) String tagName,//标签名(组播)
			@RequestParam(value = "identification") int identification,//区分推送类型  0广播推送 1单条推送 2组播
			@RequestParam(value = "sendTime") int sendTime,//修改推送时间
    		@RequestParam(value = "open_type",required=false) Integer open_type,//open_type：点击通知后的行为(1：打开Url; 2：自定义行为；)。 open_type =0，只回调onNotificationClicked方法，不做其他操作； open_type = 1，url != null：打开网页； open_type = 2，pkg_content = null：直接打开应用； open_type = 2，pkg_content != null：自定义动作打开应用。
    		@RequestParam(value = "url",required=false) String url//需要打开的Url地址，open_type为1时才有效
	){
		Map<String,Object>jsonMap = new HashMap<String,Object>();
		jsonMap = pushMessageService.updatePushParam(id,title,description,messageType,sid,appcert,channelIds,tagName,identification,sendTime,open_type,url);
		
		return jsonMap;
	}
	
	/**
	 * 根据 title 与 identification 条件查询 模糊查询
	 * @param title
	 * @param identification
	 * @return
	 */
	@RequestMapping(value = "/queryPushParam")
	@ResponseBody
	public List<Map> queryPushParam(
			@RequestParam(value = "title",required = false) String title,//标题
			@RequestParam(value = "identification",required = false) Integer identification//区分推送类型 0广播推送 1单条推送 2组播
	){
		List jsonList = new ArrayList();
		String titles = "";
		if(title != null) {
			titles = "%"+title+"%";
		}
		jsonList = pushMessageService.queryPushParam(titles,identification);
		
		return jsonList;
	}
	

	

	
	
	
	

	
}
