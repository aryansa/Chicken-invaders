package gpu;

import root.Settings;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JPanel {
    public TestFrame() {
        this.setBounds(0, 0, Settings.SCREEN_SIZE.width, Settings.SCREEN_SIZE.height);
        this.setBackground(Color.red);
        this.setVisible(true);

    }
}
