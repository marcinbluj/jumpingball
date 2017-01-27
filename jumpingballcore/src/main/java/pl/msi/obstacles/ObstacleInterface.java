package pl.msi.obstacles;

import java.util.List;

public interface ObstacleInterface {

    List<int[]> getCollisionPoints();

    void addCollisionPoint(int startY, int endY);

    void performAction();

    int getX();
}
