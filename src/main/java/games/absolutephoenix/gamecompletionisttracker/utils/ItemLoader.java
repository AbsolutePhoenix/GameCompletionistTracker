package games.absolutephoenix.gamecompletionisttracker.utils;

import games.absolutephoenix.gamecompletionisttracker.GameCompletionistTracker;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;
import org.apache.commons.io.FileUtils;
import org.ini4j.Config;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemLoader {
    public static void loadItem(String selectedGame, String selectedCategory, String selectedSubCategory) {
        List<String[]> savedItemInfo = new ArrayList<>();
        File[] files = new File("games/" + selectedGame + "/" + selectedCategory + "/" + selectedSubCategory).listFiles();
        for (File file : files) {
            List<String> itemInfo = new ArrayList<>();
            itemInfo.add(file.getPath());
            try {
                Wini ini = new Wini();
                Config config = new Config();
                config.setMultiOption(true);
                config.setMultiSection(true);
                ini.setConfig(config);
                ini.load(file);

                itemInfo.add(ini.get("ITEM INFO", "Item Name"));
                itemInfo.add(ini.get("ITEM INFO", "Item Description"));
                itemInfo.add(ini.get("ITEM INFO", "Members Only"));
                itemInfo.add(ini.get("ITEM INFO", "RuneScore"));
                itemInfo.add(ini.get("ITEM INFO", "Item Wiki"));
                itemInfo.add(ini.get("COMPLETION", "Completed"));
                savedItemInfo.add(itemInfo.toArray(new String[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GameReferences.ItemInformation = savedItemInfo.toArray(new String[0][0]);
    }
    public static void loadAllItemCompletion(String Game) {
        List<String[]> savedItemInfo = new ArrayList<>();
         Collection<File> files = FileUtils.listFiles(new File("games/" + Game), new String[]{"ini"}, true);
         for (File file : files) {
            List<String> itemInfo = new ArrayList<>();
            try {
                Wini ini = new Wini();
                Config config = new Config();
                config.setMultiOption(true);
                config.setMultiSection(true);
                ini.setConfig(config);
                ini.load(file);

                itemInfo.add(file.getParentFile().getParentFile().getName());
                itemInfo.add(file.getParentFile().getName());
                itemInfo.add(ini.get("ITEM INFO", "Item Name"));
                itemInfo.add(ini.get("COMPLETION", "Completed"));

                 savedItemInfo.add(itemInfo.toArray(new String[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GameReferences.allItems = savedItemInfo.toArray(new String[0][0]);
    }
    public static void saveItem(int id) {
        try {
            Wini ini = new Wini();
            Config config = new Config();
            config.setMultiOption(true);
            config.setMultiSection(true);
            ini.setConfig(config);

            ini.add("ITEM INFO", "Item Name", GameReferences.ItemInformation[id][1]);
            ini.add("ITEM INFO", "Item Description", GameReferences.ItemInformation[id][2]);
            ini.add("ITEM INFO", "Members Only", GameReferences.ItemInformation[id][3]);
            ini.add("ITEM INFO", "RuneScore", GameReferences.ItemInformation[id][4]);
            ini.add("ITEM INFO", "Item Wiki", GameReferences.ItemInformation[id][5]);
            ini.add("COMPLETION", "Completed", GameReferences.ItemInformation[id][6]);

            ini.store(new File(GameReferences.ItemInformation[id][0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
