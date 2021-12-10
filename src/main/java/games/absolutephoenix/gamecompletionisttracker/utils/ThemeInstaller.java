package games.absolutephoenix.gamecompletionisttracker.utils;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.*;
import games.absolutephoenix.gamecompletionisttracker.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class ThemeInstaller {
    private static void set(JMenuItem theme, String name) {
        switch (name) {
            case "Darcula":
                LafManager.install(new DarculaTheme());
                if (theme != null)
                    try {
                        theme.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(ThemeInstaller.class.getResource("/assets/icons/checkmark.png")))));
                    } catch (IOException e) {
                        Logger.log.error("Unable to find checkmark.png file");
                    }
                break;
            case "High Contrast Dark":
                LafManager.install(new HighContrastDarkTheme());
                if (theme != null)
                    try {
                        theme.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(ThemeInstaller.class.getResource("/assets/icons/checkmark.png")))));
                    } catch (IOException e) {
                        Logger.log.error("Unable to find checkmark.png file");
                    }
                break;
            case "One Dark":
                LafManager.install(new OneDarkTheme());
                if (theme != null)
                    try {
                        theme.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(ThemeInstaller.class.getResource("/assets/icons/checkmark.png")))));
                    } catch (IOException e) {
                        Logger.log.error("Unable to find checkmark.png file");
                    }
                break;
            case "Solarized Dark":
                LafManager.install(new SolarizedDarkTheme());
                if (theme != null)
                    try {
                        theme.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(ThemeInstaller.class.getResource("/assets/icons/checkmark.png")))));
                    } catch (IOException e) {
                        Logger.log.error("Unable to find checkmark.png file");
                    }
                break;
            case "Intellij":
                LafManager.install(new IntelliJTheme());
                if (theme != null)
                    try {
                        theme.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(ThemeInstaller.class.getResource("/assets/icons/checkmark.png")))));
                    } catch (IOException e) {
                        Logger.log.error("Unable to find checkmark.png file");
                    }
                break;
            case "High Contrast Light":
                LafManager.install(new HighContrastLightTheme());
                if (theme != null)
                    try {
                        theme.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(ThemeInstaller.class.getResource("/assets/icons/checkmark.png")))));
                    } catch (IOException e) {
                        Logger.log.error("Unable to find checkmark.png file");
                    }
                break;
            case "Solarized Light":
                LafManager.install(new SolarizedLightTheme());
                if (theme != null)
                    try {
                        theme.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(ThemeInstaller.class.getResource("/assets/icons/checkmark.png")))));
                    } catch (IOException e) {
                        Logger.log.error("Unable to find checkmark.png file");
                    }
                break;
        }
    }
    public static void set(JMenuItem theme) {
        set(theme, theme.getText());
    }
}
