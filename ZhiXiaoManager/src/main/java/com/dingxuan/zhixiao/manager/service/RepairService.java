package com.dingxuan.zhixiao.manager.service;

import java.util.List;
import java.util.Map;

public interface RepairService {
	
	public List<Map> repairMyRecord(Map map) throws Exception;

	public List<Map> repairKnowMe(Map map) throws Exception;

	public List<Map> repairMyApprovals(Map map) throws Exception;

	public Map<String, Object> repairDetails(Map map) throws Exception;

	public Map repairCount(Map map) throws Exception;

	public List<Map> repairDetailsOfCount(Map map) throws Exception;

	public boolean repairExplainOfDispose(Map map) throws Exception;

	public boolean repairExplainOfReject(Map map) throws Exception;

	public boolean repairExplainOfTransfer(Map map) throws Exception;

	public boolean repairFinish(Map map) throws Exception;

	public boolean repairApply(Map map) throws Exception;
	
	
}
