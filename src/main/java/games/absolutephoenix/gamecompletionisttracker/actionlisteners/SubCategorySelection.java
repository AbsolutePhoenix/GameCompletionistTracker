package games.absolutephoenix.gamecompletionisttracker.actionlisteners;

import games.absolutephoenix.gamecompletionisttracker.GameCompletionistTracker;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;
import games.absolutephoenix.gamecompletionisttracker.ui.elements.ButtonWithID;
import games.absolutephoenix.gamecompletionisttracker.ui.elements.ItemPanel;
import games.absolutephoenix.gamecompletionisttracker.ui.elements.SubCategoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubCategorySelection implements ActionListener {
    JButton button;

    public SubCategorySelection(JButton button){
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton item: GameReferences.subCategoryButtons) {
            item.setBackground(null);
        }
        for(JButton item: GameReferences.itemButtons) {
            item.setBackground(null);
        }
        GameReferences.currentItem = "";
        button.setBackground(Color.lightGray.darker().darker());
        GameReferences.currentSubCategory = button.getText();
        GameCompletionistTracker.mainFrame.getItemPanel().buildButtonsList(GameReferences.currentGame, GameReferences.currentCategory, GameReferences.currentSubCategory);
        for (ButtonWithID item : GameReferences.itemButtons) {
            if(GameReferences.ItemInformation[item.getId()][6].equals("true"))
                item.setBackground(Color.green.darker().darker().darker().darker().darker());
            item.addActionListener(new ItemSelection(item));
        }
        GameCompletionistTracker.mainFrame.getInfoPanel().updateInformation();
    }
}
