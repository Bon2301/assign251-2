package org.example;


import org.junit.Test;

import java.io.CharArrayWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class Test3 {
    MemAppender ma = MemAppender.getInstance();

    @Test
    public void addingTest() throws Exception{
        String s = "testing string";
        ma.addLog(s);
        assertEquals("testing string", ma.getCurrentList(0));
        ma.discardLog(0);
    }
    @Test
    public void currentLogTest() throws Exception{
        String s = "testing log";
        ma.addLog(s);
        //assertEquals("testing log", ma.getCurrentList(0));
         assertEquals("INFO: 17/10/2024 in main - testing log", ma.getLogAsString(0));
    }

}
