package games.absolutephoenix.gamecompletionisttracker.actionlisteners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WikiAction implements ActionListener {

    String url;
    public WikiAction(String url){
        this.url = url;
    }
    public void actionPerformed(ActionEvent e) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
