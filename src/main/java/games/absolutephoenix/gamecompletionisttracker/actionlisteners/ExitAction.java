package games.absolutephoenix.gamecompletionisttracker.actionlisteners;

import games.absolutephoenix.gamecompletionisttracker.GameCompletionistTracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        GameCompletionistTracker.mainFrame.dispose();
    }
}
