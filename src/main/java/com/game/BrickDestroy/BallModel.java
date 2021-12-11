package com.game.BrickDestroy;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * The BallModel class is the ball model.
 * @author Clarine Tan Kaili (20194533)
 * @version 2.0
 * @since 11/12/21
 */
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

    /**
     * Creates a new instance of BallModel.
     * @param name the name of the ball
     * @param ball the ball to be created
     * @param wall the wall in which the ball is in
     */
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

    /**
     * Creates the ball shape.
     * @param ball the ball to be created
     * @return the created ball
     */
    private Circle makeBall(Circle ball) {
        return new Circle(ball.getCenterX(), ball.getCenterY(), ball.getRadius());
    }

    /**
     * Moves the ball.
     */
    public void move() {
        double x = ballFace.getCenterX() + getMove(moveX);
        double y = ballFace.getCenterY() + getMove(moveY);

        //Allow ball to move only within wall boundary
        if(y < minY) {
            y = minY;
        }
        if(y < maxY) {
            if (x < minX) {
                x = minX;
            } else if (x > maxX) {
                x = maxX;
            }
        }

        ballFace.setCenterX(x);
        ballFace.setCenterY(y);
    }

    /**
     * Moves the ball to its starting position.
     */
    public void moveToStart() {
        ballFace.setCenterX(startX);
        ballFace.setCenterY(startY);
    }

    /**
     * Sets the direction the ball moves.
     */
    public void setMoveDirection() {
        moveX = moveY = DEFAULT_MOVE;

        double x = rnd.nextDouble();
        if(x < 0.5) {
            reverseX();
        }
    }

    /**
     * Reverses the direction the ball moves horizontally.
     */
    public void reverseX() {
        moveX *= -1;
    }

    /**
     * Reverses the direction the ball moves vertically.
     */
    public void reverseY() {
        moveY *= -1;
    }

    /**
     * Gets the ball shape.
     * @return the created ball
     */
    public Circle getBallFace() {
        return ballFace;
    }

    /**
     * Gets the total move amount the ball.
     * @param move the move amount of the ball
     * @return the move amount of the ball
     */
    private double getMove(double move) {
        if(move < 0) {
            return move - (1 * speed.get());
        }
        return move + 1 * speed.get();
    }

    /**
     * Gets the double property of ball's speed.
     * @return the double property of speed
     */
    public DoubleProperty getSpeed() {
        return speed;
    }

    /**
     * Gets the string property of the ball's name.
     * @return the string property of name
     */
    public StringProperty getName() {
        return name;
    }
}
