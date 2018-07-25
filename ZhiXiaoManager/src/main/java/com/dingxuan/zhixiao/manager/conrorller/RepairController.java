package com.dingxuan.zhixiao.manager.conrorller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dingxuan.zhixiao.manager.service.RepairService;


@Controller
@RequestMapping("/repair")
public class RepairController {
	private static final Logger LOGGER = Logger.getLogger(RepairController.class);
	
	@Autowired
	private RepairService repairService;
	
	//物品报修++++++++++接口模板-----------------------------------
//	@RequestMapping("/")
//	@ResponseBody
//	public List<Map> repair(@RequestParam Map map){
//		try{
//			return repairService.repair(map);
//		}catch(Exception e){
//			LOGGER.error(e.getMessage());
//			e.getStackTrace();
//			return null;
//		}
//	}
	
	//物品报修----我的记录列表----查询
	@RequestMapping("/repairMyRecord")
	@ResponseBody
	public List<Map> repairMyRecord(@RequestParam Map map){
		try{
			return repairService.repairMyRecord(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			return null;
		}
	}
	
	//物品报修----知会我列表----查询
	@RequestMapping(value = "/repairKnowMe", method = RequestMethod.GET)
	@ResponseBody
	public List<Map> repairKnowMe(@RequestParam Map map){
		try{
			return repairService.repairKnowMe(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	
	//物品报修----待我审批列表----查询
	@RequestMapping(value = "/repairMyApprovals", method = RequestMethod.GET)
	@ResponseBody
	public List<Map> repairMyApprovals(@RequestParam Map map){
		try{
			return repairService.repairMyApprovals(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	
	//物品报修----我的物品报修详情----查询
	@RequestMapping(value = "/repairDetails", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> repairDetails(@RequestParam Map map){
		try{
			//获取信息时请对应字段
			//基本信息----basic----返回为Map
			//知会人信息----whoKnow
			//处理信息----process
			return repairService.repairDetails(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	
	//物品报修----报修统计----查询
	@RequestMapping(value = "/repairCount", method = RequestMethod.GET)
	@ResponseBody
	public Map repairCount(@RequestParam Map map){
		try{
			return repairService.repairCount(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	
	//物品报修----统计明细----查询
	@RequestMapping(value = "/repairDetailsOfCount", method = RequestMethod.GET)
	@ResponseBody
	public List<Map> repairDetailsOfCount(@RequestParam Map map){
		try{
			return repairService.repairDetailsOfCount(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	
//	//物品报修----物品报修详情（待受理）----查询
//	@RequestMapping("/repairDetailsOfWaitingForDispose")
//	@ResponseBody
//	public List<Map> repairDetailsOfWaitingForDispose(@RequestParam Map map){
//		try{
//			return repairService.repairDetailsOfWaitingForDispose(map);
//		}catch(Exception e){
//			LOGGER.error(e.getMessage());
//			return null;
//		}
//	}
//	
//	//物品报修----物品报修详情（受理中）----查询
//	@RequestMapping("/repairDetailsOfDisposing")
//	@ResponseBody
//	public List<Map> repairDetailsOfDisposing(@RequestParam Map map){
//		try{
//			return repairService.repairDetailsOfDisposing(map);
//		}catch(Exception e){
//			LOGGER.error(e.getMessage());
//			return null;
//		}
//	}
	
	//物品报修----受理说明----添加（提交按钮）----实质应为修改数据库表格中相关字段内容
	@RequestMapping(value = "/repairExplainOfDispose", method = RequestMethod.GET)
	@ResponseBody
	public boolean repairExplainOfDispose(@RequestParam Map map){
		try{
			return repairService.repairExplainOfDispose(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return false;
		}
	}
	
	//物品报修----驳回理由----添加（提交按钮）----实质应为修改数据库表格中相关字段内容
	@RequestMapping(value = "/repairExplainOfReject", method = RequestMethod.GET)
	@ResponseBody
	public boolean repairExplainOfReject(@RequestParam Map map){
		try{
			return repairService.repairExplainOfReject(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return false;
		}
	}
	
	//物品报修----转送理由----添加（提交按钮）----实质应为修改数据库表格中相关字段内容
	@RequestMapping(value = "/repairExplainOfTransfer", method = RequestMethod.GET)
	@ResponseBody
	public boolean repairExplainOfTransfer(@RequestParam Map map){
		try{
			return repairService.repairExplainOfTransfer(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return false;
		}
	}
	
	//物品报修----解决报修----按钮
	@RequestMapping(value = "/repairFinish", method = RequestMethod.GET)
	@ResponseBody
	public boolean repairFinish(@RequestParam Map map){
		try{
			return repairService.repairFinish(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return false;
		}
	}
	
	//物品报修----报修申请----添加
	@RequestMapping(value = "/repairApply", method = RequestMethod.GET)
	@ResponseBody
	public boolean repairApply(@RequestParam Map map){
		try{
			return repairService.repairApply(map);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			return false;
		}
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
