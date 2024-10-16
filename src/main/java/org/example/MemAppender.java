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
    private static List<String> logList = new ArrayList<String>();
    private static int maxSize;
    private static String pattern;
    private static Layout layout;
    private static Appender append;
    private static int curSize;
    private static long discLogs;

    private MemAppender(Logger LOG, String pattern, Layout layout, Appender append){
        super();
        BasicConfigurator.configure();
        LOG = Logger.getLogger("Log");
        LOG.setLevel(Level.ALL);
        maxSize = 2;
        curSize = 0;
        discLogs = 0;
        append = (Appender)LOG.getAllAppenders().nextElement();
        pattern = "%p: %d{dd/MM/yyyy} in %t - %m%n";
        layout = new org.apache.log4j.PatternLayout(pattern);
        append.setLayout(layout);
        for(int i=0; i<maxSize; i++){
            logList.add("testString");
        }
        System.out.println(logList);

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
//        curSize++;
//        if(curSize>maxSize) {
//            logList.removeFirst();
//            logList.add(x);
//        } else{
//            logList.add(x);
//        }
        logList.removeFirst();
        logList.add(x);
    }
    public void getCurrentLogs(){
        for(int i=0; i<logList.size(); i++){
            LOG.info(logList.get(i));
        }
    }
    public void getEventStrings(){
        for(int i=0; i<logList.size(); i++){
            System.out.println(logList.get(i));
        }
    }
    public void printLogs(){
        for(int i=0; i<logList.size(); i++){
            LOG.info(logList.get(i));
        }
        discLogs += logList.size();
        logList.clear();
    }
    public void getDiscardedLogCount(){
        System.out.println("Total discarded logs:");
        System.out.println(discLogs);
    }
    public String getCurrentList(int i){
        return logList.get(i);
    }
    public int getMaxSize(){
        return maxSize;
    }


}
