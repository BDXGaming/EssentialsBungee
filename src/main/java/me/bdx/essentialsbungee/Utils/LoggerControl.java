package me.bdx.essentialsbungee.Utils;

import java.util.logging.Logger;

public class LoggerControl {

    private static Logger logger;

    /** Sets the active logger (Called by either Spigot Entrypoint or BungeeCord)
     * @param LoggerInstance Logger
     */
    public static void setLogger(Logger LoggerInstance){
        logger = LoggerInstance;
    }

    /**
     * All logs calls are logged as INFO
     * @param msg String
     */
    public static void logInfo(String msg){
        logger.info(msg);
    }

    /** Logs a string as a warning to the active logger
     * @param msg String
     */
    public static void logWarning(String msg){
        logger.warning(msg);
    }

}
