package games.absolutephoenix.gamecompletionisttracker.ui.elements;

import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SubCategoryPanel extends JPanel {

    int blankList;

    public SubCategoryPanel(int listNoScrollSize) {
        GridLayout layout = new GridLayout(0, 1);
        blankList = listNoScrollSize;
        setLayout(layout);
        createEmptyItems();
    }

    public void buildButtonsList(String GameName, String Category) {
        removeAll();
        System.out.println(GameName + "   " +  Category);
        if (GameName != null && Category != null) {
            File[] files = new File("games/" + GameName + "/" + Category).listFiles();
            int x = 0;
            for (File file : files) {
                JButton button = new JButton(file.getName());
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setFocusable(false);
                add(button);
                x++;
                GameReferences.subCategoryButtons.add(button);
            }
            while (x < blankList) {
                JButton button = new JButton(" ");
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setFocusable(false);
                button.setEnabled(false);
                add(button);
                x++;
            }
        }
        revalidate();
        repaint();
    }

    public void createEmptyItems() {
        removeAll();
        int x = 0;
        while (x < blankList) {
            JButton button = new JButton(" ");
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setFocusable(false);
            button.setEnabled(false);
            add(button);
            x++;
        }
        revalidate();
        repaint();
    }


}
