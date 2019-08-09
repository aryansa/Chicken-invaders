package containers.Enemy.chickens;

import containers.DrawbleObject;
import root.Settings;

import java.awt.*;

public class Chicken implements DrawbleObject {
    private int power;
    private Image image;
    private double x;
    private double y;
    private int speed;
    boolean destruction = false;
    int health = 5;


    public Chicken(double x, double y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void increaseHealth(int power) {
        health -= power;
        if (health <= 0) {
            setDestruction(true);
        }
    }

    public synchronized void move(float time) {
    }

    void setPower(int power) {
        this.power = power;
    }

    void setImage(Image image) {
        this.image = image;
    }

    public void setX(double x) {
        synchronized (lock) {
            this.x = x;
        }
    }

    public void setY(double y) {
        synchronized (lock) {
            this.y = y;
        }
    }

    public void setDestruction(boolean destruction) {
        this.destruction = destruction;
    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
        return Settings.CHICKEN_SIZE.width;
    }

    @Override
    public double getHeight() {
        return Settings.CHICKEN_SIZE.height;
    }

    @Override
    public boolean isDestruction() {
        return destruction;
    }
}
