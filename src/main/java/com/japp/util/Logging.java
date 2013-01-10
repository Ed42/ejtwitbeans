package com.japp.util;

import com.japp.*;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;


public class Logging {
    
    public static void configureBasicConsoleLogging()
	{
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		lc.reset();

		ch.qos.logback.classic.Logger rootLogger = lc.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

		ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<ILoggingEvent>();
		consoleAppender.setContext(lc);
		PatternLayout pl = new PatternLayout();
		pl.setPattern("%d %5p %t [%c:%L] %m%n)");
		pl.setContext(lc);
		pl.start();
		consoleAppender.setLayout(pl);
		consoleAppender.start();

		rootLogger.addAppender(consoleAppender);
		rootLogger.setLevel(Level.WARN);

	}
    
}
