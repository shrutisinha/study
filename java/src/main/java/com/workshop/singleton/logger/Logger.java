package com.workshop.singleton.logger;

import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;


public class Logger {
    //    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    private static org.slf4j.Logger instance;

    private Logger() {
    }

    public static org.slf4j.Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    //already a singleton
                    instance = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
                }
            }
        }
        return instance;
    }
}