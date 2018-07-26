package com.dingxuan.zhixiao.manager.service.ThreadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//模拟客户端提交的线程
public class StartTaskThread implements Runnable {

	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	private int i;
	
	public StartTaskThread(ThreadPoolTaskExecutor threadPoolTaskExecutor,int i) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
		this.i = i;
	}
	
	@Override
	public synchronized void run() {
		String task = "task@" + i;
		System.out.println("创建任务并提交到线程池中：" + task);
		FutureTask<String> futureTask = new FutureTask<String>(new ThreadPoolTask(task));
		threadPoolTaskExecutor.execute(futureTask);
		
		String result = null;
		try {
			result = futureTask.get(2000, TimeUnit.MILLISECONDS);
		}catch(InterruptedException e) {
			futureTask.cancel(true);
		}catch(ExecutionException e) {
			futureTask.cancel(true);
		}catch(Exception e) {
			futureTask.cancel(true);
			//超时后进行相应处理
		}finally {
			System.out.println(task + ":result=" + result);
		}
	}

	
	public void test() throws InterruptedException, ExecutionException {
		List<String> list = new ArrayList<>();
		for(int i = 0; i < 1000 ; i++) {
			String task = "task@" + i;
			//System.out.println("创建任务并提交到线程池中：" + task);
//			FutureTask<String> futureTask = new FutureTask<String>(new ThreadPoolTask(task));
			Future<String> future = threadPoolTaskExecutor.submit(new ThreadPoolTask(task));
			list.add(future.get().toString());
			//System.out.println(future.get().toString());
//			String result = null;
//			try {
//				result = futureTask.get(2000, TimeUnit.MILLISECONDS);
//			}catch(InterruptedException e) {
//				futureTask.cancel(true);
//			}catch(ExecutionException e) {
//				futureTask.cancel(true);
//			}catch(Exception e) {
//				futureTask.cancel(true);
//				//超时后进行相应处理
//			}finally {
//				System.out.println(task + ":result=" + result);
//			}
		}
	}
	
}
