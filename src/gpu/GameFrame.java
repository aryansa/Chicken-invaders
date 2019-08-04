package gpu;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static root.Settings.SCREEN_SIZE;

public class GameFrame extends JFrame {
    private JPanel jPanel;

    public GameFrame() throws HeadlessException, SQLException {
        GameFrame gameFrame = this;
        this.setBounds(0, 0, SCREEN_SIZE.width, SCREEN_SIZE.height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Chicken");
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
        this.setBounds(0, 0, SCREEN_SIZE.width, SCREEN_SIZE.height);
    }
}
