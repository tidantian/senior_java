package com.framework.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
	private static AtomicInteger counter = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(100);
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						latch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//System.out.println(Thread.currentThread().getName() + ": " + counter.incrementAndGet());
					counter.incrementAndGet();

				}

			}).start();

			latch.countDown();
		}
		
		Thread.currentThread().sleep(500);
		System.out.println(counter.get());

	}
}
