package gpu;

import containers.DrawbleObject;
import containers.Enemy.chickens.Chicken;
import containers.Enemy.chikenGroups.ChickenGroup;
import containers.Enemy.chikenGroups.Rectangle;
import containers.Game;
import containers.Spaceship;
import containers.bullet.*;
import root.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GamePanel extends MyPanel {
    private long previousTime = System.currentTimeMillis();
    private long currentTime = previousTime;
    private long elapsedTime;
    private Game game;
    private int[] mouseLocation = {100, 100};
    private final ArrayList<DrawbleObject> draw = new ArrayList();
    private final ArrayList<Bullet> bullets = new ArrayList();
    private final ArrayList<Chicken> enemy = new ArrayList();
    private final ArrayList<Bullet> eggs = new ArrayList<>();
    private final ArrayList<Coin> coins = new ArrayList<>();
    private final Spaceship spaceship;
    private JLabel temp;
    private int showingTemp;
    private static final Object lock = new Object();
    private ChickenGroup chickenGroup;
    private int i = 5;


    GamePanel(Game game) {
        this.game = game;
        this.spaceship = this.game.getSpaceship();
        synchronized (draw) {
            draw.add(spaceship);
        }
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
                Level1 level1 = new Level1(mouseEvent.getX(), mouseEvent.getY());

                synchronized (draw) {
                    draw.add(level1);
                }
                synchronized (bullets) {
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
                Level1 level1 = new Level1(mouseEvent.getX(), mouseEvent.getY());
                synchronized (draw) {
                    draw.add(level1);
                }
                synchronized (bullets) {
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

        synchronized (draw) {
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
        new Thread(() -> {
            while (true) {
                if (!enemy.isEmpty())
                    for (Chicken chicken : enemy) {
                        if (chicken.soot()) {
                            Egg egg = new Egg(chicken.getX() + (Settings.CHICKEN_SIZE.width / 2), chicken.getY() + Settings.CHICKEN_SIZE.height);
                            synchronized (eggs) {
                                eggs.add(egg);
                            }
                            synchronized (draw) {
                                draw.add(egg);
                            }
                        }
                    }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.increaseTime();
            }
        }).start();
    }

    private void handel(float elapsedTime, int[] mouseLocation) {
        synchronized (spaceship) {
            spaceship.move(elapsedTime, mouseLocation);
        }
        synchronized (draw) {
            draw.removeIf(DrawbleObject::isDestruction);
            for (DrawbleObject drawbleObject : draw) {
                drawbleObject.move(elapsedTime);
            }
        }
        if (enemy.isEmpty()) createChickenGroup();
        chickenGroup.move(elapsedTime);
        check();
    }

    private void check() {

        if (game.getSpendTime() > 5) {
            synchronized (spaceship) {
                if (!spaceship.isDestruction())
                    synchronized (enemy) {
                        enemy.removeIf(Chicken::isDestruction);
                        for (Chicken chicken : enemy) {
                            if (
                                    Math.abs((spaceship.getX() + (spaceship.getWidth() / 2)) - (chicken.getX() + (chicken.getWidth() / 2))) < 50 &&
                                            Math.abs((spaceship.getY() + (spaceship.getHeight() / 2)) - (chicken.getY() + (chicken.getHeight() / 2))) < 100
                            ) {
                                spaceship.setDestruction(true);
                                chicken.setDestruction(true);
                                break;
                            }
                        }
                    }
                synchronized (eggs) {
                    eggs.removeIf(Bullet::isDestruction);
                    if (!eggs.isEmpty() && !spaceship.isDestruction())
                        for (Bullet bullet : eggs) {
                            if (bullet.getX() - spaceship.getX() <= 0 &&
                                    Math.abs((bullet.getY() + (bullet.getHeight() / 2)) - (spaceship.getY() + (spaceship.getHeight() / 2))) < Settings.SPACESHIP_SIZE.height / 2) {
                                if (bullet instanceof Egg)
                                    spaceship.increaseHealth(bullet.getPower());
                                else if (bullet instanceof IncreaseTemp)
                                    spaceship.setMaxTemp(5);
                                else if (bullet instanceof Coin) {
                                    spaceship.increaseCoins(1);
                                }
                                bullet.setDestruction(true);
                            }
                        }
                }
            }
        }
        synchronized (bullets) {
            bullets.removeIf(Bullet::isDestruction);
            if (!bullets.isEmpty()) {
                for (Bullet bullet : bullets) {
                    if (!coins.isEmpty())
                        synchronized (coins) {
                            {
                                for (Bullet coin : coins) {
                                    if (
                                            bullet.getX() > coin.getX() &&
                                                    Math.abs((bullet.getY() + (bullet.getHeight() / 2)) - (coin.getY() + (coin.getHeight() / 2))) < 100
                                    ) {
                                        System.out.println("shoot to coin");
                                        bullet.setDestruction(true);
                                        coin.setDestruction(true);
                                    }
                                }
                            }
                        }
                    synchronized (enemy) {
                        for (Chicken chicken : enemy) {
                            if (
                                    Math.abs((bullet.getX() + (bullet.getWidth() / 2)) - (chicken.getX() + (chicken.getWidth() / 2))) < 50 &&
                                            Math.abs((bullet.getY() + (bullet.getHeight() / 2)) - (chicken.getY() + (chicken.getHeight() / 2))) < 100
                            ) {
                                bullet.setDestruction(true);
                                chicken.increaseHealth(2);
                                if (chicken.isDestruction()) {
                                    Bullet e = chicken.gift();
                                    if (e != null) {
                                        synchronized (eggs) {
                                            eggs.add(e);
                                        }
                                        synchronized (draw) {
                                            draw.add(e);
                                        }
                                    }
                                }
                            }
                        }
                    }
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
