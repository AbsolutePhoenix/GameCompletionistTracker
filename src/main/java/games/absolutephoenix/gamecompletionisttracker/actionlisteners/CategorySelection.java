package games.absolutephoenix.gamecompletionisttracker.actionlisteners;

import games.absolutephoenix.gamecompletionisttracker.GameCompletionistTracker;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategorySelection implements ActionListener {
    JButton button;

    public CategorySelection(JButton button){
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        for(JButton item: GameReferences.categoryButtons) {
            item.setBackground(null);
        }
        for(JButton item: GameReferences.subCategoryButtons) {
            item.setBackground(null);
        }
        for(JButton item: GameReferences.itemButtons) {
            item.setBackground(null);
        }
        button.setBackground(Color.lightGray.darker().darker());
        GameReferences.currentSubCategory = "";
        GameReferences.currentItem = "";
        GameCompletionistTracker.mainFrame.getSubCategoryPanel().createEmptyItems();
        GameCompletionistTracker.mainFrame.getItemPanel().createEmptyItems();
        GameReferences.currentCategory = button.getText();
        GameCompletionistTracker.mainFrame.getSubCategoryPanel().buildButtonsList(GameReferences.currentGame, GameReferences.currentCategory);
        for (JButton item : GameReferences.subCategoryButtons)
            item.addActionListener(new SubCategorySelection(item));
        GameCompletionistTracker.mainFrame.getInfoPanel().updateInformation();
    }
}
