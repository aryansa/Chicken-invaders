package containers.bullet;

import containers.DrawbleObject;
import root.Settings;

import java.awt.*;

public class Bullet implements DrawbleObject {
    private int power;
    private Image image;
    private double x;
    private double y;
    private int speed;
    boolean destruction = false;

    Bullet(double x, double y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;

    }

    private int getSpeed() {
        return speed;
    }

    public synchronized void move(float time) {
        setY((getY() - (speed * time)));
    }

    public void setDestruction(boolean destruction) {
        this.destruction = destruction;
    }

    public boolean isDestruction() {
        return destruction;
    }

    void setPower(int power) {
        this.power = power;
    }

    void setImage(Image image) {
        this.image = image;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public void setX(double x) {
        synchronized (lock) {
            this.x = x;
        }
    }

    public void setY(double y) {
        synchronized (lock) {
            this.y = y;
            if (y <= 0 || y >= Settings.SCREEN_SIZE.height)
                setDestruction(true);
        }
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
        return Settings.BULLET_SIZE.width;
    }

    @Override
    public double getHeight() {
        return Settings.BULLET_SIZE.height;
    }

    public int getPower() {
        return power;
    }
}
