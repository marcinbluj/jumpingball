package pl.msi.obstacles;

import pl.msi.Border;

import java.util.ArrayList;

public class Obs2 extends Obstacle {
    private final int START_Y = Border.y - 25;
    private final int END_Y = Border.y - 100;

    public Obs2(int x) {
        super(x);
        collisionPoints = new ArrayList<>();
        addCollisionPoint(START_Y, END_Y);
    }
}
