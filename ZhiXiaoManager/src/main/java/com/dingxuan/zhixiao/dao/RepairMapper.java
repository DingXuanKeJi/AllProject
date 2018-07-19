package com.dingxuan.zhixiao.dao;

import java.util.List;
import java.util.Map;

public interface RepairMapper {

	List<Map> repair(Map map) throws Exception;

	List<Map> repairMyRecord(Map map) throws Exception;

	List<Map> repairKnowMe(Map map) throws Exception;

	List<Map> repairMyApprovals(Map map) throws Exception;

	List<Map> repairDetailsOfMyself_Basic(Map map) throws Exception;
	
	List<Map> repairDetailsOfMyself_WhoKnow(Map map) throws Exception;
	
	List<Map> repairDetailsOfMyself_Process(Map map) throws Exception;

	List<Map> repairCount(Map map) throws Exception;

	List<Map> repairDetailsOfCount(Map map) throws Exception;

	void repairExplainOfDispose(Map map) throws Exception;

	void repairExplainOfReject(Map map) throws Exception;

	void repairExplainOfTransfer(Map map) throws Exception;

	void repairFinish(Map map) throws Exception;

	Integer repairApplyBasicAndProcess(Map map) throws Exception;

	void repairApplyWhoKnow(Map map) throws Exception;
}
