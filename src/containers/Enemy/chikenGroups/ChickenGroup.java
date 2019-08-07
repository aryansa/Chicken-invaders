package containers.Enemy.chikenGroups;

import containers.Enemy.chickens.Chicken;
import root.Settings;

import java.util.ArrayList;

public abstract class ChickenGroup {
    ArrayList<Chicken> chickens = new ArrayList<>();
    double x = Settings.CHICKEN_SIZE.width;
    double y = Settings.SPACESHIP_SIZE.height;
    double endX;
    double endY;
    double stack;
    int speed;
    int space = 40;

    ChickenGroup(int gameLevel) {
    }

    public double getStack() {
        return stack;
    }

    public void repaint() {

    }

    public void setStack(double stack) {
        this.stack = stack;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getEndX() {
        return Double.parseDouble(null);
    }


    public int getSpeed() {
        return speed;
    }

    public double getEndY() {
        return getY() * stack * (Settings.SCREEN_SIZE.height + space);
    }


    public ArrayList<Chicken> getChickens() {
        return chickens;
    }

    public double getX() {
        return x;
    }

    void setX(double x) {
        this.x = x;

    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void move(float time) {
    }

    public void setChickens(ArrayList<Chicken> chickens) {
        this.chickens = chickens;
    }
}
