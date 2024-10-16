package org.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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
    private static DateFormat DATE_FORMAT;
    private static List<Logger> logList;

    private MemAppender(Logger LOG, DateFormat DATE_FORMAT){
        super();
        BasicConfigurator.configure();
        LOG = Logger.getLogger("Log");
        DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    }
    private MemAppender() {}

    private static MemAppender instance = new MemAppender();

    public static MemAppender getInstance(){
        return instance;
    }
    public void check(){
        System.out.println("object successfully created");
    }


}
