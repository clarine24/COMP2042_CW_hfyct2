package com.game.BrickDestroy;

import javafx.scene.shape.Circle;

import java.util.Random;

public abstract class BallModel {
    private Circle ballFace;
    private double startX;
    private double startY;

    private Random rnd;
    private double moveX;
    private double moveY;

    public BallModel(Circle ball) {
        startX = ball.getCenterX();
        startY = ball.getCenterY();

        ballFace = makeBall(ball);

        rnd = new Random();
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
        moveX = -5;
        moveY = -3;

        double x = rnd.nextDouble();
        if(x < 0.5) {
            reverseX();
        }
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
