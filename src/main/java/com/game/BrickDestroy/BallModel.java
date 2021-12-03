package com.game.BrickDestroy;

import javafx.scene.shape.Circle;

public abstract class BallModel {
    private Circle ballFace;
    private double startX;
    private double startY;

    private double moveX;
    private double moveY;

    public BallModel(Circle ball) {
        startX = ball.getCenterX();
        startY = ball.getCenterY();

        ballFace = makeBall(ball);

        setMoveDirection();
    }

    protected abstract Circle makeBall(Circle ball);

    public void move() {
        double x = ballFace.getCenterX() + moveX;
        double y = ballFace.getCenterY() + moveY;

        ballFace.setCenterX(x);
        ballFace.setCenterY(y);
    }

    public void moveToStart() {
        ballFace.setCenterX(startX);
        ballFace.setCenterY(startY);
    }

    public void setMoveDirection() {
        moveX = -3;
        moveY = -2;
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
