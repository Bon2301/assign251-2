package org.example;

import jdk.jshell.spi.ExecutionControlProvider;
import org.apache.log4j.*;

import java.io.*;
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
    private static long maxSize;
    private static Appender append;
    private static String pattern;
    private static Layout layout;
    private static int curSize;
    private static long discLogs;

    private MemAppender(){
        super();
        //setup of the Logger
        BasicConfigurator.configure();
        LOG = Logger.getLogger("Log");
        LOG.setLevel(Level.ALL);
        //the maxSize variable can be changed to suit however many the user wants
        maxSize = 2;
        curSize = 0;
        discLogs = 0;
        //setup of the appender and the layout
        append = LOG.getAppender("appender");
        pattern = "%p - %d{dd MMM yyyy} in %t - %m";
        layout = new org.apache.log4j.PatternLayout(pattern);
        LOG.addAppender(append);
    }

        //setup of the singleton pattern
        private static MemAppender instance = new MemAppender();
        public static MemAppender getInstance () {
            return instance;
        }
        public void check () {
            System.out.println("object successfully created");
        }
        public void setLayout(String patternToBeUsed){
            LOG.removeAppender(append);
            append = LOG.getAppender("appender");
            pattern = patternToBeUsed;
            layout = new org.apache.log4j.PatternLayout(pattern);
            LOG.addAppender(append);
        }

        //this function is used to add strings into the memory list ready to be logged
        public void addLog (String x){
            curSize++;
            if (curSize > maxSize) {
                logList.removeFirst();
                logList.add(x);
            } else {
                logList.add(x);
            }
        }
        //this is used to discard logs from the memory list
        public void discardLog(int i){
            curSize--;
            logList.remove(i);
            discLogs++;
        }
        //this prints the current logs held in memory into the console
        public void getCurrentLogs () {
            for (int i = 0; i < logList.size(); i++) {
                LOG.info(logList.get(i));
            }
        }
        //thi prints the current list of strings held in memory into the console
        public void getEventStrings () {
            for (int i = 0; i < logList.size(); i++) {
                System.out.println(logList.get(i));
            }
        }
        //this prints the logs held in memory and then removes them from the memory
        public void printLogs () {
            for (int i = 0; i < logList.size(); i++) {
                LOG.info(logList.get(i));
            }
            discLogs += logList.size();
            logList.clear();
        }
        //this prints the current amount of logs discarded from memory
        public void getDiscardedLogCount () {
            System.out.println("Total discarded logs:");
            System.out.println(discLogs);
        }
        //this is used in the tests to assert the string that was inputted
        public String getCurrentList ( int i){
            return logList.get(i);
        }

        //this is used to get the max size that the list can be
        public long getMaxSize() {
            return maxSize;
        }

        public String getLogAsString(int i){
            StringWriter sw = new StringWriter();
            WriterAppender  wa = new WriterAppender(layout, sw);
            LOG.addAppender(wa);
            LOG.info(logList.get(i));
            String s = sw.toString();
            LOG.removeAppender(wa);
            return s;

        }


}
