package com.framework.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**    
 * Title: OrderController.java
 * Description:  
 * Copyright: 2014 Duopay, all rights reserved. Duopay PROPRIETARY/CONFIDENTIAL. 
 *            Use is subject to license terms.
 * Company:   Duopay   
 * @author:   Administrator 
 * @version:  1.0 
 * Create at: 2016-4-18
 *  
 */
public class OrderController {
    
    public static CountDownLatch coachSayDM_start = new CountDownLatch(1);
    
    public static CountDownLatch teamMemberRaiseHand_start = new CountDownLatch(AssignNumber.getTotalNumbers());
    
    public static CountDownLatch coachSayGameStart = new CountDownLatch(1);
    
    public static CountDownLatch teamMemberGame = new CountDownLatch(AssignNumber.getTotalNumbers());
    
    public static CountDownLatch coachSayDM_end = new CountDownLatch(1);
    
    public static CountDownLatch teamMemberRaiseHand_end = new CountDownLatch(AssignNumber.getTotalNumbers());

}




