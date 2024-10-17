package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Test4V {
    MemAppender ma3 = MemAppender.getInstance();

    @Test
    public void VelocityLimitTest(){
        ma3.setVL();
        for(int i=0; i<ma3.getMaxSize(); i++){
            String s = "test number "+i;
            ma3.addLog(s);
            assertEquals("[INFO] [main] [18/10/2024] = "+s, ma3.getLogAsString(i));
        }
        ma3.printLogs();
        assertEquals(ma3.getMaxSize()*2+5, ma3.getDiscLogs());
    }

}
