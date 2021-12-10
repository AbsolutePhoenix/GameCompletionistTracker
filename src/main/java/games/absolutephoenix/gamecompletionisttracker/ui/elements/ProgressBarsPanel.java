package games.absolutephoenix.gamecompletionisttracker.ui.elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProgressBarsPanel extends JPanel {

    List<JProgressBar> bars = new ArrayList<>();
    List<JLabel> labels = new ArrayList<>();

    public ProgressBarsPanel() {
        setBorder(BorderFactory.createBevelBorder(0));
        GridLayout layout = new GridLayout(0, 1);
        layout.setHgap(110);
        setLayout(layout);
        buildComponentList();
        buildComponents();
    }

    public void buildComponentList() {
        for (int x = 0; x < 100; x++) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setMaximum(100);
            progressBar.setValue(new Random().nextInt(100 - 1) + 1 );
            bars.add(progressBar);
            JLabel label = new JLabel("  bar" + (x + 1));
            labels.add(label);
        }
    }

    public void buildComponents() {
        for(int x = 0; x < bars.size(); x++){
            add(labels.get(x));
            add(bars.get(x));
        }
    }
}
