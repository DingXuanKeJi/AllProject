package com.dingxuan.zhixiao.manager.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dingxuan.zhixiao.dao.RepairMapper;
import com.dingxuan.zhixiao.manager.service.RepairService;

@Service
public class RepairServiceImpl implements RepairService{

	@Autowired
	private RepairMapper repairMapper;
	
	public List<Map> repair(Map map) throws Exception{
		return repairMapper.repair(map);
	}

	public List<Map> repairMyRecord(Map map) throws Exception{
		return repairMapper.repairMyRecord(map);
	}

	public List<Map> repairKnowMe(Map map) throws Exception{
		return repairMapper.repairKnowMe(map);
	}

	public List<Map> repairMyApprovals(Map map) throws Exception{
		return repairMapper.repairMyApprovals(map);
	}

	public Map<String, Object> repairDetails(Map map) throws Exception{
		Map<String, Object> result = new HashMap<>();
		//1.查询出我的报修详情的基本信息
		List<Map> basicList = repairMapper.repairDetailsOfMyself_Basic(map);
		Map basic = new HashMap();
		if(basicList.size()!=0){
			basic = basicList.get(0);
		}
		//2.查询该条信息的知会人ID以及姓名
		List<Map> whoKnow = repairMapper.repairDetailsOfMyself_WhoKnow(map);
		//3.查询该条信息的处理流程信息
		List<Map> process = repairMapper.repairDetailsOfMyself_Process(map);
		//4.将所有信息装入result中
		result.put("basic", basic);
		result.put("whoKnow", whoKnow);
		result.put("process", process);
		return result;
	}

	public Map repairCount(Map map) throws Exception{
		//将存储过程中取出的数据转换成Map数据类型 
		return repairMapper.repairCount(map).get(0);
	}

	public List<Map> repairDetailsOfCount(Map map) throws Exception{
		return repairMapper.repairDetailsOfCount(map);
	}

	public boolean repairExplainOfDispose(Map map) throws Exception{
		try{
			if(map.get("msg")==null){
				String msg = "";
				map.put("msg", msg);
			}
			repairMapper.repairExplainOfDispose(map);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean repairExplainOfReject(Map map) throws Exception{
		try{
			repairMapper.repairExplainOfReject(map);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean repairExplainOfTransfer(Map map) throws Exception{
		try{
			repairMapper.repairExplainOfTransfer(map);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean repairFinish(Map map) throws Exception{
		try{
			repairMapper.repairFinish(map);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean repairApply(Map map) throws Exception{
		try {
			if(map.get("note")==null){
				map.put("note", "");
			}
			//插入申请表和流程表的数据，并返回申请表的ID
			int apply_id = repairMapper.repairApplyBasicAndProcess(map);
			map.put("apply_id", apply_id);
			//判断carbon字段是否存在后，将其以,进行拆分并转换成数字格式，然后逐条插入到知会人表中
			if(map.get("carbon")!=null){
				String carbons = (String) map.get("carbon");
				String[] carbon = carbons.split(",");
				for(int i = 0;i<carbon.length;i++){
					int carbonInt = Integer.parseInt(carbon[i]);
					map.put("carbon", carbonInt);
					repairMapper.repairApplyWhoKnow(map);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
