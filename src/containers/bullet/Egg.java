package containers.bullet;

import root.Settings;

import java.awt.*;

public class Egg extends Bullet {
    public Egg(double x, double y) {
        super(x, y,-500);
        String bulletImage = Settings.CHICKEN_BULLET;
        setImage(Toolkit.getDefaultToolkit().createImage(bulletImage).getScaledInstance(Settings.GOTODOWN_SIZE.width, Settings.GOTODOWN_SIZE.height, Image.SCALE_DEFAULT));
        setPower(10);
    }
}
