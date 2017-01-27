package pl.msi.obstacles;

import java.util.ArrayList;
import java.util.List;

abstract class Obstacle implements ObstacleInterface {
    List<int[]> collisionPoints;
    private int x;

    Obstacle(int x) {
        collisionPoints = new ArrayList<>();
        this.x = x;
    }

    @Override
    public List<int[]> getCollisionPoints() {
        return collisionPoints;
    }

    @Override
    public void addCollisionPoint(int startY, int endY) {
        collisionPoints.add(new int[]{startY, endY});
    }

    @Override
    public void performAction() {
        x -= 1;
    }

    @Override
    public int getX() {
        return x;
    }
}
