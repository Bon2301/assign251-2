package org.example;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Test3 {
    MemAppender ma = MemAppender.getInstance();

    //This test is used to see if the inputted string is being added to the list of logs
    @Test
    public void addingTest() throws Exception{
        String s = "testing string";
        ma.addLog(s);
        assertEquals("testing string", ma.getCurrentList(0));
        ma.discardLog(0);
    }
    //This test is used to see if the inputted string can be printed as a log
    @Test
    public void currentLogTest() throws Exception{
        String s = "testing log";
        ma.addLog(s);
        assertEquals("INFO - 17 Oct 2024 in main - testing log", ma.getLogAsString(0));
        ma.discardLog(0);
    }
    //This test is used to check if the layout can be changed
    @Test
    public void setLayoutTest() throws Exception{
        String pattern = "%m - %t - %d{dd MM yyyy} - %p";
        String s = "testing layout 1";
        ma.setPL(pattern);
        ma.addLog(s);
        assertEquals("testing layout 1 - main - 17 10 2024 - INFO", ma.getLogAsString(0));
        ma.discardLog(0);
        pattern = "%p - %d{dd MMM yyyy} in %t - %m";
        s = "testing layout 2";
        ma.setPL(pattern);
        ma.addLog(s);
        assertEquals("INFO - 17 Oct 2024 in main - testing layout 2", ma.getLogAsString(0));
        ma.discardLog(0);
    }

    @Test
    public void sizeTest(){
        assertEquals(0, ma.getCurSize());
        assertEquals(2, ma.getMaxSize());
        assertEquals(4,ma.getDiscLogs());
    }

    @Test
    public void VelocityTest(){
        String s = "velocity test";
        ma.addLog(s);
        ma.setVL();
        assertEquals("[INFO] [main] [17/10/2024] = velocity test", ma.getLogAsString(0));
        ma.discardLog(0);
    }



}
