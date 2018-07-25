package com.dingxuan.zhixiao.manager.service;

import java.util.List;
import java.util.Map;

public interface LeaveService {

	public List<Map> myRecord(Map map); 
	
	public List<Map> knowMe(Map map);
	
	public List<Map> myApproval(Map map);
	
	public List<Map> knowList(Map map);
	
	public List<Map> approverList(Map map);
	
	public void leaveSubmit(Map map) throws Exception;
	
	public Map leaveStatistics(Map map);
	
	public Map leaveDetails(Map map);
	
	public List<Map> leaveDetailsProcess(Map map);
	
	public List<Map> selectStaff(Map map);
	
	public void leaveIncrease(Map map) throws Exception;
	
	public void leaveSick(Map map) throws Exception;
	
	public void leaveRevoke(Map map) throws Exception;
	
	public void leaveTurn(Map map) throws Exception;
	
	public void leaveReject(Map map) throws Exception;
	
	public void leaveAgreel(Map map) throws Exception;
	
	public void leaveReapply(Map map) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
}
