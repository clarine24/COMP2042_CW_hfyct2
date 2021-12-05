package com.game.BrickDestroy;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

import java.util.Random;

public abstract class BallModel {
    private Circle ballFace;
    private final double startX;
    private final double startY;

    private Random rnd;
    private double moveX;
    private double moveY;
    private final double DEFAULT_MOVE = -3;

    private DoubleProperty speed;
    private final double DEFAULT_SPEED = 1;

    public BallModel(Circle ball) {
        startX = ball.getCenterX();
        startY = ball.getCenterY();

        ballFace = makeBall(ball);

        rnd = new Random();
        speed = new SimpleDoubleProperty(DEFAULT_SPEED);
        setMoveDirection();
    }

    protected abstract Circle makeBall(Circle ball);

    public void move() {
        double x = ballFace.getCenterX() + getMove(moveX);
        double y = ballFace.getCenterY() + getMove(moveY);

        ballFace.setCenterX(x);
        ballFace.setCenterY(y);
    }

    public void moveToStart() {
        ballFace.setCenterX(startX);
        ballFace.setCenterY(startY);
    }

    public void setMoveDirection() {
        moveX = moveY = DEFAULT_MOVE;

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

    private double getMove(double move) {
        if(move < 0) {
            return move - (1 * speed.get());
        }
        return move + 1 * speed.get();
    }

    public DoubleProperty getSpeed() {
        return speed;
    }
}
