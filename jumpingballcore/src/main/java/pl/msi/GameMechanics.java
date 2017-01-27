package pl.msi;

import pl.msi.obstacles.Obs1;
import pl.msi.obstacles.Obs2;
import pl.msi.obstacles.Obs3;
import pl.msi.obstacles.ObstacleInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class GameMechanics extends JPanel implements Runnable, MouseListener {
    private Thread thread;
    private Border border;
    private Square square;
    private int framesPerSecond;
    private boolean gameStatus = true;

    private java.util.List<ObstacleInterface> obstacles;
    private int currentX = 0;

    private int levelCounter;
    private int lvlModifier = 1;

    public GameMechanics(Border border, Square square, int framesPerSecond) {
        addMouseListener(this);
        this.border = border;
        this.square = square;
        this.framesPerSecond = framesPerSecond;

        obstacles = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            addRandomObstacle();
        }
    }


    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    private void addRandomObstacle() {
        int randomNumber = new Random().nextInt(3);

        switch (randomNumber) {
            case 0:
                currentX += 800;
                obstacles.add(new Obs1(currentX));
                break;
            case 1:
                currentX += 800;
                obstacles.add(new Obs2(currentX));
                break;
            case 2:
                currentX += 800;
                obstacles.add(new Obs3(currentX));
                break;
        }
    }

    boolean isCollide() {
        boolean flag = false;

        if (obstacles.get(0).getX() == square.getXLeft() || obstacles.get(0).getX() == square.getXRight()) {
            for (int i = 0; i < obstacles.get(0).getCollisionPoints().size(); i++) {
                if ((obstacles.get(0).getCollisionPoints().get(i)[0] >= square.getYUp()
                        && obstacles.get(0).getCollisionPoints().get(i)[1] <= square.getYUp())
                        || (obstacles.get(0).getCollisionPoints().get(i)[0] >= square.getYDown()
                        && obstacles.get(0).getCollisionPoints().get(i)[1] <= square.getYDown())
                        ) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        drawThings(graphics2D);
    }

    private void drawThings(Graphics2D graphics2D) {

        graphics2D.drawLine(border.getStartX(), border.getY(), border.getEndX(), border.getY());
        graphics2D.drawLine(border.getStartX2(), border.getY(), border.getEndX2(), border.getY());
        int x = border.getStartX();
        while (x <= border.getEndX()) {
            graphics2D.drawLine(x, border.getY(), x, border.getY() + 100);
            x += 20;
        }
        int x2 = border.getStartX2();
        while (x2 <= border.getEndX2()) {
            graphics2D.drawLine(x2, border.getY(), x2, border.getY() + 100);
            x2 += 20;
        }
        graphics2D.setColor(Color.red);
        graphics2D.fillRect(square.getXLeft(), square.getYUp(), 20, 20);

        graphics2D.setColor(Color.blue);
        for (ObstacleInterface obstacleInterface : obstacles) {
            int tempX = obstacleInterface.getX();
            List<int[]> collisionPoints = obstacleInterface.getCollisionPoints();
            for (int i = 0; i < collisionPoints.size(); i++) {

                graphics2D.drawLine(tempX, collisionPoints.get(i)[0], tempX, collisionPoints.get(i)[1]);
            }
        }

    }

    @Override
    public void run() {
        while (thread != null) {

            square.performAction();
            border.performAction();

            if (square.getYUp() > border.getY() + 30 || isCollide()) {
                thread = null;
                gameStatus = false;
            }

            if (obstacles.get(0).getX() < 0) {
                currentX = obstacles.get(2).getX();
                obstacles.remove(0);
                addRandomObstacle();
                levelCounter++;

                if (levelCounter == lvlModifier) {
                    framesPerSecond += 25;
                    levelCounter = 0;

                    lvlModifier += 1;
                }
            }
            for (ObstacleInterface obstacleInterface : obstacles) {
                obstacleInterface.performAction();
            }

            repaint();

            try {
                Thread.sleep(1000 / framesPerSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (thread == null && gameStatus) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        start();
        if ((square.getYDown() == border.getY()
                && square.getXLeft() >= border.getStartX()
                && square.getXLeft() <= border.getEndX())
                || (square.getYDown() == border.getY()
                && square.getXLeft() >= border.getStartX2()
                && square.getXLeft() <= border.getEndX2())) {
            square.setClicked(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
