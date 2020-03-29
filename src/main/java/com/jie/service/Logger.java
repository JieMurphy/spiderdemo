package com.jie.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Logger {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class.getSimpleName());

    public String logMessage(String type, String hex) {
        String log = type + " " + hex;
        logger.info(log);
        return log;
    }

    public String logEvent(String event) {
        String log = event;
        logger.info(log);
        return log;
    }

    public String logUsers(String event, String messages)
    {
        String log = event + " " + messages;
        logger.info(log);
        return log;
    }
}
