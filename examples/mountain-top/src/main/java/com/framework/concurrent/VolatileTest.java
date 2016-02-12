package com.framework.concurrent;

public class VolatileTest {

	private static volatile boolean isStart = false;

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (!isStart) {

					}

					System.out.println(Thread.currentThread().getName() + ": start");
				}

			}).start();
		}

		//Thread.currentThread().sleep(1000);
		isStart = true;
	}

}
