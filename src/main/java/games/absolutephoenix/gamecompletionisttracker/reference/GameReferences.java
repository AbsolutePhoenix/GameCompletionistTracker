package games.absolutephoenix.gamecompletionisttracker.reference;

import games.absolutephoenix.gamecompletionisttracker.ui.elements.ButtonWithID;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameReferences {

    public static List<JMenuItem> gameMenuItems = new ArrayList<>();
    public static List<JButton> categoryButtons = new ArrayList<>();
    public static List<JButton> subCategoryButtons = new ArrayList<>();
    public static List<ButtonWithID> itemButtons = new ArrayList<>();
    public static String[][] allItems = new String[][]{};
    public static String[][] ItemInformation = new String[][]{};
    public static String currentGame = "";
    public static String currentCategory = "";
    public static String currentSubCategory = "";
    public static String currentItem = "";
    public static double totalItems = 0;
    public static double totalCompletedItems = 0;

}
