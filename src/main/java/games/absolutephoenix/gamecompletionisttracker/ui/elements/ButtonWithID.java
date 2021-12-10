package games.absolutephoenix.gamecompletionisttracker.ui.elements;

import javax.swing.*;

public class ButtonWithID extends JButton {
    public int id;
    public ButtonWithID(Icon icon, int id){
        super(icon);
        this.id = id;
    }
    public ButtonWithID(String text, int id){
        super(text);
        this.id = id;
    }
    public ButtonWithID(Action a, int id){
        super(a);
        this.id = id;
    }
    public ButtonWithID(String text, Icon icon, int id){
        super(text, icon);
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
