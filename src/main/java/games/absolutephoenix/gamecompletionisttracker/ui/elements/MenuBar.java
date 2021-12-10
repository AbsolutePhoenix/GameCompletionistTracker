package games.absolutephoenix.gamecompletionisttracker.ui.elements;

import games.absolutephoenix.gamecompletionisttracker.actionlisteners.ThemeAction;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;

import javax.swing.*;
import java.io.File;

public class MenuBar extends JMenuBar {
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu gameMenu = new JMenu("Games");
    private final JMenu settingsMenu = new JMenu("Settings");
    private final JMenu themeMenu = new JMenu("Themes");
    private final JSeparator themeDarkLightSeparator = new JSeparator();
    private final JMenuItem exitItem = new JMenuItem("Exit");
    private final JMenuItem darculaTheme = new JMenuItem("Darcula");
    private final JMenuItem highContrastDarkTheme = new JMenuItem("High Contrast Dark");
    private final JMenuItem oneDarkTheme = new JMenuItem("One Dark");
    private final JMenuItem solarizedDarkTheme = new JMenuItem("Solarized Dark");
    private final JMenuItem intellijTheme = new JMenuItem("Intellij");
    private final JMenuItem highContrastLightTheme = new JMenuItem("High Contrast Light");
    private final JMenuItem solarizedLightTheme = new JMenuItem("Solarized Light");

    public MenuBar() {

        if(new File("games").exists()){
            File[] gameFolders = new File("games").listFiles();
            for (File gameFolder: gameFolders)
                GameReferences.gameMenuItems.add(new JMenuItem(gameFolder.getName()));
        }

        buildMenuItems();
        storeThemes();
    }
    private void buildMenuItems() {
        add(fileMenu);
        add(gameMenu);
        add(settingsMenu);

        fileMenu.add(exitItem);

        for(JMenuItem item: GameReferences.gameMenuItems){
            gameMenu.add(item);
        }
        settingsMenu.add(themeMenu);
        themeMenu.add(darculaTheme);
        themeMenu.add(oneDarkTheme);
        themeMenu.add(solarizedDarkTheme);
        themeMenu.add(highContrastDarkTheme);
        themeMenu.add(themeDarkLightSeparator);
        themeMenu.add(intellijTheme);
        themeMenu.add(solarizedLightTheme);
        themeMenu.add(highContrastLightTheme);
    }
    private void storeThemes() {
        ThemeAction.allThemes.add(darculaTheme);
        ThemeAction.allThemes.add(oneDarkTheme);
        ThemeAction.allThemes.add(solarizedDarkTheme);
        ThemeAction.allThemes.add(highContrastDarkTheme);
        ThemeAction.allThemes.add(intellijTheme);
        ThemeAction.allThemes.add(solarizedLightTheme);
        ThemeAction.allThemes.add(highContrastLightTheme);
    }

    public JMenuItem getDarculaTheme(){
        return darculaTheme;
    }
    public JMenuItem getOneDarkTheme(){
        return oneDarkTheme;
    }
    public JMenuItem getSolarizedDarkTheme(){
        return solarizedDarkTheme;
    }
    public JMenuItem getHighContrastDarkTheme(){
        return highContrastDarkTheme;
    }
    public JMenuItem getIntellijTheme(){
        return intellijTheme;
    }
    public JMenuItem getSolarizedLightTheme(){
        return solarizedLightTheme;
    }
    public JMenuItem getHighContrastLightTheme(){
        return highContrastLightTheme;
    }
    public JMenuItem getExitItem(){
        return exitItem;
    }

}
