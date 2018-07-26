package com.dingxuan.zhixiao.manager.conrorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dingxuan.zhixiao.manager.service.DeviceService;
import com.dingxuan.zhixiao.util.CardConstants;
import com.dingxuan.zhixiao.util.DeviceUtil;

@Controller
@RequestMapping("/device")
public class DeviceController {

	private static final Logger LOGGER = Logger.getLogger(DeviceController.class);
	
	@Autowired
	private DeviceService deviceService;
		
	/**************************************Begin:对接  平台端  接口**************************************/
	//推送设备电子围栏触发报警
	@RequestMapping(value = "/PushDeviceFence", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String pushDeviceFence(@RequestParam String jsonStr){
		String jsonResult = null;
		try{
			jsonResult = deviceService.pushDeviceFence(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	//推送设备电量不足报警
	@RequestMapping(value = "/PushDeviceLowbat", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushDeviceLowbat(@RequestParam String jsonStr,HttpServletRequest req){
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceLowbat(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	//推送学生步数
	@RequestMapping(value = "/PushDeviceStep", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushDeviceStep(@RequestParam String jsonStr){
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceStep(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	//推送学生考勤记录
	@RequestMapping(value = "/PushDeviceCHECK", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushDeviceCHECK(@RequestParam String jsonStr){
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceCHECK(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	//学生 SOS 求救记录上报
	@RequestMapping(value = "/PushDeviceSOS", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushDeviceSOS(@RequestParam String jsonStr){
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceSOS(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
		
	//推送设备脱落上报
	@RequestMapping(value = "/PushDeviceStatic", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushDeviceStatic(@RequestParam String jsonStr){
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceStatic(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	//推送设备最新位置
	@RequestMapping(value = "/PushDeviceCurpos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushDeviceCurpos(@RequestParam String jsonStr){
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceCurpos(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	//推送设备通话记录
	@RequestMapping(value = "/PushDeviceCallnote", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushDeviceCallnote(@RequestParam String jsonStr){
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceCallnote(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	//推送设备欠费短信
	@RequestMapping(value = "/PushDeviceOverdue", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushDeviceOverdue(@RequestParam String jsonStr){
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceOverdue(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			e.printStackTrace();
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	 //接收平台命令设置的结果
	@RequestMapping(value = "/PushWriteDeviceResult", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushWriteDeviceResult(@RequestParam String jsonStr) {
		String jsonResult = null;
		try {
			jsonResult = deviceService.PushWriteDeviceResult(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	/**************************************End:对接  平台端   接口**************************************/
	
	
	/**************************************Begin:用户→平台 接口    由知校端调用**************************************/
	//设置学校经纬度 PushSchoolCenter
	@RequestMapping(value = "/PushSchoolCenter", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String PushSchoolCenter(@RequestParam String jsonStr){
		//String jsonStr = "{\"DEVICENUM\":\"867587050000270\",\"LO\":\"121.600895\",\"LA\":\"38.903636\",\"RADIUS\":\"100\",\"SCHOOLID\":\"1\",\"SCHOOLRFIDS\":\"0\",\"SCHOOLNAME\":\"CESHIXUEXIAO\",\"OPTTYPE\":\"0\",\"TIMESTAMP\":1532336519,\"VERSION\":1}";
		String jsonResult = null;
		try{
			jsonResult = deviceService.PushSchoolCenter(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			jsonResult = CardConstants.CONTROLLER_ERROR;
		}
		return jsonResult;
	}
	
	//圆形围栏数据设置 PushFenceRound
	@RequestMapping(value = "/PushFenceRound", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> PushFenceRound(@RequestParam String jsonStr){
		//String jsonStr = "{\"DEVICENUM\":\"867587050000270\",\"LO\":\"121.600895\",\"LA\":\"38.903636\",\"RADIUS\":\"100\",\"FENCEID\":\"1\",\"FENCETYPE\":\"2\",\"FENCENAME\":\"CESHI11\",\"OPTTYPE\":\"2\",\"KEY\":\"DLTSKJ\",\"SIGN\":\"C24EDC61ADFBAD91BCB1EA12D22F333D\",\"TIMESTAMP\":1532335898}";
		Map<Object, Object> jsonResult = null;
		try{
			jsonResult = deviceService.PushFenceRound(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			jsonResult = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return jsonResult;
	}
	
	//设置亲情号码数据 PushDeviceFamily
	@RequestMapping(value = "/PushDeviceFamily", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> PushDeviceFamily(@RequestParam String jsonStr){
		//String jsonStr = "{\"DEVICENUM\":\"867587050000270\",\"PHONE\":\"15998328177\",\"NAME\":\"WWW\",\"POS\":\"2\",\"FAMILYKEY\":\"270ZAX2\",\"OPTTYPE\":\"0\",\"KEY\":\"DLTSKJ\",\"SIGN\":\"61AD6B6F84E221391384BC0062CBE5F1\",\"TIMESTAMP\":1532338220}";
		Map<Object, Object> jsonResult = null;
		try{
			jsonResult = deviceService.PushDeviceFamily(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			jsonResult = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return jsonResult;
	}
	
	//设置设备上报位置的时间间隔
	@RequestMapping(value = "/PushDevPosInterval", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> PushDevPosInterval(@RequestParam String jsonStr) {
		//String jsonStr = "{\"DEVICENUM\":\"867587050000270\",\"INTERVAL\":\"30\",\"TIMESTAMP\":1532337777}";
				
		Map<Object, Object> jsonResult = null;
		try {
			jsonResult = deviceService.PushDevPosInterval(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			jsonResult = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return jsonResult;
	}
	
	//设置设备工作模式
	@RequestMapping(value = "/PushDevWorkMode", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> PushDevWorkMode(@RequestParam String jsonStr) {
		//String jsonStr = "{\"DEVICENUM\":\"867587050000270\",\"MODE\":\"1\",\"TIMESTAMP\":1532337990}";
		
		Map<Object, Object> jsonResult = null;
		try {
			jsonResult = deviceService.PushDevWorkMode(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			jsonResult = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return jsonResult;
	}
	
	
	//家庭作业推送 PushDeviceHomework
	//免打扰时段 PushDeviceSilent
	//设置设备音量 PushDeviceSpeakerLevel
	/**************************************End:用户→平台 接口**************************************/
	
	
	/**************************************Begin:对接  知校端  接口**************************************/
	//将学生与学生证进行 绑定0,解绑1,更改2(单一设置)
	@RequestMapping(value = "/StudentBindingDevice", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> StudentBindingDevice(@RequestParam String jsonStr) {
		Map<Object, Object> jsonResult = null;
		try {
			jsonResult = deviceService.StudentBindingDevice(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			jsonResult = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return jsonResult;
	}
	
	//查询学生所绑定的设备
	@RequestMapping(value = "/FindStudentDevice", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> FindStudentDevice(@RequestParam String jsonStr) {
		Map<Object, Object> resultMap = new HashMap<>();
		try {
			resultMap = deviceService.FindStudentDevice(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultMap;
	}
	
	//查询设备最新定位
	@RequestMapping(value = "/ShowDeviceCurpos", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> ShowDeviceCurpos(@RequestParam String jsonStr){
		Map<Object, Object> resultMap = new HashMap<>();
		try {
			resultMap = deviceService.ShowDeviceCurpos(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultMap;
	}
	
	//查询设备时间段内轨迹
	@RequestMapping(value = "/ShowDeviceTrail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceTrail(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try{
			list = deviceService.ShowDeviceTrail(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询设备所设置的围栏信息
	@RequestMapping(value = "/ShowDeivceFence", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceFence(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceFence(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询设备所设置的亲情号信息
	@RequestMapping(value = "/ShowDeivceFamily", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeivceFamily(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeivceFamily(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询设备所设置的上报位置时间间隔
	@RequestMapping(value = "/ShowDevPosInterval", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> ShowDevPosInterval(@RequestParam String jsonStr) {
		Map<Object, Object> resultMap = new HashMap<>();
		try {
			resultMap = deviceService.ShowDevPosInterval(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultMap;
	}
	
	//查询设备工作模式
	@RequestMapping(value = "/ShowDevWorkMode", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> ShowDevWorkMode(@RequestParam String jsonStr) {
		Map<Object, Object> resultMap = new HashMap<>();
		try {
			resultMap = deviceService.ShowDevWorkMode(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultMap;
	}
	
	//查询最新学校经纬度数据
	@RequestMapping(value = "/ShowSchoolCenter", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> ShowSchoolCenter(@RequestParam String jsonStr) {
		Map<Object, Object> resultMap = new HashMap<>();
		try {
			resultMap = deviceService.ShowSchoolCenter(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultMap;
	}
	
	//添加  学校经纬度（按学校设置）
	@RequestMapping(value = "/AddSchoolCenterForAll", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> AddSchoolCenterForAll(@RequestParam String jsonStr){
		jsonStr = "{\"LO\":\"121.600895\",\"LA\":\"38.903636\",\"RADIUS\":\"100\",\"SCHOOLID\":\"6918\",\"SCHOOLRFIDS\":\"0\",\"SCHOOLNAME\":\"ceshiThread\",\"VERSION\":1}";
		Map<Object, Object> resultMap = null;
		try{
			resultMap = deviceService.AddSchoolCenterForAll(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			resultMap = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return resultMap;
	}
	
	//修改学校经纬度----全部已设置过学校位置信息的设备  （按学校设置），即更新整个学校位置信息的版本
	@RequestMapping(value = "/UpdateSchoolCenterForAll", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> UpdateSchoolCenterForAll(@RequestParam String jsonStr){
		Map<Object, Object> resultMap = null;
		try{
			resultMap = deviceService.UpdateSchoolCenterForAll(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			resultMap = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return resultMap;
	}
	
	//修改学校经纬度----更新非最新版本学校位置的设备  （按学校设置）
	@RequestMapping(value = "/UpdateSchoolCenterForOld", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> UpdateSchoolCenterForOld(@RequestParam String jsonStr){
		Map<Object, Object> resultMap = null;
		try{
			resultMap = deviceService.UpdateSchoolCenterForOld(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			resultMap = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return resultMap;
	}
	
	//删除学校经纬度----当前最新版本
	@RequestMapping(value = "/DelSchoolCenter", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<Object, Object> DelSchoolCenter(@RequestParam String jsonStr){
		Map<Object, Object> resultMap = null;
		try{
			resultMap = deviceService.DelSchoolCenter(jsonStr);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			resultMap = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return resultMap;
	}
	
	
	//查询电子围栏触发记录
	@RequestMapping(value = "/ShowDeviceFenceWarn", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceFenceWarn(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceFenceWarn(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询电量不足报警记录
	@RequestMapping(value = "/ShowDeviceLowbat", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceLowbat(@RequestParam String jsonStr){
		//jsonStr = "{\"DEVICENUM\":\"867587050000270\"}";
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceLowbat(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询已推送步数
	@RequestMapping(value = "/ShowDeviceStep", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceStep(@RequestParam String jsonStr){
		jsonStr = "{\"DEVICENUM\":\"867587050000270\"}";
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceStep(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询学生考勤记录
	@RequestMapping(value = "/ShowDeviceCHECK", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceCHECK(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceCHECK(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询学生SOS求救记录
	@RequestMapping(value = "/ShowDeviceSOS", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceSOS(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceSOS(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询设备脱落上报
	@RequestMapping(value = "/ShowDeviceStatic", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceStatic(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceStatic(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询设备通话记录
	@RequestMapping(value = "/ShowDeviceCallnote", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceCallnote(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceCallnote(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
	
	//查询欠费短信提醒
	@RequestMapping(value = "/ShowDeviceOverdue", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<Object, Object>> ShowDeviceOverdue(@RequestParam String jsonStr){
		List<Map<Object, Object>> list = new ArrayList<>();
		try {
			list = deviceService.ShowDeviceOverdue(jsonStr);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	
		
		
	/**************************************End:对接  知校端  接口**************************************/
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	AtomicInteger k = new AtomicInteger(0);
	@RequestMapping(value = "/Test", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String Test(@RequestParam String jsonStr) throws InterruptedException, ExecutionException{
		//Map<Object, Object> resultMap = null;
		Future<String> future = null;
		try{
			 future = threadPoolTaskExecutor.submit(new com.dingxuan.zhixiao.job.Test(k));
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			//resultMap = DeviceUtil.jsonToMap(CardConstants.CONTROLLER_ERROR);
		}
		return future.get().toString();
	}
	
	
	
	
	
}
