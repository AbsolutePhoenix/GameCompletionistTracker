package games.absolutephoenix.gamecompletionisttracker.actionlisteners;
import games.absolutephoenix.gamecompletionisttracker.GameCompletionistTracker;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;
import games.absolutephoenix.gamecompletionisttracker.utils.ItemLoader;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class GameSelect implements ActionListener {
    JMenuItem item;

    public GameSelect(JMenuItem item){
        this.item = item;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Collection<File> files = FileUtils.listFiles(new File("games/" + item.getText()), new String[]{"ini"}, true);
        List<Integer> values = new ArrayList<Integer>();
        try {
            Scanner scanner = new Scanner(new File("games/" + item.getText() + "/system managed"));
            while (scanner.hasNextLine()) {
                values.add(Integer.parseInt(scanner.nextLine()));
            }
            scanner.close();
        }catch (IOException i){}
        if(!new File("games/" + item.getText() + "/system managed").exists() || !(values.get(0) + "").equals(files.size() + "")) {
            System.out.println("test");
            ItemLoader.loadAllItemCompletion(item.getText());
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("games/" + item.getText() + "/system managed")));
                writer.write(GameReferences.allItems.length + "");
                writer.newLine();

            int completedItems = 0;
            for(String[] item : GameReferences.allItems) {
                System.out.println(Arrays.toString(item));

                if (item[3].equals("true"))
                    completedItems++;
            }

                writer.write(completedItems + "");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Scanner scanner = new Scanner(new File("games/" + item.getText() + "/system managed"));
                while (scanner.hasNextLine()) {
                    values.add(Integer.parseInt(scanner.nextLine()));
                }
                scanner.close();
            }catch (IOException i){}
        }
        GameReferences.totalItems = values.get(0);
        GameReferences.totalCompletedItems = values.get(1);

        for(JMenuItem item: GameReferences.gameMenuItems){
            item.setIcon(null);
        }
        try {
            item.setIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/icons/checkmark.png")))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        GameReferences.currentItem = "";
        GameReferences.currentSubCategory = "";
        GameReferences.currentCategory = "";
        GameCompletionistTracker.mainFrame.getCategoryPanel().createEmptyItems();
        GameCompletionistTracker.mainFrame.getSubCategoryPanel().createEmptyItems();
        GameCompletionistTracker.mainFrame.getItemPanel().createEmptyItems();
        GameCompletionistTracker.mainFrame.getCategoryPanel().buildButtonsList(item.getText());
        for (JButton item : GameReferences.categoryButtons)
            item.addActionListener(new CategorySelection(item));
        GameReferences.currentGame = item.getText();
        GameCompletionistTracker.mainFrame.getInfoPanel().updateInformation();
    }
}
