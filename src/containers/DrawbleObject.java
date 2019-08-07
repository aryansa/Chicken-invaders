package containers;

import java.awt.*;

public interface DrawbleObject {
    final Object lock = new Object();
    public Image getImage();
    public double getX();
    public double getY();
    public double getWidth();
    public double getHeight();
    public boolean isDestruction();
    public void move(float time);
}
