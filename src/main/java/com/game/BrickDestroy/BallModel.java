package com.game.BrickDestroy;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class BallModel {
    private Circle ballFace;
    private final StringProperty name;

    private final double startX;
    private final double startY;

    private Random rnd;
    private final double DEFAULT_MOVE = -3;
    private double moveX;
    private double moveY;

    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;

    private DoubleProperty speed;
    private final double DEFAULT_SPEED = 1;

    public BallModel(String name, Circle ball, Rectangle wall) {
        this.name = new SimpleStringProperty(name);

        startX = ball.getCenterX();
        startY = ball.getCenterY();

        ballFace = makeBall(ball);

        minX = wall.getX() + ball.getRadius();
        maxX = wall.getX() + wall.getWidth() - ball.getRadius();
        minY = wall.getY() + ball.getRadius();
        maxY = wall.getY() + wall.getHeight() - ball.getRadius();

        rnd = new Random();
        speed = new SimpleDoubleProperty(DEFAULT_SPEED);
        setMoveDirection();
    }

    private Circle makeBall(Circle ball) {
        return new Circle(ball.getCenterX(), ball.getCenterY(), ball.getRadius());
    }

    public void move() {
        double x = ballFace.getCenterX() + getMove(moveX);
        double y = ballFace.getCenterY() + getMove(moveY);

        //Allow ball to move only within wall boundary
        if(y < minY) {
            y = minY;
        }
        else if(y < maxY) {
            if (x < minX) {
                x = minX;
            } else if (x > maxX) {
                x = maxX;
            }
        }

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

    public StringProperty getName() {
        return name;
    }
}
