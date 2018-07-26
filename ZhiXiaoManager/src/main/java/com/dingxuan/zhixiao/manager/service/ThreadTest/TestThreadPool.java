package com.dingxuan.zhixiao.manager.service.ThreadTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
public class TestThreadPool extends AbstractJUnit4SpringContextTests {

	private static int produceTaskSleepTime = 10;
	
	private static int produceTaskMaxNumber = 1;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
		return threadPoolTaskExecutor;
	}
	
	public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}
	
	@Test
	public void testThreadPoolExecutor() {
		for(int i = 1; i <= produceTaskMaxNumber; i++) {
//			try {
//				//Thread.sleep(produceTaskSleepTime);
//			}catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//			new Thread(
					try {
						new StartTaskThread(threadPoolTaskExecutor,i).test();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					).start();
		}
	}
}
