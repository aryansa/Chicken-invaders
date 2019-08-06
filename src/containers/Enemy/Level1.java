package containers.Enemy;

import root.Settings;

import java.awt.*;

public class Level1 extends Chicken {
    public Level1(int x, int y) {
        super(x, y, 5);
        String bulletImage = Settings.CHICKEN_PNG;
        String imageAddress = bulletImage.substring(0, bulletImage.length() - 4) + "1" + bulletImage.substring(bulletImage.length() - 4);
        setImage(Toolkit.getDefaultToolkit().createImage(imageAddress).getScaledInstance(Settings.CHICKEN_SIZE.width, Settings.CHICKEN_SIZE.height, Image.SCALE_DEFAULT));
        setPower(10);
        move();
    }

}
