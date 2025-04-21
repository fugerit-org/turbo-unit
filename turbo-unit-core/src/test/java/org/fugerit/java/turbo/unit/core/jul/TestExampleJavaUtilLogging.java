package org.fugerit.java.turbo.unit.core.jul;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/*
 * This class test if a specific line of log has been written using java util logging
 */
class TestExampleJavaUtilLogging {

    @Test
    void testLogHello() {
        ExampleJavaUtilLogging log = new ExampleJavaUtilLogging();
        log.logHello();
        Assertions.assertTrue( LOG_MESSAGE_INTERCEPTOR_HANDLER.containsLogMessage(ExampleJavaUtilLogging.HELLO) );
    }

    private static final LogMessageInterceptorHandler LOG_MESSAGE_INTERCEPTOR_HANDLER =
            new LogMessageInterceptorHandler();

    /*
     * Store the log level to restore at the end
     */
    private static Level previousLevel;

    /*
     * The logger to configure in order to intercept the log message
     */
    private static Logger logger = Logger.getLogger(ExampleJavaUtilLogging.class.getName());

    /*
     * Logging handler used to test if a message has been really logged.
     */
    public static class LogMessageInterceptorHandler extends Handler {

        private Set<String> messages = new HashSet<>();

        @Override
        public void publish(LogRecord lr) {
            // add log messages to a set
            this.messages.add(lr.getMessage());
        }

        @Override
        public void flush() {
            // no need to customize this method
        }

        @Override
        public void close() throws SecurityException {
            // no need to customize this method
        }

        @Override
        public boolean isLoggable(LogRecord lr) {
            return super.isLoggable(lr);
        }

        public boolean containsLogMessage(String message) {
            // check if a message has been logged
            return this.messages.contains(message);
        }

    }

    @BeforeAll
    static void start() {
        // add log message intercepting handler
        logger.addHandler(LOG_MESSAGE_INTERCEPTOR_HANDLER);
        previousLevel = logger.getLevel();
        logger.setLevel(Level.FINEST);
    }

    @AfterAll
    static void end() {
        // restore the logger status
        logger.removeHandler(LOG_MESSAGE_INTERCEPTOR_HANDLER);
        logger.setLevel(previousLevel);
    }

}
