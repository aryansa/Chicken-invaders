package gpu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private JPanel jPanel ;
    public GameFrame() throws HeadlessException {
        GameFrame gameFrame = this;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Chiken");
        this.setLayout(null);
        Menu menu = new Menu();
        this.setJMenuBar(menu);
        this.setVisible(true);
        // comment
    }

    public void setJpanel(JPanel jpanel) {
        this.remove(jpanel);
        this.jPanel = jpanel;
        this.jPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(jpanel);
    }
}
