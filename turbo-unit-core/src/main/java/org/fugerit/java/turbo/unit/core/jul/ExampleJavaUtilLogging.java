package org.fugerit.java.turbo.unit.core.jul;

import java.util.logging.Logger;

/**
 * Sample class implementing ha method responsible for logging the line :
 *
 * "Hello Turbo!"
 *
 * at INFO level, using JUL (Java Util Logging)
 */
public class ExampleJavaUtilLogging {

    public static final String HELLO = "Hello Turbo!";

    private static Logger log = Logger.getLogger(ExampleJavaUtilLogging.class.getName());

    public void logHello() {
        log.info( HELLO );
    }

}
