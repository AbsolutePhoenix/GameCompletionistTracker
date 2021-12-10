package games.absolutephoenix.gamecompletionisttracker;


import games.absolutephoenix.gamecompletionisttracker.config.ConfigurationHandler;
import games.absolutephoenix.gamecompletionisttracker.logging.Logger;
import games.absolutephoenix.gamecompletionisttracker.reference.Reference;
import games.absolutephoenix.gamecompletionisttracker.ui.MainFrame;
import games.absolutephoenix.gamecompletionisttracker.utils.Parse;
import games.absolutephoenix.gamecompletionisttracker.utils.VersionHandler;

import java.io.IOException;

public class GameCompletionistTracker {
    public static MainFrame mainFrame;

    public static void main(String[] args) throws IOException {
        Parse.programArguments(args);
        Reference.PROGRAM_VERSION = VersionHandler.getVersionString();
        ConfigurationHandler.loadConfiguration();
        Logger.setup();

        mainFrame = new MainFrame();
    }
}
