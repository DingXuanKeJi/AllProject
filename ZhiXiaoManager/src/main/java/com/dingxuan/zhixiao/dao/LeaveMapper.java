package com.dingxuan.zhixiao.dao;

import java.util.List;
import java.util.Map;

public interface LeaveMapper {
		//我的记录
		List<Map> myRecord(Map map);
		
		//知会我列表
		List<Map> knowMe(Map map);
		
		//待我审批
		List<Map> myApproval(Map map);
		
		//知会人列表
		List<Map> knowList(Map map);
		
		//审批人列表
		List<Map> approverList(Map map);
		
		//提交请假
		void leaveSubmit(Map map);
		
		//请假统计
		Map leaveStatistics(Map map);
		
		//请假详情(请假基础信息+知会人信息)
		Map leaveDetails(Map map);
			
		//请假详情(流程信息)
		List<Map> leaveDetailsProcess(Map map);	
			
		//请假统计
		List<Map> selectStaff(Map map);	
				
		//增假
		void leaveIncrease (Map map);	
		
		//销假
		void leaveSick (Map map);
		
		//撤销请假
		void leaveRevoke (Map map);
		
		//转审-提交
		void leaveTurn (Map map);
		
		//驳回-提交
		void leaveReject (Map map);
		
		//同意
		void leaveAgreel (Map map);
		
		//重新申请
		void leaveReapply (Map map);
	
	
	
	
	
}
