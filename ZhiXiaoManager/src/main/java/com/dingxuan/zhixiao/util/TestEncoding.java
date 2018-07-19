package com.dingxuan.zhixiao.util;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dingxuan.zhixiao.manager.service.DeviceService;

@Component
public class TestEncoding {
	
	public static TestEncoding tes;
	
	@Autowired
	private DeviceService deviceService;
	
	@PostConstruct
	public void init(){
		tes = this;
	}
	
	//测试方法入口
	public static void main(String[] args) {
		String res = "";
		res = testSchoolCenter();
		
		
		//res = testSchoolCenter();
		
		//res = testFenceRound();
		
		//res = testDeviceFamily();
		
		System.out.println(res);
	}
	
	//测试设置学校经纬度的方法
	public static String testSchoolCenter() {    
		String res;
		//模拟传入的字符串
		//调用Service中对应的方法进行测试，查看返回结果
		String testStr = "{\"DEVICENUM\":\"867587050000221\",\"LO\":\"121.600895\",\"LA\":\"38.903636\",\"RADIUS\":\"10\",\"SCHOOLID\":\"222\",\"SCHOOLRFIDS\":\"0\",\"SCHOOLNAME\":\"test111\",\"OPTTYPE\":\"0\",\"TIMESTAMP\":1531201899}";
		try{
			 res = tes.deviceService.PushSchoolCenter(testStr);
			 System.out.println(res);
		}catch(Exception e){
			 res  = "测试错误！";
		}
		return res;
    }
	
	
	//测试设置围栏的方法
	public static String testFenceRound(){
		String testStr = "";
		String res = tes.deviceService.PushFenceRound(testStr);
		return res;
	}
	
	
	//测试设置亲情号码的方法
	public static String testDeviceFamily(){
		String testStr = "";
		String res = tes.deviceService.PushDeviceFamily(testStr);
		return res;
	}
	
}
