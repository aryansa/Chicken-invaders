package gpu;

import root.Settings;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JPanel {
    public TestFrame() {
        this.setBounds(0, 0, Settings.screenSize.width, Settings.screenSize.height);
        this.setBackground(Color.red);
        this.setVisible(true);

    }
}
