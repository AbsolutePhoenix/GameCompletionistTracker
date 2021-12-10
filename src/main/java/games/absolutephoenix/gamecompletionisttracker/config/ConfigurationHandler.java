package games.absolutephoenix.gamecompletionisttracker.config;

import games.absolutephoenix.gamecompletionisttracker.reference.Settings;
import org.ini4j.Config;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class ConfigurationHandler {
    public static void loadConfiguration(){
        if(!new File("config/programConfig.ini").exists()) {
            new File("config").mkdir();
            saveConfiguration();
        }
        try {
            Wini ini = new Wini();
            Config config = new Config();
            config.setMultiOption(true);
            config.setMultiSection(true);
            ini.setConfig(config);
            ini.load(new File("config/programConfig.ini"));

            Settings.CurrentTheme = ini.get("Visual Settings",  "Theme");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveConfiguration(){
        try {
        Wini ini = new Wini();
        Config config = new Config();
        config.setMultiOption(true);
        config.setMultiSection(true);
        ini.setConfig(config);

        ini.add("Visual Settings",  "Theme", Settings.CurrentTheme);
        ini.get("Visual Settings").putComment("Theme", "Set the them of the window. possible Options: Darcula, Intellij, One Dark, High Contrast Dark, High Contrast Light, Solarized Dark, Solarized Light");

        ini.store(new File("config/programConfig.ini"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
