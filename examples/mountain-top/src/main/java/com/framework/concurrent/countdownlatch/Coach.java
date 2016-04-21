package com.framework.concurrent.countdownlatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**    
 * Title: Coach.java
 * Description:  
 * Copyright: 2014 Duopay, all rights reserved. Duopay PROPRIETARY/CONFIDENTIAL. 
 *            Use is subject to license terms.
 * Company:   Duopay   
 * @author:   Administrator 
 * @version:  1.0 
 * Create at: 2016-4-18
 *  
 */
public class Coach {
    
    private void sayStartDM() {
        System.out.println("教练说开始点名。。。");
        OrderController.coachSayDM_start.countDown();
    }
    
    private void sayStartGame() throws InterruptedException {
        
        OrderController.teamMemberRaiseHand_start.await();
        System.out.println("教练说开始游戏。。。");
        OrderController.coachSayGameStart.countDown();
    }
    
    private void sayEndDM() throws InterruptedException {
        
        OrderController.teamMemberGame.await();
        System.out.println("教练说开始点名。。。");
        OrderController.coachSayDM_end.countDown();
    }

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        Coach coach = new Coach();
        ExecutorService service = Executors.newFixedThreadPool(10);
        
        for (int i=1; i <= AssignNumber.getTotalNumbers(); i++) {
            try {
                service.execute(new TeamMember("SEA++_"+i, "ECE"));
            } catch (Exception e) {
                System.out.println("exc: " + i);
                e.printStackTrace();
            }
        }
        
        coach.sayStartDM();
        
        coach.sayStartGame();
        
        coach.sayEndDM();
        
        service.shutdown();
    }

}




