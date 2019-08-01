package containers.bullet;

import root.Settings;

import java.awt.*;

public class IncreaseTemp extends Bullet {
    public IncreaseTemp(double x, double y) {
        super(x, y, -500);
        String bulletImage = Settings.TEMP_INCREASE;
        setImage(Toolkit.getDefaultToolkit().createImage(bulletImage).getScaledInstance(Settings.GOTODOWN_SIZE.width, Settings.GOTODOWN_SIZE.height, Image.SCALE_DEFAULT));
        setPower(10);
    }
}
