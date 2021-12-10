package games.absolutephoenix.gamecompletionisttracker.utils;

import games.absolutephoenix.gamecompletionisttracker.logging.Logger;
import games.absolutephoenix.gamecompletionisttracker.reference.AppArgs;
import games.absolutephoenix.gamecompletionisttracker.reference.Reference;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class VersionHandler {
    public static String getVersionString(){
        if(AppArgs.DevelopmentMode) {
            try {
                return getRemote();
            } catch (IOException e) {
                Logger.log.error("Unable to find the version.properties file");
                System.exit(1);
            }
        }

        try {
            return getLocal();
        } catch (IOException e) {
            Logger.log.error("Unable to find the version file");
        }
        return null;
    }
    private static String getRemote() throws IOException{
        Properties properties = new Properties();
        properties.load(new FileReader("../gradle.properties"));
        properties.setProperty("buildVersion", (Integer.parseInt(properties.getProperty("buildVersion")) + 1) + "");
        properties.store(new FileWriter("../gradle.properties"), "Updated by " + Reference.PROGRAM_NAME);
        String outputVersion = properties.getProperty("majorVersion") + "." + properties.getProperty("minorVersion") + "." + properties.getProperty("patchVersion") + "." + properties.getProperty("buildVersion") + "-" + properties.getProperty("releaseStatus");
        FileWriter fw = new FileWriter("version.txt");
        fw.write(outputVersion);
        fw.close();
        return outputVersion;
    }
    private static String getLocal() throws IOException {
        Scanner scanner = new Scanner(new File("version"));
        String outputVersion = scanner.nextLine();
        scanner.close();
        return outputVersion;
    }
}
