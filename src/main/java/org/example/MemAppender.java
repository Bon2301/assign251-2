package org.example;

import org.apache.log4j.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class MemAppender {
    private static Logger LOG;
    private static List<String> logList = new ArrayList<String>();
    private static long maxSize;
    private static Appender append;
    private static String pattern;
    private static Layout layout;
    private static Layout layoutV;
    private static long curSize;
    private static long discLogs;
    private static String layoutVP;

    private static VelocityContext vc;
    private static Template template;

    private MemAppender(){
        super();
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        //setup of the Logger
        BasicConfigurator.configure();
        LOG = Logger.getLogger("Log");
        LOG.setLevel(Level.ALL);
        //the maxSize variable can be changed to suit however many the user wants
        maxSize = 10;
        curSize = 0;
        discLogs = 0;
        //setup of the appender and the layout
        append = LOG.getAppender("appender");
        pattern = "%p - %d{dd MMM yyyy} in %t - %m";
        layout = new org.apache.log4j.PatternLayout(pattern);
        LOG.addAppender(append);
        layoutVP = "Pattern";
    }
        //Setup velocity layout
        public void setVL(){
        vc = new VelocityContext();
        template = Velocity.getTemplate("template.vm");
        vc.put("c", "%c");
        vc.put("d", "%d{dd MM yyyy}");
        vc.put("m", "%m");
        vc.put("p", "%p");
        vc.put("t", "%t");
        vc.put("n", "%n");
        layoutVP = "Velocity";
        }
        //setup pattern layout
        public void setPL(String patternToBeUsed){
            pattern = patternToBeUsed;
            layout = new org.apache.log4j.PatternLayout(pattern);
            layoutVP = "Pattern";
        }

        //setup of the singleton pattern
        private static MemAppender instance = new MemAppender();
        public static MemAppender getInstance () {
            return instance;
        }
        public void check () {
            System.out.println("object successfully created");
        }
        //this function is used so the user can change the layout


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
            curSize = 0;
            while(!logList.isEmpty()){
                logList.removeFirst();
            }
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

        //this is used to get the sizes that the list can be
        public long getMaxSize() {
            return maxSize;
        }
        //this is used to change the max size for tests.
        public void setMaxSize(long maxSize){
            this.maxSize = maxSize;
        }
        public long getCurSize(){return curSize;}

        public long getDiscLogs(){return discLogs;}


    //this is used so that the Logs can be returned to the tests
    //as strings.
    //this is important because the logs can't otherwise be added
    //to the tests are they are technically null when they
    //print to the console.
        public String getLogAsString(int i){
            String s = "";
            String p = "";
            StringWriter sw = new StringWriter();
            WriterAppender wa = new WriterAppender(layout, sw);
            StringWriter swV = new StringWriter();
            WriterAppender waV;
            //if template chosen is normal Pattern
            if(layoutVP.equals("Pattern")) {
                LOG.addAppender(wa);
                LOG.info(logList.get(i));
                s = sw.toString();
                LOG.removeAppender(wa);
            //if template chosen is Velocity
            } else if(layoutVP.equals("Velocity")) {
                template.merge(vc, sw);
                p = sw.toString();
                layoutV = new org.apache.log4j.PatternLayout(p);
                waV = new WriterAppender(layoutV, swV);
                LOG.addAppender(waV);
                LOG.info(logList.get(i));
                s = swV.toString();
                LOG.removeAppender(waV);
            }
            return s;
        }


}
