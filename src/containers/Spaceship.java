package containers;

import root.Settings;

import java.awt.*;

public class Spaceship implements DrawbleObject {
    private int temp = 0;
    private Image image = Toolkit.getDefaultToolkit().createImage(Settings.SPACESHIP_JPG).getScaledInstance(Settings.SPACESHIP_SIZE.width, Settings.SPACESHIP_SIZE.height, Image.SCALE_DEFAULT);
    private int x = Settings.SCREEN_SIZE.width / 2 - (Settings.SPACESHIP_SIZE.width / 2);
    private int y = Settings.SCREEN_SIZE.height - Settings.SPACESHIP_SIZE.height - 100;
    private double maxTemp = Settings.MAX_TEMP;
    private long lastShootTime = 0;
    Thread increaseTemp = new Thread(this::run);

    public Spaceship() {
        increaseTemp.start();
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    public int getTemp() {
        return temp;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean isDestruction() {
        return false;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public long getLastShootTime() {
        return lastShootTime;
    }

    public boolean canShoot() {
        long now = System.currentTimeMillis();
        if (temp < maxTemp) {
            if (now - lastShootTime > 200) {
                temp += 5;
                lastShootTime = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }

    public void increaseTemp() {
        if (!increaseTemp.isAlive()) {
            increaseTemp.run();
        }
    }

    public void move(float time, int[] location) {
        setX((int) (location[0]));
        setY((int) (location[1]));
    }

    private void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (temp > 0) {
                temp -= 1;
            } else break;
        }
        Thread.currentThread().suspend();
    }
}
