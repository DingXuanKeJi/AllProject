package com.dingxuan.zhixiao.manager.service.ThreadTest;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class ThreadPoolTask implements Callable<String>, Serializable {

	private static final long serialVersionUID = 0;

	//储存任务所需要的数据
	private Object threadPoolTaskData;
	
	private static int consumeTaskSleepTime = 2000;
	
	public ThreadPoolTask(Object task) {
		this.threadPoolTaskData = task;
	}
	
	public synchronized String call() throws Exception{
		//此处处理一个任务
		System.out.println("开始执行任务：" + threadPoolTaskData);
		
		String result = "";
		try {
			
//			for(int i = 0;i<100; i++) {
//				//System.out.println("aaa"+threadPoolTaskData);
//				//此处为了拉长时间，进行观察
//			}
			result = "OK";
		}catch(Exception e) {
			e.printStackTrace();
			result = "ERROR";
		}
		threadPoolTaskData = null;
		return result;
	}

}
