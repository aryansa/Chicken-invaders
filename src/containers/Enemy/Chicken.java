package containers.Enemy;

import containers.DrawbleObject;
import root.Settings;

import java.awt.*;
import java.util.Random;

public class Chicken implements DrawbleObject {
    private int power;
    private Image image;
    private int x;
    private int y;
    private int speed;
    boolean destruction = false;

    Chicken(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    void move() {
        new Thread(() -> {
            Random r = new Random();
            int range = 50 ;
            int newX = getX() + r.nextInt(range + range) - range;
            int newY = getY() + r.nextInt(range + range) - range;
            while (true) {
                if (newX > 0
                        && newY > 0
                        && newX + Settings.CHICKEN_SIZE.width < Settings.SCREEN_SIZE.width
                        && newY + Settings.CHICKEN_SIZE.height < Settings.SCREEN_SIZE.height) {
                    setY(newY);
                    setX(newX);
                    newX = getX() + r.nextInt(range + range) - range;
                    newY = getY() + r.nextInt(range + range) - range;
                } else {
                    newX = getX() +r.nextInt(range + range) - range;
                    newY = getY() +r.nextInt(range + range) - range;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void setPower(int power) {
        this.power = power;
    }

    void setImage(Image image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean isDestruction() {
        return destruction;
    }
}
