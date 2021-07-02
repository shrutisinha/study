package com.workshop.singleton.logger;

import com.workshop.singleton.singleton.CustomLogger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.stream.LongStream;

public class LoggerUtils {
    static org.slf4j.Logger logger = Logger.getInstance();
    static CustomLogger customLogger = CustomLogger.getInstance();

    public static void generalLog(long count) {
        LongStream.rangeClosed(1, count).forEach(counter -> {
            logger.info("Counter:"+counter);
            customLogger.log("info","Counter"+counter);
        });
    }

    public static void conditionalLog(long count) {
        LongStream.rangeClosed(1, count).forEach(counter -> {
            if (logger.isInfoEnabled()) {
                logger.info("Counter:"+counter);
                customLogger.log("info","Counter:"+counter);
            }
        });
    }

    public static void parameterizedLog(long count) {
        LongStream.rangeClosed(1, count).forEach(counter -> {
            logger.info("Counter:{}", counter);
        });
    }
}
