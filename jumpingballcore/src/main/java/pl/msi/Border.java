package pl.msi;

public class Border {
    private int startX;
    private int endX;

    private int startX2;
    private int endX2;

    public static int y;

    public Border(int startX, int endX, int y) {
        this.startX = startX;
        this.endX = endX;
        this.startX2 = endX;
        this.endX2 = endX + (endX - startX);
        Border.y = y;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartX2() {
        return startX2;
    }

    public int getEndX2() {
        return endX2;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void move() {
        startX -= 1;
        endX -= 1;
        startX2 -= 1;
        endX2 -= 1;

        if (endX < 0) {
            startX = endX2;
            endX = startX + (endX2 - startX2);
        } else if (endX2 < 0) {
            startX2 = endX;
            endX2 = startX2 + (endX - startX);
        }
    }

    public void performAction() {
        move();
    }
}
