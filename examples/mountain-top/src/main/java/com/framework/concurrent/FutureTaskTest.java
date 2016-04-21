package com.framework.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**    
 * Title: FutureTask.java
 * Description:  
 * Copyright: 2014 Duopay, all rights reserved. Duopay PROPRIETARY/CONFIDENTIAL. 
 *            Use is subject to license terms.
 * Company:   Duopay   
 * @author:   Administrator 
 * @version:  1.0 
 * Create at: 2016-4-21
 *  
 */
public class FutureTaskTest {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        RefundTask task = new RefundTask(123456789);
        Future<Boolean> future = executor.submit(task);
        
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(task);
        executor.submit(futureTask);
        
        System.out.println("Execute Result: " + future.get());
        
        System.out.println("Execute Result: " + futureTask.get());
        
        executor.shutdown();
    }

    private static class RefundTask implements Callable<Boolean> {
        
        private long orderId;
        
        public RefundTask (long orderId) {
            this.orderId = orderId;
        }

        @Override
        public Boolean call() throws Exception {

            System.out.println("Start to refund for order. orderId=" + orderId);
            Random rand = new Random();
            if (rand.nextInt() % 2 == 0) {
                return true;
            }
            
            return false;
        }
        
    }
}




