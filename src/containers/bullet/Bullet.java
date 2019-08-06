package containers.bullet;

import containers.DrawbleObject;

import java.awt.*;

abstract class Bullet implements DrawbleObject {
    private int power;
    private Image image;
    private int x;
    private int y;
    private int speed;
    boolean destruction = false;

    Bullet(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;

    }

    private int getSpeed() {
        return speed;
    }

    void move() {
        new Thread(() -> {
            do {
                try {
                    Thread.sleep(getSpeed() * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setY(getY() - speed*10);
            } while (getY() >= 0);
            setDestruction(true);
            Thread.currentThread().interrupt();
        }).start();
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getPower() {
        return power;
    }
}
