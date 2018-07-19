package com.dingxuan.zhixiao.manager.conrorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dingxuan.zhixiao.manager.service.OtherService;

@Controller  
@RequestMapping(value = "/")
public class TSSController {
	
	@Autowired  
    private OtherService otherService;  
      
    @RequestMapping(value="/getOtherList",produces="text/html;charset=UTF-8" )   
    @ResponseBody  
    private String getOtherList(){  
        String json=otherService.getOterList();  
        return json;  
    }  

}
