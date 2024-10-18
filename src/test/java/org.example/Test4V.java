package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Test4V {
    MemAppender ma3 = MemAppender.getInstance();

//    @Test
//    public void velocityLimitTestLow(){
//        ma3.setMaxSize(1);
//        ma3.setVL();
//        for(int i=0; i<ma3.getMaxSize(); i++){
//            String s = "test number "+i;
//            ma3.addLog(s);
//            assertEquals("INFO - 18 10 2024 in main - "+s, ma3.getLogAsString(i));
//        }
//        ma3.printLogs();
//        assertEquals(100007, ma3.getDiscLogs());
//    }
//    @Test
//    public void velocityLimitTestHigh(){
//        ma3.setMaxSize(50000);
//        ma3.setVL();
//        for(int i=0; i<ma3.getMaxSize(); i++){
//            String s = "test number "+i;
//            ma3.addLog(s);
//            assertEquals("INFO - 18 10 2024 in main - "+s, ma3.getLogAsString(i));
//        }
//        ma3.printLogs();
//        assertEquals(100006, ma3.getDiscLogs());
//    }

}


//for testing visual VM use this cmd line:
//visualvm.exe --jdkhome "C:\Program Files\Eclipse Adoptium\jdk-21.0.2.13-hotspot"