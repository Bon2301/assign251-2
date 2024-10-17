package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

//stress tests
public class Test4 {
    MemAppender ma2 = MemAppender.getInstance();
    @Test
    public void PatternLimitTest(){
        ma2.setPL("%p - %d{dd MM yyyy} in %t - %m");
        for(int i=0; i<ma2.getMaxSize(); i++){
            String s = "test number "+i;
            ma2.addLog(s);
            assertEquals("INFO - 18 10 2024 in main - "+s, ma2.getLogAsString(i));
        }
        ma2.printLogs();
        assertEquals(ma2.getMaxSize()+5, ma2.getDiscLogs());
    }


}
