package games.absolutephoenix.gamecompletionisttracker.ui;

import com.github.weisj.darklaf.listener.ComponentResizeListener;
import games.absolutephoenix.gamecompletionisttracker.actionlisteners.ExitAction;
import games.absolutephoenix.gamecompletionisttracker.actionlisteners.GameSelect;
import games.absolutephoenix.gamecompletionisttracker.actionlisteners.ThemeAction;
import games.absolutephoenix.gamecompletionisttracker.logging.Logger;
import games.absolutephoenix.gamecompletionisttracker.reference.GameReferences;
import games.absolutephoenix.gamecompletionisttracker.reference.Reference;
import games.absolutephoenix.gamecompletionisttracker.reference.Settings;
import games.absolutephoenix.gamecompletionisttracker.ui.elements.*;
import games.absolutephoenix.gamecompletionisttracker.ui.elements.MenuBar;
import games.absolutephoenix.gamecompletionisttracker.utils.ThemeInstaller;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.Objects;

public class MainFrame extends JFrame implements ComponentResizeListener {

    MenuBar menuBar;

    CategoryPanel categoryPanel;
    SubCategoryPanel subCategoryPanel;
    ItemPanel itemPanel;
    JScrollPane categoryScrollPane;
    JScrollPane subCategoryScrollPane;
    JScrollPane itemScrollPane;

    InfoPanel infoPanel;
    JProgressBar totalProgress;
    JLabel totalProgressLabel;

    int ListNoScrollSize;

    public MainFrame(){
        Logger.log.info("Setting up window.");
        buildWindow();
        Logger.log.info("Building UI elements.");
        buildComponents();
        Logger.log.info("Adding UI elements.");
        addComponents();
        Logger.log.info("Configuring action listeners.");
        actionListeners();
        Logger.log.info("Configuring additional listeners.");
        addListeners();
        Logger.log.info("Creating and displaying the frame.");
        finalizeWindow();
    }
    private void buildWindow(){
        setTitle(Reference.PROGRAM_NAME + " v" + Reference.PROGRAM_VERSION);
        try {setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/logos/gct.png"))));} catch (IOException e) {
            Logger.log.error("Unable to locate gtc.png");
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DisplayMode device = new Window(this).getGraphicsConfiguration().getDevice().getDisplayMode();
        setPreferredSize(new Dimension((int) (device.getWidth() * 0.81), (int)(device.getHeight() * 0.81)));
        pack();
        ListNoScrollSize = ((getContentPane().getHeight() - 85) / 27);
        System.out.println(ListNoScrollSize);

        setLayout(null);
    }
    private void buildComponents(){
        menuBar = new MenuBar();
        //CategoryPanel
        categoryPanel = new CategoryPanel(ListNoScrollSize);
        categoryScrollPane = new JScrollPane(categoryPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        categoryScrollPane.setBorder(BorderFactory.createSoftBevelBorder(1));
        //SubCategoryPanel
        subCategoryPanel = new SubCategoryPanel(ListNoScrollSize);
        subCategoryScrollPane = new JScrollPane(subCategoryPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        subCategoryScrollPane.setBorder(BorderFactory.createSoftBevelBorder(1));
        //ItemPanel
        itemPanel = new ItemPanel(ListNoScrollSize);
        itemScrollPane = new JScrollPane(itemPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemScrollPane.setBorder(BorderFactory.createSoftBevelBorder(1));
        //InfoPanel
        infoPanel = new InfoPanel();
        infoPanel.setBorder(BorderFactory.createSoftBevelBorder(1));
        //Footer
        totalProgress = new JProgressBar(SwingConstants.HORIZONTAL);
        totalProgress.setMaximum(100);
        totalProgress.setValue(0);
        totalProgressLabel = new JLabel("Total Progress: 0%");
    }
    private void addComponents(){
        add(menuBar);
        add(categoryScrollPane);
        add(subCategoryScrollPane);
        add(itemScrollPane);
        add(infoPanel);
        add(totalProgress);
        add(totalProgressLabel);
    }
    private void actionListeners(){
        menuBar.getDarculaTheme().addActionListener(new ThemeAction(menuBar.getDarculaTheme()));
        menuBar.getOneDarkTheme().addActionListener(new ThemeAction(menuBar.getOneDarkTheme()));
        menuBar.getSolarizedDarkTheme().addActionListener(new ThemeAction(menuBar.getSolarizedDarkTheme()));
        menuBar.getHighContrastDarkTheme().addActionListener(new ThemeAction(menuBar.getHighContrastDarkTheme()));
        menuBar.getIntellijTheme().addActionListener(new ThemeAction(menuBar.getIntellijTheme()));
        menuBar.getSolarizedLightTheme().addActionListener(new ThemeAction(menuBar.getSolarizedLightTheme()));
        menuBar.getHighContrastLightTheme().addActionListener(new ThemeAction(menuBar.getHighContrastLightTheme()));
        menuBar.getExitItem().addActionListener(new ExitAction());

        for (JMenuItem item : GameReferences.gameMenuItems)
            item.addActionListener(new GameSelect(item));

    }
    private void addListeners() {
        addComponentListener(this);
    }

    private void finalizeWindow() {
        for (JMenuItem item : ThemeAction.allThemes)
            if (item.getText().equals(Settings.CurrentTheme))
                ThemeInstaller.set(item);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public CategoryPanel getCategoryPanel(){
        return categoryPanel;
    }
    public SubCategoryPanel getSubCategoryPanel(){
        return subCategoryPanel;
    }
    public ItemPanel getItemPanel(){
        return itemPanel;
    }
    public InfoPanel getInfoPanel(){
        return infoPanel;
    }

    public void setTotalProgress(int percent) {
        this.totalProgress.setValue(percent);
        this.totalProgress.repaint();
        this.totalProgressLabel.setText("Total Progress: " + percent + "%");
    }

    public void componentResized(ComponentEvent e) {
        menuBar.setBounds(0, 0, getContentPane().getWidth(), 25);

        categoryScrollPane.setBounds(10, 35, (int)(getContentPane().getWidth() / 4.6), getContentPane().getHeight() - 85);
        subCategoryScrollPane.setBounds(categoryScrollPane.getX() + categoryScrollPane.getWidth(), 35, (int)(getContentPane().getWidth() / 4.6), categoryScrollPane.getHeight());
        itemScrollPane.setBounds(subCategoryScrollPane.getX() + subCategoryScrollPane.getWidth(), 35, (int)(getContentPane().getWidth() / 4.6), subCategoryScrollPane.getHeight());

        infoPanel.setBounds(itemScrollPane.getX() + itemScrollPane.getWidth(), 35, (int)(getContentPane().getWidth() / 3), itemScrollPane.getHeight());
        totalProgress.setBounds(10, (infoPanel.getY() + infoPanel.getHeight()) + 35, getContentPane().getWidth() - 20, 5);
        totalProgressLabel.setBounds(10, totalProgress.getY() - 25, getContentPane().getWidth() - 20, 25);
    }
}
