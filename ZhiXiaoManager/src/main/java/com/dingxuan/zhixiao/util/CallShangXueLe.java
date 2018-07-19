package com.dingxuan.zhixiao.util;


import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class CallShangXueLe {
	
	//调用平台端接口的方法
	public static String callShangxuela(String url,String methodName,String jsonStr){
		Service service = new Service();
		Call call = null;
		String result = "平台方法调用时出现错误！";
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			
			//调用wsdl文件中的方法
			//call.setOperationName(methodName);
			call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/ns.xsd",methodName));
			call.addParameter("InInfo", org.apache.axis.encoding.XMLType.XSD_DATE,javax.xml.rpc.ParameterMode.IN);//接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型 
			
			//根据描述传入一个String 对象，因为可能有多个入参，所以使用Object[]
			//描述的返回参数是String，强转String 
			result = (String) call.invoke(new Object[]{jsonStr});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		String url = "http://39.106.116.138:7899/?wsdl";
		//String url = "http://113.106.92.84:7899/?wsdl";
		//String url = "http://192.168.3.108/WebServiceProject/services/sxlServiceImplPort?wsdl";
		
		String methodName = "PushSchoolCenter";
		//String jsonStr = "{\"BAT\":\"40\",\"DEVICENUM\":\"1451237979\",\"GPS\":\"0\",\"GSM\":\"0\",\"KEY\":\"SHANGXUELA\",\"LA\":\"45.582917\",\"LO\":\"126.643638\",\"LOCTIME\":\"2018-11-11 11:11:11\",\"POSDESC\":\"黑龙江省哈尔滨市平房区联盟街道建安五道街3号;花蕾ABC幼儿园在东边271.087米处\",\"SIGN\":\"69CFDCCCEB899DC6531D2C3F2A3F96FE\",\"TIMESTAMP\":1530498454,\"TYPE\":\"0\"}";
		//String jsonStr = "{\"DEVICENUM\":\"111\",\"LEVEL\":\"0\",\"KEY\":\"SHANGXUELA\",\"SIGN\":\"222\",\"TIMESTAMP\":1588744552}";
		//String jsonStr = "{\"DEVICENUM\":\"867587050000221\",\"PHONE\":\"13080775045\",\"NAME\":\"AAA\",\"POS\":\"2\",\"FAMILYKEY\":\"123\",\"OPTTYPE\":\"2\",\"KEY\":\"DLTSKJ\",\"SIGN\":\"90E59F5E4274B51A6C15A0532558D333\",\"TIMESTAMP\":1530773054}";
		//String jsonStr =   "{\"DEVICENUM\":\"867587050000221\",\"WHINFO\":\"0201\",\"COURSEID\":\"1\",\"WHID\":\"266565656\",\"KEY\":\"DLTSKJ\",\"SIGN\":\"3605FC79A362B4FC0FB0605CA809F359\",\"TIMESTAMP\":15307736}";
		String jsonStr = "{\"DEVICENUM\":\"867587050000221\",\"LO\":\"121.600895\",\"LA\":\"38.903636\",\"RADIUS\":\"30\",\"SCHOOLID\":\"1123\",\"SCHOOLRFIDS\":\"0\",\"SCHOOLNAME\":\"CESHIXUEXIAO\",\"OPTTYPE\":\"0\",\"KEY\":\"DLTSKJ\",\"SIGN\":\"5F0F1758050976C7F65FEF3A6C781535\",\"TIMESTAMP\":1530783038}";
		
		
		String res = callShangxuela(url, methodName, jsonStr);
		System.out.println(res);
	}
}
