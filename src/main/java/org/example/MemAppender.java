package org.example;

import org.apache.log4j.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import org.apache.log4j.Logger;

public class MemAppender {
    private static Logger LOG;
    private static List<String> logList;
    private static int maxSize;
    private static String pattern;
    private static Layout layout;

    private MemAppender(Logger LOG, String pattern, Layout layout){
        super();
        BasicConfigurator.configure();
        LOG = Logger.getLogger("Log");
        maxSize = 2;
        LOG.setLevel(Level.ALL);
        pattern = "%p: %d{dd/MM/yyyy HH:mm:ss} in %t - %m%n";
        layout = new org.apache.log4j.PatternLayout(pattern);
    }
    private MemAppender() {}

    private static MemAppender instance = new MemAppender();

    public static MemAppender getInstance(){
        return instance;
    }
    public void check(){
        System.out.println("object successfully created");
    }
    public void addLog(String x){
        String s = LOG.info("test");
        logList.add(s);
    }


}
