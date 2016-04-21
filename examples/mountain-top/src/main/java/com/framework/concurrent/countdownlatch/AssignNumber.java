package com.framework.concurrent.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: AssignNumber.java Description: Copyright: 2014 Duopay, all rights
 * reserved. Duopay PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Company: Duopay
 * 
 * @author: Administrator
 * @version: 1.0 Create at: 2016-4-18
 */
public class AssignNumber {

    private static ReentrantLock lock = new ReentrantLock();

    private static int totalNumbers = 10;

    private static boolean initializationFlag = false;

    private static List<Node> numberList = new ArrayList<Node>();

    private static void initNumbersSet() {

        for (int i = 1; i <= totalNumbers; i++) {
            Node node = new Node();
            node.setNumber(i);
            node.setNameNo(null);
            numberList.add(node);
        }

        initializationFlag = true;
    }

    public static int getNumber() {

        if (!initializationFlag) {
            System.out.println("init number list");
            initNumbersSet();
        }

//        lock.lock();
        try {
            for (Node node: numberList) {
                System.out.println("-- " + node.getNumber());
                if (node.getNameNo() == null || node.getNameNo().isEmpty()) {
                    node.setNameNo(String.valueOf(node.getNumber()));
                    return node.getNumber();
                }
            }
        } finally {
//            lock.unlock();
        }

        return -1;
    }

    public static int getTotalNumbers() {

        return totalNumbers;
    }

    public static void setTotalNumbers(int totalNumbers) {

        AssignNumber.totalNumbers = totalNumbers;
    }
    
    public static List<Node> getNumberList() {
        
        return numberList;
    }

    public static void setNumberList(List<Node> numberList) {
    
        AssignNumber.numberList = numberList;
    }

    static class Node {

        private int number;

        private String nameNo;

        public String getNameNo() {

            return nameNo;
        }

        public void setNameNo(String nameNo) {

            this.nameNo = nameNo;
        }

        public int getNumber() {

            return number;
        }

        public void setNumber(int number) {

            this.number = number;
        }
    }
}
