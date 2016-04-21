package com.framework.concurrent.countdownlatch;
/**    
 * Title: TeamMember.java
 * Description:  
 * Copyright: 2014 Duopay, all rights reserved. Duopay PROPRIETARY/CONFIDENTIAL. 
 *            Use is subject to license terms.
 * Company:   Duopay   
 * @author:   Administrator 
 * @version:  1.0 
 * Create at: 2016-4-18
 *  
 */
public class TeamMember extends Person implements Runnable {
    
    private String group;
    
    private String fighting = "加油";
    
    private int no;
    
    private long gameTime;
    
    public TeamMember(String name, String group) throws Exception {
        super(name);
        int no = AssignNumber.getNumber();
        if (no == -1) {
            throw new Exception("获取编号失败，出局。");
        }
        
        setNo(no);
        setGroup(group);
    }
    
    public void sayStartDM() throws InterruptedException {
        OrderController.coachSayDM_start.await();
        System.out.println("组：" + group + "， 姓名：" + getName() + ", 喊--到！ No: " + no);
        OrderController.teamMemberRaiseHand_start.countDown();
    }
    
    public void sayEndDM() throws InterruptedException {
        OrderController.coachSayDM_end.await();
        System.out.println("组：" + group + "， 姓名：" + getName() + ", 喊--到！ No: " + no);
        OrderController.teamMemberRaiseHand_end.countDown();
    }
    
    public void playGame() throws InterruptedException {
        OrderController.coachSayGameStart.await();
        long start = System.currentTimeMillis();
        OrderController.teamMemberGame.countDown();
        setGameTime(System.currentTimeMillis() - start);
        
        System.out.println("组：" + group + "， 姓名：" + getName() + ", 游戏时间：" + getGameTime());
    }

    public String getGroup() {
    
        return group;
    }

    public void setGroup(String group) {
    
        this.group = group;
    }

    public String getFighting() {
    
        return fighting;
    }

    public void setFighting(String fighting) {
    
        this.fighting = fighting;
    }

    public int getNo() {
    
        return no;
    }

    public void setNo(int no) {
    
        this.no = no;
    }

    public long getGameTime() {
    
        return gameTime;
    }

    public void setGameTime(long gameTime) {
    
        this.gameTime = gameTime;
    }

    @Override
    public void run() {

        try {
            sayStartDM();
            
            playGame();
            
            sayEndDM();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}




