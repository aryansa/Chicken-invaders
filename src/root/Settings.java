package root;

import java.awt.*;

public class Settings {
    // IMAGES
    public static final String BACKGROUND_IMAGE = "/home/aryanpc/IdeaProjects/Chiken-invaders/img/background.jpg";
    public static final String USER_PANEL_JPG = "/home/aryanpc/IdeaProjects/Chiken-invaders/img/userpanel.jpg";
    public static final String SPACESHIP_JPG = "/home/aryanpc/IdeaProjects/Chiken-invaders/img/spaceship.gif";
    public static final String BULLET_PNG = "/home/aryanpc/IdeaProjects/Chiken-invaders/img/bullet.png";
    public static final String CHICKEN_PNG = "/home/aryanpc/IdeaProjects/Chiken-invaders/img/chicken.png";

    //SIZES
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension SPACESHIP_SIZE = new Dimension(100, 200);
    public static final Dimension BULLET_SIZE = new Dimension(20, 40);
    public static final Dimension CHICKEN_SIZE = new Dimension(50, 100);

    // idea : implement by Yaml file
    // DATA
    public static final String[] DATABASE = {"appr", "root", "areman2251378"};

    // GAME SETTINGS
    public static final int MAX_TEMP = 100;


}
