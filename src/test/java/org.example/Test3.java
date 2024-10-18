package org.example;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Test3 {
    MemAppender ma = MemAppender.getInstance();

    //This test is used to see if the inputted string is being added to the list of logs
    @Test
    public void addingTest(){
        String s = "testing string";
        ma.addLog(s);
        assertEquals("testing string", ma.getCurrentList(0));
        ma.discardLog(0);
    }
    //This test is used to see if the inputted string can be printed as a log
    @Test
    public void currentLogTest(){
        String s = "testing log";
        ma.addLog(s);
        assertEquals("INFO - 18 Oct 2024 in main - testing log", ma.getLogAsString(0));
        ma.discardLog(0);
    }
    //This test is used to check if the layout can be changed
    @Test
    public void setLayoutTest(){
        String pattern = "%m - %t - %d{dd MM yyyy} - %p";
        String s = "testing layout 1";
        ma.setPL(pattern);
        ma.addLog(s);
        assertEquals("testing layout 1 - main - 18 10 2024 - INFO", ma.getLogAsString(0));
        ma.discardLog(0);
        pattern = "%p - %d{dd MMM yyyy} in %t - %m";
        s = "testing layout 2";
        ma.setPL(pattern);
        ma.addLog(s);
        assertEquals("INFO - 18 Oct 2024 in main - testing layout 2", ma.getLogAsString(0));
        ma.discardLog(0);
    }

    //this test checks that the size variables work correctly.
    @Test
    public void sizeTest(){
        ma.setMaxSize(10);
        assertEquals(0, ma.getCurSize());
        assertEquals(10, ma.getMaxSize());
        //at this point in the code, there should be 4 deleted logs.
        assertEquals(4,ma.getDiscLogs());
    }

    //this code tests that the velocity template can be taken and used
    @Test
    public void VelocityTest(){
        String s = "velocity test";
        ma.addLog(s);
        ma.setVL();
        assertEquals("INFO - 18 10 2024 in main - velocity test", ma.getLogAsString(0));
        ma.discardLog(0);
    }



}
