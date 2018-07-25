package com.dingxuan.zhixiao.manager.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dingxuan.zhixiao.dao.LeaveMapper;
import com.dingxuan.zhixiao.manager.service.LeaveService;
import com.dingxuan.zhixiao.manager.service.OtherService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Autowired  
    private LeaveMapper leaveMapper;

	@Override
	public List<Map> myRecord(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.myRecord(map);
	}

	@Override
	public List<Map> knowMe(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.knowMe(map);
	}

	@Override
	public List<Map> myApproval(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.myApproval(map);
	}

	@Override
	public List<Map> knowList(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.knowList(map);
	}

	@Override
	public List<Map> approverList(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.approverList(map);
	}

	@Override
	public void leaveSubmit(Map map) throws Exception {
		// TODO Auto-generated method stub
		leaveMapper.leaveSubmit(map);
	}

	@Override
	public Map leaveStatistics(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.leaveStatistics(map);
	}

	@Override
	public Map leaveDetails(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.leaveDetails(map);
	}

	@Override
	public List<Map> leaveDetailsProcess(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.leaveDetailsProcess(map);
	}

	@Override
	public List<Map> selectStaff(Map map) {
		// TODO Auto-generated method stub
		return leaveMapper.selectStaff(map);
	}

	@Override
	public void leaveIncrease(Map map) throws Exception {
		// TODO Auto-generated method stub
		leaveMapper.leaveIncrease(map);
	}

	@Override
	public void leaveSick(Map map) throws Exception {
		// TODO Auto-generated method stub
		leaveMapper.leaveSick(map);
	}

	@Override
	public void leaveRevoke(Map map) throws Exception {
		// TODO Auto-generated method stub
		leaveMapper.leaveRevoke(map);
	}

	@Override
	public void leaveTurn(Map map) throws Exception {
		// TODO Auto-generated method stub
		leaveMapper.leaveTurn(map);
	}

	@Override
	public void leaveReject(Map map) throws Exception {
		// TODO Auto-generated method stub
		leaveMapper.leaveReject(map);
	}

	@Override
	public void leaveAgreel(Map map) throws Exception {
		// TODO Auto-generated method stub
		leaveMapper.leaveAgreel(map);
	}

	@Override
	public void leaveReapply(Map map) throws Exception {
		// TODO Auto-generated method stub
		leaveMapper.leaveReapply(map);
	}        
   
	
	
	
	
	
	
	
	
}
