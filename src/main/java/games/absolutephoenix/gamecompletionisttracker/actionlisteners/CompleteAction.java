package games.absolutephoenix.gamecompletionisttracker.actionlisteners;

import games.absolutephoenix.gamecompletionisttracker.GameCompletionistTracker;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;
import games.absolutephoenix.gamecompletionisttracker.utils.ItemLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CompleteAction implements ActionListener {
    int id;
    public CompleteAction(int id) {
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameReferences.ItemInformation[id][6].equals("false")){
            GameReferences.ItemInformation[id][6] = "true";
            GameReferences.totalCompletedItems++;
        }else {
            GameReferences.ItemInformation[id][6] = "false";
            GameReferences.totalCompletedItems--;
        }
        GameCompletionistTracker.mainFrame.getInfoPanel().updateInformation();
        ItemLoader.saveItem(id);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("games/" + GameReferences.currentGame + "/system managed"));
            writer.write( (int)GameReferences.totalItems + "");
            writer.newLine();
            writer.write((int)GameReferences.totalCompletedItems + "");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
