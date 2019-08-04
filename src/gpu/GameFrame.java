package gpu;

import javax.swing.*;
import java.awt.*;

import static root.Settings.screenSize;

public class GameFrame extends JFrame {
    private JPanel jPanel;

    public GameFrame() throws HeadlessException {
        GameFrame gameFrame = this;
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Chiken");
        this.setLayout(null);
        Menu menu = new Menu();
        this.setJMenuBar(menu);
        this.jPanel = new WellcomePanel();
        this.add(jPanel);
        this.setVisible(true);
        // comment
    }

    public void setJpanel(JPanel jpanel) {
        this.remove(jPanel);
        this.jPanel = jpanel;
        this.add(jPanel);
        pack();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }
}
