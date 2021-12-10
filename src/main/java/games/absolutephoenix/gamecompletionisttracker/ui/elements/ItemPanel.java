package games.absolutephoenix.gamecompletionisttracker.ui.elements;

import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;
import games.absolutephoenix.gamecompletionisttracker.utils.ItemLoader;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel{
    int blankList;

    public ItemPanel(int listNoScrollSize){
        setLayout(new GridLayout(0, 1));
        blankList = listNoScrollSize;
        createEmptyItems();
    }

    public void buildButtonsList(String game, String category, String subCategory){
        ItemLoader.loadItem(game, category, subCategory);
        removeAll();
        int x = 0;
        GameReferences.itemButtons.clear();
        for (String[] items : GameReferences.ItemInformation) {
            ButtonWithID button;

            if(items[1].length() > 50)
                button = new ButtonWithID(items[1].substring(0,49) + " ...", x);
            else
                button = new ButtonWithID(items[1], x);

            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setFocusable(false);
            add(button);
            x++;
            GameReferences.itemButtons.add(button);
        }
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
