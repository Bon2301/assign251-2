package org.example;


import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;

public class Test3 {
int a = 5;

    @Test
    public void outputTest() throws Exception{
        //insert stuff here
        assert(OutContent.toString(), containsString("a"));
    }

}
