package gpu;

import root.Settings;

import javax.swing.*;

public class ThrowText extends JLabel {
    private MyPanel myPanel;

    public ThrowText(String s, int t, MyPanel panel) {
        super(s);
        this.myPanel = panel;
        int width = Settings.SCREEN_SIZE.width / 4;
        int height = Settings.SCREEN_SIZE.height / 8;
        this.setBounds(
                (Settings.SPACESHIP_SIZE.width / 2) - (width / 2),
                (Settings.SPACESHIP_SIZE.height / 2) - (height / 2),
                width,
                height
        );
        this.setVisible(true);
        showMSG();
    }

    private void showMSG() {
        this.myPanel.add(this);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.myPanel.remove(this);
    }
}
