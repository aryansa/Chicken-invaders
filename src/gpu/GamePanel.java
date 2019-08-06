package gpu;

import containers.DrawbleObject;
import containers.Game;
import containers.Spaceship;
import containers.bullet.Level1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;

class GamePanel extends MyPanel {
    private long previousTime = System.currentTimeMillis();
    private long currentTime = previousTime;
    private long elapsedTime;
    private Game game;
    private int[] mouseLocation = {100, 100};
    ArrayList<DrawbleObject> draw = new ArrayList();
    private Spaceship spaceship;

    GamePanel(Game game) {
        this.game = game;
        this.spaceship = this.game.getSpaceship();
        draw.add(spaceship);
        draw.add(new containers.Enemy.Level1(300,700));
        MouseHandler mouseHandler = new MouseHandler();
        addMouseMotionListener(mouseHandler);
        addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter implements MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);
            if (game.getSpaceship().canShoot()) {
                draw.add(new Level1(mouseEvent.getX(), mouseEvent.getY()));
                game.getSpaceship().increaseTemp();
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            super.mouseReleased(mouseEvent);
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            super.mouseEntered(mouseEvent);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            super.mouseExited(mouseEvent);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
            super.mouseWheelMoved(mouseWheelEvent);
            System.out.println("mam : " + mouseWheelEvent.getX());
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            super.mouseDragged(mouseEvent);
            mouseLocation[0] = mouseEvent.getX();
            mouseLocation[1] = mouseEvent.getY();
            if (game.getSpaceship().canShoot()) {
                draw.add(new Level1(mouseEvent.getX(), mouseEvent.getY()));
                spaceship.increaseTemp();
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
        for (DrawbleObject drawbleObject : draw) {
            g2.drawImage(
                    drawbleObject.getImage(),
                    drawbleObject.getX(),
                    drawbleObject.getY(),
                    new Color(0, 0, 0, 0), null
            );
        }

    }

    //
    @Override
    public void core() {

        Thread core = new Thread(() -> {
            while (true) {
                draw.removeIf(drawbleObject -> drawbleObject.isDestruction());
                currentTime = System.currentTimeMillis();
                elapsedTime = (currentTime - previousTime);
                if (mouseLocation != null)
                    handel(elapsedTime / 1000f, mouseLocation);
                this.repaint();
                try {
                    Thread.sleep(50);
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
    }
}
