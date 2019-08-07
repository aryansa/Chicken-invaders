package gpu;

import containers.DrawbleObject;
import containers.Enemy.chickens.Chicken;
import containers.Enemy.chikenGroups.ChickenGroup;
import containers.Enemy.chikenGroups.Rectangle;
import containers.Game;
import containers.Spaceship;
import containers.bullet.Bullet;
import containers.bullet.Level1;
import root.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

class GamePanel extends MyPanel {
    private long previousTime = System.currentTimeMillis();
    private long currentTime = previousTime;
    private long elapsedTime;
    private Game game;
    private int[] mouseLocation = {100, 100};
    private ArrayList<DrawbleObject> draw = new ArrayList();
    private ArrayList<Bullet> bullets = new ArrayList();
    private ArrayList<Chicken> enemy = new ArrayList();
    private Spaceship spaceship;
    private JLabel temp;
    private int showingTemp;
    private final Object lock = new Object();
    private ChickenGroup chickenGroup;

    GamePanel(Game game) {
        this.game = game;
        this.spaceship = this.game.getSpaceship();
        draw.add(spaceship);
        temp = new JLabel("<html><i style='color:white;font-size:20px'>" + spaceship.getTemp() + "</i></html>");
        temp.setBounds(20, 20, 500, 50);
        add(temp);
        MouseHandler mouseHandler = new MouseHandler();
        addMouseMotionListener(mouseHandler);
        addMouseListener(mouseHandler);
    }

    private void setTemp(int temp) {
        showingTemp = temp;
        if (temp < Settings.MAX_TEMP)
            this.temp.setText("<html><i style='color:white;font-size:20px'>" + String.join("", Collections.nCopies(temp / 5, "*")) + "</i></html>");
        else
            this.temp.setText("<html><i style='color:red;font-size:20px'>" + String.join("", Collections.nCopies(20, "*")) + "</i></html>");
    }

    private class MouseHandler extends MouseAdapter implements MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
            if (game.getSpaceship().canShoot()) {
                synchronized (lock) {
                    Level1 level1 = new Level1(mouseEvent.getX(), mouseEvent.getY());
                    draw.add(level1);
                    bullets.add(level1);
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            super.mouseDragged(mouseEvent);
            mouseLocation[0] = mouseEvent.getX();
            mouseLocation[1] = mouseEvent.getY();
            if (game.getSpaceship().canShoot()) {
                synchronized (lock) {
                    Level1 level1 = new Level1(mouseEvent.getX(), mouseEvent.getY());
                    draw.add(level1);
                    bullets.add(level1);
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            super.mouseMoved(mouseEvent);
            mouseLocation[0] = mouseEvent.getX();
            mouseLocation[1] = mouseEvent.getY();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(
                getBackgroundImage(),
                0,
                0,
                new Color(0, 0, 0, 0), null
        );
        if (showingTemp != spaceship.getTemp()) {
            setTemp(spaceship.getTemp());
        }
        synchronized (lock) {
            for (DrawbleObject drawbleObject : draw) {
                g2.drawImage(
                        drawbleObject.getImage(),
                        (int) (drawbleObject.getX() - (drawbleObject.getWidth() / 2)),
                        (int) (drawbleObject.getY() - (drawbleObject.getHeight() / 2)),
                        new Color(0, 0, 0, 0), null
                );
            }
        }
    }

    @Override
    public void core() {
        Thread core = new Thread(() -> {
            while (true) {
                currentTime = System.currentTimeMillis();
                elapsedTime = (currentTime - previousTime);
                if (mouseLocation != null)
                    handel(elapsedTime / 1000f, mouseLocation);
                this.repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                previousTime = currentTime;
            }
        });
        setCore(core);
        startCore();
    }

    private void handel(float elapsedTime, int[] mouseLocation) {

        spaceship.move(elapsedTime, mouseLocation);
        for (DrawbleObject drawbleObject : draw) {
            drawbleObject.move(elapsedTime);
        }
        if (enemy.isEmpty()) createChickenGroup();
        chickenGroup.move(elapsedTime);
        synchronized (lock) {
            draw.removeIf(DrawbleObject::isDestruction);
        }
        enemy.removeIf(Chicken::isDestruction);
        bullets.removeIf(Bullet::isDestruction);
        for (Bullet bullet : bullets) {
            for (Chicken chicken : enemy) {
                if (
                        Math.abs((bullet.getX() + (bullet.getWidth() / 2)) - (chicken.getX() + (chicken.getWidth() / 2))) < 35 &&
                                Math.abs((bullet.getY() + (bullet.getHeight() / 2)) - (chicken.getY() + (chicken.getHeight() / 2))) < 75
                ) {
                    bullet.setDestruction(true);
                    chicken.increaseHealth(2);
                    if (chicken.isDestruction()) chickenGroup.repaint();
                    System.out.println("shoot");
                    break;
                }
            }
        }
    }

    private void createChickenGroup() {
        draw.removeIf(drawbleObject -> drawbleObject.equals(Chicken.class));
        enemy.clear();
        chickenGroup = new Rectangle(1);
        for (Chicken chicken : chickenGroup.getChickens()
        ) {
            draw.add(chicken);
            enemy.add(chicken);
        }
    }
}
