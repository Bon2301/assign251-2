package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

//stress tests
public class Test4 {
    MemAppender ma = MemAppender.getInstance();

    @Test
    public void VelocityTest(){
        String s = "velocity test";
        ma.addLog(s);
        ma.setVL();
        assertEquals("This is a test to see if logs are working velocity test", ma.getLogAsString(0));
        ma.discardLog(0);
    }

}
