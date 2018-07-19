package com.dingxuan.zhixiao.manager.service;

import java.util.List;
import java.util.Map;

public interface DeviceService {
	

	public String pushDeviceFence(String jsonStr) throws Exception;

	public String PushDeviceLowbat(String jsonStr);

	public String PushDeviceStep(String jsonStr);

	public String PushDeviceCHECK(String jsonStr);

	public String PushDeviceSOS(String jsonStr);

	public String PushDeviceStatic(String jsonStr);

	public String PushDeviceCurpos(String jsonStr);

	public String PushDeviceCallnote(String jsonStr);

	public String PushDeviceOverdue(String jsonStr);

	public String PushSchoolCenter(String jsonStr);

	public String PushFenceRound(String jsonStr);

	public String PushDeviceFamily(String jsonStr);

	public String PushWriteDeviceResult(String jsonStr);

	public String StudentBindingDevice(String jsonStr);

	public Map<Object, Object> FindStudentDevice(String jsonStr);

	public Map<Object, Object> ShowDeviceCurpos(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceTrail(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceFence(String jsonStr);

	public List<Map<Object, Object>> ShowDeivceFamily(String jsonStr);

	public String PushDevPosInterval(String jsonStr);

	public String PushDevWorkMode(String jsonStr);

	public Map<Object, Object> ShowDevPosInterval(String jsonStr);

	public Map<Object, Object> ShowDevWorkMode(String jsonStr);

	public Map<Object, Object> ShowSchoolCenter(String jsonStr);

	public Map<Object, Object> AddSchoolCenterForAll(String jsonStr);

	public Map<Object, Object> UpdateSchoolCenterForAll(String jsonStr);

	public Map<Object, Object> UpdateSchoolCenterForOld(String jsonStr);

	public Map<Object, Object> DelSchoolCenter(String jsonStr);
	
	
}
