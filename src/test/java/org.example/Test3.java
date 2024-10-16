package org.example;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Test3 {
    MemAppender ma = MemAppender.getInstance();

    @Test
    public void addingTest() throws Exception{
        ma.addLog("This is Log 1");
        assertEquals(ma.getCurrentList(0),"This is Log 1");
    }

}
