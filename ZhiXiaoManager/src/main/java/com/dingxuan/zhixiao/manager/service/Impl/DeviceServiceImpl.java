package com.dingxuan.zhixiao.manager.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.dingxuan.zhixiao.dao.DeviceMapper;
import com.dingxuan.zhixiao.manager.service.DeviceService;
import com.dingxuan.zhixiao.util.CallShangXueLe;
import com.dingxuan.zhixiao.util.CardConstants;
import com.dingxuan.zhixiao.util.DeviceUtil;
import com.dingxuan.zhixiao.util.DeviceVerification;
import com.dingxuan.zhixiao.util.MD5Util;

import net.sf.json.JSONObject;

@Service
public class DeviceServiceImpl implements DeviceService{
	private static final Logger LOGGER = Logger.getLogger(DeviceServiceImpl.class);
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	
	/**************************************Begin:对接  平台端  接口**************************************/
	//JSON模式的String转map方法
	public Map<Object, Object> jsonToMap(String jsonStr){
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
	
	//推送设备电子围栏触发报警
	@Override
	public String pushDeviceFence(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,LO,LA,POSDESC,LOCTIME,BAT,GSM,GPS,TYPE,FENCETYPE,FENCENID";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.pushDeviceFence(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}

	//推送设备电量不足报警
	@Override
	public String PushDeviceLowbat(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,LO,LA,POSDESC,LOCTIME,BAT,GSM,GPS,TYPE";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.PushDeviceLowbat(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}

	//推送学生步数
	@Override
	public String PushDeviceStep(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,STEPS,TIME";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.PushDeviceStep(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}
	
	//推送学生考勤记录
	@Override
	public String PushDeviceCHECK(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,LO,LA,POSDESC,LOCTIME,BAT,GSM,GPS,TYPE,SCHOOLID,DIR";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.PushDeviceCHECK(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}
	
	//学生 SOS 求救记录上报
	@Override
	public String PushDeviceSOS(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,LO,LA,POSDESC,LOCTIME,BAT,GSM,GPS,TYPE";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.PushDeviceSOS(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}
	
	//推送设备脱落上报
	@Override
	public String PushDeviceStatic(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,LO,LA,POSDESC,LOCTIME,BAT,GSM,GPS,TYPE";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.PushDeviceStatic(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}
	
	//推送设备最新位置
	@Override
	public String PushDeviceCurpos(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,LO,LA,POSDESC,LOCTIME,BAT,GSM,GPS,TYPE";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.PushDeviceCurpos(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}
	
	//推送设备通话记录
	@Override
	public String PushDeviceCallnote(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,CALLTIME,LENGTH,PHONE,CALLDIR";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.PushDeviceCallnote(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}
	
	//推送设备欠费短信
	@Override
	public String PushDeviceOverdue(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,DATA";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			deviceMapper.PushDeviceOverdue(map);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}


	//接收平台命令设置的结果
	@Override
	public String PushWriteDeviceResult(String jsonStr) {
		String jsonResult = null;
		
		Map<Object,Object> map = jsonToMap(jsonStr);
		
		deviceMapper.PushWriteDeviceResult(map);
		
		//此处需要进行key,sign,timestamp的验证
		String strOrder = "DEVICENUM,CMDTIMESTAMP,CMDNAME,CMDRESULT";
		int verRes = DeviceVerification.allVerification(map, strOrder);
		if(verRes != 0){
			return "{\"RESULT\":\"1\",\"ERRORCODE\":\""+verRes+"\",\"ERRORDESC\":\"\"}";
		}
		
		try{
			//处理回调函数的一系列逻辑，判断回调对应的是哪个命令
			Map<Object, Object> jsonMap = jsonToMap(jsonStr);
			String cmdName = (String) jsonMap.get("CMDNAME");
			if(cmdName != null && !cmdName.equals("null") && !cmdName.equals("")) {
				if(cmdName.equals("PushSchoolCenter")) {
					deviceMapper.ResultOfPushSchoolCenter(jsonMap);
				}else if(cmdName.equals("PushFenceRound")) {
					deviceMapper.ResultOfPushFenceRound(jsonMap);
				}else if(cmdName.equals("PushDeviceFamily")) {
					deviceMapper.ResultOfPushDeviceFamily(jsonMap);
				}else if(cmdName.equals("PushDevPosInterval")) {
					deviceMapper.ResultOfPushDevPosInterval(jsonMap);
				}else if(cmdName.equals("PushDevWorkMode")) {
					deviceMapper.ResultOfPushDevWorkMode(jsonMap);
				}else {
					return jsonResult = CardConstants.CMDNAME_ERROR;
				}
				deviceMapper.PushWriteDeviceResult(map);
				jsonResult = CardConstants.RESULT_SUCCESS;
			}else {
				jsonResult = CardConstants.SERVICE_ERROR;
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		
		return jsonResult;
	}
	

	/**************************************End:对接  平台端   接口**************************************/
	
	
	/**************************************Begin:用户→平台 接口**************************************/
	//设置学校经纬度  PushSchoolCenter
	/* @Param 	平台文档中该方法所列出的变量，除去KEY和SIGN的所有变量所组成的json形式的String
	 * @return 	平台端调用完成后返回的String信息会直接返回给前台
	 */
	@Override
	public String PushSchoolCenter(String jsonStr) {
		//获取厂商的所属密码
		String appSecret = deviceMapper.keyVerfication(CardConstants.APP_KEY).get(0);
		//由前台所得的变量转Map格式
		Map<Object,Object> jsonMap = jsonToMap(jsonStr);
		
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
		
		Map<Object,Object> platformMap = jsonToMap(platformRes);
		if(platformMap.get("RESULT") != null && !platformMap.get("RESULT").equals("null") && platformMap.get("RESULT").equals("0")){
			//判断返回的RESULT = 0 ,即调用成功时,将这组数据同步到知校端DB中 并将返回的String返回给前台，否则将返回的String直接返回给前台
			deviceMapper.PushSchoolCenter(jsonMap);
		}
		return platformRes;
	}

	//设置圆形围栏数据
	/*
	 * @Param 	平台文档中该方法所列出的变量，除去KEY和SIGN的所有变量所组成的json形式的String
	 * @return 	平台端调用完成后返回的String信息会直接返回给前台
	 */
	@Override
	public String PushFenceRound(String jsonStr) {
		String appSecret = deviceMapper.keyVerfication(CardConstants.APP_KEY).get(0);
		
		Map<Object,Object> jsonMap = jsonToMap(jsonStr);
		
		//StringBuffer sb = new StringBuffer(appSecret);
		String strOrder = "DEVICENUM,LO,LA,RADIUS,FENCEID,FENCETYPE,FENCENAME,OPTTYPE";
		String splicingStr = DeviceUtil.SplicingString(jsonMap, strOrder, appSecret);
		String getSign = MD5Util.MD5(splicingStr);
		getSign.toUpperCase();
		jsonMap.put("KEY", CardConstants.APP_KEY);
		jsonMap.put("SIGN", getSign);
		String jsonRes = JSONUtils.toJSONString(jsonMap).toString();
		String platformRes = CallShangXueLe.callShangxuela(CardConstants.PLATFORM_URL, "PushFenceRound", jsonRes);
		
		Map<Object,Object> platformMap = jsonToMap(platformRes);
		if(platformMap.get("RESULT") != null && !platformMap.get("RESULT").equals("null") && platformMap.get("RESULT").equals("0")){
			deviceMapper.PushFenceRound(jsonMap);
		}
		return platformRes;
	}

	//设置亲情号码数据
	/*
	 * @Param 	平台文档中该方法所列出的变量，除去KEY和SIGN的所有变量所组成的json形式的String
	 * @return 	平台端调用完成后返回的String信息会直接返回给前台
	 */
	@Override
	public String PushDeviceFamily(String jsonStr) {
		String appSecret = deviceMapper.keyVerfication(CardConstants.APP_KEY).get(0);
		
		Map<Object,Object> jsonMap = jsonToMap(jsonStr);
		
		//StringBuffer sb = new StringBuffer(appSecret);
		String strOrder = "DEVICENUM,PHONE,NAME,POS,FAMILYKEY,OPTTYPE";
		String getSign = MD5Util.MD5(DeviceUtil.SplicingString(jsonMap, strOrder, appSecret)).toUpperCase();
		
		jsonMap.put("KEY", CardConstants.APP_KEY);
		jsonMap.put("SIGN", getSign);
		String platformRes = CallShangXueLe.callShangxuela(CardConstants.PLATFORM_URL, "PushDeviceFamily", JSONUtils.toJSONString(jsonMap).toString());
		
		Map<Object,Object> platformMap = jsonToMap(platformRes);
		if(platformMap.get("RESULT") != null && !platformMap.get("RESULT").equals("null") && platformMap.get("RESULT").equals("0")){
			deviceMapper.PushDeviceFamily(jsonMap);
		}
		return platformRes;
	}
	
	//设置设备上报位置的时间间隔
	@Override
	public String PushDevPosInterval(String jsonStr) {
		String appSecret = deviceMapper.keyVerfication(CardConstants.APP_KEY).get(0);
		
		Map<Object,Object> jsonMap = jsonToMap(jsonStr);
		
		//StringBuffer sb = new StringBuffer(appSecret);
		String strOrder = "DEVICENUM,INTERVAL";
		String getSign = MD5Util.MD5(DeviceUtil.SplicingString(jsonMap, strOrder, appSecret)).toUpperCase();
		
		jsonMap.put("KEY", CardConstants.APP_KEY);
		jsonMap.put("SIGN", getSign);
		String platformRes = CallShangXueLe.callShangxuela(CardConstants.PLATFORM_URL, "PushDevPosInterval", JSONUtils.toJSONString(jsonMap).toString());
		
		Map<Object,Object> platformMap = jsonToMap(platformRes);
		if(platformMap.get("RESULT") != null && !platformMap.get("RESULT").equals("null") && platformMap.get("RESULT").equals("0")){
			deviceMapper.PushDevPosInterval(jsonMap);
		}
		return platformRes;
	}
		
	//设置设备工作模式
	@Override
	public String PushDevWorkMode(String jsonStr) {
		String appSecret = deviceMapper.keyVerfication(CardConstants.APP_KEY).get(0);
		
		Map<Object,Object> jsonMap = jsonToMap(jsonStr);
		
		//StringBuffer sb = new StringBuffer(appSecret);
		String strOrder = "DEVICENUM,MODE";
		String getSign = MD5Util.MD5(DeviceUtil.SplicingString(jsonMap, strOrder, appSecret)).toUpperCase();
		
		jsonMap.put("KEY", CardConstants.APP_KEY);
		jsonMap.put("SIGN", getSign);
		String platformRes = CallShangXueLe.callShangxuela(CardConstants.PLATFORM_URL, "PushDevWorkMode", JSONUtils.toJSONString(jsonMap).toString());
		
		Map<Object,Object> platformMap = jsonToMap(platformRes);
		if(platformMap.get("RESULT") != null && !platformMap.get("RESULT").equals("null") && platformMap.get("RESULT").equals("0")){
			deviceMapper.PushDevWorkMode(jsonMap);
		}
		return platformRes;
	}	
	

	/**************************************End:用户→平台 接口**************************************/
	
	
	/**************************************Begin:对接  知校端  接口**************************************/
	
	//将学生与学生证进行绑定0,解绑1,更改2
	@Override
	public String StudentBindingDevice(String jsonStr) {
		String jsonResult = null;
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		try {
			deviceMapper.StudentBindingDevice(jsonMap);
			jsonResult = CardConstants.RESULT_SUCCESS;
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			jsonResult = CardConstants.SERVICE_ERROR;
		}
		return jsonResult;
	}

	//查询学生所绑定的设备
	@Override
	public Map<Object, Object> FindStudentDevice(String jsonStr) {
		Map<Object, Object> resMap = new HashMap<>();
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		try {
			List<Map<Object, Object>> list = deviceMapper.FindStudentDevice(jsonMap);
			if(list.size() != 0) {
				resMap = list.get(0);
			}
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resMap;
	}
	
	//查询设备最新位置
	@Override
	public Map<Object, Object> ShowDeviceCurpos(String jsonStr) {
		Map<Object, Object> jsonResult = new HashMap<>();
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		try {
			List<Map<Object, Object>> list = deviceMapper.ShowDeviceCurpos(jsonMap);
			if(list.size() != 0) {
				jsonResult = list.get(0);
			}
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return jsonResult;
	}

	//查询设备轨迹
	@Override
	public List<Map<Object, Object>> ShowDeviceTrail(String jsonStr) {
		List<Map<Object, Object>> list = new ArrayList<>();
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		try {
			list = deviceMapper.ShowDeviceTrail(jsonMap);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	//查询设备所设置的围栏的信息
	@Override
	public List<Map<Object, Object>> ShowDeviceFence(String jsonStr) {
		List<Map<Object, Object>> list = new ArrayList<>();
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		try {
			list = deviceMapper.ShowDeviceFence(jsonMap);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	//查询设备所设置的亲情号信息
	@Override
	public List<Map<Object, Object>> ShowDeivceFamily(String jsonStr) {
		List<Map<Object, Object>> list = new ArrayList<>();
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		try {
			list = deviceMapper.ShowDeivceFamily(jsonMap);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	//查询设备所设置的上报位置时间间隔
	@Override
	public Map<Object, Object> ShowDevPosInterval(String jsonStr) {
		Map<Object, Object> resMap = new HashMap<>();
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		try {
			List<Map<Object, Object>> list = deviceMapper.ShowDevPosInterval(jsonMap);
			if(list.size() != 0) {
				resMap = list.get(0);
			}
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resMap;
	}

	//查询设备工作模式
	@Override
	public Map<Object, Object> ShowDevWorkMode(String jsonStr) {
		Map<Object, Object> resMap = new HashMap<>();
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		try {
			List<Map<Object, Object>> list = deviceMapper.ShowDevWorkMode(jsonMap);
			if(list.size() != 0) {
				resMap = list.get(0);
			}
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resMap;
	}

	
	//查询最新的学校经纬度数据
	@Override
	public Map<Object, Object> ShowSchoolCenter(String jsonStr){
		Map<Object, Object> resultMap = new HashMap<>();
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		
		try {
			List<Map<Object, Object>> list = deviceMapper.ShowSchoolCenter(jsonMap);
			if(list.size() != 0) {
				resultMap = list.get(0);
			}
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultMap;
	}
	
	//添加  学校经纬度（按学校设置）
	@Override
	public Map<Object, Object> AddSchoolCenterForAll(String jsonStr) {
		Map<Object, Object> resultMap = new HashMap<>();
		int failNum = 0;
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		Integer schoolId = (Integer) jsonMap.get("schoolid");
		//根据学校的id查询所有本学校下未被设置过学校位置的设备号
		List<String> deviceList = deviceMapper.FindStudentForPushCenter(schoolId);
		for(int i = 0;i < deviceList.size(); i++) {			//循环所有的DEVICENUM
			jsonMap.put("DEVICENUM", deviceList.get(i));	//将DEVICENUM放入需要传给平台方的数据中
			jsonMap.put("OPTTYPE", "0");					//此参数为0即为添加
			Long timestamp = (System.currentTimeMillis())/1000L;
			jsonMap.put("TIMESTAMP", timestamp);
			
			String jsonRes = JSONUtils.toJSONString(jsonMap).toString();	//转换成String格式
			//调用平台端的接口，传递数据
			String platformRes = PushSchoolCenter(jsonRes);						//用于接收平台端返回的数据
			Map<Object,Object> platformMap = jsonToMap(platformRes);		//将平台返回的字符串转换为Map格式
			if(platformMap.get("RESULT") == null && platformMap.get("RESULT").equals("null") && !platformMap.get("RESULT").equals("0")){
				//设置时失败，将失败的计数+1
				failNum++;
			}
		}
		if(failNum == 0) {
			resultMap = jsonToMap(CardConstants.ADD_CENTER_SUCCESS);
			resultMap.put("FAILNUM", 0);
		}else {
			resultMap = jsonToMap(CardConstants.ADD_CENTER_FAIL);
			resultMap.put("FAILNUM", failNum);
		}
		return resultMap;
	}

	//修改学校经纬度----全部已设置过学校位置信息的设备  （按学校设置），即更新整个学校位置信息的版本
	@Override
	public Map<Object, Object> UpdateSchoolCenterForAll(String jsonStr) {
		Map<Object, Object> resultMap = new HashMap<>();
		int failNum = 0;
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		Integer schoolId = (Integer) jsonMap.get("schoolid");
		//根据学校id查询所有本学校下已设置过学校位置的设备号
		List<String> deviceList = deviceMapper.FindStuForUpdateCenterVersion(schoolId);
		for(int i = 0;i < deviceList.size(); i++) {			//循环所有的DEVICENUM
			jsonMap.put("DEVICENUM", deviceList.get(i));	//将DEVICENUM放入需要传给平台方的数据中
			jsonMap.put("OPTTYPE", "2");					//此参数为2即为修改
			Long timestamp = (System.currentTimeMillis())/1000L;
			jsonMap.put("TIMESTAMP", timestamp);
			
			String jsonRes = JSONUtils.toJSONString(jsonMap).toString();	//转换成String格式
			//调用平台端的接口，传递数据
			String platformRes = PushSchoolCenter(jsonRes);						//用于接收平台端返回的数据
			Map<Object,Object> platformMap = jsonToMap(platformRes);		//将平台返回的字符串转换为Map格式
			if(platformMap.get("RESULT") == null && platformMap.get("RESULT").equals("null") && !platformMap.get("RESULT").equals("0")){
				//设置时失败，将失败的计数+1
				failNum++;
			}
		}
		if(failNum == 0) {
			resultMap = jsonToMap(CardConstants.UPDATE_CENTER_SUCCESS);
			resultMap.put("FAILNUM", 0);
		}else {
			resultMap = jsonToMap(CardConstants.UPDATE_CENTER_FAIL);
			resultMap.put("FAILNUM", failNum);
		}
		return resultMap;
	}
	
	//修改学校经纬度----更新非最新版本学校位置的设备  （按学校设置）
	@Override
	public Map<Object, Object> UpdateSchoolCenterForOld(String jsonStr) {
		Map<Object, Object> resultMap = new HashMap<>();
		int failNum = 0;
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		Integer schoolId = (Integer) jsonMap.get("schoolid");
		//根据学校id查询所有本学校下已设置过学校位置 但非最新版本的设备号
		List<String> deviceList = deviceMapper.FindStuForUpdateCenterToNewest(schoolId);
		for(int i = 0;i < deviceList.size(); i++) {			//循环所有的DEVICENUM
			jsonMap.put("DEVICENUM", deviceList.get(i));	//将DEVICENUM放入需要传给平台方的数据中
			jsonMap.put("OPTTYPE", "2");					//此参数为2即为修改
			Long timestamp = (System.currentTimeMillis())/1000L;
			jsonMap.put("TIMESTAMP", timestamp);
			
			String jsonRes = JSONUtils.toJSONString(jsonMap).toString();	//转换成String格式
			//调用平台端的接口，传递数据
			String platformRes = PushSchoolCenter(jsonRes);						//用于接收平台端返回的数据
			Map<Object,Object> platformMap = jsonToMap(platformRes);		//将平台返回的字符串转换为Map格式
			if(platformMap.get("RESULT") == null && platformMap.get("RESULT").equals("null") && !platformMap.get("RESULT").equals("0")){
				//设置时失败，将失败的计数+1
				failNum++;
			}
		}
		if(failNum == 0) {
			resultMap = jsonToMap(CardConstants.UPDATE_CENTER_SUCCESS);
			resultMap.put("FAILNUM", 0);
		}else {
			resultMap = jsonToMap(CardConstants.UPDATE_CENTER_FAIL);
			resultMap.put("FAILNUM", failNum);
		}
		return resultMap;
	}
	
	//删除学校经纬度----当前最新版本
	@Override
	public Map<Object, Object> DelSchoolCenter(String jsonStr) {
		Map<Object, Object> resultMap = new HashMap<>();
		int failNum = 0;
		
		Map<Object, Object> jsonMap = jsonToMap(jsonStr);
		Integer schoolId = (Integer) jsonMap.get("schoolid");
		//根据学校id查询所有本学校下已设置过学校位置而且是最新版本的设备号
		List<String> deviceList = deviceMapper.FindStuForDelCenter(schoolId);
		for(int i = 0;i < deviceList.size(); i++) {			//循环所有的DEVICENUM
			jsonMap.put("DEVICENUM", deviceList.get(i));	//将DEVICENUM放入需要传给平台方的数据中
			jsonMap.put("OPTTYPE", "1");					//此参数为1即为删除
			Long timestamp = (System.currentTimeMillis())/1000L;
			jsonMap.put("TIMESTAMP", timestamp);
			
			String jsonRes = JSONUtils.toJSONString(jsonMap).toString();	//转换成String格式
			//调用平台端的接口，传递数据
			String platformRes = PushSchoolCenter(jsonRes);						//用于接收平台端返回的数据
			Map<Object,Object> platformMap = jsonToMap(platformRes);		//将平台返回的字符串转换为Map格式
			if(platformMap.get("RESULT") == null && platformMap.get("RESULT").equals("null") && !platformMap.get("RESULT").equals("0")){
				//设置时失败，将失败的计数+1
				failNum++;
			}
		}
		if(failNum == 0) {
			resultMap = jsonToMap(CardConstants.DELETE_CENTER_SUCCESS);
			resultMap.put("FAILNUM", 0);
		}else {
			resultMap = jsonToMap(CardConstants.DELETE_CENTER_FAIL);
			resultMap.put("FAILNUM", failNum);
		}
		return resultMap;
	}
	
	
	/**************************************End:对接  知校端  接口**************************************/
	
}
