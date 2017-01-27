package pl.msi;

public class Square {
    private final int SIZE = 20;

    private int xLeft;
    private int yUp;

    private int xRight;
    private int yDown;

    private int jumpHeight;
    private Border border;
    private boolean jumpState = false;
    private boolean isClicked = false;

    private int counter;

    public Square(int xLeft, int yUp, int jumpHeight, Border border) {
        this.xLeft = xLeft;
        this.yUp = yUp;
        xRight = this.xLeft +SIZE;
        yDown = this.yUp +SIZE;
        this.jumpHeight = jumpHeight;
        this.border = border;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public int getXLeft() {
        return xLeft;
    }

    public int getYUp() {
        return yUp;
    }

    public int getXRight() {
        return xRight;
    }

    public int getYDown() {
        return yDown;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public void moveHigher() {
        yUp -= 1;
        yDown -= 1;
    }

    public void moveLower() {
        yUp += 1;
        yDown += 1;
    }

    public boolean isInJumpState() {
        return jumpState;
    }

    public void setJumpState(boolean jumpState) {
        this.jumpState = jumpState;
    }

    public void resetCounter() {
        counter = 0;
    }

    public void performAction() {
        if (isClicked) {
            resetCounter();
            jumpState = true;
            isClicked = false;
        }
        if (jumpState && counter < jumpHeight) {
            counter++;
            if (counter < jumpHeight) {
                moveHigher();
            } else {
                resetCounter();
                jumpState = false;
            }

        } else if ((!jumpState
                && yDown < border.getY()
                && xLeft >= border.getStartX()
                && xLeft <= border.getEndX())
                ||(!jumpState
                && yDown < border.getY()
                && xLeft >= border.getStartX2()
                && xLeft <= border.getEndX2())) {
            moveLower();
        } else if ((!jumpState
                && xLeft >= border.getEndX())
                &&(!jumpState
                && xLeft >= border.getEndX2())) {
            moveLower();
        }
    }
}

