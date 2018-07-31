package com.dingxuan.zhixiao.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;


public interface PushMessageMapper {
	
    //根据appcert 与 sid 查询   apiKey secretKey 值
    Map selectPushKey(@Param("sid")int sid,@Param("appcert")int appcert);

    //设置推送参数,(定时推送功能,增加).
	Map addPushParam(@Param("title")String title, @Param("description")String description, @Param("messageType")int messageType, @Param("sid")int sid, @Param("appcert")int appcert,@Param("channelIds")String channelIds,@Param("tagName")String tagName,@Param("identification")int identification,@Param("sendTime")int sendTime, @Param("open_type") Integer open_type, @Param("url")String url);

	//删除定时推送
	Map deletePushById(@Param("id") int id);
	
	//根据Id与 identification修改
	Map updatePushParam(@Param("id") int id,@Param("title") String title,@Param("description") String description,@Param("messageType") String messageType,@Param("sid") int sid,@Param("appcert") int appcert,@Param("channelIds") String channelIds,@Param("tagName") String tagName,@Param("identification") int identification,@Param("sendTime") int sendTime,@Param("open_type") Integer open_type,@Param("url") String url);																								
	
	//根据 title 与 identification 条件查询 模糊查询
	List<Map> queryPushParam(@Param("titles") String titles,@Param("identification")  Integer identification);

	//查询从当前时间开始,按当前时间排序
	Map queryDateTime();


	
}