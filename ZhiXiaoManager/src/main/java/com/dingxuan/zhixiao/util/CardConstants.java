package com.dingxuan.zhixiao.util;


public class CardConstants {

	//上学啦给厂商提供的   身份识别的KEY
	public static final String APP_KEY = "DLTSKJ";
	//学生证平台的地址（正式）
	public static final String PLATFORM_URL = "http://39.106.116.138:7899/?wsdl";
	//学生证平台的测试地址
	public static final String PLATFORM_URL_TEST = "http://113.106.92.84:7899/?wsdl";
	//Controller调用Service时提示的异常
	public static final String CONTROLLER_ERROR = "{\"RESULT\":\"1\",\"ERRORCODE\":\"101\",\"ERRORDESC\":\"调取服务异常\"}";
	//平台调取用户，成功后返回的提示
	public static final String RESULT_SUCCESS = "{\"RESULT\":\"0\",\"ERRORCODE\":\"0\",\"ERRORDESC\":\"成功\"}";
	//Service调用Mapper时，错误提示
	public static final String SERVICE_ERROR = "{\"RESULT\":\"1\",\"ERRORCODE\":\"201\",\"ERRORDESC\":\"数据库异常\"}";
	//添加学校位置后的返回数据
	public static final String ADD_CENTER_SUCCESS = "{\"RESULT\":\"0\",\"ERRORCODE\":\"0\",\"ERRORDESC\":\"全部添加成功\"}";
	public static final String ADD_CENTER_FAIL = "{\"RESULT\":\"1\",\"ERRORCODE\":\"301\",\"ERRORDESC\":\"部分未添加成功\"}";
	//修改学校下  全部已设置过的设备的  学校位置后的  返回数据
	public static final String UPDATE_CENTER_SUCCESS = "{\"RESULT\":\"0\",\"ERRORCODE\":\"0\",\"ERRORDESC\":\"全部修改成功\"}";
	public static final String UPDATE_CENTER_FAIL = "{\"RESULT\":\"1\",\"ERRORCODE\":\"302\",\"ERRORDESC\":\"部分未修改成功\"}";
	//删除学校下最新位置信息的设备  返回数据
	public static final String DELETE_CENTER_SUCCESS = "{\"RESULT\":\"0\",\"ERRORCODE\":\"0\",\"ERRORDESC\":\"全部删除成功\"}";
	public static final String DELETE_CENTER_FAIL = "{\"RESULT\":\"1\",\"ERRORCODE\":\"303\",\"ERRORDESC\":\"部分未删除成功\"}";
	//设备回调时不存在对应命令名称
	public static final String CMDNAME_ERROR = "{\"RESULT\":\"1\",\"ERRORCODE\":\"304\",\"ERRORDESC\":\"未找到对应命令名称\"}";
}
