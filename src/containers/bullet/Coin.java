package containers.bullet;

import root.Settings;

import java.awt.*;

public class Coin extends Bullet {
    public Coin(double x, double y) {
        super(x, y, -500);
        String bulletImage = Settings.COIN_IMG;
        setImage(Toolkit.getDefaultToolkit().createImage(bulletImage).getScaledInstance(Settings.GOTODOWN_SIZE.width, Settings.GOTODOWN_SIZE.height, Image.SCALE_DEFAULT));
        setPower(10);
    }
}
