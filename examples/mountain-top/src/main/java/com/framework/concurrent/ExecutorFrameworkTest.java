package com.framework.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorFrameworkTest {

	// ȱ�ٹرյĽӿ�
	private static Executor executor = Executors.newSingleThreadExecutor();

	// ��չExecutor�õ�ִ�����Ĺرսӿ�
	private static ExecutorService executorService = Executors.newSingleThreadExecutor();

	private static ExecutorCompletionService<String> completionService = new ExecutorCompletionService<String>(
			executorService);

	public static void main(String[] args) {

		Runnable task1 = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ": runnable");
			}
		};
		// executor.execute(task1);

		Callable<String> task2 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				// throw new Exception("test");
				return Thread.currentThread().getName() + ": callable";
			}
		};
		Future<String> taskFuture = executorService.submit(task2);
		try {
			System.out.println(taskFuture.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		completionService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				//Thread.currentThread();
				//Thread.sleep(1000);
				return Thread.currentThread().getName() + ": completion service";
			}
		});
		Future<String> result;
		try {
			result = completionService.take();
			System.out.println(result.get());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		executorService.shutdown();

	}

}
