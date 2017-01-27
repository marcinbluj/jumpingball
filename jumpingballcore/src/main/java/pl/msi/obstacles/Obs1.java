package pl.msi.obstacles;

import pl.msi.Border;

import java.util.ArrayList;

public class Obs1 extends Obstacle {
    private final int START_Y = Border.y;
    private final int END_Y = Border.y - 50;

    public Obs1(int x) {
        super(x);
        collisionPoints = new ArrayList<>();
        addCollisionPoint(START_Y, END_Y);
    }
}
