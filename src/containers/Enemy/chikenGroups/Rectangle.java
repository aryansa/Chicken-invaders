package containers.Enemy.chikenGroups;

import containers.Enemy.chickens.Chicken;
import containers.Enemy.chickens.Level1;
import root.Settings;

public class Rectangle extends ChickenGroup {
    public Rectangle(int gameLevel) {
        super(gameLevel);
        switch (gameLevel) {
            case 1: {
                setY(getY() * 2 / 3);
                for (int i = 1; i <= 5; i++) {
                    for (int j = 1; j <= 4; j++) {
                        chickens.add(new Level1(x *2* (i), y * j));
                    }
                }
                speed = 255;
                break;
            }
        }
    }

    @Override
    public void repaint() {
    }

    @Override
    public void move(float time) {

        if (getChickens().get(0).getX() < 5 || getChickens().get(getChickens().size() - 1).getX() + Settings.CHICKEN_SIZE.width >= Settings.SCREEN_SIZE.width)
            speed = -speed;
        for (Chicken chicken : chickens) {
            chicken.setX(chicken.getX() + (speed * time));
        }
        setX(getX()+(speed*time));
    }

    @Override
    public double getEndX() {

        return getX();
    }
}
