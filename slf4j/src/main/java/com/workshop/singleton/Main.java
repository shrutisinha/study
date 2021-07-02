package com.workshop.singleton;

import com.workshop.singleton.logger.Logger;
import com.workshop.singleton.singleton.CustomLogger;
import org.slf4j.MDC;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

import static com.workshop.singleton.logger.LoggerUtils.*;

public class Main {

    static org.slf4j.Logger logger = Logger.getInstance();
    static CustomLogger customLogger = CustomLogger.getInstance();

    public static void main(String... args) {
        MDC.put("app", "Workshop");
        IntStream.rangeClosed(1,10).forEach(counter ->{
            logger.info("Counter:"+counter);
        });

        long count = 10;
        Instant start = Instant.now();
        generalLog(count);
        Duration generalLogDuration = Duration.between(start, Instant.now());
        start = Instant.now();
        conditionalLog(count);
        Duration conditionalLogDuration = Duration.between(start, Instant.now());
        start = Instant.now();
        parameterizedLog(count);
        Duration parameterizedLogDuration = Duration.between(start, Instant.now());
        logger.error("General Log->{}",generalLogDuration);
        logger.error("Conditional Log->{}",conditionalLogDuration);
        logger.error("parameterized Log->{}",parameterizedLogDuration);

        customLogger.log("error","General Log->"+generalLogDuration);
        customLogger.log("error","Conditional Log->"+conditionalLogDuration);
        customLogger.log("error", "parameterized Log->"+parameterizedLogDuration);
    }
}

