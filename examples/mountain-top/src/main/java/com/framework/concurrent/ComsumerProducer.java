package com.framework.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ComsumerProducer {

	private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();

	public static void main(String[] args) throws InterruptedException {

		producer();
		consumer();

		while (true) {
			System.out.println("queue size: " + blockingQueue.size());
			Thread.sleep(1000);
		}
	}

	private static void consumer() {
		Thread consumer = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					String e = blockingQueue.poll();
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (e == null) {
						System.out.println("no element");
					} else {
						System.out.println(e);
					}
				}

			}

		});
		
		consumer.start();
	}

	private static void producer() {
		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				String flag = Thread.currentThread().getName() + ": ";
				for (int i = 0; i < 10000; i++) {
					try {
						blockingQueue.put(flag + i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		});
		
		producer.start();
	}
}
