package root;

import java.awt.*;

public class Settings {
    // IMAGES
    public static final String BACKGROUND_IMAGE = "img/background.jpg";
    public static final String USER_PANEL_JPG = "img/userpanel.jpg";
    public static final String SPACESHIP_JPG = "img/spaceship.gif";
    public static final String BULLET_PNG = "img/bullet.png";
    public static final String CHICKEN_PNG = "img/chicken.png";
    public static final String CHICKEN_BULLET = "img/chickenBullet.png";
    public static final String TEMP_INCREASE = "img/tempicon.jpg";
    public static final String COIN_IMG = "img/coin.png";

    //SIZES
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension SPACESHIP_SIZE = new Dimension(100, 200);
    public static final Dimension BULLET_SIZE = new Dimension(20, 40);
    public static final Dimension CHICKEN_SIZE = new Dimension(50, 100);
    public static final Dimension GOTODOWN_SIZE = new Dimension(25, 50);
    // idea : implement by Yaml file
    // DATA
    public static final String[] DATABASE = {"appr", "root", "areman2251378"};

    // GAME SETTINGS
    public static final int MAX_TEMP = 100;


}
