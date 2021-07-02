package com.workshop.singleton.singleton;

import com.workshop.singleton.logger.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class CustomLogger{
    //Implements a singleton logger instance
    private static CustomLogger instance;
    private static final Calendar cal = Calendar.getInstance();


    //Retrieve the execution directory. Note that this is whereever this process was launched
    public String logname = "customLog";
    protected String env = System.getProperty("user.dir");
    private static File logFile;

    public static CustomLogger getInstance(){
        if (instance == null) {
            synchronized (CustomLogger.class) {
                if (instance == null) {
                    instance = new CustomLogger();
                }
            }
        }
        return instance;
    }

    public static CustomLogger getInstance(String withName){
        instance.logname = withName;
        instance.createLogFile();
        return instance;
    }

    public void createLogFile(){
        //Determine if a logs directory exists or not.
        File logsFolder = new File(env + '/' + "logs");
        if(!logsFolder.exists()){
            //Create the directory
            System.err.println("INFO: Creating new logs directory in " + env);
            logsFolder.mkdir();

        }

        //Get the current date and time
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //Create the name of the file from the path and current time
        logname =  logname + '-' +  dateFormat.format(cal.getTime()) + ".log";
        CustomLogger.logFile = new File(logsFolder.getName(),logname);
        try{
            if(logFile.createNewFile()){
                //New file made
                System.err.println("INFO: Creating new log file");
            }
        }catch(IOException e){
            System.err.println("ERROR: Cannot create log file");
            System.exit(1);
        }
    }

    private CustomLogger(){
        if (instance != null){
            //Prevent Reflection
            throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
        }
        this.createLogFile();
    }

    public void log(String type, String message){
        StringBuilder sb = new StringBuilder(1024);
        DateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        sb.append("[").append(type).append("] ").append(dateTimeFormat.format(cal.getTime())).append(" - ").append(message);
        String modifiedMsg = sb.toString();

        //print to console
        System.out.println(modifiedMsg);

        //write to file
        try{
            FileWriter out = new FileWriter(CustomLogger.logFile, true);
            out.write(modifiedMsg.toCharArray());
            out.write('\n');
            out.close();
        }catch(IOException e){
            System.err.println("ERROR: Could not write to log file");
        }
    }

}