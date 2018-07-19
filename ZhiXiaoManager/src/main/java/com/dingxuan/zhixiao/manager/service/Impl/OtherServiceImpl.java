package com.dingxuan.zhixiao.manager.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
public class OtherServiceImpl implements OtherService  {
	
//	@Autowired  
//    private OtherListMapper otherListMapper;  
//      
    @Override  
    public String getOterList() { 
    	List<Map> jsonList=new ArrayList<Map>();
    	
        System.out.println("测试一");
        for(int i=0;i<2;i++){
        	Map<Object, Object> map = new HashMap<Object, Object>();
        	map.put("test1", "测试一");
        	map.put("test2", "测试2");
        	map.put("test3", "测试3");
        	Map<Object, Object> map2 = new HashMap<Object, Object>();
        	map2.put("test1", "测试一");
        	map2.put("test2", "测试2");
        	map2.put("test3", map);
        	jsonList.add(map2);
        }
        return JSON.toJSONString(jsonList, SerializerFeature.WriteMapNullValue);
    }  

}
