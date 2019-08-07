package containers;

import root.Settings;

import java.awt.*;

public class Spaceship implements DrawbleObject {
    private int temp = 0;
    private Image image = Toolkit.getDefaultToolkit().createImage(Settings.SPACESHIP_JPG).getScaledInstance(Settings.SPACESHIP_SIZE.width, Settings.SPACESHIP_SIZE.height, Image.SCALE_DEFAULT);
    private double x = Settings.SCREEN_SIZE.width / 2 - (Settings.SPACESHIP_SIZE.width / 2);
    private double y = Settings.SCREEN_SIZE.height - Settings.SPACESHIP_SIZE.height - 100;
    private double maxTemp = Settings.MAX_TEMP;
    private long lastShootTime = 0;
    Thread increaseTemp = new Thread(this::run);

    public Spaceship() {

        increaseTemp.start();
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    private void setX(double x) {
        this.x = x;

    }

    private void setY(double y) {
        synchronized (lock) {
            this.y = y;
        }
    }

    public int getTemp() {
        synchronized (lock) {
            return temp;
        }
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public double getX() {
        synchronized (lock) {
            return x;
        }
    }

    @Override
    public double getY() {
        synchronized (lock) {
            return y;
        }
    }

    @Override
    public double getWidth() {
        return Settings.SPACESHIP_SIZE.width;
    }

    @Override
    public double getHeight() {
        return Settings.SPACESHIP_SIZE.height;
    }

    @Override
    public boolean isDestruction() {
        return false;
    }

    @Override
    public void move(float time) {

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


    public void move(float time, int[] location) {
        setX((location[0]));
        setY(location[1]);
    }

    private void run() {
        while (true) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (temp > 0) {
                setTemp(temp - 1);
            }
        }
    }
}
