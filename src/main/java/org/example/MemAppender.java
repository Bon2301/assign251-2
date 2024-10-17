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
    private static int maxSize;
    private static Appender append;
    private static String pattern;
    private static Layout layout;
    private static int curSize;
    private static long discLogs;

    private MemAppender(){
        super();
        BasicConfigurator.configure();
        LOG = Logger.getLogger("Log");
        LOG.setLevel(Level.ALL);
        maxSize = 2;
        curSize = 0;
        discLogs = 0;
        append = LOG.getAppender("appender");
        pattern = "%p: %d{dd/MM/yyyy} in %t - %m%n";
        layout = new org.apache.log4j.PatternLayout(pattern);
        LOG.addAppender(append);
    }


        private static MemAppender instance = new MemAppender();

        public static MemAppender getInstance () {
            return instance;
        }
        public void check () {
            System.out.println("object successfully created");
        }
        public void addLog (String x){
            curSize++;
            if (curSize > maxSize) {
                logList.removeFirst();
                logList.add(x);
            } else {
                logList.add(x);
            }
        }
        public void discardLog(int i){
            curSize--;
            logList.remove(i);
        }

        public void getCurrentLogs () {
            for (int i = 0; i < logList.size(); i++) {
                LOG.info(logList.get(i));
            }
        }
        public void getEventStrings () {
            for (int i = 0; i < logList.size(); i++) {
                System.out.println(logList.get(i));
            }
        }
        public void printLogs () {
            for (int i = 0; i < logList.size(); i++) {
                LOG.info(logList.get(i));
            }
            discLogs += logList.size();
            logList.clear();
        }
        public void getDiscardedLogCount () {
            System.out.println("Total discarded logs:");
            System.out.println(discLogs);
        }
        public String getCurrentList ( int i){
            return logList.get(i);
        }
        public int getMaxSize() {
            return maxSize;
        }
        public String getLogAsString(int i){
            StringWriter sw = new StringWriter();
            WriterAppender  wa = new WriterAppender(layout, sw);
            LOG.addAppender(wa);
            return sw.toString();
        }


}
