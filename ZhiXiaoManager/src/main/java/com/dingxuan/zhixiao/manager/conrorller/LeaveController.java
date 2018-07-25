package com.dingxuan.zhixiao.manager.conrorller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dingxuan.zhixiao.manager.service.LeaveService;
import com.dingxuan.zhixiao.manager.service.OtherService;

@Controller  
@RequestMapping(value = "/leave")
public class LeaveController {
	
	@Autowired  
    private LeaveService leaveService;  
      
	@RequestMapping("/myRecord")
	@ResponseBody
	public List<Map> myRecord(@RequestParam Map map){		
		try {
			String startTime=map.get("startTime").toString();
			String endTime=map.get("endTime").toString();
			if (startTime==null||startTime.equals("")) {
				map.put("startTime",0);
			}
			if (endTime==null||endTime.equals("")) {
				map.put("endTime",System.currentTimeMillis()/1000);
			}
			return leaveService.myRecord(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}
	
	@RequestMapping("/knowMe")
	@ResponseBody
	public List<Map> knowMe(@RequestParam Map map){
		try {
			return leaveService.knowMe(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}
	
	@RequestMapping("/myApproval")
	@ResponseBody
	public List<Map> myApproval(@RequestParam Map map){
		try {
			return leaveService.myApproval(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}
	
	@RequestMapping("/knowList")
	@ResponseBody
	public List<Map> knowList(@RequestParam Map map){
		try {
			return leaveService.knowList(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}
	
	@RequestMapping("/approverList")
	@ResponseBody
	public List<Map> approverList(@RequestParam Map map){
		try {
			return leaveService.approverList(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}
	
	@RequestMapping("/leaveSubmit")
	@ResponseBody
	public boolean leaveSubmit(@RequestParam Map map){			
		try {
			String content=map.get("content").toString();
			String zuid=map.get("zuid").toString();
			String msg=map.get("msg").toString();
			if (content==null||content.equals("")) {
				map.put("content","");
			}
			if (zuid==null||zuid.equals("")) {
				map.put("zuid","");
			}
			if (msg==null||msg.equals("")) {
				map.put("msg","");
			}
			leaveService.leaveSubmit(map);
			return true;
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return false;
		}			
	}
	
	@RequestMapping("/leaveStatistics")
	@ResponseBody
	public Map leaveStatistics(@RequestParam Map map){
		try {
			String startTime=map.get("startTime").toString();
			String endTime=map.get("endTime").toString();
			if (startTime==null||startTime.equals("")) {
				map.put("startTime",0);
			}
			if (endTime==null||endTime.equals("")) {
				map.put("endTime",System.currentTimeMillis()/1000);
			}
			return leaveService.leaveStatistics(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}

	@RequestMapping("/leaveDetails")
	@ResponseBody
	public Map leaveDetails(@RequestParam Map map){
		try {
			return leaveService.leaveDetails(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}
	
	@RequestMapping("/leaveDetailsProcess")
	@ResponseBody
	public List<Map> leaveDetailsProcess(@RequestParam Map map){
		try {
			return leaveService.leaveDetailsProcess(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}
	
	@RequestMapping("/selectStaff")
	@ResponseBody
	public List<Map> selectStaff(@RequestParam Map map){
		try {
			return leaveService.selectStaff(map);
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return null;
		}
	}
	
	//增假
	@RequestMapping("/leaveIncrease")
	@ResponseBody
	public boolean leaveIncrease(@RequestParam Map map){
		try {
			leaveService.leaveIncrease(map);
			return true;
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return false;
		}			
	}
	
	//销假
	@RequestMapping("/leaveSick")
	@ResponseBody
	public boolean leaveSick(@RequestParam Map map){
		try {
			leaveService.leaveSick(map);
			return true;
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return false;
		}			
	}
	
	//撤销请假
	@RequestMapping("/leaveRevoke")
	@ResponseBody
	public boolean leaveRevoke(@RequestParam Map map){
		try {
			leaveService.leaveRevoke(map);
			return true;
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return false;
		}			
	}
	
	//转审-提交
	@RequestMapping("/leaveTurn")
	@ResponseBody
	public boolean leaveTurn(@RequestParam Map map){
		try {
			leaveService.leaveTurn(map);
			return true;
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return false;
		}			
	}	
	
	//驳回-提交
	@RequestMapping("/leaveReject")
	@ResponseBody
	public boolean leaveReject(@RequestParam Map map){
		try {
			leaveService.leaveReject(map);
			return true;
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return false;
		}			
	}	
	
	//同意
	@RequestMapping("/leaveAgreel")
	@ResponseBody
	public boolean leaveAgreel(@RequestParam Map map){
		try {
			leaveService.leaveAgreel(map);
			return true;
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return false;
		}			
	}	
	
	//重新申请
	@RequestMapping("/leaveReapply")
	@ResponseBody
	public boolean leaveReapply(@RequestParam Map map){
		try {
			leaveService.leaveReapply(map);
			return true;
		} catch (Exception e) {
			System.out.println("访问数据异常!");
			return false;
		}			
	}	
		

}
