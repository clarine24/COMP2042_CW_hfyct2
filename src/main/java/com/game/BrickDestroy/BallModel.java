package com.game.BrickDestroy;

import javafx.scene.shape.Circle;

public abstract class BallModel {
    private Circle ballFace;
    private double centerX;
    private double centerY;

    private double moveX;
    private double moveY;

    public BallModel(Circle ball) {
        centerX = ball.getCenterX();
        centerY = ball.getCenterY();

        ballFace = makeBall(ball);

        moveX = -3;
        moveY = -2;
    }

    protected abstract Circle makeBall(Circle ball);

    public void move() {
        centerX += moveX;
        centerY += moveY;

        ballFace.setCenterX(centerX);
        ballFace.setCenterY(centerY);
    }

    public void reverseX() {
        moveX *= -1;
    }

    public void reverseY() {
        moveY *= -1;
    }

    public Circle getBallFace() {
        return ballFace;
    }

    public double getMoveX(){
        return moveX;
    }

    public double getMoveY(){
        return moveY;
    }
}
