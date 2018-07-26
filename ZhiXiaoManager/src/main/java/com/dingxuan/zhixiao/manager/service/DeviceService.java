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

	public Map<Object, Object> PushFenceRound(String jsonStr);

	public Map<Object, Object> PushDeviceFamily(String jsonStr);

	public String PushWriteDeviceResult(String jsonStr);

	public Map<Object, Object> StudentBindingDevice(String jsonStr);

	public Map<Object, Object> FindStudentDevice(String jsonStr);

	public Map<Object, Object> ShowDeviceCurpos(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceTrail(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceFence(String jsonStr);

	public List<Map<Object, Object>> ShowDeivceFamily(String jsonStr);

	public Map<Object, Object> PushDevPosInterval(String jsonStr);

	public Map<Object, Object> PushDevWorkMode(String jsonStr);

	public Map<Object, Object> ShowDevPosInterval(String jsonStr);

	public Map<Object, Object> ShowDevWorkMode(String jsonStr);

	public Map<Object, Object> ShowSchoolCenter(String jsonStr);

	public Map<Object, Object> AddSchoolCenterForAll(String jsonStr);

	public Map<Object, Object> UpdateSchoolCenterForAll(String jsonStr);

	public Map<Object, Object> UpdateSchoolCenterForOld(String jsonStr);

	public Map<Object, Object> DelSchoolCenter(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceFenceWarn(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceLowbat(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceStep(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceCHECK(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceSOS(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceStatic(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceCallnote(String jsonStr);

	public List<Map<Object, Object>> ShowDeviceOverdue(String jsonStr);
	
	
}
