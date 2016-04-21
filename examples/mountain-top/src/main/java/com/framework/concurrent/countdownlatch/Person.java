package com.framework.concurrent.countdownlatch;
/**    
 * Title: Person.java
 * Description:  
 * Copyright: 2014 Duopay, all rights reserved. Duopay PROPRIETARY/CONFIDENTIAL. 
 *            Use is subject to license terms.
 * Company:   Duopay   
 * @author:   Administrator 
 * @version:  1.0 
 * Create at: 2016-4-18
 *  
 */
public class Person {
    
    public Person(String name) {
        this.name = name;
    }
    
    private String name;

    public String getName() {
    
        return name;
    }

    public void setName(String name) {
    
        this.name = name;
    }

}




