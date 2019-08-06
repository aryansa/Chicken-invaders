package gpu;

import root.Settings;

import javax.swing.*;
import java.awt.*;

public abstract class MyPanel extends JPanel {
    private Image backgroundImage;
    private int width = Settings.SCREEN_SIZE.width;
    private int height = Settings.SCREEN_SIZE.height;
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

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundImage,
                0, 0, new Color(0, 0, 0, 0), null);
    }

    public void core() {
        Thread core = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.repaint();
            }
        });
        setCore(core);
        startCore();

    }

    void throwText(String s, int time) {
        new Thread(()->{
            JLabel jLabel = new JLabel("<html><i style='font-size:20px;color:white'>"+s+"</i></html>");
            int width = Settings.SCREEN_SIZE.width / 16;
            int height = Settings.SCREEN_SIZE.height / 32;
            jLabel.setBounds(
                    (Settings.SCREEN_SIZE.width / 2)-( width/2),
                    (Settings.SCREEN_SIZE.height / 2)-( height/2),
                   width,height
            );
            add(jLabel);
            try {
                Thread.sleep(time*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            remove(jLabel);
        }).start();
    }

    public Thread getCoreThread() {
        return this.core;
    }

    public void stopCore() {
        this.core.stop();
    }

    public void setCore(Thread core) {
        this.core = core;
    }

    public void startCore() {
        this.core.start();
    }


    @Override
    public int getHeight() {
        return height;
    }
}
