package com.dingxuan.zhixiao.dao;

import java.util.List;
import java.util.Map;

public interface DeviceMapper {

	//模板
	void device(Map<Object,Object> map);

	//验证appKey
	List<String> keyVerfication(String appKey);
	
	//推送设备电子围栏触发报警
	void pushDeviceFence(Map<Object,Object> map);

	//推送设备电量不足报警
	void PushDeviceLowbat(Map<Object,Object> map);

	//推送学生步数
	void PushDeviceStep(Map<Object,Object> map);

	//推送学生考勤记录
	void PushDeviceCHECK(Map<Object,Object> map);

	//学生 SOS 求救记录上报
	void PushDeviceSOS(Map<Object,Object> map);

	//推送设备脱落上报
	void PushDeviceStatic(Map<Object,Object> map);

	//推送设备最新位置
	void PushDeviceCurpos(Map<Object,Object> map);
	
	//推送设备通话记录
	void PushDeviceCallnote(Map<Object,Object> map);

	//推送设备欠费短信
	void PushDeviceOverdue(Map<Object,Object> map);

	//推送某命令的设置结果
	void PushWriteDeviceResult(Map<Object, Object> map);
	
	//设置学校经纬度
	void PushSchoolCenter(Map<Object,Object> jsonMap);
	
	//圆形围栏数据设置
	void PushFenceRound(Map<Object,Object> jsonMap);
	
	//亲情号码数据设置
	void PushDeviceFamily(Map<Object,Object> jsonMap);

	//学生与学生证的关系操作
	void StudentBindingDevice(Map<Object, Object> jsonMap);

	//查询学生的学生证编号
	List<Map<Object, Object>> FindStudentDevice(Map<Object, Object> jsonMap);

	//查询设备的最新位置
	List<Map<Object, Object>> ShowDeviceCurpos(Map<Object, Object> jsonMap);

	//查询设备轨迹
	List<Map<Object, Object>> ShowDeviceTrail(Map<Object, Object> jsonMap);

	//查询设备所设置的围栏的信息
	List<Map<Object, Object>> ShowDeviceFence(Map<Object, Object> jsonMap);

	//查询设备所设置的亲情号信息
	List<Map<Object, Object>> ShowDeivceFamily(Map<Object, Object> jsonMap);

	//设置设备上报位置的时间间隔
	void PushDevPosInterval(Map<Object, Object> jsonMap);

	//设置设备工作模式
	void PushDevWorkMode(Map<Object, Object> jsonMap);

	//查询设备所设置的上报位置时间间隔
	List<Map<Object, Object>> ShowDevPosInterval(Map<Object, Object> jsonMap);

	//查询设备工作模式
	List<Map<Object, Object>> ShowDevWorkMode(Map<Object, Object> jsonMap);

	//查询最新的学校位置数据
	List<Map<Object, Object>> ShowSchoolCenter(Map<Object, Object> jsonMap);

	//根据学校的id查询所有本学校下未被设置过学校位置的设备号
	List<String> FindStudentForPushCenter(Integer schoolId);

	//根据学校id查询所有本学校下已设置过学校位置的设备号
	List<String> FindStuForUpdateCenterVersion(Integer schoolId);

	//根据学校id查询所有本学校下已设置过学校位置 但非最新版本的设备号
	List<String> FindStuForUpdateCenterToNewest(Integer schoolId);

	//根据学校id查询所有本学校下已设置过学校位置而且是最新版本的设备号
	List<String> FindStuForDelCenter(Integer schoolId);

	//回调：设置学校经纬度
	void ResultOfPushSchoolCenter(Map<Object, Object> jsonMap);

	//回调：设置围栏数据
	void ResultOfPushFenceRound(Map<Object, Object> jsonMap);

	//回调：设置亲情号码
	void ResultOfPushDeviceFamily(Map<Object, Object> jsonMap);

	//回调：设置上传间隔
	void ResultOfPushDevPosInterval(Map<Object, Object> jsonMap);
	
	//回调：设置工作模式
	void ResultOfPushDevWorkMode(Map<Object, Object> jsonMap);

	
	
	

}
