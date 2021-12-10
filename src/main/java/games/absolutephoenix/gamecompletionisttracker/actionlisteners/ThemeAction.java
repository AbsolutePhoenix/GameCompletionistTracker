package games.absolutephoenix.gamecompletionisttracker.actionlisteners;

import games.absolutephoenix.gamecompletionisttracker.config.ConfigurationHandler;
import games.absolutephoenix.gamecompletionisttracker.logging.Logger;
import games.absolutephoenix.gamecompletionisttracker.reference.Settings;
import games.absolutephoenix.gamecompletionisttracker.utils.ThemeInstaller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ThemeAction implements ActionListener {
    JMenuItem theme;
    public static List<JMenuItem> allThemes = new ArrayList<>();
    public ThemeAction(JMenuItem theme){
        this.theme = theme;
    }
    public void actionPerformed(ActionEvent e) {
        Thread.currentThread().setName("GCT - Theme Manager");
        for (JMenuItem themeCount : allThemes) {
            themeCount.setIcon(null);
        }
        ThemeInstaller.set(theme);
        Settings.CurrentTheme = theme.getText();
        ConfigurationHandler.saveConfiguration();
        Logger.log.info("Theme has been set to " + theme.getText());
    }
}
