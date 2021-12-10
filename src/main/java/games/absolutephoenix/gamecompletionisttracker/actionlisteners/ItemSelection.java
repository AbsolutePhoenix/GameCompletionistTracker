package games.absolutephoenix.gamecompletionisttracker.actionlisteners;

import games.absolutephoenix.gamecompletionisttracker.GameCompletionistTracker;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;
import games.absolutephoenix.gamecompletionisttracker.ui.elements.ButtonWithID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemSelection implements ActionListener {
    public ButtonWithID button;
    public ItemSelection(ButtonWithID button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(ButtonWithID item: GameReferences.itemButtons) {
            item.setBackground(null);
            if(GameReferences.ItemInformation[item.getId()][6].equals("true"))
                item.setBackground(Color.green.darker().darker().darker().darker().darker());
        }
        button.setBackground(Color.lightGray.darker().darker());
        GameReferences.currentItem = GameReferences.ItemInformation[button.id][1];
        GameCompletionistTracker.mainFrame.getInfoPanel().updateInformation();
    }
}
