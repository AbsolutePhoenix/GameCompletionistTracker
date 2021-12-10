package games.absolutephoenix.gamecompletionisttracker.ui.elements;

import com.github.weisj.darklaf.listener.ComponentResizeListener;
import games.absolutephoenix.gamecompletionisttracker.GameCompletionistTracker;
import games.absolutephoenix.gamecompletionisttracker.actionlisteners.CompleteAction;
import games.absolutephoenix.gamecompletionisttracker.actionlisteners.WikiAction;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;
import games.absolutephoenix.gamecompletionisttracker.utils.ItemLoader;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class InfoPanel extends JPanel implements ComponentResizeListener {

    private static final Font HeaderFont = new Font(Font.DIALOG, Font.BOLD, 14);

    private JLabel navigationHeader;
    private JLabel itemHeader;
    private JLabel description;
    private JLabel membersOnly;
    private JLabel runeScore;
    private JLabel completed;
    private JButton link;
    private JButton complete;

    public WikiAction linkAction;
    public CompleteAction completeAction;

    public InfoPanel(){
        setLayout(null);
        buildPane();
    }
    private void buildPane(){
        addComponentListener(this);
        navigationHeader = new JLabel("");
        navigationHeader.setFont(HeaderFont);
        navigationHeader.setBorder(BorderFactory.createTitledBorder("Path"));
        navigationHeader.setVerticalAlignment(SwingConstants.TOP);
        add(navigationHeader);

        itemHeader = new JLabel("");
        itemHeader.setFont(HeaderFont);
        itemHeader.setBorder(BorderFactory.createTitledBorder("Achievement"));
        itemHeader.setVerticalAlignment(SwingConstants.TOP);
        add(itemHeader);

        description = new JLabel("");
        description.setVerticalAlignment(SwingConstants.TOP);
        description.setBorder(BorderFactory.createTitledBorder("Description"));
        add(description);

        runeScore = new JLabel("");
        runeScore.setVerticalAlignment(SwingConstants.TOP);
        runeScore.setBorder(BorderFactory.createTitledBorder("RuneScore"));
        add(runeScore);

        membersOnly = new JLabel("");
        membersOnly.setVerticalAlignment(SwingConstants.TOP);
        membersOnly.setBorder(BorderFactory.createTitledBorder("Members Only"));
        add(membersOnly);

        completed = new JLabel("");
        completed.setVerticalAlignment(SwingConstants.TOP);
        completed.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1), "Completed", 1,  1));
        add(completed);

        link = new JButton("Wiki");
        link.setFocusable(false);
        add(link);

        complete = new JButton("Complete Task");
        completed.setFocusable(false);
        add(complete);
    }
    public void updateInformation(){
        GameCompletionistTracker.mainFrame.setTotalProgress((int)((GameReferences.totalCompletedItems / GameReferences.totalItems) * 100));
        String outputHeader = "";
        if(!GameReferences.currentGame.equals(""))
            outputHeader = GameReferences.currentGame;
        if(!GameReferences.currentCategory.equals(""))
            outputHeader = outputHeader + "/" + GameReferences.currentCategory;
        if(!GameReferences.currentSubCategory.equals(""))
            outputHeader = outputHeader + "/" + GameReferences.currentSubCategory;
        navigationHeader.setText(outputHeader);
        itemHeader.setText("<html>" + GameReferences.currentItem + "</html>");
        runeScore.setText("");
        description.setText("");
        completed.setText("");
        membersOnly.setText("");
        link.setEnabled(false);
        complete.setEnabled(false);
        int id = 0;
        for(String[] item : GameReferences.ItemInformation) {
            if(item[1].equals(GameReferences.currentItem)) {
                description.setText("<html>" + item[2].replace("    ", "<br/><br/>") + "</html>");

                if(GameReferences.currentGame.equals("Runescape")) {
                    membersOnly.setText(item[3]);
                    runeScore.setText(item[4]);
                }
                completed.setText(item[6]);

                link.removeActionListener(linkAction);
                if(!item[5].equals("")) {
                    linkAction = new WikiAction(item[5]);
                    link.addActionListener(linkAction);
                    link.setEnabled(true);
                }
                complete.removeActionListener(completeAction);

                if(item[6].equals("false")){
                    complete.setText("Complete");
                }else{
                    complete.setText("Un-Complete");
                }
                completeAction = new CompleteAction(id);
                complete.addActionListener(completeAction);
                complete.setEnabled(true);
            }
            id++;
        }
    }
    @Override
    public void componentResized(ComponentEvent e) {
        navigationHeader.setBounds(10, 10, getWidth() - 20, 35);
        itemHeader.setBounds(navigationHeader.getX(), navigationHeader.getY() + navigationHeader.getHeight() + 10, navigationHeader.getWidth(), (int)(navigationHeader.getHeight()* 2.5));
        description.setBounds(itemHeader.getX(), itemHeader.getY() + itemHeader.getHeight() + 10, itemHeader.getWidth(), getHeight() / 2);
        runeScore.setBounds(description.getX(), description.getY() + description.getHeight() + 10, (description.getWidth() / 2) - 5, 35);
        membersOnly.setBounds(runeScore.getX() + runeScore.getWidth() + 10, runeScore.getY(), runeScore.getWidth(), runeScore.getHeight());
        completed.setBounds(10, getHeight() - 55, membersOnly.getWidth(), membersOnly.getHeight() + 10);
        link.setBounds(completed.getX() + completed.getWidth() + 5, completed.getY() + 15, completed.getWidth() / 2, completed.getHeight() - 15);
        complete.setBounds(link.getX() + link.getWidth() + 5, link.getY(), link.getWidth(), link.getHeight());
    }
}
