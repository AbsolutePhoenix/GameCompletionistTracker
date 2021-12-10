package games.absolutephoenix.gamecompletionisttracker.logging;

import games.absolutephoenix.gamecompletionisttracker.reference.Reference;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logging logger;
    public static class log {
        public static void info(Object object) {
            logger.log(Level.info, object);
        }

        public static void warn(Object object) {
            logger.log(Level.warn, object);
        }

        public static void error(Object object) {
            logger.log(Level.error, object);
        }

        public static void fatal(Object object) {
            logger.log(Level.fatal, object);
        }

        public static void debug(Object object) {
            logger.log(Level.debug, object);
        }

        public static void trace(Object object) {
            logger.log(Level.trace, object);
        }
    }
    public static void setup() {
        Thread.currentThread().setName((Reference.PROGRAM_NAME).replace(" ", ""));
        logger = new Logging("logs", 5, Level.fatal);
        Logger.log.info("\"" + Reference.PROGRAM_NAME + "\" is currently running version " + Reference.PROGRAM_VERSION);
    }
    protected static class Logging {
        private static String today = "";
        private static int loggingLevel;
        private static boolean logToFile;
        private static int numberOfLogs;
        private static String logLocation = "";

        public Logging(String logLocation, int numberOfLogs, Level loggingLevel) {
            Logging.loggingLevel = loggingLevel.getId();
            logToFile = true;
            Logging.numberOfLogs = numberOfLogs;
            Logging.logLocation = logLocation;
            organizeFiles();
        }
        public Logging(Level loggingLevel) {
            Logging.loggingLevel = loggingLevel.getId();
            logToFile = false;
        }
        public void log(Level level, Object object) {
            if (level.getId() < loggingLevel) {
                if (!today.equals(getDate())) {
                    today = getDate();
                    writeLogToFile(today);
                    System.out.println(today);
                }
                Object output = getTime() + " [" + level.getName() + "/" + Thread.currentThread().getName() + "]: " + object;
                if (logToFile) {
                    writeLogToFile(output);
                }
                System.out.println(output);
            }
        }
        private void writeLogToFile(Object output) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(logLocation + "/current.log", true));
                writer.write(output.toString());
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void organizeFiles() {
            if (!new File(logLocation).exists())
                new File(logLocation).mkdir();
            for (int x = numberOfLogs; x > 0; x--) {
                if (x == numberOfLogs) {
                    new File(logLocation + "/backup-" + numberOfLogs + ".log").delete();
                } else if (x == 1) {
                    new File(logLocation + "/current.log").renameTo(new File(logLocation + "/backup-2.log"));
                    x = -1;
                } else if (new File(logLocation + "/backup-" + x + ".log").exists())
                    new File(logLocation + "/backup-" + x + ".log").renameTo(new File(logLocation + "/backup-" + (x + 1) + ".log"));
            }
        }
        private String getDate() {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("[EEEE, MMMM yyyy]");
            return format.format(date);
        }
        private String getTime() {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa zz");
            return format.format(date);
        }
    }
    protected static class Level{
        public static Level off = new Level(0, "OFF");
        public static Level info = new Level(1000, "INFO");
        public static Level warn = new Level(2000, "WARN");
        public static Level error = new Level(3000, "ERROR");
        public static Level fatal = new Level(4000, "FATAL");
        public static Level debug = new Level(5000, "DEBUG");
        public static Level trace = new Level(6000, "TRACE");
        public static Level all = new Level(100000, "ALL");

        private int id;
        private String name;

        public Level (int id, String name)
        {
            this.id = id;
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }
}
