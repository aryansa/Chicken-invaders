package gpu;

import root.Settings;

import javax.swing.*;
import java.awt.*;

public abstract class MyPanel extends JPanel {
    private Image backgroundImage;
    private int width = Settings.SCREEN_SIZE.width;
    private int height = Settings.SCREEN_SIZE.height;
    private long previousTime = System.currentTimeMillis();
    private long currentTime = previousTime;
    private long elapsedTime;
    Thread core;

    MyPanel() {
        this.setLayout(null);
        this.setBounds(0, 0, width, height);
        backgroundImage = Toolkit.getDefaultToolkit().createImage(Settings.BACKGROUND_IMAGE).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
        core();

    }

    void setBackgroundImage(String image) {
        backgroundImage = Toolkit.getDefaultToolkit().createImage(image).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundImage,
                0, 0, new Color(0, 0, 0, 0), null);
    }

    private void core() {
        core = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.repaint();
            }
        });
        core.start();
//        new Thread(() -> {
//            while (true) {
//                currentTime = System.currentTimeMillis();
//                elapsedTime = (currentTime - previousTime);
//                handel(elapsedTime / 1000f);
//                this.repaint();
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                previousTime = currentTime;
//            }
//        }).start();
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void handel(float elapsedTime) {

    }

    @Override
    public int getHeight() {
        return height;
    }
}
