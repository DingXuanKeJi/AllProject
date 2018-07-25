package com.dingxuan.zhixiao.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PushMessageMapper {
    
    Map<?, ?> selectPushuser(@Param("sid")int sid,@Param("appcert")int appcert);

    
}